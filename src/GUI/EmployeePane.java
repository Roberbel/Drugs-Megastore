package GUI;

import java.awt.List;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
		java.util.List<Drugs> drugs = new java.util.List<Drugs> ();
		//drugs = SQLManager.getDrugsByName();
		
		//first we clear the panel
		mainPane.setTop(null);
		mainPane.setCenter(null);
		mainPane.setRight(null);
		mainPane.setLeft(null);
		mainPane.setBottom(null);
		
		VBox listPane = new VBox();
		
		/*on the right we will have a list of the drugs present on the DB
		 *with the info necessary: name, stock and corridor
		 */
		Iterator<Drugs> iterador = drugs.iterator();
		while(iterador.hasNext()) {
			Drugs d = iterador.next();
			HBox hBox = new HBox();
			Label name = new Label(d.getName());
			Label stock = new Label(""+d.getStock());
			Label corridor = new Label (""+d.getStock());
			hBox.getChildren().addAll(name, stock, corridor);
			listPane.getChildren().add(hBox);
		}
		/*
		 * Finally create a scroll panel in case there are too many drugs
		 */
		ScrollPane scrollPane = new ScrollPane(listPane);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
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
