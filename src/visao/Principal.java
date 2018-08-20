package visao;

import utilitario.*;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Principal extends Application {

    private static Stage stage;
    private Scene scene;
    private AnchorPane anchorPane;
    private MenuBar menuBar;
    private Rectangle rectangle1 = new Rectangle(0, 0, 1600, 1);
    private Rectangle rectangle2 = new Rectangle(0, 1400, 1600, 1);
    private ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(2), rectangle1);
    private ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(2), rectangle2);
    public static Menu menuVendas = new Menu("Vendas"),
            menuCadastro = new Menu("Cadastros"),
            menuAluguel = new Menu("Alugar veiculo"),
            menuManutencao = new Menu("Manutecao de veiculos"),
            menuRetornoVeiculo = new Menu("Retorno de veiculo"),
            menuHistorico = new Menu("Historico"),
            menuSair = new Menu("Sair");

    public static MenuItem menuItemAluguel = new MenuItem("Alugar veiculo"),
            menuItemNovoCarro = new MenuItem("Novo Carro"),
            menuItemMandarCarroOficina = new MenuItem("Mandar carro para oficina"),
            menuItemRetirarCarroOficina = new MenuItem("Retirar carro da oficina"),
            menuItemAluguelRecebido = new MenuItem("Retorno de veiculo"),
            menuItemCarroCadastrado = new MenuItem("Carros cadastrados"),
            menuItemClienteCadastrado = new MenuItem("Clientes cadastrados"),
            menuItemCarrosAlugados = new MenuItem("veiculos alugados"),
            menuItemSair = new MenuItem("Sair da aplicacao"),
            menuItemVendas = new MenuItem("Vender veiculo"),
            menuItemNovoCliente = new MenuItem("Novo Cliente");

    private Label labelDate;
    private String habilita;

    ControleArquivo arquivo = new ControleArquivo();

    public Principal() {

        if (arquivo.habilitaNovoCli().isEmpty()) {
            menuAluguel.setDisable(true);
            menuHistorico.setDisable(true);
            menuManutencao.setDisable(true);
            menuVendas.setDisable(true);
            menuRetornoVeiculo.setDisable(true);
            menuItemNovoCliente.setDisable(true);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Principal.stage = primaryStage;
        inicializarComponentes();
        definirEventos();
        scene = new Scene(anchorPane);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Projeto Integrador");
        primaryStage.setScene(scene);
        primaryStage.show();
        layout();

    }

    private void inicializarComponentes() {

        anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url(/imagens/morena.jpg)");
        anchorPane.setPrefSize(500, 400);
        menuBar = new MenuBar();
        menuBar.setPrefSize(1025, 0);

        menuSair = new Menu("Sair");

        menuItemSair = new MenuItem("Sair do programa");
        labelDate = new Label(new DataHora().mostraData());

        menuBar.getMenus().addAll(menuCadastro, menuAluguel, menuManutencao, menuRetornoVeiculo, menuVendas, menuSair);
        menuCadastro.getItems().addAll(menuItemNovoCarro, menuItemNovoCliente, menuHistorico);
        menuHistorico.getItems().addAll(menuItemCarroCadastrado, menuItemClienteCadastrado, menuItemCarrosAlugados);
        menuAluguel.getItems().add(menuItemAluguel);
        menuManutencao.getItems().addAll(menuItemMandarCarroOficina, menuItemRetirarCarroOficina);
        menuRetornoVeiculo.getItems().add(menuItemAluguelRecebido);
        menuVendas.getItems().add(menuItemVendas);
        menuSair.getItems().add(menuItemSair);
        anchorPane.getChildren().addAll(menuBar, labelDate, rectangle1, rectangle2);

    }

    private void layout() {

        DataHora dataHora = new DataHora();
        //labelDate.setLayoutX((anchorPane.getWidth() - labelDate.getWidth()/0));
        labelDate.setLayoutY(520);
        labelDate.setFont(new Font("serif", 13));
        labelDate.setText("" + dataHora.mostraData());
    }

    private void definirEventos() {
        menuItemNovoCliente.setOnAction(e -> {
            try {
                new CadastroCliente().start(new Stage());

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        menuItemNovoCarro.setOnAction(e -> {
            try {
                new CadastroDeCarro().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        menuAluguel.setOnAction(e -> {
            try {
                new AluguelCarro().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        menuItemVendas.setOnAction(e -> {
            new VendaCarro().start(new Stage());
        });

        menuItemSair.setOnAction(e -> {

            rectangle1.setFill(Color.BLACK);
            rectangle2.setFill(Color.BLACK);
            scaleTransition1.setToY(1200);
            scaleTransition1.setCycleCount(1);
            scaleTransition1.play();
            scaleTransition2.setToY(-1200);
            scaleTransition2.setCycleCount(1);
            scaleTransition2.play();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sair do programa");
            alert.setHeaderText("Projeto integrador diz:");
            alert.setContentText("Deseja sair do programa?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                stage.close();
            } else {
                inicializarComponentes();
            }
        });

        menuItemAluguelRecebido.setOnAction(e -> {
            new RetornoVeiculo().start(new Stage());
        });

        menuItemMandarCarroOficina.setOnAction(e -> {
            new MandarParaManutencao().start(new Stage());
        });
        menuItemRetirarCarroOficina.setOnAction(e -> {
            new RetirarDaManutencao().start(new Stage());
        });
        menuItemCarroCadastrado.setOnAction(e -> {
            try {
                new ModeloTabelaCarro().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        menuItemClienteCadastrado.setOnAction(e -> {
            try {
                new ModeloTableCliente().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        menuItemCarrosAlugados.setOnAction(e -> {
            try {
                new CarrosAlugados().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
