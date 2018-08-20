package visao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.CountryData;
import utilitario.ControleArquivo;
import utilitario.DataHora;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

public class MandarParaManutencao extends Application {

    private CheckBox checkBox;
    private Timeline timelineClique;
    private KeyValue keyValueClique;
    private KeyFrame keyFrame;
    private InnerShadow sair, regis;
    private AnchorPane anchorPane;
    private TextArea textAreaDescricao;
    private TableView<CountryData> tableView;
    private Button btnRegistrar;
    private Button btnSair;
    private Label labelTeste;
    private Label labelMensagem;
    private Label labelAlerta;
    private String trasModelo = "";
    private String compara = "";
    private String causa;
    private String carro;
    private Alert alert, alert1;
    private static Stage stage;
    private TextInputDialog dialog;
    private Alert alertAtencao;

    ControleArquivo controleArquivo = new ControleArquivo();

    public void start(Stage stage) {

        if (!controleArquivo.trasCarroManutencao().isEmpty()) {
            alertAtencao = new Alert(Alert.AlertType.INFORMATION);
            alertAtencao.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
            alertAtencao.setTitle("Atencao");
            alertAtencao.setHeaderText("Voce possui em seu cadastro somente um carro\ne este ja esta na oficina!");
            alertAtencao.show();
            stage.close();

        } else {

            MandarParaManutencao.stage = stage;
            carregarTabela();
            anchorPane = new AnchorPane();
            tableView.setPrefSize(476, 150);
            inicializarComponentes();
            definirEventos();
            Scene scene = new Scene(anchorPane);
            anchorPane.setPrefSize(400, 415);
            scene.getStylesheets().add("/utilitario/Botao.css");
            stage.setScene(scene);
            stage.setTitle("Incluir veiculo para manutencao");
            stage.show();
            layout();
            initTimelineDuploClique();
        }
    }

