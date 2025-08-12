package tamagotchi;

import javafx.application.Application;
import javafx.stage.Stage;
import tamagotchi.controller.PetController;
import tamagotchi.view.View;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        PetController petController = new PetController();


        View telaPrincipal = new View(petController);
        telaPrincipal.mostrar(primaryStage);

    }

}