package visao;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import dao.CadastroDAO;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import modelo.Fabricante;
import utilitario.ControleArquivo;
import utilitario.DataHora;
import utilitario.FormataData;
import utilitario.FormataPlaca;
import utilitario.Instrucao;

public class CadastroDeCarro extends Application {

    InnerShadow registro, cadastro, sair;
    private DatePicker datePickerTeste;
    private Label labelVw;
    private ImageView imageViewLogo;
    private ImageView imageViewSair;
    private ImageView imageViewNovo;
    private ImageView imageViewRegistros;
    private ImageView imageAlerta;
    private AnchorPane anchorPane;
    private ComboBox<String> marca;
    private Label labelMensagem;
    private Label labelMarca;
    private Label labelModelo;
    private Label labelAno;
    private Label labelPlaca;
    private Label labelDataCadastro;
    private Label labelValorHora;
    private Label labelImageAlerta;
    private Label labelSeguro;
    private Button btnRegistrar;
    private Button btnCadastrados;
    private Button btnSair;
    private TextField textFieldModelo;
    private TextField textFieldPlaca;
    private TextField textFieldDataCadastro;
    private TextField textFieldValorHora;
    private TextField texteFieldSeguro;
    private final String empresas[] = {"FIAT", "WOLKSVAGEM", "CHEVROLET", "FORD"};
    private Stage stage;
    public static String dataHoje;

    ControleArquivo arquivoCsv = new ControleArquivo();

    DataHora dataHora = new DataHora();
    FormataPlaca formataPlaca = new FormataPlaca();