    private void inicializarComponentes() {

        sair = new InnerShadow(5, Color.RED);
        regis = new InnerShadow(10, Color.GREEN);
        anchorPane.setStyle("-fx-background-color: WHITE");
        textAreaDescricao = new TextArea();
        textAreaDescricao.setPrefSize(476, 180);
        textAreaDescricao.setFont(new Font(14));
        textAreaDescricao.setStyle("-fx-text-fill: BLUE");
        textAreaDescricao.setEffect(new InnerShadow(5, Color.BLUE));
        textAreaDescricao.setEditable(false);

        btnRegistrar = new Button("Registrar", new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-manutencao.png"))));
        btnRegistrar.getStyleClass().add("btnRegistro");
        btnRegistrar.setEffect(regis);
        btnRegistrar.setDisable(true);

        btnSair = new Button("Sair", new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
        btnSair.getStyleClass().add("btnSair");
        btnSair.setEffect(sair);

        labelTeste = new Label("Maximo de 100 caractere.");
        labelTeste.setStyle("-fx-background-color: AQUAMARINE");

        labelMensagem = new Label("* Faca um breve descricao sobre o defeito.");
        labelMensagem.setStyle("-fx-text-fill: red");
        labelMensagem.setVisible(false);

        labelAlerta = new Label("De duplo clike sobre o carro!");
        labelAlerta.setFont(new Font("serif", 10));
        labelAlerta.setStyle("-fx-text-fill: green");

        checkBox = new CheckBox("Foi acidente?");
        checkBox.setStyle("-fx-background-color: AQUAMARINE");
        checkBox.setVisible(false);

        anchorPane.getChildren()
                .addAll(
                        tableView,
                        textAreaDescricao,
                        btnRegistrar,
                        btnSair,
                        labelTeste,
                        labelMensagem,
                        labelAlerta,
                        checkBox);
    }

    private void layout() {

        //textAreaDescricao.setLayoutX((anchorPane.getWidth() - textAreaDescricao.getWidth())/0);
        textAreaDescricao.setLayoutY(180);

        //btnRegistrar.setLayoutX((anchorPane.getWidth() - btnRegistrar.getWidth())/9);
        btnRegistrar.setLayoutY(370);
        btnRegistrar.setPrefSize(90, 32);

        btnSair.setLayoutX((anchorPane.getWidth() - btnSair.getWidth()) / 4);
        btnSair.setLayoutY(370);

        labelMensagem.setLayoutX((anchorPane.getWidth() - labelMensagem.getWidth()) / 1.3);
        labelMensagem.setLayoutY(380);

        labelTeste.setLayoutY(160);

        labelAlerta.setLayoutX((anchorPane.getWidth() - labelAlerta.getWidth()) / 2);
        labelAlerta.setLayoutY(5);

        checkBox.setLayoutX((anchorPane.getWidth() - checkBox.getWidth()) / 1);
        checkBox.setLayoutY(160);

    }

    private void definirEventos() {

        tableView.setOnMouseClicked(e -> {

            if (e.getClickCount() == 2) {
                textAreaDescricao.setText("" + tableView.getSelectionModel().getSelectedItem().getCountry());
                textAreaDescricao.setEditable(true);
                btnRegistrar.setDisable(false);
                carro = String.valueOf(tableView.getSelectionModel().getSelectedItem().getCountry());
                timelineClique.stop();
                labelAlerta.setVisible(false);
                labelMensagem.setVisible(true);
                checkBox.setVisible(true);
            }
        });

        checkBox.setOnAction(e -> {

            if (checkBox.isSelected()) {
                labelMensagem.setText("* Faca uma breve descricao sobre o acidente");
            } else {
                labelMensagem.setText("* Faca um breve descricao sobre o defeito.");
            }
        });

        textAreaDescricao.setOnKeyPressed(e -> {

            labelTeste.setText("" + textAreaDescricao.getLength());

            if (textAreaDescricao.getLength() == 0) {
                labelTeste.setText("Maximo de 100 caractere.");
                btnRegistrar.setDisable(true);
                timelineClique.play();
                labelAlerta.setVisible(true);
            }
            if (textAreaDescricao.getLength() == 90) {
                textAreaDescricao.setEditable(false);
            }
            if (!textAreaDescricao.getText().equals("")) {
                btnRegistrar.setDisable(false);
            }
            causa = textAreaDescricao.getText();

        });

        btnRegistrar.setOnAction(e -> {

            if ((!controleArquivo.verificaAluguel().isEmpty() && controleArquivo.trasFormaPagamento().equals("1") && checkBox.isSelected())) {
                alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
                alert1.setTitle("Atencao");
                alert1.setHeaderText("Deseja continuar a operacao:");
                alert1.setContentText("Este carro esta alugado para o (a) cliente:\n" + controleArquivo.habilitaAluguel()
                        + "\nData de entrada " + controleArquivo.trasDataEntrada()
                        + "\nData de devolucao " + controleArquivo.trasDataDevolucao()
                        + "\nDias de posse " + controleArquivo.trasPosse()
                        + "\nA opcao acidente foi selecionada"
                        + "\nA forma de pagamento nao foi atraves de cartao"
                        + "\nNeste caso sera cobrado do cliente o valor da diaria de R$ " + controleArquivo.trasValorDiaria() + " +  o valor de calcao de R$ " + controleArquivo.trasValorSeguro());
                Optional<ButtonType> result1 = alert1.showAndWait();

                if (result1.get() == ButtonType.OK) {

                    dialog = new TextInputDialog();
                    dialog.setTitle("Permissao");
                    dialog.setHeaderText("Projeto integrador diz:");
                    dialog.setContentText("Digite sua senha de super usuario");
                    Optional<String> senha = dialog.showAndWait();
                    if (senha.get().equals("admin")) {
                        new ControleArquivo().gravaCarroManutencao(causa + "," + "Entrada:" + new DataHora().mostraData());
                        alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Registro salvo com sucesso!");
                        alert1.show();
                    } else {
                        alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Senha incorreta!");
                        alert1.show();
                    }
                } else if (result1.get() == ButtonType.CANCEL) {
                    alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("Operacao cancelada!");
                    alert1.show();
                }

            }
            if ((!controleArquivo.verificaAluguel().isEmpty() && controleArquivo.trasFormaPagamento().equals("2") && checkBox.isSelected() == false)) {
                alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/Cancelar.png"))));
                alert1.setTitle("Atecao");
                alert1.setHeaderText("Deseja continuar a operacao:");
                alert1.setContentText("Este carro esta alugado para o 'a' cliente:\n" + controleArquivo.habilitaAluguel()
                        + "\nData de entrada " + controleArquivo.trasDataEntrada()
                        + "\nData de devolucao " + controleArquivo.trasDataDevolucao()
                        + "\nDias de posse " + controleArquivo.trasPosse()
                        + "\nA opcao acidente nao foi selecionada"
                        + "\nA forma de pagamento foi atravez de cartao"
                        + "\nNeste caso sera cobrado do cliente o valor da diaria de " + controleArquivo.trasValorDiaria());
                Optional<ButtonType> result1 = alert1.showAndWait();
                if (result1.get() == ButtonType.OK) {

                    dialog = new TextInputDialog();
                    dialog.setTitle("Permissao");
                    dialog.setHeaderText("Projeto integrador diz:");
                    dialog.setContentText("Digite sua senha de super usuario");
                    Optional<String> senha = dialog.showAndWait();
                    if (senha.get().equals("admin")) {
                        new ControleArquivo().gravaCarroManutencao(causa + "," + "Entrada:" + new DataHora().mostraData());
                        alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Registro salvo com sucesso!");
                        alert1.show();
                    } else {
                        alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Senha incorreta!");
                        alert1.show();
                    }
                }
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png"))));
                alert.setTitle("Confirmacao de registro");
                alert.setHeaderText("Projeto integrador diz:");
                alert.setContentText("Deseja salvar o registro?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    new ControleArquivo().gravaCarroManutencao(causa + "," + "Entrada:" + new DataHora().mostraData());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Registro salvo com sucesso!");
                    alert.show();
                    textAreaDescricao.setText("");
                    btnRegistrar.setDisable(true);
                }

            }
        });
        btnSair.setOnAction(e -> {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacao");
            alert.setHeaderText("Projeto integrador diz:");
            alert.setContentText("Deseja realmente sair?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        });
    }

    @SuppressWarnings({"unused", "unchecked"})
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

    private String verificaCarro() {

        String caminho = "c:/Temp/ModeloCarro" + ".csv";

        BufferedReader bufferedModelo;
        try {
            bufferedModelo = new BufferedReader(new FileReader(caminho));
            while ((trasModelo = bufferedModelo.readLine()) != null) {
                compara = compara + trasModelo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compara;
    }

    public void initTimelineDuploClique() {

        timelineClique = new Timeline();
        keyValueClique = new KeyValue(labelAlerta.opacityProperty(), 0.0);
        keyFrame = new KeyFrame(Duration.millis(1000), keyValueClique);
        timelineClique.getKeyFrames().add(keyFrame);
        timelineClique.setCycleCount(Timeline.INDEFINITE);
        timelineClique.setAutoReverse(true);
        timelineClique.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
