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
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.CountryData;
import utilitario.ControleArquivo;

public class ModeloTableCliente extends Application {

    private InnerShadow atualizar, sair;
    private ImageView imageViewSair, imageViewAtualizar;
    private Label labelMensagem;
    private Label labelNome;
    private Label labelCpf;
    private Label labelTelefone;
    private Label labelEndereco;
    private Label labelCnh;
    private Label labelIdade;
    private TextField textFieldNome;
    private TextField textFieldCpf;
    private TextField textFieldTelefone;
    private TextField textFieldEndereco;
    private TextField textFieldCnh;
    private TextField textFieldIdade;
    private Button btnSair;
    private Button btnAlterar;
    private TableView<CountryData> tableView;
    private static Stage stage;
    private AnchorPane anchorPane;
    private Alert alert;
    private String alterarDados;
    private String teste;

    ControleArquivo controlaArquivo = new ControleArquivo();

    @Override
    public void start(Stage primaryStage) throws Exception {
        carregarTabela();
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selecionarTableViewCliente(newValue);
        });
        anchorPane = new AnchorPane(tableView);
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("utilitario/Botao.css");
        anchorPane.setStyle("-fx-background-color: WHITE");
        //scene.setFill(Color.TRANSPARENT);
        anchorPane.setPrefSize(700, 0);
        primaryStage.setScene(scene);
        primaryStage.show();
        inicializarComponentes();
        stage = primaryStage;
        stage.setTitle("Historico de cadastro de clientes");
        definirEventos();

    }

    private void inicializarComponentes() {

        sair = new InnerShadow(5, Color.RED);
        atualizar = new InnerShadow(5, Color.BLUE);
        labelNome = new Label("NOME:");
        labelIdade = new Label("IDADE:");
        labelCpf = new Label("CPF:");
        labelCnh = new Label("CNH:");
        labelEndereco = new Label("ENDERECO:");
        labelTelefone = new Label("TELEFONE:");
        labelMensagem = new Label("Historico de cadastro de clientes");

        textFieldNome = new TextField();
        textFieldIdade = new TextField();
        textFieldCpf = new TextField();
        textFieldCnh = new TextField();
        textFieldEndereco = new TextField();
        textFieldTelefone = new TextField();

        imageViewSair = new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png")));
        btnSair = new Button("Sair", imageViewSair);
        btnSair.getStyleClass().add("btnSair");
        btnSair.setEffect(sair);

        imageViewAtualizar = new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png")));
        btnAlterar = new Button("Alterar", imageViewAtualizar);
        btnAlterar.getStyleClass().add("btnEntrar");
        btnAlterar.setEffect(atualizar);

        anchorPane.getChildren().addAll(
                labelNome,
                labelIdade,
                labelCpf,
                labelCnh,
                labelEndereco,
                labelTelefone,
                labelMensagem,
                textFieldNome,
                textFieldIdade,
                textFieldCpf,
                textFieldCnh,
                textFieldEndereco,
                textFieldTelefone,
                imageViewSair,
                btnAlterar,
                btnSair);

        tableView.setPrefSize(370, 350);

        tableView.setLayoutX((anchorPane.getWidth() - tableView.getWidth()) / 13);
        tableView.setLayoutY(10);

        labelMensagem.setLayoutX((anchorPane.getWidth() - labelMensagem.getWidth()) / 1.6);
        labelMensagem.setLayoutY(3);
        labelMensagem.setFont(new Font("sefif", 15));

        labelNome.setLayoutX((anchorPane.getWidth() - labelNome.getWidth()) / 1.7);
        labelNome.setLayoutY(49);
        textFieldNome.setLayoutX((anchorPane.getWidth() - textFieldNome.getWidth()) / 1.44);
        textFieldNome.setLayoutY(45);

        labelIdade.setLayoutX((anchorPane.getWidth() - labelIdade.getWidth()) / 1.7);
        labelIdade.setLayoutY(94);
        textFieldIdade.setLayoutX((anchorPane.getWidth() - textFieldIdade.getWidth()) / 1.44);
        textFieldIdade.setLayoutY(90);

        labelCpf.setLayoutX((anchorPane.getWidth() - labelCpf.getWidth()) / 1.7);
        labelCpf.setLayoutY(140);
        textFieldCpf.setLayoutX((anchorPane.getWidth() - textFieldCpf.getWidth()) / 1.44);
        textFieldCpf.setLayoutY(135);

        labelCnh.setLayoutX((anchorPane.getWidth() - labelCnh.getWidth()) / 1.7);
        labelCnh.setLayoutY(185);
        textFieldCnh.setLayoutX((anchorPane.getWidth() - textFieldCnh.getWidth()) / 1.44);
        textFieldCnh.setLayoutY(180);

        labelTelefone.setLayoutX((anchorPane.getWidth() - labelTelefone.getWidth()) / 1.7);
        labelTelefone.setLayoutY(230);
        textFieldTelefone.setLayoutX((anchorPane.getWidth() - textFieldTelefone.getWidth()) / 1.44);
        textFieldTelefone.setLayoutY(226);

        labelEndereco.setLayoutX((anchorPane.getWidth() - labelEndereco.getWidth()) / 1.7);
        labelEndereco.setLayoutY(273);
        textFieldEndereco.setLayoutX((anchorPane.getWidth() - textFieldEndereco.getWidth()) / 1.44);
        textFieldEndereco.setLayoutY(269);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 1.7);
        btnSair.setLayoutY(327);

        btnAlterar.setLayoutX((anchorPane.getWidth() - btnAlterar.getWidth()) / 1.45);
        btnAlterar.setLayoutY(327);

        textFieldNome.setEditable(true);
        textFieldIdade.setEditable(true);
        textFieldCpf.setEditable(true);
        textFieldCnh.setEditable(true);
        textFieldEndereco.setEditable(true);
        textFieldTelefone.setEditable(true);

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

        btnAlterar.setOnAction(e -> {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Confirmacao");
                dialog.setContentText("Forneca sua senha de super usuario:");
                Optional<String> senha = dialog.showAndWait();

                if (senha.get().equals("admin")) {

                    CadastroDAO contatoDAO = new CadastroDAO();
                    Cliente cliente = new Cliente();

                    cliente.setNome(textFieldNome.getText());
                    cliente.setIdade(textFieldIdade.getText());
                    cliente.setCpf(textFieldCpf.getText());
                    cliente.setCnh(textFieldCnh.getText());
                    cliente.setTelefone(textFieldTelefone.getText());
                    cliente.setEndereco(textFieldEndereco.getText());

                    String caminho = "c:/Temp/Cliente";
                    String registro = cliente.getNome() + ","
                            + cliente.getIdade() + ","
                            + cliente.getCpf() + ","
                            + cliente.getCnh() + ","
                            + cliente.getTelefone() + ","
                            + cliente.getEndereco();

                    contatoDAO.gravar(registro, caminho);

                    controlaArquivo.gravaNomeCnh(textFieldNome.getText(), textFieldCnh.getText());

                    stage.close();
                    try {
                        new ModeloTableCliente().start(new Stage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacao");
                    alert.setContentText("Senha de super usuario invalida!");
                    alert.show();
                }
            } catch (Exception erro) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacao");
                alert.setContentText(erro.getMessage() + " Operacao cancelada");
                alert.show();
            }

        });

    }

    @SuppressWarnings("unchecked")
    private void carregarTabela() throws IOException {

        Collection<CountryData> list = Files.readAllLines(new File("c:/Temp/Cliente.csv").toPath()).stream()
                .map(line -> {
                    String[] details = line.split(",");
                    CountryData cd = new CountryData();

                    cd.setCountry(details[0]);
                    cd.setCapital(details[1]);
                    cd.setPopulation(details[2]);
                    cd.setDemocracy(details[3]);
                    cd.setDemocracy2(details[4]);
                    cd.setDemocracy3(details[5]);

                    return cd;
                }).collect(Collectors.toList());

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);

        tableView = new TableView<>();
        TableColumn<CountryData, String> col1 = new TableColumn<>();
        TableColumn<CountryData, String> col2 = new TableColumn<>();
        TableColumn<CountryData, String> col3 = new TableColumn<>();
        TableColumn<CountryData, String> col4 = new TableColumn<>();
        TableColumn<CountryData, String> col5 = new TableColumn<>();
        TableColumn<CountryData, String> col6 = new TableColumn<>();

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6);

        col1.setCellValueFactory(data -> data.getValue().countryProperty());
        col2.setCellValueFactory(data -> data.getValue().capitalProperty());
        col3.setCellValueFactory(data -> data.getValue().populationProperty());
        col4.setCellValueFactory(data -> data.getValue().democracyProperty());
        col5.setCellValueFactory(data -> data.getValue().democracyProperty2());
        col6.setCellValueFactory(data -> data.getValue().democracyProperty3());

        tableView.setItems(details);
    }

    public void selecionarTableViewCliente(CountryData data) {

        textFieldNome.setText(data.getCountry());
        textFieldIdade.setText(data.getCapital());
        textFieldCpf.setText(data.getPopulation());
        textFieldCnh.setText(data.getDemocracy());
        textFieldTelefone.setText(data.getDemocracy2());
        textFieldEndereco.setText(data.getDemocracy3());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
