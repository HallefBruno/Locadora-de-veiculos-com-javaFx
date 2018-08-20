package utilitario;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FormataTelefone {

	public void formatarTelefone (TextField textFieldTelefone) {

		textFieldTelefone.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
	        String mascara = "##-#####-####";
	        String alphaAndDigits = textFieldTelefone.getText().replaceAll("[\\-\\.]","");
	        StringBuilder resultado = new StringBuilder();
	        int i = 0;
	        int quant = 0;

	        if (number2.intValue() > number.intValue()) {
	            if (textFieldTelefone.getText().length() <= mascara.length()) {
	                while (i<mascara.length()) {
	                    if (quant < alphaAndDigits.length()) {
	                        if ("#".equals(mascara.substring(i,i+1))) {
	                            resultado.append(alphaAndDigits.substring(quant,quant+1));
	                            quant++;
	                        } else {
	                           resultado.append(mascara.substring(i,i+1));
	                        }
	                    }
	                i++;
	                }
	                textFieldTelefone.setText(resultado.toString());
	            }
	            if (textFieldTelefone.getText().length() > mascara.length()) {
	            	textFieldTelefone.setText(textFieldTelefone.getText(0,mascara.length()));
	            }
	        }
	    });
	}
}
