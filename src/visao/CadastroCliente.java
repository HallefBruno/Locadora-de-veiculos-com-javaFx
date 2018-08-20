package visao;

import java.util.Optional;
import dao.CadastroDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Cliente;
import utilitario.ControleArquivo;
import utilitario.FormataCnh;
import utilitario.FormataTelefone;
import utilitario.FormatarCPF;

public class CadastroCliente extends Application {

    InnerShadow registro, cadastro, sair;
    private ImageView imageViewSair, imageViewNovo, imageViewRegistros;
    private Label labelMensagem;
    private Label labelNome;
    private Label labelIdade;
    private Label labelCpf;
    private Label labelCnh;
    private Label labelTelefone;
    private Label labelEndereco;
    private TextField textFieldNome;
    private TextField textFieldIdade;
    private TextField textFieldCpf;
    private TextField textFieldCnh;
    private TextField textFieldTelefone;
    private TextField textFieldEndereco;
    private Button btnRegistar;
    private Button btnSair;
    private Button btnTelaClienteRegistrados;
    private Stage stage;
    private Scene scene;
    private AnchorPane anchorPane;

    ControleArquivo controleArquivo = new ControleArquivo();

    public CadastroCliente() {
        if (!controleArquivo.habilitaAluguel().isEmpty()) {
            Principal.menuAluguel.setDisable(false);
            Principal.menuRetornoVeiculo.setDisable(false);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        inicializarComponentes();
        definirEventos();
        stage = primaryStage;
        scene = new Scene(anchorPane);
        scene.getStylesheets().add("/utilitario/BotaoFormulario.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setTitle("Cadastros de clientes");
        layout();
    }

    private void inicializarComponentes() {

        registro = new InnerShadow(5, Color.BLUE);
        cadastro = new InnerShadow(5, Color.GREEN);
        sair = new InnerShadow(5, Color.RED);

        imageViewSair = new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png")));
        imageViewNovo = new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png")));
        imageViewRegistros = new ImageView(new Image(getClass().getResourceAsStream("/imagens/lupa.png")));
        FormatarCPF formatarCPF = new FormatarCPF();
        FormataTelefone formataTelefone = new FormataTelefone();

        anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: WHITE");
        anchorPane.setPrefSize(500, 400);
        Font fonte = new Font("serif", 13);

        labelNome = new Label("NOME:");
        labelNome.setFont(fonte);

        labelIdade = new Label("IDADE:");
        labelIdade.setFont(fonte);

        labelCpf = new Label("CPF:");
        labelCpf.setFont(fonte);

        labelCnh = new Label("CNH:");
        labelCnh.setFont(fonte);

        labelTelefone = new Label("TELEFONE:");
        labelTelefone.setFont(fonte);

        labelEndereco = new Label("ENDERECO:");
        labelEndereco.setFont(fonte);

        labelMensagem = new Label("CADASTRO DE CLIENTE");
        labelMensagem.setFont(fonte);

        textFieldNome = new TextField();
        textFieldNome.setTooltip(new Tooltip("Nome do cliente"));
        textFieldIdade = new TextField();
        textFieldIdade.setTooltip(new Tooltip("Idade do cliente"));
        textFieldCpf = new TextField();
        textFieldCpf.setTooltip(new Tooltip("CPF do cliente"));
        formatarCPF.formatarCPF(textFieldCpf);
        textFieldCnh = new TextField();
        textFieldCnh.setTooltip(new Tooltip("CNH do cliente"));
        new FormataCnh().formatarCNH(textFieldCnh);
        textFieldTelefone = new TextField();
        textFieldTelefone.setTooltip(new Tooltip("Telefone do cliente"));
        formataTelefone.formatarTelefone(textFieldTelefone);
        textFieldEndereco = new TextField();
        textFieldEndereco.setTooltip(new Tooltip("Endereco do cliente"));

        btnRegistar = new Button("Novo Cliente", imageViewNovo);
        btnRegistar.setEffect(registro);
        btnRegistar.getStyleClass().add("btnEntrar");

        btnSair = new Button("Sair", imageViewSair);
        btnSair.setEffect(sair);
        btnSair.getStyleClass().add("btnSair");

        btnTelaClienteRegistrados = new Button("Registros", imageViewRegistros);
        btnTelaClienteRegistrados.setEffect(cadastro);
        btnTelaClienteRegistrados.getStyleClass().add("btnRegistro");

        anchorPane.getChildren().addAll(
                labelNome, labelIdade,
                labelCpf, labelCnh,
                labelTelefone, labelEndereco, labelMensagem,
                textFieldNome, textFieldIdade,
                textFieldCpf, textFieldCnh,
                textFieldTelefone, textFieldEndereco,
                btnRegistar, btnSair, btnTelaClienteRegistrados,
                imageViewNovo, imageViewRegistros, imageViewSair
        );

    }

    private void layout() {

        labelMensagem.setLayoutX((anchorPane.getWidth() - labelMensagem.getWidth()) / 2);
        labelMensagem.setLayoutY(10);

        labelNome.setLayoutX((anchorPane.getWidth() - labelNome.getWidth()) / 6);
        labelNome.setLayoutY(54);
        textFieldNome.setLayoutX((anchorPane.getWidth() - textFieldNome.getWidth()) / 2.5);
        textFieldNome.setLayoutY(50);
        textFieldNome.setPrefSize(250, 30);

        labelIdade.setLayoutX((anchorPane.getWidth() - labelIdade.getWidth()) / 6);
        labelIdade.setLayoutY(110);
        textFieldIdade.setLayoutX((anchorPane.getWidth() - textFieldIdade.getWidth()) / 2.5);
        textFieldIdade.setLayoutY(107);
        textFieldIdade.setPrefSize(250, 30);

        labelCpf.setLayoutX((anchorPane.getWidth() - labelCpf.getWidth()) / 6);
        labelCpf.setLayoutY(160);
        textFieldCpf.setLayoutX((anchorPane.getWidth() - textFieldCpf.getWidth()) / 2.5);
        textFieldCpf.setLayoutY(155);
        textFieldCpf.setPrefSize(250, 30);

        labelCnh.setLayoutX((anchorPane.getWidth() - labelCnh.getWidth()) / 6);
        labelCnh.setLayoutY(210);
        textFieldCnh.setLayoutX((anchorPane.getWidth() - textFieldCnh.getWidth()) / 2.5);
        textFieldCnh.setLayoutY(200);
        textFieldCnh.setPrefSize(250, 30);

        labelTelefone.setLayoutX((anchorPane.getWidth() - labelTelefone.getWidth()) / 6);
        labelTelefone.setLayoutY(260);
        textFieldTelefone.setLayoutX((anchorPane.getWidth() - textFieldTelefone.getWidth()) / 2.5);
        textFieldTelefone.setLayoutY(250);
        textFieldTelefone.setPrefSize(250, 30);

        labelEndereco.setLayoutX((anchorPane.getWidth() - labelEndereco.getWidth()) / 6);
        labelEndereco.setLayoutY(305);
        textFieldEndereco.setLayoutX((anchorPane.getWidth() - textFieldEndereco.getWidth()) / 2.5);
        textFieldEndereco.setLayoutY(300);
        textFieldEndereco.setPrefSize(250, 30);

        btnRegistar.setLayoutX((anchorPane.getWidth() - btnRegistar.getWidth()) / 5.7);
        btnRegistar.setLayoutY(350);
        btnRegistar.setPrefSize(115, 30);

        btnTelaClienteRegistrados.setLayoutX((anchorPane.getWidth() - btnRegistar.getWidth()) / 2.05);
        btnTelaClienteRegistrados.setLayoutY(350);
        btnTelaClienteRegistrados.setPrefSize(95, 30);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 1.47);
        btnSair.setLayoutY(350);
        btnSair.setPrefSize(90, 30);
    }

    private void definirEventos() {

        btnTelaClienteRegistrados.setOnAction(e -> {
            try {
                new ModeloTableCliente().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        btnSair.setOnAction(e -> {
            Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmacao");
            alert.setHeaderText("Projeto integrador diz:");
            alert.setContentText("Dejesa Realmente Sair?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }

        });

        btnRegistar.setOnAction(e -> {

            try {
                if (!(textFieldNome.getText().isEmpty() || textFieldIdade.getText().isEmpty() || textFieldCpf.getText().isEmpty() || textFieldTelefone.getText().isEmpty())) {
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
                    controleArquivo.gravaNomeCnh(textFieldNome.getText(), textFieldCnh.getText());
                    textFieldNome.setText("");
                    textFieldIdade.setText("");
                    textFieldCpf.setText("");
                    textFieldCnh.setText("");
                    textFieldTelefone.setText("");
                    textFieldEndereco.setText("");
                    textFieldNome.requestFocus();
                    Principal.menuAluguel.setDisable(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencao");
                    alert.setContentText("Preencha todos os campos!");
                    alert.show();
                    textFieldNome.setText("");
                    textFieldIdade.setText("");
                    textFieldCpf.setText("");
                    textFieldCnh.setText("");
                    textFieldTelefone.setText("");
                    textFieldEndereco.setText("");
                    textFieldNome.requestFocus();
                }

            } catch (Exception erro) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencao");
                alert.setContentText("Preencha todos os campos!");
                alert.show();

            }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
