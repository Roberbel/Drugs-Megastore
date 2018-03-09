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
import pojos.Client;
import pojos.Delivery;
import pojos.Drug;
import pojos.Employee;
import pojos.Provider;

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

//=====================================Constructors===========================================================================
	
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
	
//==============================================DRUGS PART ======================================	
	
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
			Label stock = new Label(d.getStock().toString());
			Label corridor = new Label (d.getCorridor().toString());
			hBox.getChildren().addAll(name, stock, corridor);
			listPane.getChildren().add(hBox);
		}*/
	
	
	}
	
	
//==================================================================ARRIVALS PART======================================
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
		
		Iterator<Arrival> iterador = arrivals.iterator();
		while(iterador.hasNext()) {
			Arrival a = iterador.next();
			HBox hBox = new HBox();
			Provider p = a.getProvider();
			Label providerName = new Label(p.getName());
			Label providerEmail = new Label(p.getEmail());
			Label providerAdress = new Label(p.getAdress());
			Label providerPhone = new Label(p.getTelephone().toString());
			Label arrivalDate = new Label(a.getDate().toString());
			ScrollPane drugNammountPane = new ScrollPane();
			VBox listPane = new VBox();
			int numDrugs = a.getDrugs().size();
			for (int i = 0; i < numDrugs; i++ ) {
				HBox h = new HBox();
				Label drug = new Label(a.getDrugs().get(i).getName());
				Label ammount = new Label(a.getAmount().get(i).toString());
				h.getChildren().addAll(drug, ammount);
				listPane.getChildren().add(h);
			}
			drugNammountPane.setContent(listPane);
			
			hBox.getChildren().addAll(providerName, providerEmail, providerAdress, providerPhone, arrivalDate, drugNammountPane);
			listPane.getChildren().add(hBox);
		}
	
	
	}
	
	private void showArrivalInfo(Arrival a) {
		
	}
	
//=============================================================DEPARTURES PART===================================================
	
	private void showDeparturesPane() {
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
		order.setOnAction(e -> changeDepartureListPane());
		//order.setSelectionModel();
		
		direction = new ChoiceBox <String>();
		direction.getItems().addAll("Ascending", "Descending");
		direction.setValue("Acending");
		direction.setOnAction(e -> changeDepartureListPane());
		
		leftPane.getChildren().addAll(order, direction);
		mainPane.setLeft(leftPane);
		changeDepartureListPane();
		mainPane.setCenter(listPane);
	}
	
	private void changeDepartureListPane() {
		//mainPane.setCenter(null);
				listPane.getChildren().clear();
				
				java.util.List <Delivery> departures;
				
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
				
				Iterator<Delivery> iterador = departures.iterator();
				while(iterador.hasNext()) {
					Delivery d = iterador.next();
					HBox hBox = new HBox();
					Client c = d.getClient();
					
					Label providerName = new Label(c.getName());
					Label providerEmail = new Label(c.getEmail());
					Label providerAdress = new Label(c.getAdress());
					Label providerPhone = new Label(c.getTelephone().toString());
					Label paymentMethod = new Label(c.getPaymentMethod());
					Label transactionDate = new Label(d.getTransactionDate().toString());
					Label transactionCost = new Label(d.getSellingPrice().toString());
					
					ScrollPane drugNammountPane = new ScrollPane();
					VBox listPane = new VBox();
					int numDrugs = d.getDrugId().size();
					for (int i = 0; i < numDrugs; i++ ) {
						HBox h = new HBox();
						Label drug = new Label(d.getDrugId().get(i).getName());
						Label ammount = new Label(d.getAmmount().get(i).toString());
						h.getChildren().addAll(drug, ammount);
						listPane.getChildren().add(h);
					}
					drugNammountPane.setContent(listPane);
					
					hBox.getChildren().addAll(providerName, providerEmail, providerAdress, providerPhone, paymentMethod ,transactionDate, transactionCost , drugNammountPane);
					listPane.getChildren().add(hBox);
				}
			
			
		
	}
	private void showDeparturesInfo(Delivery d) {
		
	}

}