    public CadastroDeCarro() {
        if (!arquivoCsv.habilitaNovoCli().isEmpty()) {
            habilitaMenuEMenuItem();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        inicilizarComponentes();
        definirEventos();
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add("/utilitario/Botao.css");
        stage.setScene(scene);
        stage.setTitle("Cadastro de veiculo");
        stage.show();
        layout();

    }

    private void inicilizarComponentes() {

        registro = new InnerShadow(5, Color.BLUE);
        cadastro = new InnerShadow(5, Color.GREEN);
        sair = new InnerShadow(5, Color.RED);
        anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: WHITE");
        anchorPane.setPrefSize(550, 410);
        marca = new ComboBox<>();
        marca.getItems().addAll(empresas);
        imageViewSair = new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png")));
        imageViewNovo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png")));
        imageViewRegistros = new ImageView(new Image(getClass().getResourceAsStream("/imagens/lupa.png")));
        imageAlerta = new ImageView(new Image(getClass().getResourceAsStream("/imagens/alert.png")));

        marca.setTooltip(new Tooltip("Fabricante"));
        labelVw = new Label();

        labelMarca = new Label("MARCA:");
        labelModelo = new Label("MODELO:");
        labelAno = new Label("ANO:");
        labelPlaca = new Label("PLACA:");
        labelDataCadastro = new Label("DATA:");
        labelMensagem = new Label("CADASTRO DE CARRO");
        labelValorHora = new Label("R$ HORA:");
        labelSeguro = new Label("R$ SEGURO:");
        labelImageAlerta = new Label();
        labelImageAlerta.setGraphic(imageAlerta);
        labelImageAlerta.setTooltip(new Tooltip("Clique na imagem"));
        initTimeline();
        labelMensagem.setFont(new Font("serif", 13));

        btnRegistrar = new Button("Novo Registro", imageViewNovo);
        btnRegistrar.getStyleClass().add("btnEntrar");//alterar a cor passar mouse
        btnRegistrar.setEffect(registro);

        btnCadastrados = new Button("Cadastros", imageViewRegistros);
        btnCadastrados.getStyleClass().add("btnRegistro");
        btnCadastrados.setEffect(cadastro);

        btnSair = new Button("Sair", imageViewSair);
        btnSair.getStyleClass().add("btnSair");
        btnSair.setEffect(sair);

        textFieldModelo = new TextField();
        textFieldModelo.setTooltip(new Tooltip("Modelo do carro"));

        datePickerTeste = new DatePicker();
        datePickerTeste.setTooltip(new Tooltip("Ano de fabricacao"));

        textFieldDataCadastro = new TextField(dataHora.mostraData());
        textFieldDataCadastro.setTooltip(new Tooltip("Data cadastro"));

        textFieldPlaca = new TextField();
        textFieldPlaca.setPromptText("AAA-1111");
        textFieldPlaca.setTooltip(new Tooltip("Placa do carro"));
        formataPlaca.formatarPlaca(textFieldPlaca);

        textFieldValorHora = new TextField();
        textFieldValorHora.setPromptText("Valor/Hora");
        textFieldValorHora.setDisable(true);

        texteFieldSeguro = new TextField();
        texteFieldSeguro.setPromptText("Valor do seguro");
        texteFieldSeguro.setTooltip(new Tooltip("Valor do seguro"));
        texteFieldSeguro.setDisable(true);

        anchorPane.getChildren().addAll(
                labelMarca, labelModelo, labelVw,
                labelAno, labelPlaca, labelImageAlerta,
                labelDataCadastro, labelMensagem, labelValorHora,
                imageViewSair, imageViewNovo, imageViewRegistros, imageAlerta,
                marca, textFieldModelo,
                textFieldPlaca, textFieldValorHora,
                textFieldDataCadastro, datePickerTeste,
                btnRegistrar, btnCadastrados, btnSair,
                labelSeguro, texteFieldSeguro
        );

    }

    private void layout() {

        marca.setLayoutX((anchorPane.getWidth() - marca.getWidth()) / 2.65);
        marca.setLayoutY(60);
        marca.setPrefSize(250, 30);

        textFieldModelo.setLayoutX((anchorPane.getWidth() - textFieldModelo.getWidth()) / 2.5);
        textFieldModelo.setLayoutY(100);
        textFieldModelo.setPrefSize(250, 30);

        datePickerTeste.setLayoutX((anchorPane.getWidth() - datePickerTeste.getWidth()) / 2.42);
        datePickerTeste.setLayoutY(140);
        datePickerTeste.setPrefSize(250, 30);

        textFieldDataCadastro.setLayoutX((anchorPane.getWidth() - textFieldDataCadastro.getWidth()) / 2.5);
        textFieldDataCadastro.setLayoutY(180);
        textFieldDataCadastro.setPrefSize(250, 30);
        textFieldDataCadastro.setEditable(false);

        textFieldPlaca.setLayoutX((anchorPane.getWidth() - textFieldPlaca.getWidth()) / 2.5);
        textFieldPlaca.setLayoutY(220);
        textFieldPlaca.setPrefSize(250, 30);

        textFieldValorHora.setLayoutX((anchorPane.getWidth() - textFieldValorHora.getWidth()) / 2.5);
        textFieldValorHora.setLayoutY(260);
        textFieldValorHora.setPrefSize(250, 30);

        texteFieldSeguro.setLayoutX((anchorPane.getWidth() - texteFieldSeguro.getWidth()) / 2.5);
        texteFieldSeguro.setLayoutY(300);
        texteFieldSeguro.setPrefSize(250, 30);

        labelMarca.setLayoutX((anchorPane.getWidth() - labelMarca.getWidth()) / 5);
        labelMarca.setLayoutY(65);

        labelModelo.setLayoutX((anchorPane.getWidth() - labelModelo.getWidth()) / 5);
        labelModelo.setLayoutY(108);

        labelAno.setLayoutX((anchorPane.getWidth() - labelAno.getWidth()) / 5);
        labelAno.setLayoutY(151);

        labelDataCadastro.setLayoutX((anchorPane.getWidth() - labelDataCadastro.getWidth()) / 5);
        labelDataCadastro.setLayoutY(191);

        labelPlaca.setLayoutX((anchorPane.getWidth() - labelPlaca.getWidth()) / 5);
        labelPlaca.setLayoutY(232);

        labelValorHora.setLayoutX((anchorPane.getWidth() - labelValorHora.getWidth()) / 5);
        labelValorHora.setLayoutY(265);

        labelMensagem.setLayoutX((anchorPane.getWidth() - labelMensagem.getWidth()) / 2);
        labelMensagem.setLayoutY(20);

        labelSeguro.setLayoutX((anchorPane.getWidth() - labelSeguro.getWidth()) / 5);
        labelSeguro.setLayoutY(305);

        btnRegistrar.setLayoutX((anchorPane.getWidth() - btnRegistrar.getWidth()) / 4.4);
        btnRegistrar.setLayoutY(365);
        btnRegistrar.setPrefSize(120, 30);

        btnCadastrados.setLayoutX((anchorPane.getWidth() - btnCadastrados.getWidth()) / 2);
        btnCadastrados.setLayoutY(365);
        btnCadastrados.setPrefSize(100, 30);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 1.45);
        btnSair.setLayoutY(365);
        btnSair.setPrefSize(75, 30);

        labelVw.setLayoutX((anchorPane.getWidth() - labelVw.getWidth()) / 1.3);
        labelVw.setLayoutY(55);

        labelImageAlerta.setLayoutX((anchorPane.getWidth() - labelImageAlerta.getWidth()) / 8);
        labelImageAlerta.setLayoutY(105);

    }

