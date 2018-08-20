package visao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.CadastroDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.CountryData;
import modelo.Fabricante;
import utilitario.ControleArquivo;

public class ModeloTabelaCarro extends Application {

    InnerShadow sair, atualizar;
    private ImageView imageViewSair, imageViewAtualizar;
    private Label labelMensagem;
    private Label labelMarca;
    private Label labelModelo;
    private Label labelDatacadastro;
    private Label labeldataFabricacao;
    private Label labelplaca;
    private Label labelValorHora;
    private Label labelValorSeguro;
    private TextField textFieldMarca;
    private TextField textFieldModelo;
    private TextField textFieldDatacadastro;
    private TextField textFieldFabricacao;
    private TextField textFieldplaca;
    private TextField textFieldValorHora;
    private TextField textFieldValorSeguro;
    private Button btnSair;
    private Button btnAtulizar;
    private TableView<CountryData> tableView;
    private static Stage stage;
    private AnchorPane anchorPane;
    private Alert alert;

    public void start(Stage stage) throws Exception {
        carregarTabela();
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selecionarTableViewCliente(newValue);
        });
        anchorPane = new AnchorPane(tableView);
        Scene scene = new Scene(anchorPane);
        anchorPane.setStyle("-fx-background-color: WHITE");
        scene.getStylesheets().add("/utilitario/Botao.css");
        inicializarComponentes();
        anchorPane.setPrefSize(700, 0);
        stage.setScene(scene);
        this.stage = stage;
        stage.setTitle("Historico de cadastro de vaiculos");
        stage.show();
        layout();
        definirEventos();

    }

    private void inicializarComponentes() {
        atualizar = new InnerShadow(5, Color.BLUE);
        sair = new InnerShadow(5, Color.RED);

        labelMensagem = new Label("Historico de cadastro de carro");
        labelMarca = new Label("FABRICANTE:");
        labelModelo = new Label("MODELO:");
        labeldataFabricacao = new Label("ANO:");
        labelplaca = new Label("PLACA:");
        labelDatacadastro = new Label("DATA:");
        labelValorHora = new Label("R$ HORA:");
        labelValorSeguro = new Label("R$ SEGURO:");

        textFieldMarca = new TextField();
        textFieldModelo = new TextField();
        textFieldFabricacao = new TextField();
        textFieldplaca = new TextField();
        textFieldDatacadastro = new TextField();
        textFieldValorHora = new TextField();
        textFieldValorSeguro = new TextField();

        imageViewSair = new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png")));
        btnSair = new Button("Sair", imageViewSair);
        btnSair.getStyleClass().add("btnSair");
        btnSair.setEffect(sair);

        imageViewAtualizar = new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png")));
        btnAtulizar = new Button("Atualizar", imageViewAtualizar);
        btnAtulizar.getStyleClass().add("btnEntrar");
        btnAtulizar.setEffect(atualizar);

        anchorPane.getChildren().addAll(
                labelMensagem,
                labelMarca,
                labelModelo,
                labeldataFabricacao,
                labelplaca,
                labelDatacadastro,
                labelValorHora,
                labelValorSeguro,
                textFieldMarca,
                textFieldModelo,
                textFieldFabricacao,
                textFieldplaca,
                textFieldDatacadastro,
                textFieldValorHora,
                textFieldValorSeguro,
                imageViewSair,
                btnSair, btnAtulizar);

    }

    private void layout() {

        tableView.setPrefSize(375, 369);
        tableView.setLayoutX((anchorPane.getWidth() - tableView.getWidth()) / 13);
        tableView.setLayoutY(10);

        labelMensagem.setLayoutX((anchorPane.getWidth() - labelMensagem.getWidth()) / 1.22);
        labelMensagem.setLayoutY(3);
        labelMensagem.setFont(new Font("sefif", 15));

        labelMarca.setLayoutX((anchorPane.getWidth() - labelMarca.getWidth()) / 1.5);
        labelMarca.setLayoutY(49);
        textFieldMarca.setLayoutX((anchorPane.getWidth() - textFieldMarca.getWidth()) / 1.1);
        textFieldMarca.setLayoutY(45);

        labelModelo.setLayoutX((anchorPane.getWidth() - labelModelo.getWidth()) / 1.5);
        labelModelo.setLayoutY(94);
        textFieldModelo.setLayoutX((anchorPane.getWidth() - textFieldModelo.getWidth()) / 1.1);
        textFieldModelo.setLayoutY(90);

        labeldataFabricacao.setLayoutX((anchorPane.getWidth() - labeldataFabricacao.getWidth()) / 1.5);
        labeldataFabricacao.setLayoutY(140);
        textFieldFabricacao.setLayoutX((anchorPane.getWidth() - textFieldFabricacao.getWidth()) / 1.1);
        textFieldFabricacao.setLayoutY(135);

        labelplaca.setLayoutX((anchorPane.getWidth() - labelplaca.getWidth()) / 1.5);
        labelplaca.setLayoutY(185);
        textFieldplaca.setLayoutX((anchorPane.getWidth() - textFieldplaca.getWidth()) / 1.1);
        textFieldplaca.setLayoutY(180);

        labelDatacadastro.setLayoutX((anchorPane.getWidth() - labelDatacadastro.getWidth()) / 1.5);
        labelDatacadastro.setLayoutY(230);

        textFieldDatacadastro.setLayoutX((anchorPane.getWidth() - textFieldDatacadastro.getWidth()) / 1.1);
        textFieldDatacadastro.setLayoutY(226);

        labelValorHora.setLayoutX((anchorPane.getWidth() - labelValorHora.getWidth()) / 1.5);
        labelValorHora.setLayoutY(274);

        textFieldValorHora.setLayoutX((anchorPane.getWidth() - textFieldValorHora.getWidth()) / 1.1);
        textFieldValorHora.setLayoutY(272);

        labelValorSeguro.setLayoutX((anchorPane.getWidth() - labelValorSeguro.getWidth()) / 1.5);
        labelValorSeguro.setLayoutY(318);

        textFieldValorSeguro.setLayoutX((anchorPane.getWidth() - textFieldValorSeguro.getWidth()) / 1.1);
        textFieldValorSeguro.setLayoutY(315);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 1.5);
        btnSair.setLayoutY(350);

        btnAtulizar.setLayoutX((anchorPane.getWidth() - btnAtulizar.getWidth()) / 1.2);
        btnAtulizar.setLayoutY(350);
    }

    private void definirEventos() {

        btnSair.setOnAction(e -> {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmacao");
            alert.setHeaderText("Projeto integrador diz:");
            alert.setContentText("Dejesa Realmente Sair?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        });

        btnAtulizar.setOnAction(e -> {

            try {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Confirmacao");
                dialog.setContentText("Digite sua senha de super usuario:");
                Optional<String> senha = dialog.showAndWait();

                if (senha.get().equals("admin")) {
                    CadastroDAO contatoDAO = new CadastroDAO();
                    Fabricante fabricante = new Fabricante();

                    fabricante.setMarca(textFieldMarca.getText());
                    fabricante.setModelo(textFieldModelo.getText());
                    fabricante.setDataCadastro(textFieldDatacadastro.getText());
                    fabricante.setDataFabricacao(textFieldFabricacao.getText());
                    fabricante.setPlaca(textFieldplaca.getText());
                    fabricante.setValorSeguro(textFieldValorSeguro.getText());

                    String caminho = "c:/Temp/Carro";
                    String registro = fabricante.getMarca() + ","
                            + fabricante.getModelo() + ","
                            + fabricante.getDataCadastro() + ","
                            + fabricante.getDataFabricacao() + ","
                            + fabricante.getPlaca() + ","
                            + textFieldValorHora.getText() + ","
                            + fabricante.getValorSeguro();

                    contatoDAO.gravar(registro, caminho);

                    ControleArquivo.gravaModeloCarro(textFieldModelo.getText());
                    ControleArquivo.gravaPrecoModelo(textFieldValorHora.getText(), textFieldModelo.getText());
                    ControleArquivo.gravarPreco(textFieldValorHora.getText());
                    stage.close();

                    try {
                        new ModeloTabelaCarro().start(new Stage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencao");
                    alert.setContentText("Senha de super usuario incalida!");
                    alert.show();
                }

            } catch (Exception erro) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencao");
                alert.setContentText(erro.getMessage() + " Operacao cancelada");
                alert.show();
            }

        });
    }

    private void carregarTabela() throws IOException {

        Collection<CountryData> list = Files.readAllLines(new File("c:/Temp/Carro.csv").toPath()).stream()
                .map(line -> {
                    String[] details = line.split(",");
                    CountryData cd = new CountryData();
                    cd.setCountry(details[0]);
                    cd.setCapital(details[1]);
                    cd.setPopulation(details[2]);
                    cd.setDemocracy(details[3]);
                    cd.setDemocracy2(details[4]);

                    return cd;
                })
                .collect(Collectors.toList());

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);

        tableView = new TableView<>();
        TableColumn<CountryData, String> col1 = new TableColumn<>();
        TableColumn<CountryData, String> col2 = new TableColumn<>();
        TableColumn<CountryData, String> col3 = new TableColumn<>();
        TableColumn<CountryData, String> col4 = new TableColumn<>();
        TableColumn<CountryData, String> col5 = new TableColumn<>();

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);

        col1.setCellValueFactory(data -> data.getValue().countryProperty());
        col2.setCellValueFactory(data -> data.getValue().capitalProperty());
        col3.setCellValueFactory(data -> data.getValue().populationProperty());
        col4.setCellValueFactory(data -> data.getValue().democracyProperty());
        col5.setCellValueFactory(data -> data.getValue().democracyProperty2());

        tableView.setItems(details);
    }

    public void selecionarTableViewCliente(CountryData data) {

        textFieldMarca.setText(data.getCountry());
        textFieldModelo.setText(data.getCapital());
        textFieldFabricacao.setText(data.getPopulation());
        textFieldplaca.setText(data.getDemocracy());
        textFieldDatacadastro.setText(data.getDemocracy2());

    }

    public static void main(String[] args) {
        launch(args);
    }

}

/*
  		textFieldMarca.setEditable(false);
    	textFieldModelo.setEditable(false);
    	textFieldFabricacao.setEditable(false);
    	textFieldplaca.setEditable(false);
    	textFieldDatacadastro.setEditable(false);
    	textFieldValorHora.setEditable(false);
    	textFieldValorSeguro.setEditable(false);
 */
