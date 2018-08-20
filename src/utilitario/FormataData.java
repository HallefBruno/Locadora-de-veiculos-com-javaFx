package utilitario;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FormataData {

	public void formatarData (TextField textFieldData) {

		textFieldData.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
	        String mascara = "##-##-####";
	        String alphaAndDigits = textFieldData.getText().replaceAll("[\\-\\.]","");
	        StringBuilder resultado = new StringBuilder();
	        int i = 0;
	        int quant = 0;

	        if (number2.intValue() > number.intValue()) {
	            if (textFieldData.getText().length() <= mascara.length()) {
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
	                textFieldData.setText(resultado.toString());
	            }
	            if (textFieldData.getText().length() > mascara.length()) {
	            	textFieldData.setText(textFieldData.getText(0,mascara.length()));
	            }
	        }
	    });
	}
}
