package visao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application {

    private AnchorPane pane;
    private Label labelMensagem;
    private Button btnLogar;
    private Button btnSair;
    private TextField textFieldLogin;
    private PasswordField password;
    private static Stage stage;
    private Integer i = 1;
    private DropShadow usuario, senha;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        inicializarComponentes();
        definirEventos();
        Login.stage = stage;
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/utilitario/Login.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
        initLayout();
        definirEventos();

    }

    public static Stage getStage() {
        return stage;
    }

    private void inicializarComponentes() {

        pane = new AnchorPane();
        textFieldLogin = new TextField();
        labelMensagem = new Label("Tela de acesso ao sistema");
        textFieldLogin.setPromptText("Digite seu Usuario");
        password = new PasswordField();
        password.setPromptText("Digite sua senha");
        btnLogar = new Button("Logar ");
        btnSair = new Button("Sair   ");
        pane.getChildren().addAll(textFieldLogin, password, btnLogar, btnSair, labelMensagem);

    }

    private void initLayout() {
        usuario = new DropShadow(5, Color.CADETBLUE);
        senha = new DropShadow(5, Color.CADETBLUE);

        textFieldLogin.setPrefSize(300, 50);
        usuario.setSpread(0.3);
        textFieldLogin.setEffect(usuario);
        textFieldLogin.setFont(new Font("serif", 15));
        textFieldLogin.setLayoutX((pane.getWidth() - textFieldLogin.getWidth()) / 2.3);
        textFieldLogin.setLayoutY(100);

        password.setPrefSize(300, 50);
        senha.setSpread(0.3);
        password.setEffect(senha);
        password.setLayoutX((pane.getWidth() - password.getWidth()) / 2.3);
        password.setFont(new Font("serif", 15));
        password.setLayoutY(180);

        btnLogar.setFont(new Font("serif", 15));
        btnLogar.setPrefSize(300, 50);
        btnLogar.setLayoutX((pane.getWidth() - btnLogar.getWidth()) / 2.56);
        btnLogar.setLayoutY(250);

        btnSair.setFont(new Font("serif", 15));
        btnSair.setPrefSize(300, 50);
        btnSair.setLayoutX((pane.getWidth() - btnSair.getWidth()) / 2.56);
        btnSair.setLayoutY(325);

        labelMensagem.setLayoutX((pane.getWidth() - labelMensagem.getWidth()) / 2);
        labelMensagem.setLayoutY(20);
    }

    private void definirEventos() {

        btnLogar.setOnAction(e -> {

            if (textFieldLogin.getText().equalsIgnoreCase("admin") && password.getText().equalsIgnoreCase("admin")) {

                try {
                    new Principal().start(new Stage());
                    Login.getStage().close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                i = 1;
            } else if (i == 2) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Lhe resta mais uma chance ");
                alert.showAndWait();
            } else if (i == 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("O sistema sera fechado, tente novamente.");
                alert.showAndWait();
                stage.close();
            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Usuario ou senha incorretos.");
                alert.showAndWait();
            }

            ++i;

        });

        btnSair.setOnAction(e -> {
            stage.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
