module com.example.jogotamagotchi {
    requires javafx.controls;
    requires javafx.fxml;

    exports tamagotchi.model;
    exports tamagotchi.controller;

    opens tamagotchi.controller to javafx.fxml;
    exports tamagotchi;
    opens tamagotchi to javafx.fxml;
}