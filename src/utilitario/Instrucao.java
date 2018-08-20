package utilitario;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Instrucao extends Application {

	public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informacao importante");
    	alert.setHeaderText("Leia com  Atenção");
    	alert.setContentText("Clique em Mostrar Detalhes!");


    	TextArea textArea = new TextArea();
    	textArea.setText("Para uma melhor manipulacaodos dados do sistema será preciso que sigua algumas intruções de cadastro de modelo. Exemplo:\nCaso a Fabricante seja FIAT:Novo Uno 1.0 completo\nCaso a Fabricante seja Volkswagen:Saveiro 1.6 completo\nCaso a Fabricante seja FORD:Ford Ranger 2.2 completo\nCaso a Fabricante seja Chevolet:Cobalt LTZ 1.8 completo.\n\n\n* Os campos valor hora e valor de seguro, será habilitado no habito do aluguel.");
    	textArea.setEditable(false);
    	textArea.setWrapText(true);

    	textArea.setMaxWidth(Double.MAX_VALUE);
    	textArea.setMaxHeight(Double.MAX_VALUE);
    	GridPane.setVgrow(textArea, Priority.ALWAYS);
    	GridPane.setHgrow(textArea, Priority.ALWAYS);

    	GridPane expContent = new GridPane();
    	expContent.setMaxWidth(Double.MAX_VALUE);
    	//expContent.add(label, 0, 0);
    	expContent.add(textArea, 0, 1);

    	alert.getDialogPane().setExpandableContent(expContent);

    	alert.showAndWait();
    }
}
