package dad.cambioDivisa;

import java.text.DecimalFormat;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application{
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra", 0.8873);
	Divisa dolar = new Divisa("Dolar", 1.2007);
	Divisa yen = new Divisa("Yen", 133.59);
	Divisa origen;
	Divisa destino;
	private TextField aCambiarText,resultadoText;
	private ComboBox<String> aCambiarBox,resultadoBox;
	private Button cambiarButton;
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		aCambiarText=new TextField();
		aCambiarText.setPromptText("Cantidad a cambiar");
		aCambiarText.setPrefColumnCount(10);
		
		aCambiarBox=new ComboBox<String>();
		aCambiarBox.getItems().addAll("Euro", "Libra", "Dolar","Yen");
		aCambiarBox.setPromptText("Moneda");
		
		
		resultadoText=new TextField();
		resultadoText.setPromptText("Resultado");
		resultadoText.setPrefColumnCount(10);
		resultadoText.cancelEdit();
		
		resultadoBox=new ComboBox<String>();
		resultadoBox.getItems().addAll("Euro", "Libra", "Dolar","Yen");
		resultadoBox.setPromptText("Moneda");
		
		cambiarButton =new Button();
		cambiarButton.setText("Cambiar");
		cambiarButton.setOnAction(e -> onLoginButtonAction(e));
		
		HBox root1 =new HBox(5, aCambiarText, aCambiarBox);
		root1.setAlignment(Pos.CENTER);
		
		HBox root2 =new HBox(5, resultadoText, resultadoBox);
		root2.setAlignment(Pos.CENTER);

		VBox root =new VBox(5,root1,root2,cambiarButton);
		root.setAlignment(Pos.CENTER);	
		Scene scene=new Scene(root,320,200);
		
		primaryStage.setTitle("CambioDivisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void onLoginButtonAction(ActionEvent e) {
		try {
			double cantidad = Double.parseDouble(aCambiarText.getText());
			
			String aCambiar = aCambiarBox.getSelectionModel().getSelectedItem();
			String resultadoMoneda = resultadoBox.getSelectionModel().getSelectedItem();
			DecimalFormat df = new DecimalFormat("#.00");
			switch(aCambiar) {
				case "Euro":
					switch(resultadoMoneda) {
						case "Euro":
							resultadoText.setText(aCambiarText.getText());
							break;
						case "Libra":
							origen=euro;
							destino=libra;
							//resultadoText.setText(String.valueOf((double)((int)(destino.fromEuro(origen.toEuro(cantidad))*100)/100)));
							 
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
						case "Dolar":
							origen=euro;
							destino=dolar;
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
						case "Yen":
							origen=euro;
							destino=yen;
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
					}
					break;
				case "Libra":
					switch(resultadoMoneda) {
						case "Euro":
							origen=libra;
							destino=euro;
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
						case "Libra":
							
							resultadoText.setText(aCambiarText.getText());
							break;
						case "Dolar":
							origen=libra;
							destino=dolar;
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
						case "Yen":
							origen=libra;
							destino=yen;
							resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
							break;
					}
					break;
				case "Dolar":
					switch(resultadoMoneda) {
					case "Euro":
						origen=dolar;
						destino=euro;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
					case "Libra":
						origen=dolar;
						destino=libra;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
					case "Dolar":
						resultadoText.setText(aCambiarText.getText());
						break;
					case "Yen":
						origen=dolar;
						destino=yen;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
				}
					break;
				case "Yen":
					switch(resultadoMoneda) {
					case "Euro":
						origen=yen;
						destino=euro;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
					case "Libra":
						origen=yen;
						destino=libra;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
					case "Dolar":
						origen=yen;
						destino=dolar;
						resultadoText.setText(String.valueOf(df.format(destino.fromEuro(origen.toEuro(cantidad)))));
						break;
					case "Yen":
						resultadoText.setText(aCambiarText.getText());
						break;
				}
					break;
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(primaryStage);
			alert.setHeaderText("Datos Erroneos");
			alert.setContentText(
					"Debe introducir solamente numeros y haber seleccionado algun tipo de moneda"
				);
			alert.showAndWait();		
		} 
	}
	public static void main(String[] args) {
		launch(args);
	}
}
