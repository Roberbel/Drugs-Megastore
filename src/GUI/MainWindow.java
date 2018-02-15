package GUI;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;


public class MainWindow extends Application {
	private Stage stage;
	private ChoiceBox<String> list;
	
	public static void main (String[] args) {
		launch(args);
	}
	

	private void listChanged() {
		System.out.println(list.getValue());
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
//===========================================list====================================//
		list = new ChoiceBox <String>();
		list.getItems().addAll("Hello1","Hello2","Hello3");
		list.setOnAction(e -> listChanged());
		
//===========================================Table===================================//
		
		
//===========================================Pane====================================//
		FlowPane pane = new FlowPane();
		pane.getChildren().add();
		
//===========================================Scene===================================//		
		Scene scene = new Scene(pane, 300, 400);
		stage.setScene(scene);
		stage.show();
	}
	
}
