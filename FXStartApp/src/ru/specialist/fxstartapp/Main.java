package ru.specialist.fxstartapp;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	int counter = 0;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = new AnchorPane();
			Button btn = new Button();
			btn.setText("Press me");
			root.getChildren().add(btn);
			
			btn.setOnAction( e->{
				counter++;
				btn.setText("PRESSED: "+String.valueOf(counter)); // PRESSED: 3
			});
			
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
