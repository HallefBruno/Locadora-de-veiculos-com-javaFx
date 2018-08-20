package visao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.CountryData;
import utilitario.ConfirmarContrato;
import utilitario.ControleArquivo;
import utilitario.DataHora;

public class AluguelCarro extends Application {

    private InnerShadow btnRegs, btnAluga;
    private CheckBox checkBox;
    private Timeline timelineCliente, timelineCarro, timelineData;
    private KeyValue keyValue;
    private KeyFrame keyFrame;
    private ImageView imageViewContrato;
    private ImageView imageViewAlugados;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    private static String dados;
    private AnchorPane anchorPane;
    private ComboBox<String> marcas;
    private TextField textFiledNome;
    private TextField textFieldCnh;
    private TextField textFieldValor;
    private TextField textFieldPosse;
    private TextField textFieldDataDevolucao;
    private TextField textFieldValorHoraCarro;
    private Button btnRegistrar;
    private Button btnAlugados;
    private Label labelNome;
    private Label labelCnh;
    private Label labelValorAluguel;
    private Label labelDataEntrada;
    private Label labelDataEntrega;
    private Label labelPosse;
    private Label labelValorHoraCarro;
    private Label labelAlertaData;
    private Label labelAlertaCarro;
    private Label labelAlertaCliente;
    private Scene scene;
    private TableView<CountryData> tableView;
    private final int HORA_DIA = 24;
    private int dias;
    private int posse;
    private int valorCarro;
    private int valorTotal;
    private int valorSeguroCarro = 0;
    private String formaPagamaneto;
    String semValor = null;
    private Alert alertAtencao;
    ControleArquivo controleArquivo = new ControleArquivo();

