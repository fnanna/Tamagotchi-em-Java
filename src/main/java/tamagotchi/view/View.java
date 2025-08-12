package tamagotchi.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tamagotchi.controller.PetController;
import tamagotchi.model.Pet;

import java.util.Objects;

public class View {
    private PetController petController;
    private VBox root;
    private Label statusLabel;
    private ImageView petImageView;
    private Font fontPadrao;
    private Font fontTitulo;
    private Font fontMenu;

    public void TelaPrincipal(PetController petController) {
        this.petController = petController;
    }

    public View(PetController petController) {
        this.petController = petController;

        // Carrega a fonte VT323
        fontPadrao = Font.loadFont(getClass().getResourceAsStream("/fonts/VT323.ttf"), 18);
        fontTitulo = Font.loadFont(getClass().getResourceAsStream("/fonts/VT323.ttf"), 32);
        fontMenu = Font.loadFont(getClass().getResourceAsStream("/fonts/VT323.ttf"), 14);
    }

    public void mostrar(Stage stage) {
        stage.setTitle("Tamagotchi");

        criarLayout();
        atualizarTela();

        Scene scene = new Scene(root, 600, 600);
        Image icone = new Image(getClass().getResourceAsStream("/imagens/icone.png"));

        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.getIcons().add(icone);
        stage.show();
    }

    private void criarLayout() {
        root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #fffaf0;");

        // Cabeçalho
        Label tituloLabel = new Label("Tamagotchi");
        tituloLabel.setFont(fontTitulo);
        tituloLabel.setStyle("-fx-text-fill: #C0C78C;");

        // Área do pet
        HBox imageContainer = new HBox();
        imageContainer.setAlignment(Pos.CENTER); // Centraliza horizontalmente
        petImageView = new ImageView();
        petImageView.setFitHeight(200);
        petImageView.setFitWidth(200);
        petImageView.setPreserveRatio(true);
        imageContainer.getChildren().add(petImageView);

        // Status
        statusLabel = new Label();
        statusLabel.setFont(fontPadrao);
        statusLabel.setStyle("-fx-text-fill: #333;");

        // Controles
        HBox botoesBox = new HBox(15);
        botoesBox.setAlignment(Pos.CENTER);
        botoesBox.setPadding(new Insets(10));

        Button alimentarBtn = criarBotao("Alimentar", "#C0C78C");
        alimentarBtn.setOnAction(e -> {
            if (petController.getPetAtual() != null) {
                petController.getPetAtual().alimentar();
                atualizarTela();
            }
        });

        Button brincarBtn = criarBotao("Brincar", "#C0C78C");
        brincarBtn.setOnAction(e -> {
            if (petController.getPetAtual() != null) {
                petController.getPetAtual().brincar();
                atualizarTela();
            }
        });

        Button dormirBtn = criarBotao("Dormir", "#C0C78C");
        dormirBtn.setOnAction(e -> {
            if (petController.getPetAtual() != null) {
                petController.getPetAtual().dormir();
                atualizarTela();
            }
        });

        Button cuidarBtn = criarBotao("Cuidar", "#C0C78C");
        cuidarBtn.setOnAction(e -> {
            if (petController.getPetAtual() != null) {
                petController.getPetAtual().cuidar();
                atualizarTela();
            }
        });

        botoesBox.getChildren().addAll(alimentarBtn, brincarBtn, dormirBtn, cuidarBtn);

        // Menu
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #C0C78C;-fx-font-family: '" + fontMenu.getFamily() + "'; -fx-font-size: " + fontMenu.getSize() + "px;");

        // Menu principal
        Menu menu = new Menu("Menu");
        menu.setStyle("-fx-text-fill: white;-fx-font-family: '" + fontMenu.getFamily() + "';");

        MenuItem novoPetItem = new MenuItem("Adotar Novo Pet");
        novoPetItem.setStyle("-fx-font-family: '" + fontMenu.getFamily() + "';");
        novoPetItem.setOnAction(e -> mostrarTelaSelecaoPet());

        MenuItem removerPetItem = new MenuItem("Remover Pet");
        removerPetItem.setStyle("-fx-font-family: '" + fontMenu.getFamily() + "';");
        removerPetItem.setOnAction(e -> {
            if (petController.getPetAtual() != null) {
                petController.removerPet(petController.getPetAtual());
                atualizarTela();
            }
        });

        menu.getItems().addAll(novoPetItem, removerPetItem);

        // Menu de Pets
        Menu petsMenu = new Menu("Pets");
        petsMenu.setStyle("-fx-text-fill: white;-fx-font-family: '" + fontMenu.getFamily() + "';");
        atualizarMenuPets(petsMenu);

        menuBar.getMenus().addAll(menu, petsMenu);

        root.getChildren().addAll(menuBar, tituloLabel, imageContainer, statusLabel, botoesBox);
    }

