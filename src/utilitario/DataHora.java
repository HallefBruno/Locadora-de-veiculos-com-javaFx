package utilitario;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DataHora {

	public String mostraData() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return dateFormat.format(date);
	}
	public Integer calculaData(DatePicker datePicker1, DatePicker datePicker2) {

		DateFormat dt = DateFormat.getDateInstance(DateFormat.MEDIUM);
		LocalDate data1 = datePicker1.getValue();
    	LocalDate data2 = datePicker2.getValue();
    	Period  periodo = Period.between(data1, data2);
    	String teste  = String.valueOf(periodo);
    	String teste1 =  teste.replace("D", "");
    	String teste2 = teste1.replace("P","");
    	Integer i = Integer.valueOf(teste2);
		return i;
	}
	public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }

	public String formatacaoMonetaria(TextField valor) {
		DecimalFormat decimalFormat = new DecimalFormat();
    	decimalFormat.applyPattern("R$ #,##0.00");
    	decimalFormat.format(valor);
    	return decimalFormat.format(valor);
	}


}
