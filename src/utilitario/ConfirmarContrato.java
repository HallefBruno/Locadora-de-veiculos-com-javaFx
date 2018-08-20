package utilitario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfirmarContrato extends Application {

    private AnchorPane anchorPane;
    private TextArea textArea;
    private String nomeCli;
    private String cnhCli;
    private String dataContrato;

    public ConfirmarContrato(String nome, String cnh, String data) {
        this.nomeCli = nome;
        this.cnhCli = cnh;
        this.dataContrato = data;
    }

    public void start(Stage stage) {
        inicializarComponentes();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    private void inicializarComponentes() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(755, 500);
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(755, 500);
        anchorPane.getChildren().add(textArea);
        termosDoContrato(nomeCli, cnhCli, dataContrato);
    }

    private void termosDoContrato(String nome, String cnh, String dta) {
        nomeCli = nome;
        cnhCli = cnh;
        dataContrato = dta;
        textArea.setFont(new Font("serif", 15));
        textArea.setText("\n				    	TERMOS E CONDICOES GERAIS DE LOCACAO DE VEICULOS\n\n\n		Pelo presente instrumento particular de Termos e Condicoes Gerais de Locacao de Veiculos\n		doravante designado simplesmente CONTRATO DE LOCACAO LOCADORA E LOCATORIO\n		especificados nos respectivos campos do CONTRATO DE LOCACAO mutuamente acordam as\n		seguintes clausulas, termos e condicoes que contemplam os direitos, responsabilidades e obrigacoes\n		das Partes em relacao e locacao de veiculos, diarias contratadas e situacoes decorrentes desse\n		processo. Eu " + nome + " portador da CNH " + cnh + " estou ciente.\n\n		Assinatura do cliente___________________________________________" + dataContrato);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
