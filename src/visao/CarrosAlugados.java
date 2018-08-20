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
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.CountryData;
import utilitario.ControleArquivo;

public class CarrosAlugados extends Application {

    private static Stage stage;
    private InnerShadow sair;
    private AnchorPane anchorPane;
    private TableView<CountryData> tableView;
    private Label labelNomeCliente;
    private Label labelCnhCliente;
    private Label labelNomeCarro;
    private Label labelDataEntrada;
    private Label labelDateEntrega;
    private Label labelDiaPosse;
    private Label labelValorPagar;
    private Label labelValorSeguro;
    private Label labelValorDiaria;
    private Label labelFormaPagamento;
    private Label labelValorTotal;
    private TextField textFieldNomeCliente;
    private TextField textFieldCnhCliente;
    private TextField textFieldNomeCarro;
    private TextField textFieldDataEntrada;
    private TextField textFieldDataEntrega;
    private TextField textFieldDiaPosse;
    private TextField textFieldValorHora;
    private TextField textFieldValorSeguro;
    private TextField textFieldValorDiaria;
    private TextField textFieldValorTotalPagar;
    private TextField textFieldFormaPagamento;
    private Button btnSair;
    private Alert alert;
    ControleArquivo controleArquivo = new ControleArquivo();

    public void start(Stage stage) {
        CarrosAlugados.stage = stage;
        if (!(controleArquivo.trasContrato().isEmpty())) {
            carregaTabela();
            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                selecionarTableCliente(newValue);
            });
            anchorPane = new AnchorPane();
            anchorPane.setStyle("-fx-background-color: WHITE");
            anchorPane.setPrefSize(650, 510);
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("/utilitario/Botao.css");
            stage.setScene(scene);
            iniciliazarComponentes();
            definirEventos();
            stage.show();
            stage.setTitle("Historico de veiculos alugados");
            layout();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Voce nao possue carros alugados!");
            alert.show();
        }

    }

    private void iniciliazarComponentes() {

        labelNomeCliente = new Label("NOME:");
        labelCnhCliente = new Label("CNH:");
        labelNomeCarro = new Label("CARRO:");
        labelDataEntrada = new Label("ENTRADA");
        labelDateEntrega = new Label("ENTREGA");
        labelDiaPosse = new Label("POSSE:");
        labelValorPagar = new Label("R$ HORA:");
        labelValorSeguro = new Label("R$ SEGURO:");
        labelValorDiaria = new Label("R$ DIARIA:");
        labelFormaPagamento = new Label("PAGAMENTO:");
        labelValorTotal = new Label("R$ TOTAL:");

        textFieldNomeCliente = new TextField();
        textFieldCnhCliente = new TextField();
        textFieldNomeCarro = new TextField();
        textFieldDataEntrada = new TextField();
        textFieldDataEntrega = new TextField();
        textFieldDiaPosse = new TextField();
        textFieldValorHora = new TextField();
        textFieldValorSeguro = new TextField();
        textFieldValorDiaria = new TextField();
        textFieldValorTotalPagar = new TextField();
        textFieldFormaPagamento = new TextField();

        btnSair = new Button("Sair", new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
        btnSair.getStyleClass().add("btnSair");
        sair = new InnerShadow(5, Color.RED);
        btnSair.setEffect(sair);
        anchorPane.getChildren().addAll(
                tableView,
                labelNomeCliente,
                labelCnhCliente,
                labelNomeCarro,
                labelDataEntrada,
                labelDateEntrega,
                labelDiaPosse,
                labelValorPagar,
                labelValorDiaria,
                labelFormaPagamento,
                labelValorTotal,
                textFieldNomeCliente,
                textFieldCnhCliente,
                textFieldNomeCarro,
                textFieldDataEntrada,
                textFieldDataEntrega,
                textFieldDiaPosse,
                textFieldValorHora,
                textFieldValorSeguro,
                textFieldValorDiaria,
                textFieldValorTotalPagar,
                textFieldFormaPagamento,
                labelValorSeguro,
                btnSair
        );
    }

    private void layout() {

        labelNomeCliente.setLayoutX((anchorPane.getWidth() - labelNomeCliente.getWidth()) / 1.5);
        labelNomeCliente.setLayoutY(33);
        textFieldNomeCliente.setLayoutX((anchorPane.getWidth() - textFieldNomeCliente.getWidth()) / 1.1);
        textFieldNomeCliente.setLayoutY(30);

        labelCnhCliente.setLayoutX((anchorPane.getWidth() - labelCnhCliente.getWidth()) / 1.5);
        labelCnhCliente.setLayoutY(73);
        textFieldCnhCliente.setLayoutX((anchorPane.getWidth() - textFieldCnhCliente.getWidth()) / 1.1);
        textFieldCnhCliente.setLayoutY(70);

        labelNomeCarro.setLayoutX((anchorPane.getWidth() - labelNomeCarro.getWidth()) / 1.5);
        labelNomeCarro.setLayoutY(113);
        textFieldNomeCarro.setLayoutX((anchorPane.getWidth() - textFieldNomeCarro.getWidth()) / 1.1);
        textFieldNomeCarro.setLayoutY(110);

        labelDataEntrada.setLayoutX((anchorPane.getWidth() - labelDataEntrada.getWidth()) / 1.5);
        labelDataEntrada.setLayoutY(153);
        textFieldDataEntrada.setLayoutX((anchorPane.getWidth() - textFieldDataEntrada.getWidth()) / 1.1);
        textFieldDataEntrada.setLayoutY(150);

        labelDateEntrega.setLayoutX((anchorPane.getWidth() - labelDateEntrega.getWidth()) / 1.5);
        labelDateEntrega.setLayoutY(193);
        textFieldDataEntrega.setLayoutX((anchorPane.getWidth() - textFieldDataEntrega.getWidth()) / 1.1);
        textFieldDataEntrega.setLayoutY(190);

        labelDiaPosse.setLayoutX((anchorPane.getWidth() - labelDiaPosse.getWidth()) / 1.5);
        labelDiaPosse.setLayoutY(233);
        textFieldDiaPosse.setLayoutX((anchorPane.getWidth() - textFieldDiaPosse.getWidth()) / 1.1);
        textFieldDiaPosse.setLayoutY(230);

        labelValorPagar.setLayoutX((anchorPane.getWidth() - labelValorPagar.getWidth()) / 1.5);
        labelValorPagar.setLayoutY(273);
        textFieldValorHora.setLayoutX((anchorPane.getWidth() - textFieldValorHora.getWidth()) / 1.1);
        textFieldValorHora.setLayoutY(270);

        labelValorSeguro.setLayoutX((anchorPane.getWidth() - labelValorSeguro.getWidth()) / 1.5);
        labelValorSeguro.setLayoutY(313);
        textFieldValorSeguro.setLayoutX((anchorPane.getWidth() - textFieldValorSeguro.getWidth()) / 1.1);
        textFieldValorSeguro.setLayoutY(309);

        labelValorDiaria.setLayoutX((anchorPane.getWidth() - labelValorDiaria.getWidth()) / 1.5);
        labelValorDiaria.setLayoutY(345);
        textFieldValorDiaria.setLayoutX((anchorPane.getWidth() - textFieldValorDiaria.getWidth()) / 1.1);
        textFieldValorDiaria.setLayoutY(343);

        labelValorTotal.setLayoutX((anchorPane.getWidth() - labelValorTotal.getWidth()) / 1.5);
        labelValorTotal.setLayoutY(385);
        textFieldValorTotalPagar.setLayoutX((anchorPane.getWidth() - textFieldValorTotalPagar.getWidth()) / 1.1);
        textFieldValorTotalPagar.setLayoutY(383);

        labelFormaPagamento.setLayoutX((anchorPane.getWidth() - labelFormaPagamento.getWidth()) / 1.5);
        labelFormaPagamento.setLayoutY(425);
        textFieldFormaPagamento.setLayoutX((anchorPane.getWidth() - textFieldFormaPagamento.getWidth()) / 1.1);
        textFieldFormaPagamento.setLayoutY(423);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 1.09);
        btnSair.setLayoutY(458);

    }

    private void selecionarTableCliente(CountryData data) {

        textFieldNomeCliente.setText(data.getCountry());
        textFieldCnhCliente.setText(data.getCapital());
        textFieldNomeCarro.setText(data.getDemocracy());
        textFieldDataEntrada.setText(data.getDemocracy2());
        textFieldDataEntrega.setText(data.getDemocracy3());
        textFieldDiaPosse.setText(data.getDemocracy4());
        textFieldValorHora.setText(data.getDemocracy5());
        textFieldValorSeguro.setText(data.getDemocracy6());
        textFieldValorDiaria.setText(data.getDemocracy7());
        textFieldValorTotalPagar.setText(data.getDemocracy8());
        textFieldFormaPagamento.setText(data.getDemocracy9());

    }

    private void definirEventos() {
        btnSair.setOnAction(e -> {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Deseja realmente sair");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }

        });
    }

    @SuppressWarnings("unchecked")

    private void carregaTabela() {
        try {
            Collection<CountryData> list = Files.readAllLines(new File("c:/Temp/DadosContrato.csv").toPath()).stream()
                    .map(line -> {
                        String[] details = line.split(",");
                        CountryData cd = new CountryData();

                        cd.setCountry(details[0]);
                        cd.setCapital(details[1]);
                        cd.setDemocracy(details[2]);
                        cd.setDemocracy2(details[3]);
                        cd.setDemocracy3(details[4]);
                        cd.setDemocracy4(details[5]);
                        cd.setDemocracy5(details[6]);
                        cd.setDemocracy6(details[7]);
                        cd.setDemocracy7(details[8]);
                        cd.setDemocracy8(details[9]);
                        cd.setDemocracy9(details[10]);

                        return cd;

                    }).collect(Collectors.toList());

            ObservableList<CountryData> details = FXCollections.observableArrayList(list);

            tableView = new TableView<>();
            tableView.setPrefSize(370, 450);

            TableColumn<CountryData, String> col1 = new TableColumn<>();
            TableColumn<CountryData, String> col2 = new TableColumn<>();
            TableColumn<CountryData, String> col3 = new TableColumn<>();
            TableColumn<CountryData, String> col4 = new TableColumn<>();
            TableColumn<CountryData, String> col5 = new TableColumn<>();
            TableColumn<CountryData, String> col6 = new TableColumn<>();
            TableColumn<CountryData, String> col7 = new TableColumn<>();
            TableColumn<CountryData, String> col8 = new TableColumn<>();
            TableColumn<CountryData, String> col9 = new TableColumn<>();
            TableColumn<CountryData, String> col10 = new TableColumn<>();
            TableColumn<CountryData, String> col11 = new TableColumn<>();

            tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11);

            col1.setCellValueFactory(data -> data.getValue().countryProperty());
            col2.setCellValueFactory(data -> data.getValue().capitalProperty());
            col3.setCellValueFactory(data -> data.getValue().democracyProperty());
            col4.setCellValueFactory(data -> data.getValue().democracyProperty2());
            col5.setCellValueFactory(data -> data.getValue().democracyProperty3());
            col6.setCellValueFactory(data -> data.getValue().democracyProperty4());
            col7.setCellValueFactory(data -> data.getValue().democracyProperty5());
            col8.setCellValueFactory(data -> data.getValue().democracyProperty6());
            col9.setCellValueFactory(data -> data.getValue().democracyProperty7());
            col10.setCellValueFactory(data -> data.getValue().democracyProperty8());
            col11.setCellValueFactory(data -> data.getValue().democracyProperty9());

            tableView.setItems(details);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
