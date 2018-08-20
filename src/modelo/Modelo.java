package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Modelo {

	private SimpleStringProperty modelo;
	private SimpleStringProperty cor;
	private SimpleStringProperty placa;
	private SimpleIntegerProperty anoFabricacao;


	public SimpleStringProperty getModelo() {
		return modelo;
	}
	public void setModelo(SimpleStringProperty modelo) {
		this.modelo = modelo;
	}
	public SimpleStringProperty getCor() {
		return cor;
	}
	public void setCor(SimpleStringProperty cor) {
		this.cor = cor;
	}
	public SimpleStringProperty getPlaca() {
		return placa;
	}
	public void setPlaca(SimpleStringProperty placa) {
		this.placa = placa;
	}
	public SimpleIntegerProperty getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(SimpleIntegerProperty anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}


}
