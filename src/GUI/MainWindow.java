package GUI;

import javafx.application.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;
import pojo.*;



public class MainWindow extends Application {
	private Stage stage;
	
	private BorderPane mainPane;
	
	private BorderPane secondPane;
	private FlowPane secondPaneNorth;
	private BorderPane secondPaneSouth;
	private FlowPane secondPaneSouthTextBox;
	private FlowPane tablesPane;
	private Button add;
	private Button del;
	private ChoiceBox<String> list;
	
	private BorderPane tertPane;
	
	
	public static void main (String[] args) {
		launch(args);
	}
	
	private Node[] getTextBoxesDrug() {
		int num = 4;
		TextField name;
		TextField stock;
		TextField sellingPrice;
		TextField activePrinciple;
		//picture
		
		name = new TextField();
		stock = new TextField();
		sellingPrice = new TextField();
		activePrinciple = new TextField();
		
		Node[] nodes = new Node[num];
		nodes [0] = name;
		nodes [1] = stock;
		nodes [2] = sellingPrice;
		nodes [3] = activePrinciple;
		//nodes [4] = picture;
		return nodes;
		
	}
	

	private void listChanged() {
		String value=list.getValue();
		switch (value) {
		//case "Drugs":
			//tablesPane.
		
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		mainPane = new BorderPane();
		secondPane = new BorderPane();
		secondPaneNorth= new FlowPane();
		secondPaneSouth= new BorderPane();
		secondPaneSouthTextBox= new FlowPane();	
		tablesPane = new FlowPane();
		add = new Button("Add");
		del = new Button ("Delete");
		list = new ChoiceBox <String>();
		
		
		
//========================Secondary panel North(List)====================================================//
		
		list.getItems().addAll("Drugs","Arrivals","Clients","Corridors","Deliveries","Employees","Provider","Warehouse");
		list.setOnAction(e -> listChanged());
		secondPaneNorth.getChildren().add(list);
		secondPane.setTop(secondPaneNorth);

//================Secondary Panel South(Add/del buttons and text box)====================================//
		FlowPane buttonPane = new FlowPane();
		buttonPane.getChildren().addAll(add, del);
		secondPaneSouth.setRight(buttonPane);
		secondPaneSouthTextBox.getChildren().addAll(getTextBoxesDrug());
		secondPaneSouth.setCenter(secondPaneSouthTextBox);
		secondPane.setBottom(secondPaneSouth);
				

		
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
		
		tablesPane.getChildren().add(employeeTable);
		secondPane.setCenter(tablesPane);
		mainPane.setCenter(secondPane);
		
		
	
		
//===========================================Scene===================================//		
		Scene scene = new Scene(mainPane, 300, 400);
		stage.setScene(scene);
		stage.show();
	}
	
}
