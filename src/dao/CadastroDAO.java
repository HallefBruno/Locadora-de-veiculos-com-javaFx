package dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;

import javax.swing.JOptionPane;
import contratos.InterfaceDAO;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastroDAO extends Application implements InterfaceDAO {

	private Alert alert;

	public void start(Stage stage) {

	}

	public void gravar(String registro,String caminho) {

		alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Escolha uma opcao");
		alert.setHeaderText("Projeto integrador diz:");
		alert.setContentText("Deseja salvar o registro.");
		alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagens/file-txt.png"))));
		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() == ButtonType.OK) {

			try{
				if(!registro.isEmpty()&& registro!= null) {
					FileWriter fileWriter = new FileWriter(caminho+".csv",true);
					PrintWriter printWriter = new PrintWriter(fileWriter);
					printWriter.println(registro);
					printWriter.close();

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Mensagem de confirmacao");
					alert.setContentText("Registro salvo com sucesso");
					alert.showAndWait();
				}
				else {

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Atenção");
					alert.setContentText("Preencha todos os campos!");
					alert.showAndWait();
				}


			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}
	public static void main(String[] args) {
		launch(args);
	}
}

/*
 String escola[] = {"Confirmar","Cancelar"};
	int resp = JOptionPane.showOptionDialog(null, "Projeto Integrador diz:\n\nDeseja salvar o novo registro", "Mensagem", 0, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:/Users/Brno/Downloads/JOptionPane.png"), escola, escola[0]);

	if(resp == 0) {

 		public void alterar(String registro,String caminho) {
			try{
			String escola[] = {"Confirmar","Cancelar"};
			int resp = JOptionPane.showOptionDialog(null, "Projeto Integrador diz:\n\nDeseja alterar o registro?", "Mensagem", 0, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:/Users/Brno/Downloads/JOptionPane.png"), escola, escola[0]);

			if(resp == 0) {

				File file = new File(caminho+".csv");
				file.delete();
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!\n\nFeche a tela e abra novamente!");

				FileWriter fileWriter = new FileWriter(caminho+".csv",false);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.println(registro);
				printWriter.close();

			}

		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
 */
