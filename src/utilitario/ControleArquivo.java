package utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Alert;

public class ControleArquivo {

    private static Alert alert;

    public static void gravaModeloCarro(String modeloCarro) {

        String registro = modeloCarro;
        String caminho = "c:/Temp/ModeloCarro" + ".csv";

        if (modeloCarro != null && !modeloCarro.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
        }

    }

    public void CompartilhadoCarro(String modeloCarro) {

        String registro = modeloCarro;
        String caminho = "c:/Temp/CompartilhadoCarro" + ".csv";

        if (modeloCarro != null && !modeloCarro.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
        }

    }

    public void CompartilhadoCliente(String cliente) {

        String registro = cliente;
        String caminho = "c:/Temp/CompartilhadoCliente" + ".csv";

        if (cliente != null && !cliente.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
        }

    }

    public void gravaCarroManutencao(String causa) {
        String caminho = "c:/Temp/CarroManutencao" + ".csv";
        if (causa != null && !causa.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(causa);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
        }
    }

    public static void gravaPrecoModelo(String precoCarro, String modelo) {
        String registro = precoCarro + " " + modelo;
        String caminho = "c:/Temp/PrecoModelo" + ".csv";

        if (registro != null && !registro.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
        }
    }

    public void gravaNomeCnh(String nome, String cnh) {

        String registro = nome + "," + cnh;
        String caminho = "c:/Temp/NomeCnhCliente" + ".csv";

        if (registro != null && !registro.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }
    }

    public static void gravarPreco(String valor) {

        String caminho = "c:/Temp/PrecoHora" + ".csv";
        if (valor != null && !valor.isEmpty()) {
            try {

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(valor);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }
    }

    public void gravarDadosContrato(String registro) {
        if (registro != null && !registro.isEmpty()) {
            try {
                String caminho = "c:/Temp/DadosContrato" + ".csv";

                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }
    }

    public void gravaValorSeguro(String registro) {

        String caminho = "c:/Temp/GravaValorSeguro" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public void gravaFormaPagamento(String registro) {

        String caminho = "c:/Temp/GravarFormaPagamento" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public void gravaDataEntrada(String registro) {

        String caminho = "c:/Temp/GravarDataEntrada" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public void gravaDataDevolucao(String registro) {

        String caminho = "c:/Temp/GravarDataDevolucao" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public void gravaPosse(String registro) {

        String caminho = "c:/Temp/GravarPosse" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public void gravaValorDiaria(String registro) {

        String caminho = "c:/Temp/GravarValorDiaria" + ".csv";
        if (registro != null && !registro.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(caminho);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(registro);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setContentText("Preencha todos os campos");
            alert.show();
        }

    }

    public String trasValorDiaria() {

        String caminho = "c:/Temp/GravarValorDiaria" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasDataEntrada() {

        String caminho = "c:/Temp/GravarDataEntrada" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasDataDevolucao() {

        String caminho = "c:/Temp/GravarDataDevolucao" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasPosse() {

        String caminho = "c:/Temp/GravarPosse" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasFormaPagamento() {

        String caminho = "c:/Temp/GravarFormaPagamento" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasValorSeguro() {

        String caminho = "c:/Temp/GravaValorSeguro" + ".csv";
        String dados = "";
        String t = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    public String trasPrecoModelo() {

        String caminho = "c:/Temp/PrecoModelo" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedModelo;
        try {
            bufferedModelo = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedModelo.readLine()) != null) {
                t = t + dados;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return t;
    }

    public String habilitaNovoCli() {

        String caminho = "c:/Temp/Carro" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedModelo;
        try {
            bufferedModelo = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedModelo.readLine()) != null) {
                t = t + dados;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return t;
    }

    public String habilitaAluguel() {

        String caminho = "c:/Temp/NomeCnhCliente" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public String verificaAluguel() {

        String caminho = "c:/Temp/DadosContrato" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public String trasCarroManutencao() {

        String caminho = "c:/Temp/CarroManutencao" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public String trasCarro() {

        String caminho = "c:/Temp/Carro" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public void delatarCarroManutencao() {

        File file = new File("c:/Temp/CarroManutencao" + ".csv");
        file.delete();
    }

    public String trasCompartilhadoCliente() {
        String caminho = "c:/Temp/CompartilhadoCliente" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public String trasContrato() {
        String caminho = "c:/Temp/DadosContrato.csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedReader.readLine()) != null) {
                t = t + dados;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    public static void main(String[] args) {
        String caminho = "c:/Temp/PrecoModelo" + ".csv";
        String dados = "";
        String t = "";
        BufferedReader bufferedModelo;
        try {
            bufferedModelo = new BufferedReader(new FileReader(caminho));
            while ((dados = bufferedModelo.readLine()) != null) {
                t = t + dados;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println(t);
    }
}
