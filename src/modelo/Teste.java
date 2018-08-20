package modelo;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Teste extends Application {

	private AnchorPane anchorPane;

	public void start(Stage stage) {

		anchorPane = new AnchorPane();
		anchorPane.setStyle("-fx-background-color: BLACK");
		Scene scene = new Scene(anchorPane);
		stage.setScene(scene);
		inicializarComponentes();
		stage.show();

	}

	private void inicializarComponentes() {

		Rectangle rectangle1 = new Rectangle(0,0,400,1);
		rectangle1.setFill(Color.WHITE);

		Rectangle rectangle2 = new Rectangle(0,399,400,1);
		rectangle2.setFill(Color.WHITE);

		ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(3),rectangle1);
		scaleTransition1.setToY(400);
		scaleTransition1.setCycleCount(1);
		scaleTransition1.play();

		ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(3),rectangle2);
		scaleTransition2.setToY(-400);
		scaleTransition2.setCycleCount(1);
		scaleTransition2.play();

		Button button = new Button("Jesus");
		button.setLayoutY(200);
		button.setLayoutX(-40);

		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1),button);
		rotateTransition.setByAngle(360);
		rotateTransition.setDelay(Duration.seconds(1));
		rotateTransition.setRate(10);
		rotateTransition.setCycleCount(10);
		rotateTransition.play();
		TranslateTransition transition = new TranslateTransition(Duration.seconds(1),button);
		transition.setDelay(Duration.seconds(1));
		transition.setToX(210);
		transition.setCycleCount(1);
		transition.play();

		anchorPane.getChildren().addAll(rectangle1,rectangle2,button);

	}

	public static void main(String[] args) {
		launch(args);
	}
}

	/*
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com"));
    final HBox hb = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
             new Callback<TableColumn, TableCell>() {
                 public TableCell call(TableColumn p) {
                    return new EditingCell();
                 }
             };

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                }
             }
        );


        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                }
            }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                }
            }
        );

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Person(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()));
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }

    class EditingCell extends TableCell<Person, String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}

   /* private WebEngine webengine;
    private static WebView webview;

    private void publishServices() {



        try {
            webview = new WebView();
            webview.setVisible(true);
            webengine = webview.getEngine();
            webengine.setJavaScriptEnabled(true);
            File file = new File("c:/Users/Brno/Documents/ProjetosFX/Test_App/src/utilitario/TermoDeContrato.html");
            System.out.println(file.exists() + " file exitence");
            webengine.load(file.toURI().toURL().toString());
        } catch (Exception ex) {
            System.err.print("error " + ex.getMessage());
            ex.printStackTrace();
        }




    }

}

Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Informacao importante");
alert.setHeaderText("Leia com bastante atenção");
alert.setContentText("Clique em Mostrar detalhes!");


TextArea textArea = new TextArea();
textArea.setText("Para uma melhor manipulacaodos dados do sistema serápreciso que sigua algumasintruções de cadastro de modelo. Exemplo:Caso a Fabricante seja FIAT:       Palio  dua porta branco completoCaso a Fabricante seja Volkswagen: Gol G4 dua portas preto completoCaso a Fabricante seja FORD: 	   Ford Ka quatro portas prata completoCaso a Fabricante seja Chevolet:   Cobalt LTZ 1.8 quatro portas completo");
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

// Set expandable Exception into the dialog pane.
alert.getDialogPane().setExpandableContent(expContent);

alert.showAndWait();
}


private void initUI() {
VBox vbox = new VBox(20);
vbox.setStyle("-fx-padding: 10;");
Scene scene = new Scene(vbox, 400, 400);
stage.setScene(scene);

datePicker1 = new DatePicker();
datePicker2 = new DatePicker();
btn = new Button("Calculo");

GridPane gridPane = new GridPane();
gridPane.setHgap(10);
gridPane.setVgap(10);

Label checkInlabel = new Label("Check-In Date:");
gridPane.add(checkInlabel, 0, 0);

GridPane.setHalignment(checkInlabel, HPos.LEFT);
gridPane.add(datePicker1, 0, 1);

GridPane.setHalignment(checkInlabel, HPos.LEFT);
gridPane.add(datePicker2, 0, 2);

GridPane.setHalignment(checkInlabel, HPos.LEFT);
gridPane.add(btn, 0, 3);


vbox.getChildren().add(gridPane);
}
private void definirEventos() {
btn.setOnAction(e -> {
	DateFormat dt = DateFormat.getDateInstance(DateFormat.MEDIUM);
	LocalDate data1 = datePicker1.getValue();
	LocalDate data2 = datePicker2.getValue();
	Period  periodo = Period.between(data1, data2);
	String teste  = String.valueOf(periodo);
	String teste1 =  teste.replace("D", "");
	String teste2 = teste1.replace("P","");
	int i = Integer.parseInt(teste2);
	System.out.println(i+3);
});


LocalDate ld = datePicker.getValue();
  Calendar c =  Calendar.getInstance();
  c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
  Date date = c.getTime();

  private void initTimeline() {
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(imgItem.opacityProperty(), 0.0);
		KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
		timeline.getKeyFrames().add(kf);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}
	TextInputDialog dialog = new TextInputDialog("walter");
	dialog.setTitle("Text Input Dialog");
	dialog.setHeaderText("Look, a Text Input Dialog");
	dialog.setContentText("Please enter your name:");

	// Traditional way to get the response value.
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
    	System.out.println("Your name: " + result.get());
}
  */