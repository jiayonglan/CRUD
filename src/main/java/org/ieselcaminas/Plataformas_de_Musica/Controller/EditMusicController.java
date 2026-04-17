package org.ieselcaminas.Plataformas_de_Musica.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ieselcaminas.Plataformas_de_Musica.Entity.Music;
import org.springframework.stereotype.Component;

@Component
public class EditMusicController {

    @FXML private TextField txtName, txtArtist, txtDuration;
    private Music music;
    private boolean saveClicked = false;

    public void setMusic(Music music) {
        this.music = music;
        txtName.setText(music.getTrackName());
        txtArtist.setText(music.getArtist());
        txtDuration.setText(String.valueOf(music.getDurationMs()));
    }

    public boolean isSaveClicked() { return saveClicked; }

    @FXML
    private void save() {
        try {
            music.setTrackName(txtName.getText());
            music.setArtist(txtArtist.getText());
            music.setDurationMs(Long.parseLong(txtDuration.getText()));
            saveClicked = true;
            ((Stage) txtName.getScene().getWindow()).close();
        } catch (NumberFormatException e) {
            // Aquí podrías poner una alerta si no meten números
        }
    }

    @FXML private void cancel() {
        ((Stage) txtName.getScene().getWindow()).close();
    }
}