    private void atualizarMenuPets(Menu petsMenu) {
        petsMenu.getItems().clear();

        for (Pet pet : petController.getPets()) {
            MenuItem petItem = new MenuItem(pet.getNome() + " (" + pet.getClass().getSimpleName() + ")");
            petItem.setStyle("-fx-font-family: '" + fontMenu.getFamily() + "';");
            petItem.setOnAction(e -> {
                petController.trocarPet(petController.getPets().indexOf(pet));
                atualizarTela();
            });
            petsMenu.getItems().add(petItem);
        }

        if (petsMenu.getItems().isEmpty()) {
            MenuItem emptyItem = new MenuItem("Nenhum pet adotado");
            emptyItem.setStyle("-fx-font-family: '" + fontMenu.getFamily() + "';");
            emptyItem.setDisable(true);
            petsMenu.getItems().add(emptyItem);
        }
    }

    private Button criarBotao(String texto, String cor) {
        Button btn = new Button(texto);
        btn.setFont(fontPadrao);
        btn.setStyle("-fx-background-color: " + cor + "; -fx-text-fill: white;");
        btn.setMinWidth(100);

        try {
            ImageView icon = new ImageView(new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream(
                            "/imagens/" + texto + ".png"))));
            icon.setFitHeight(54);
            icon.setFitWidth(54);
            btn.setGraphic(icon);
            btn.setContentDisplay(ContentDisplay.TOP); // Ícone acima do texto
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone " + texto + ": " + e.getMessage());
        }

        return btn;
    }

    public void atualizarTela() {
        Pet petAtual = petController.getPetAtual();

        if (petAtual == null) {
            petImageView.setImage(null);
            statusLabel.setText("Nenhum pet selecionado. Adote um pet no menu 'Menu'.");
            return;
        }

        // Atualiza imagem do pet
        String imagemPath = "/imagens/" + petAtual.getClass().getSimpleName().toLowerCase() + ".png";
        try {
            petImageView.setImage(new Image(getClass().getResourceAsStream(imagemPath)));
        } catch (Exception e) {
            petImageView.setImage(null);
        }

        // Atualiza status
        statusLabel.setText(petAtual.mostrarStatus());

        // Atualiza o menu de Pets
        MenuBar menuBar = (MenuBar) root.getChildren().get(0);
        Menu petsMenu = menuBar.getMenus().get(1); // O menu Pets é o segundo item
        atualizarMenuPets(petsMenu);
    }

    private void mostrarTelaSelecaoPet() {
        Stage stage = new Stage();
        stage.setTitle("Adotar Novo Pet");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label instrucaoLabel = new Label("Escolha seu pet:");
        instrucaoLabel.setFont(fontPadrao);
        instrucaoLabel.setStyle("-fx-font-size: 24px;");

        ToggleGroup grupo = new ToggleGroup();

        RadioButton gatoBtn = criarRadioButton("Gato", "gato");
        gatoBtn.setToggleGroup(grupo);

        RadioButton cachorroBtn = criarRadioButton("Cachorro", "cachorro");
        cachorroBtn.setToggleGroup(grupo);

        RadioButton coelhoBtn = criarRadioButton("Coelho", "coelho");
        coelhoBtn.setToggleGroup(grupo);

        TextField nomeField = new TextField();
        nomeField.setFont(fontPadrao);
        nomeField.setPromptText("Nome do pet");
        nomeField.setMaxWidth(200);

        Button adotarBtn = getButton(grupo, nomeField, stage);

        layout.getChildren().addAll(instrucaoLabel, gatoBtn, cachorroBtn, coelhoBtn, nomeField, adotarBtn);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private RadioButton criarRadioButton(String texto, String userData) {
        RadioButton radioButton = new RadioButton(texto);
        radioButton.setFont(fontPadrao);
        radioButton.setUserData(userData);
        return radioButton;
    }

    private Button getButton(ToggleGroup grupo, TextField nomeField, Stage stage) {
        Button adotarBtn = new Button("Adotar");
        adotarBtn.setFont(fontPadrao);
        adotarBtn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white;");
        adotarBtn.setOnAction(e -> {
            if (grupo.getSelectedToggle() != null && !nomeField.getText().isEmpty()) {
                String tipo = grupo.getSelectedToggle().getUserData().toString();
                String nome = nomeField.getText();

                petController.adotarPet(tipo, nome);
                atualizarTela();
                stage.close();
            }
        });
        return adotarBtn;
    }
}