package GUI;

import java.awt.List;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pojo.Drugs;
import pojo.Employee;
import pojo.Deliveries;
import pojo.Arrivals;

public class EmployeePane extends FlowPane {
	
	Employee employee;
	
	//Panels
	BorderPane mainPane;
	
	//always shown elements
	Button drugButton;
	Button arrivalsButton;
	Button departuresButton;
	Button logOut;
	
	TextField searchField;

	
	public EmployeePane() {
		
		super();
		
		mainPane = new BorderPane();
		
		drugButton = new Button("Drugs");
		drugButton.setOnAction(e -> showDrugsPane());
		arrivalsButton = new Button ("Arrivals");
		arrivalsButton.setOnAction(e -> showArrivalsPane());
		departuresButton = new Button ("Departure");
		departuresButton.setOnAction(e -> showDeparturesPane());
		
		HBox hBox = new HBox(20, drugButton, arrivalsButton, departuresButton);
		
		mainPane.setCenter(hBox);
		this.getChildren().addAll(mainPane);
		
		//no idea how to do the log out, most likely we will have to make it work from the MainWindow part
		logOut = new Button("Log Out");
		
	}
	
	public EmployeePane( Employee employee) {
		
		super();
		
		//we keep track on who is making changes
		this.employee = employee;
		
		mainPane = new BorderPane();
		
		drugButton = new Button("Drugs");
		drugButton.setOnAction(e -> showDrugsPane());
		arrivalsButton = new Button ("Arrivals");
		arrivalsButton.setOnAction(e -> showArrivalsPane());
		departuresButton = new Button ("Departure");
		departuresButton.setOnAction(e -> showDeparturesPane());
		
		HBox hBox = new HBox(20, drugButton, arrivalsButton, departuresButton);
		
		mainPane.setCenter(hBox);
		this.getChildren().addAll(mainPane);
		
		//no idea how to do the log out, most likely we will have to make it work from the MainWindow part
		logOut = new Button("Log Out");
		
		
	}
	
	private void showDrugsPane() {
		List drugs;
		//drugs = SQLManager.getDrugsByName();
		
		
		
	}
	
	private void showDrugInfo(Drugs d) {
		
	}
	
	private void showArrivalsPane() {
		
	}
	
	private void showArrivalInfo(Arrivals a) {
		
	}
	
	private void showDeparturesPane() {
		
	}
	
	private void showDeparturesInfo(Deliveries d) {
		
	}

}
