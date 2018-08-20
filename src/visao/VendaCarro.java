package visao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.CountryData;
import utilitario.ControleArquivo;

public class VendaCarro extends Application {

    private Alert alert;
    private InnerShadow sair;
    private InnerShadow vender;
    private TextField textFieldDataCompra;
    private TextField textFieldVeiculo;
    private Label labelDataCompra;
    private Label labelVeiculo;
    private Button btnVender;
    private Button btnSair;
    private AnchorPane anchorPane;
    private TableView<CountryData> tableView;
    private static Stage stage;

    ControleArquivo arquivo = new ControleArquivo();

    public void start(Stage stage) {
        if (arquivo.trasContrato().isEmpty() && !arquivo.trasCarro().isEmpty()) {
            VendaCarro.stage = stage;
            carregarTabela();
            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                selecionarTableCarroVenda(newValue);
                btnVender.setDisable(false);
            });
            anchorPane = new AnchorPane();
            anchorPane.setPrefSize(500, 400);
            anchorPane.setStyle("-fx-background-color: WHITE");
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("/utilitario/Botao.css");
            inicializarComponentes();
            definirEventos();
            stage.setScene(scene);
            stage.setTitle("Venda de veiculo");
            stage.show();
            layout();
        } else if (arquivo.trasCarro().isEmpty()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Venda");
            alert.setContentText("Este carro encontra-se vendido!");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Venda");
            alert.setContentText("Este carro encontra-se alugado!");
            alert.show();
        }

    }

    private void carregarTabela() {
        Collection<CountryData> list;
        try {
            list = Files.readAllLines(new File("c:/Temp/ModeloCarro" + ".csv").toPath()).stream()
                    .map(line -> {
                        String[] details = line.split(",");
                        CountryData cd = new CountryData();

                        cd.setCountry(details[0]);

                        return cd;

                    }).collect(Collectors.toList());
            ObservableList<CountryData> details = FXCollections.observableArrayList(list);

            tableView = new TableView<>();
            TableColumn<CountryData, String> col1 = new TableColumn<>();

            tableView.getColumns().addAll(col1);

            col1.setCellValueFactory(data -> data.getValue().countryProperty());

            tableView.setItems(details);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void inicializarComponentes() {

        textFieldVeiculo = new TextField();
        textFieldDataCompra = new TextField();
        labelVeiculo = new Label("MODELO:");
        labelDataCompra = new Label("COMPRA:");

        btnVender = new Button("Vender", new ImageView(new Image("/imagens/icone-locacao.png")));
        btnVender.getStyleClass().add("btnRegistro");
        vender = new InnerShadow(5, Color.GREEN);
        btnVender.setEffect(vender);
        btnVender.setDisable(true);

        btnSair = new Button("Sair", new ImageView(new Image("/imagens/Cancelar.png")));
        btnSair.getStyleClass().add("btnSair");
        sair = new InnerShadow(5, Color.RED);
        btnSair.setEffect(sair);

        anchorPane.getChildren().addAll(tableView,
                textFieldVeiculo,
                textFieldDataCompra,
                labelDataCompra,
                labelVeiculo, btnSair,
                btnVender);
    }

    private void layout() {

        tableView.setPrefSize(400, 200);
        tableView.setLayoutX((anchorPane.getWidth() - tableView.getWidth()) / 4.7);
        tableView.setLayoutY(140);

        labelVeiculo.setLayoutX((anchorPane.getWidth() - labelVeiculo.getWidth()) / 8);
        labelVeiculo.setLayoutY(33);

        textFieldVeiculo.setLayoutX((anchorPane.getWidth() - textFieldVeiculo.getWidth()) / 3);
        textFieldVeiculo.setLayoutY(30);
        textFieldVeiculo.setPrefSize(250, 30);

        labelDataCompra.setLayoutX((anchorPane.getWidth() - labelDataCompra.getWidth()) / 8);
        labelDataCompra.setLayoutY(73);

        textFieldDataCompra.setLayoutX((anchorPane.getWidth() - textFieldDataCompra.getWidth()) / 3);
        textFieldDataCompra.setLayoutY(70);
        textFieldDataCompra.setPrefSize(250, 30);

        btnVender.setLayoutX((anchorPane.getWidth() - btnVender.getWidth()) / 3);
        btnVender.setLayoutY(360);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 8.3);
        btnSair.setLayoutY(360);

    }

    private void definirEventos() {

        btnVender.setOnAction(e -> {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Venda");
                dialog.setContentText("Necessario permissao");
                dialog.setHeaderText("Digite sua senha de super usuario:");
                Optional<String> senha = dialog.showAndWait();
                if (senha.get().equals("admin")) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Venda");
                    alert.setContentText("Deseja continuar com a operacao");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        File file = new File("c:/Temp/Carro" + ".csv");
                        file.delete();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Venda");
                        alert.setContentText("Venda realizada!");
                        alert.show();

                    }

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Venda");
                    alert.setContentText("Senha de super usuario invalida!");
                    alert.show();
                }
            } catch (Exception erro) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Venda");
                alert.setContentText(erro.getMessage() + " operacao cancelada!");
                alert.show();
            }

        });

        btnSair.setOnAction(e -> {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Venda");
            alert.setContentText("Deseja realmente sair");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        });
    }

    private void selecionarTableCarroVenda(CountryData data) {
        textFieldDataCompra.setText(arquivo.trasDataEntrada());
        textFieldVeiculo.setText(data.getCountry());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
