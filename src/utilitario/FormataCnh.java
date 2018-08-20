package utilitario;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FormataCnh {
	public void formatarCNH (TextField textFieldCNH) {

		textFieldCNH.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
	        String mascara = "###########";
	        String alphaAndDigits = textFieldCNH.getText().replaceAll("[\\-\\.]","");
	        StringBuilder resultado = new StringBuilder();
	        int i = 0;
	        int quant = 0;

	        if (number2.intValue() > number.intValue()) {
	            if (textFieldCNH.getText().length() <= mascara.length()) {
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
	                textFieldCNH.setText(resultado.toString());
	            }
	            if (textFieldCNH.getText().length() > mascara.length()) {
	            	textFieldCNH.setText(textFieldCNH.getText(0,mascara.length()));
	            }
	        }
	    });
	}
}
