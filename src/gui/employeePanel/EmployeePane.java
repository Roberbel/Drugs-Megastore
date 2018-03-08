package gui.employeePanel;

import java.awt.List;


import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pojos.Arrival;
import pojos.Delivery;
import pojos.Drug;
import pojos.Employee;

import java.util.*;

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
	ChoiceBox<String> order;
	ChoiceBox<String> direction;
	VBox listPane;

	
	public EmployeePane() {
		
		super();
		
		mainPane = new BorderPane();
		
		drugButton = new Button("Drugs");
		drugButton.setOnAction(e -> showDrugsPane());
		arrivalsButton = new Button ("Arrivals");
		arrivalsButton.setOnAction(e -> showArrivalsPane());
		departuresButton = new Button ("Departure");
		departuresButton.setOnAction(e -> showDeparturesPane());
		//we initialize some components
		searchField = new TextField();
		listPane = new VBox();
		searchField.setText("Search");
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
		//we initialize some components
		searchField = new TextField();
		searchField.setText("Search");
		listPane = new VBox();
		
		HBox hBox = new HBox(20, drugButton, arrivalsButton, departuresButton);
		
		mainPane.setCenter(hBox);
		this.getChildren().addAll(mainPane);
		
		//no idea how to do the log out, most likely we will have to make it work from the MainWindow part
		logOut = new Button("Log Out");
		
		
	}
	
	private void showDrugsPane() {
		
		//first we clear the panel
		mainPane.setTop(null);
		mainPane.setCenter(null);
		mainPane.setRight(null);
		mainPane.setLeft(null);
		mainPane.setBottom(null);
		
		HBox topPanel = new HBox(30);
		topPanel.getChildren().addAll(searchField, new Region(),  drugButton, arrivalsButton, departuresButton);
		
		mainPane.setTop(topPanel);
		
		VBox leftPane = new VBox(70);
		order = new ChoiceBox<String>();
		order.getItems().addAll("Name", "Stock");
		order.setValue("Name");
		order.setOnAction(e -> changeDrugListPane());
		//order.setSelectionModel();
		
		direction = new ChoiceBox <String>();
		direction.getItems().addAll("Ascending", "Descending");
		direction.setValue("Ascending");
		direction.setOnAction(e -> changeDrugListPane());
		
		leftPane.getChildren().addAll(order, direction);
		mainPane.setLeft(leftPane);
		/*on the right we will have a list of the drugs present on the DB
		 *with the info necessary: name, stock and corridor
		 */
		changeDrugListPane();
		
		/*
		 * Finally create a scroll panel in case there are too many drugs
		 */
		
		ScrollPane scrollPane = new ScrollPane(listPane);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
		mainPane.setCenter(scrollPane);
		
	}
	
	private void changeDrugListPane() {
		//mainPane.setCenter(null);
		listPane.getChildren().clear();
		
		java.util.List <Drug> drugs;
		
		String value1 = order.getValue();
		String value2 = direction.getValue();
		if(value2 == "Name") {
			if(value2 == "Descending") {
				//here would go the SQL commands.
			}else {
				
			}
		}else {
			if(value2 == "Descending") {
				
			}else {
				
			}
		}
		
		/*Iterator<Drugs> iterador = drugs.iterator();
		while(iterador.hasNext()) {
			Drugs d = iterador.next();
			HBox hBox = new HBox();
			Label name = new Label(d.getName());
			Label stock = new Label(""+d.getStock());
			Label corridor = new Label (""+d.getCorridor());
			hBox.getChildren().addAll(name, stock, corridor);
			listPane.getChildren().add(hBox);
		}*/
	
	
	}
	
	private void showArrivalsPane() {
		//first we clear the panel
		mainPane.setTop(null);
		mainPane.setCenter(null);
		mainPane.setRight(null);
		mainPane.setLeft(null);
		mainPane.setBottom(null);
		
		HBox topPanel = new HBox(30);
		topPanel.getChildren().addAll(searchField, new Region(),  drugButton, arrivalsButton, departuresButton);
		
		mainPane.setTop(topPanel);
		
		VBox leftPane = new VBox(70);
		order = new ChoiceBox<String>();
		order.getItems().addAll("Date");
		order.setValue("Date");
		order.setOnAction(e -> changeArrivalsListPane());
		//order.setSelectionModel();
		
		direction = new ChoiceBox <String>();
		direction.getItems().addAll("Ascending", "Descending");
		direction.setValue("Acending");
		direction.setOnAction(e -> changeArrivalsListPane());
		
		leftPane.getChildren().addAll(order, direction);
		mainPane.setLeft(leftPane);
		changeArrivalsListPane();
		mainPane.setCenter(listPane);
		
	}
	
	private void changeArrivalsListPane() {
		//mainPane.setCenter(null);
		listPane.getChildren().clear();
		
		java.util.List <Arrival> arrivals;
		
		String value1 = order.getValue();
		String value2 = direction.getValue();
		if(value2 == "Name") {
			if(value2 == "Descending") {
				//here would go the SQL commands.
			}else {
				
			}
		}else {
			if(value2 == "Descending") {
				
			}else {
				
			}
		}
		
		/*Iterator<Drugs> iterador = drugs.iterator();
		while(iterador.hasNext()) {
			Drugs d = iterador.next();
			HBox hBox = new HBox();
			Label name = new Label(d.getName());
			Label stock = new Label(""+d.getStock());
			Label corridor = new Label (""+d.getCorridor());
			hBox.getChildren().addAll(name, stock, corridor);
			listPane.getChildren().add(hBox);
		}*/
	
	
	}
	
	private void showArrivalInfo(Arrival a) {
		
	}
	
	private void showDeparturesPane() {
		
	}
	
	private void showDeparturesInfo(Delivery d) {
		
	}

}
