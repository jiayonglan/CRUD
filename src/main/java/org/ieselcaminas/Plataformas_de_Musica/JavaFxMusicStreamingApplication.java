package org.ieselcaminas.Plataformas_de_Musica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxMusicStreamingApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        // Inicializa Spring Boot
        springContext = SpringApplication.run(JavaFxMusicStreamingApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/music-view.fxml"));

        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        primaryStage.setTitle("Music Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        Application.launch(JavaFxMusicStreamingApplication.class, args);
    }
}