    private void definirEventos() {

        btnRegistrar.setOnAction(e -> {
            try {

                Fabricante fabricante = new Fabricante();
                CadastroDAO cadastroDAO = new CadastroDAO();

                String caminho = "c:/Temp/Carro";

                fabricante.setMarca(marca.getValue());
                fabricante.setModelo(textFieldModelo.getText());
                fabricante.setDataFabricacao(alteraData());
                fabricante.setPlaca(textFieldPlaca.getText());
                fabricante.setDataCadastro(textFieldDataCadastro.getText());

                String registro = fabricante.getMarca() + ","
                        + fabricante.getModelo() + ","
                        + fabricante.getDataFabricacao() + ","
                        + fabricante.getPlaca() + ","
                        + fabricante.getDataCadastro();

                cadastroDAO.gravar(registro, caminho);

                ControleArquivo.gravaModeloCarro(textFieldModelo.getText());
                arquivoCsv.gravaDataEntrada(textFieldDataCadastro.getText());
                limparCampos();
                habilitaMenuEMenuItem();

            } catch (Exception erro) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencao");
                alert.setContentText("Preencha todos os campos!");
                alert.show();
            }
        });

        marca.setOnAction(e -> {

            if (marca.getValue().equalsIgnoreCase("Fiat")) {
                imageViewLogo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/logo-fiat.png")));
                labelVw.setGraphic(imageViewLogo);
            }
            if (marca.getValue().equalsIgnoreCase("Wolksvagem")) {
                imageViewLogo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/vw-logo.png")));
                labelVw.setGraphic(imageViewLogo);

            }
            if (marca.getValue().equalsIgnoreCase("Ford")) {
                imageViewLogo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/logo-ford.gif")));
                labelVw.setGraphic(imageViewLogo);

            }
            if (marca.getValue().equalsIgnoreCase("Chevrolet")) {
                imageViewLogo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/logo-chevrolet.jpg")));
                labelVw.setGraphic(imageViewLogo);

            }

        });

        btnCadastrados.setOnAction(e -> {
            try {
                new ModeloTabelaCarro().start(new Stage());
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        });

        btnSair.setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacao");
            alert.setHeaderText("Projeto integrador diz:");
            alert.setContentText("Dejesa Realmente Sair?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        });

        datePickerTeste.setOnAction(e -> {
            alteraData();
        });

        labelImageAlerta.setOnMouseClicked(e -> {
            new Instrucao().start(new Stage());
        });
    }

    private String alteraData() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        LocalDate dataDevolucao = datePickerTeste.getValue();

        Calendar devolucao = Calendar.getInstance();

        devolucao.set(dataDevolucao.getYear(), dataDevolucao.getMonthValue(), dataDevolucao.getDayOfMonth());

        Date teste = devolucao.getTime();

        return dateFormat.format(teste);
    }

    private void limparCampos() {

        textFieldModelo.setText("");
        textFieldPlaca.setText("");
        textFieldValorHora.setText("");
        textFieldModelo.requestFocus();
    }

    private void initTimeline() {

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(labelImageAlerta.opacityProperty(), 0.0);
        KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    private void habilitaMenuEMenuItem() {

        Principal.menuHistorico.setDisable(false);
        Principal.menuItemAluguelRecebido.setDisable(false);
        Principal.menuManutencao.setDisable(false);
        Principal.menuRetornoVeiculo.setDisable(false);
        Principal.menuItemNovoCliente.setDisable(false);
        Principal.menuVendas.setDisable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