    @Override
    public void start(Stage stage) throws Exception {

        if (!(controleArquivo.trasCarroManutencao().isEmpty() && controleArquivo.trasContrato().isEmpty())) {
            alertAtencao = new Alert(Alert.AlertType.INFORMATION);
            alertAtencao.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
            alertAtencao.setTitle("Atencao");
            alertAtencao.setHeaderText("Voce nao possui carro disponivel no momento!");
            alertAtencao.show();
            stage.close();
            stage.setTitle("Aluguel de vaiculo");
        }
        if (controleArquivo.trasCarro().isEmpty()) {
            alertAtencao = new Alert(Alert.AlertType.INFORMATION);
            alertAtencao.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
            alertAtencao.setTitle("Atencao");
            alertAtencao.setHeaderText("Voce nao possui carro disponivel no momento!");
            alertAtencao.show();
        } else {
            carregarTabela();
            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                selecionarTableViewCliente(newValue);
                btnRegistrar.setDisable(false);

            });
            anchorPane = new AnchorPane();
            scene = new Scene(anchorPane);
            inicializarComponestes();
            popularCombo();
            stage.setScene(scene);
            scene.getStylesheets().add("/utilitario/Botao.css");
            stage.show();
            definirEventos();
            layout();
            initTimelineCliente();
            inabilitaCampos();
        }

    }

    private void inicializarComponestes() {

        anchorPane.setPrefSize(700, 400);
        anchorPane.setStyle("-fx-background-color: WHITE");
        imageViewContrato = new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-locacao.png")));
        imageViewAlugados = new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-carro-alugado.png")));
        labelNome = new Label("NOME:");
        labelCnh = new Label("CNH:");
        labelValorAluguel = new Label("R$:");
        labelDataEntrada = new Label("ENTRADA:");
        labelDataEntrega = new Label("ENTREGA:");
        labelPosse = new Label("POSSE:");
        labelValorHoraCarro = new Label("R$ HORA:");

        labelAlertaCliente = new Label();
        labelAlertaCliente.setTooltip(new Tooltip("Selecione um cliente"));
        labelAlertaCliente.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-seleciona-cli.png"))));

        labelAlertaCarro = new Label();
        labelAlertaCarro.setTooltip(new Tooltip("Selecione o carro"));
        labelAlertaCarro.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-atencao-carro.png"))));
        labelAlertaCarro.setVisible(false);

        labelAlertaData = new Label();
        labelAlertaData.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-atencao-data.png"))));
        labelAlertaData.setTooltip(new Tooltip("Selecione a data de retorno"));
        labelAlertaData.setVisible(false);

        textFiledNome = new TextField();
        textFieldCnh = new TextField();
        textFieldValor = new TextField();
        textFieldPosse = new TextField();
        textFieldValorHoraCarro = new TextField();

        btnRegistrar = new Button("Alugar", imageViewContrato);
        btnRegistrar.getStyleClass().add("btnRegistro");
        btnRegs = new InnerShadow(5, Color.GREEN);
        btnRegistrar.setEffect(btnRegs);
        btnRegistrar.setDisable(true);

        btnAlugados = new Button("Alugados", imageViewAlugados);
        btnAlugados.setDisable(true);
        btnAlugados.getStyleClass().add("btnEntrar");
        btnAluga = new InnerShadow(5, Color.BLUE);
        btnAlugados.setEffect(btnAluga);

        marcas = new ComboBox<>();
        marcas.setDisable(true);
        datePicker1 = new DatePicker(new DataHora().NOW_LOCAL_DATE());
        datePicker2 = new DatePicker(new DataHora().NOW_LOCAL_DATE());
        datePicker2.setDisable(true);
        checkBox = new CheckBox("É cartao?");
        checkBox.setStyle("-fx-background-color: AQUAMARINE");
        checkBox.setVisible(false);
        checkBox.setFont(new Font("serif", 13));
        //checkBox.setStyle("-fx-text-fill: VIOLET");
        anchorPane.getChildren().addAll(
                tableView,
                labelNome,
                labelCnh,
                textFiledNome,
                textFieldCnh, marcas,
                btnRegistrar,
                textFieldValor,
                textFieldPosse,
                textFieldValorHoraCarro,
                labelValorAluguel,
                btnAlugados,
                labelDataEntrada,
                labelDataEntrega,
                labelPosse,
                labelValorHoraCarro,
                datePicker1, datePicker2,
                labelAlertaData,
                labelAlertaCliente,
                labelAlertaCarro,
                checkBox
        );

    }

    private void definirEventos() {

        marcas.setOnAction(e -> {

            datePicker2.setDisable(false);
            labelAlertaData.setVisible(true);
            initTimelineData();

            if (marcas.getValue() != null) {
                labelAlertaCarro.setVisible(false);
                timelineCarro.stop();
            }
        });

        datePicker2.setOnAction(e -> {

            DataHora dataHora = new DataHora();
            textFieldPosse.setText("" + dataHora.calculaData(datePicker1, datePicker2));
            dias = Integer.parseInt(textFieldPosse.getText());
            textFieldValorHoraCarro.requestFocus();

            if (textFieldPosse.getText() != null) {
                labelAlertaData.setVisible(false);
                timelineData.stop();
                checkBox.setVisible(true);
            }

        });

        btnRegistrar.setOnAction(e -> {

            if (!(textFieldValorHoraCarro.getText().isEmpty() || textFieldValor.getText().isEmpty())) {
                if (checkBox.isSelected() == false) {
                    formaPagamaneto = "Sem cartao";
                    valorSeguroCarro = Integer.valueOf(textFieldValor.getText());
                    posse = Integer.valueOf(textFieldPosse.getText());
                    valorCarro = Integer.valueOf(textFieldValorHoraCarro.getText());
                    valorTotal = HORA_DIA * posse * valorCarro;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confira os dados");
                    alert.setHeaderText("Confira os dados");
                    alert.setContentText("Nome " + textFiledNome.getText() + "\n"
                            + "CNH " + textFieldCnh.getText() + "\n"
                            + "Carro " + marcas.getValue() + "\n"
                            + "Posse do carro " + textFieldPosse.getText() + " dia" + "\n"
                            + "Data da entrega " + alteraData() + "\n"
                            + "Valor da diaria " + valorTotal + "\n"
                            + "* Operado,sera cobrado do cliente R$ " + textFieldValor.getText() + " de calcao, que no caso seria o seguro do carro, pois a opcao cartao nao foi selecionada!" + "\n"
                            + "Valor total da receita " + (valorTotal + valorSeguroCarro));
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        new ConfirmarContrato(textFiledNome.getText(), textFieldCnh.getText(), alteraData()).start(new Stage());

                        String registro = textFiledNome.getText() + ","
                                + textFieldCnh.getText() + ","
                                + marcas.getValue() + ","
                                + alteraData() + ","
                                + alteraDataDevolucao() + ","
                                + textFieldPosse.getText() + ","
                                + textFieldValorHoraCarro.getText() + ","
                                + textFieldValor.getText() + ","
                                + valorTotal + ","
                                + (valorTotal + valorSeguroCarro) + ","
                                + formaPagamaneto;

                        controleArquivo.gravarDadosContrato(registro);
                        controleArquivo.gravaFormaPagamento("1");
                        controleArquivo.gravaValorSeguro(textFieldValor.getText());
                        controleArquivo.gravaDataEntrada(alteraDataEntrada());
                        controleArquivo.gravaDataDevolucao(alteraDataDevolucao());
                        controleArquivo.gravaPosse(textFieldPosse.getText());
                        controleArquivo.gravaValorDiaria(textFieldValor.getText());

                        btnAlugados.setDisable(false);
                        inabilitaCampos();
                        tableView.setDisable(true);
                        limparCampos();
                        checkBox.setVisible(false);
                        btnRegistrar.setDisable(true);

                    }
                }
                if (checkBox.isSelected()) {
                    formaPagamaneto = "Cartao";
                    valorTotal = HORA_DIA * posse * valorCarro;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confira os dados");
                    alert.setHeaderText("Confira os dados");
                    alert.setContentText("Nome " + textFiledNome.getText() + "\n"
                            + "CNH " + textFieldCnh.getText() + "\n"
                            + "Carro " + marcas.getValue() + "\n"
                            + "Posse do carro " + textFieldPosse.getText() + " dia" + "\n"
                            + "Data da entrega " + alteraData() + "\n"
                            + "Data da devolucao " + alteraDataDevolucao() + "\n"
                            + "Valor total de receita R$ " + valorTotal);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        new ConfirmarContrato(textFiledNome.getText(), textFieldCnh.getText(), alteraData()).start(new Stage());

                        String registro = textFiledNome.getText() + ","
                                + textFieldCnh.getText() + ","
                                + marcas.getValue() + ","
                                + alteraData() + ","
                                + alteraDataDevolucao() + ","
                                + textFieldPosse.getText() + ","
                                + textFieldValorHoraCarro.getText() + ","
                                + textFieldValor.getText() + ","
                                + valorTotal + ","
                                + semValor + ","
                                + formaPagamaneto;

                        controleArquivo.gravarDadosContrato(registro);
                        controleArquivo.gravaFormaPagamento("2");
                        controleArquivo.gravaDataEntrada(alteraDataEntrada());
                        controleArquivo.gravaDataDevolucao(alteraDataDevolucao());
                        controleArquivo.gravaValorDiaria(textFieldValor.getText());
                        controleArquivo.gravaPosse(textFieldPosse.getText());

                        btnAlugados.setDisable(false);
                        inabilitaCampos();
                        tableView.setDisable(true);
                        limparCampos();
                        checkBox.setVisible(false);
                        btnRegistrar.setDisable(true);
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencao");
                alert.setContentText("Preencha os campos corretamente!");
                alert.show();
            }

        });

        btnAlugados.setOnAction(e -> {
            new CarrosAlugados().start(new Stage());
        });
    }

    private void layout() {

        tableView.setPrefSize(280, 350);

        //tableView.setLayoutX((anchorPane.getWidth() - tableView.getWidth()) / 15);
        //tableView.setLayoutY(40);
        //labelAlertaCliente.setLayoutX((anchorPane.getWidth() - labelAlertaCliente.getWidth())/20);
        //labelAlertaCliente.setLayoutY(42);
        marcas.setLayoutX((anchorPane.getWidth() - marcas.getWidth()) / 1.37);
        marcas.setLayoutY(2);
        marcas.setPrefSize(173, 25);

        labelAlertaCarro.setLayoutX((anchorPane.getWidth() - labelAlertaCarro.getWidth()) / 1.14);
        labelAlertaCarro.setLayoutY(5);

        labelNome.setLayoutX((anchorPane.getWidth() - labelNome.getWidth()) / 2);
        labelNome.setLayoutY(42);

        labelCnh.setLayoutX((anchorPane.getWidth() - labelCnh.getWidth()) / 2);
        labelCnh.setLayoutY(82);

        labelDataEntrada.setLayoutX((anchorPane.getWidth() - labelDataEntrada.getWidth()) / 2);
        labelDataEntrada.setLayoutY(122);

        labelDataEntrega.setLayoutX((anchorPane.getWidth() - labelDataEntrega.getWidth()) / 2);
        labelDataEntrega.setLayoutY(163);

        labelPosse.setLayoutX((anchorPane.getWidth() - labelPosse.getWidth()) / 2);
        labelPosse.setLayoutY(203);

        labelValorHoraCarro.setLayoutX((anchorPane.getWidth() - labelValorHoraCarro.getWidth()) / 2);
        labelValorHoraCarro.setLayoutY(243);

        labelValorAluguel.setLayoutX((anchorPane.getWidth() - labelValorAluguel.getWidth()) / 2);
        labelValorAluguel.setLayoutY(285);

        textFiledNome.setLayoutX((anchorPane.getWidth() - textFiledNome.getWidth()) / 1.4);
        textFiledNome.setLayoutY(40);
        textFiledNome.setPrefSize(173, 20);

        textFieldCnh.setLayoutX((anchorPane.getWidth() - textFieldCnh.getWidth()) / 1.4);
        textFieldCnh.setLayoutY(80);
        textFieldCnh.setPrefSize(173, 20);

        datePicker1.setLayoutX((anchorPane.getWidth() - datePicker1.getWidth()) / 1.37);
        datePicker1.setLayoutY(120);

        datePicker2.setLayoutX((anchorPane.getWidth() - datePicker2.getWidth()) / 1.37);
        datePicker2.setLayoutY(160);

        labelAlertaData.setLayoutX((anchorPane.getWidth() - labelAlertaData.getWidth()) / 1.18);
        labelAlertaData.setLayoutY(160);

        textFieldPosse.setLayoutX((anchorPane.getWidth() - textFieldPosse.getWidth()) / 1.4);
        textFieldPosse.setLayoutY(200);
        textFieldPosse.setPrefSize(173, 20);

        textFieldValor.setLayoutX((anchorPane.getWidth() - textFieldValor.getWidth()) / 1.4);
        textFieldValor.setLayoutY(280);
        textFieldValor.setPrefSize(173, 20);

        checkBox.setLayoutX((anchorPane.getWidth() - checkBox.getWidth()) / 1.05);
        checkBox.setLayoutY(285);

        textFieldValorHoraCarro.setLayoutX((anchorPane.getWidth() - textFieldValorHoraCarro.getWidth()) / 1.4);
        textFieldValorHoraCarro.setLayoutY(240);
        textFieldValorHoraCarro.setPrefSize(173, 20);

        btnRegistrar.setLayoutX((anchorPane.getWidth() - btnRegistrar.getWidth()) / 1.63);
        btnRegistrar.setLayoutY(320);

        btnAlugados.setLayoutX((anchorPane.getWidth() - btnAlugados.getWidth()) / 1.29);
        btnAlugados.setLayoutY(320);

    }

    @SuppressWarnings("unchecked")
    private void carregarTabela() throws IOException {

        Collection<CountryData> list = Files.readAllLines(new File("c:/Temp/NomeCnhCliente.csv").toPath()).stream()
                .map(line -> {
                    String[] details = line.split(",");
                    CountryData cd = new CountryData();

                    cd.setCountry(details[0]);
                    cd.setCapital(details[1]);

                    return cd;

                }).collect(Collectors.toList());

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);

        tableView = new TableView<>();
        TableColumn<CountryData, String> col1 = new TableColumn<>();
        TableColumn<CountryData, String> col2 = new TableColumn<>();

        tableView.getColumns().addAll(col1, col2);

        col1.setCellValueFactory(data -> data.getValue().countryProperty());
        col2.setCellValueFactory(data -> data.getValue().capitalProperty());

        tableView.setItems(details);
    }

    public void selecionarTableViewCliente(CountryData data) {

        textFiledNome.setText(data.getCountry());
        textFieldCnh.setText(data.getCapital());

        timelineCliente.stop();
        labelAlertaCliente.setVisible(false);
        labelAlertaCarro.setVisible(true);
        marcas.setDisable(false);
        initTimelineCarro();
    }

    private void popularCombo() {

        String caminho = "c:/Temp/ModeloCarro" + ".csv";

        BufferedReader bufferedModelo;
        try {
            bufferedModelo = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedModelo.readLine()) != null) {
                marcas.getItems().addAll(dados);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void calcularDataDevoluca() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        LocalDate dataDevolucao = datePicker2.getValue();

        Calendar devolucao = Calendar.getInstance();

        devolucao.set(dataDevolucao.getYear(), dataDevolucao.getMonthValue() - 1, dataDevolucao.getDayOfMonth());

        int devolver = Integer.valueOf(textFieldPosse.getText());

        devolucao.add(Calendar.DAY_OF_YEAR, devolver);

        Date teste2 = devolucao.getTime();

        textFieldDataDevolucao.setText(dateFormat.format(teste2));

    }

    public void initTimelineCliente() {

        timelineCliente = new Timeline();
        keyValue = new KeyValue(labelAlertaCliente.opacityProperty(), 0.0);
        keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
        timelineCliente.getKeyFrames().add(keyFrame);
        timelineCliente.setCycleCount(Timeline.INDEFINITE);
        timelineCliente.setAutoReverse(true);
        timelineCliente.play();

    }

    public void initTimelineCarro() {

        timelineCarro = new Timeline();
        keyValue = new KeyValue(labelAlertaCarro.opacityProperty(), 0.0);
        keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
        timelineCarro.getKeyFrames().add(keyFrame);
        timelineCarro.setCycleCount(Timeline.INDEFINITE);
        timelineCarro.setAutoReverse(true);
        timelineCarro.play();
    }

    public void initTimelineData() {

        timelineData = new Timeline();
        keyValue = new KeyValue(labelAlertaData.opacityProperty(), 0.0);
        keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
        timelineData.getKeyFrames().add(keyFrame);
        timelineData.setCycleCount(Timeline.INDEFINITE);
        timelineData.setAutoReverse(true);
        timelineData.play();
    }

    private void inabilitaCampos() {

        textFiledNome.setEditable(false);
        textFieldCnh.setEditable(false);
        textFieldPosse.setEditable(false);
        datePicker1.setDisable(true);
    }

    private void limparCampos() {
        textFiledNome.setText("");
        textFieldCnh.setText("");
        textFieldPosse.setText("");
        textFieldValor.setText("");
        textFieldValorHoraCarro.setText("");

    }

    private String alteraData() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        LocalDate dataDevolucao = datePicker1.getValue();

        Calendar devolucao = Calendar.getInstance();

        devolucao.set(dataDevolucao.getYear(), dataDevolucao.getMonthValue(), dataDevolucao.getDayOfMonth());

        Date teste = devolucao.getTime();

        return dateFormat.format(teste);
    }

    private String alteraDataEntrada() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        LocalDate dataDevolucao = datePicker1.getValue();

        Calendar devolucao = Calendar.getInstance();

        devolucao.set(dataDevolucao.getYear(), dataDevolucao.getMonthValue(), dataDevolucao.getDayOfMonth());

        Date teste = devolucao.getTime();

        return dateFormat.format(teste);
    }

    private String alteraDataDevolucao() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        LocalDate dataDevolucao = datePicker2.getValue();

        Calendar devolucao = Calendar.getInstance();

        devolucao.set(dataDevolucao.getYear(), dataDevolucao.getMonthValue(), dataDevolucao.getDayOfMonth());

        Date teste = devolucao.getTime();

        return dateFormat.format(teste);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
