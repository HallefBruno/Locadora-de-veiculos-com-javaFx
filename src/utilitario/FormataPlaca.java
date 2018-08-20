package utilitario;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FormataPlaca {
	public void formatarPlaca (TextField textFieldPlaca) {

		textFieldPlaca.lengthProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
	        String mascara = "###-####";
	        String alphaAndDigits = textFieldPlaca.getText().replaceAll("[\\-\\.]","");
	        StringBuilder resultado = new StringBuilder();
	        int i = 0;
	        int quant = 0;

	        if (number2.intValue() > number.intValue()) {
	            if (textFieldPlaca.getText().length() <= mascara.length()) {
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
	                textFieldPlaca.setText(resultado.toString());
	            }
	            if (textFieldPlaca.getText().length() > mascara.length()) {
	            	textFieldPlaca.setText(textFieldPlaca.getText(0,mascara.length()));
	            }
	        }
	    });
	}
}
