package co.edu.uniquindio.projectfinal.proyectofinalversionfinal;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 450);
        stage.setTitle("Inicio de sesion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}