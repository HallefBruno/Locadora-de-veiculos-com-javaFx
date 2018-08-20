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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.CountryData;
import utilitario.ControleArquivo;

public class RetirarDaManutencao extends Application {

    ControleArquivo arquivo = new ControleArquivo();

    private TableView<CountryData> tableView;
    private AnchorPane anchorPane;
    private Button btnRetirarManutencao;
    private InnerShadow innerShadow;
    private Alert alert;


    public void start(Stage stage) {

        if(arquivo.trasCarroManutencao().isEmpty()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atencao");
            alert.setHeaderText("Voce nao possue carro em manutencao!");
            alert.show();
        }
        else {

            carregarTabela();
            anchorPane = new AnchorPane();
            tableView.setPrefSize(400, 150);
            inicializarComponentes();
            definirEventos();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Retirar veiculos da manutencao");
            scene.getStylesheets().add("/utilitario/Botao.css");
            anchorPane.setPrefSize(300, 250);
            anchorPane.setStyle("-fx-background-color: WHITE");
            stage.show();
            layout();
        }

    }

    private void inicializarComponentes() {

        innerShadow = new InnerShadow(5,Color.GREEN);
        btnRetirarManutencao = new Button("Retirar da manutencao", new ImageView(new Image(getClass().getResourceAsStream("/imagens/icone-manutencao.png"))));
        btnRetirarManutencao.getStyleClass().add("btnRegistro");
        btnRetirarManutencao.setEffect(innerShadow);
        btnRetirarManutencao.setDisable(true);

        anchorPane.getChildren()
        .addAll(tableView,btnRetirarManutencao);

    }

    private void carregarTabela() {
        Collection<CountryData> list;
        try {
            list = Files.readAllLines(new File("c:/Temp/ModeloCarro"+".csv").toPath()).stream()
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

    private void definirEventos() {

        tableView.setOnMouseClicked(e -> {

            if(e.getClickCount() == 2) {
                btnRetirarManutencao.setDisable(false);
            }
        });

        btnRetirarManutencao.setOnAction(e -> {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atencao");
            alert.setHeaderText("Deseja continuar com a operacao?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Altenticacao");
                dialog.setContentText("Digite sua senha de super usuario:");
                try{
                    Optional<String> senha = dialog.showAndWait();
                    if(senha.get().equals("admin")) {
                        //new ControleArquivo().delatarCarroManutencao();
                        arquivo.delatarCarroManutencao();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Atencao");
                        alert.setHeaderText("Carro retirado da manutencao com sucesso!");
                        alert.show();
                        tableView.setDisable(true);
                        btnRetirarManutencao.setDisable(true);
                    }
		            else {

                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Atencao");
                        alert.setHeaderText("Senha de super usuario incorreta!");
                        alert.show();
                    }

		            }catch (Exception erro) {

		                alert = new Alert(Alert.AlertType.INFORMATION);
		                alert.setTitle("Atencao");
		                alert.setHeaderText(erro.getMessage());
		                alert.show();
		            }

            }
        });

    }
    private void layout() {
        //btnRetirarManutencao.setLayoutX((anchorPane.getWidth() - btnRetirarManutencao.getWidth())/5);
        btnRetirarManutencao.setLayoutY(200);
    }
    public static void main(String[] args) {
        launch(args);
    }

}
