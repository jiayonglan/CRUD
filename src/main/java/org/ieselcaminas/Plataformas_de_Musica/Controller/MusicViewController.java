package org.ieselcaminas.Plataformas_de_Musica.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.ieselcaminas.Plataformas_de_Musica.Entity.Music;
import org.ieselcaminas.Plataformas_de_Musica.Services.MusicApiService;
import org.ieselcaminas.Plataformas_de_Musica.Services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class MusicViewController {

    private final MusicService musicService;

    @Autowired
    private ConfigurableApplicationContext context;

    public MusicViewController(MusicService musicService) {
        this.musicService = musicService;
    }

    @FXML private TableView<Music> musicTable;

    @FXML
    public void initialize() {
        loadMusic();
    }

    @FXML
    public void loadMusic() {
        List<Music> list = musicService.findAll();
        musicTable.getItems().setAll(list);
    }

    // --- OPERACIÓN AÑADIR ---
    @FXML
    public void addMusic() {
        // Usamos el nombre exacto que tienes: add_music_view.fxml
        FXMLLoader loader = loadFXML("/add_music_view.fxml");
        if (loader == null) return;

        try {
            Parent root = loader.load();
            AddMusicController addController = loader.getController();

            showStage(root, "Añadir Nueva Canción");

            if (addController.isSaveClicked()) {
                musicService.save(addController.getMusic());
                loadMusic();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error de carga", "No se pudo procesar el archivo FXML de Añadir.");
        }
    }

    // --- OPERACIÓN EDITAR ---
    @FXML
    public void editMusic() {
        Music selected = musicTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Sin selección", "Por favor, selecciona una canción para editar.");
            return;
        }

        // Usamos el nombre exacto que tienes: edit_music_view.fxml
        FXMLLoader loader = loadFXML("/edit_music_view.fxml");
        if (loader == null) return;

        try {
            Parent root = loader.load();
            EditMusicController editController = loader.getController();
            editController.setMusic(selected);

            showStage(root, "Editar Canción");

            if (editController.isSaveClicked()) {
                musicService.save(selected);
                loadMusic();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error de carga", "No se pudo procesar el archivo FXML de Edición.");
        }
    }

    // --- MÉTODOS DE AYUDA (EL ARREGLO) ---

    private FXMLLoader loadFXML(String fxmlPath) {
        java.net.URL location = getClass().getResource(fxmlPath);
        if (location == null) {
            // Imprime en consola para que veas dónde busca
            System.err.println("Intentando cargar: " + fxmlPath + " -> NO ENCONTRADO");
            showError("Archivo no encontrado",
                    "No se encuentra: " + fxmlPath + "\n\n" +
                            "Revisa que el archivo esté en src/main/resources/ con ese nombre exacto.");
            return null;
        }
        FXMLLoader loader = new FXMLLoader(location);
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    private void showStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    // --- RESTO DE MÉTODOS ---

    @FXML
    public void deleteMusic() {
        Music selected = musicTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            musicService.deleteById(selected.getId());
            loadMusic();
        } else {
            showError("Aviso", "Selecciona una canción para borrar.");
        }
    }

    @FXML
    public void showOnlyLongSongs() {
        List<Music> allMusic = musicService.findAll();
        List<Music> filteredList = allMusic.stream()
                .filter(m -> m.getDurationMs() > 180000)
                .sorted()
                .toList();
        musicTable.getItems().setAll(filteredList);
    }

    @FXML
    public void exportToCSV() {
        File file = new File("playlist_report.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("ID,Track,Artist,Duration");
            for (Music m : musicTable.getItems()) {
                writer.println(m.getId() + "," + m.getTrackName() + "," + m.getArtist() + "," + m.getDurationMs());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Exportado a: " + file.getAbsolutePath());
            alert.show();
        } catch (Exception e) {
            showError("Error", "No se pudo exportar.");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Autowired
    private MusicApiService musicApiService;

    @FXML
    public void checkArtistInfo() {
        Music selected = musicTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                // Ahora llamamos pasando Artista Y Canción
                String info = musicApiService.getTrackInfo(selected.getArtist(), selected.getTrackName());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Detalles desde API iTunes");
                alert.setHeaderText(selected.getTrackName());
                alert.setContentText(info);
                alert.showAndWait();

            } catch (Exception e) {
                showError("Error", "No se pudo conectar con la API.");
            }
        }
    }
}