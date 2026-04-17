package org.ieselcaminas.Plataformas_de_Musica.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ieselcaminas.Plataformas_de_Musica.Entity.Music;
import org.springframework.stereotype.Component;

@Component
public class AddMusicController {

    @FXML private TextField txtName, txtArtist, txtDuration;
    private Music music;
    private boolean saveClicked = false;

    public Music getMusic() { return music; }
    public boolean isSaveClicked() { return saveClicked; }

    @FXML
    private void save() {
        if (txtName.getText().isEmpty() || txtArtist.getText().isEmpty()) return;

        try {
            music = new Music();
            music.setTrackName(txtName.getText());
            music.setArtist(txtArtist.getText());
            music.setDurationMs(Long.parseLong(txtDuration.getText()));

            saveClicked = true;
            closeWindow();
        } catch (NumberFormatException e) {
            System.out.println("Error: Duración inválida");
        }
    }

    @FXML private void cancel() { closeWindow(); }

    private void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}