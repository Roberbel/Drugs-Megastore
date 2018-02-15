package GUI;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;
import pojo.*;



public class MainWindow extends Application {
	private Stage stage;
	private ChoiceBox<String> list;
	private BorderPane mainPane;
	private BorderPane secondPane;
	private BorderPane tertPane;
	private FlowPane tablesPane;
	
	public static void main (String[] args) {
		launch(args);
	}
	

	private void listChanged() {
		String value=list.getValue();
		switch (value) {
		case "Drugs":
			//tablesPane.
		
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
//===========================================Pane====================================//
		FlowPane listPane = new FlowPane();
		listPane.getChildren().add(list);
		
		BorderPane secondPane=new BorderPane();
				
//===========================================list====================================//
		list = new ChoiceBox <String>();
		list.getItems().addAll("Drugs","Arrivals","Clients","Corridors","Deliveries","Employees","Provider","Warehouse");
		list.setOnAction(e -> listChanged());
		
//===========================================Table===================================//
		
		TableColumn <pojo.Employee,String> name= new TableColumn("Name");
		TableColumn <pojo.Employee,Float> salary=new TableColumn("Salary");
		TableColumn <pojo.Employee,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojo.Employee,String> position=new TableColumn("Position");
		TableColumn <pojo.Employee,String> warehouse=new TableColumn("Warehouse");
		TableColumn <pojo.Employee,byte []> picture=new TableColumn("Picture");
		
		TableView <pojo.Employee> employeeTable=new TableView<pojo.Employee>();
		employeeTable.getColumns().addAll(name,salary,phone,position,warehouse,picture);
		
		mainPane.setCenter(employeeTable);
		
		
		
	
		
//===========================================Scene===================================//		
		Scene scene = new Scene(mainPane, 300, 400);
		stage.setScene(scene);
		stage.show();
	}
	
}
