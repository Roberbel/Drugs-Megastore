package GUI;

import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class AdminPane extends FlowPane {

	BorderPane mainPanel;
	ChoiceBox tableChoice;
	
	
	public AdminPane() {
		
		tableChoice=new ChoiceBox <String>();
		tableChoice.getItems().addAll("Employees","Drugs","Clients","Arrivals","Deliveries","Users","Providers",
		"Warehouses","Corridors");
		tableChoice.setValue("Employees");
		tableChoice.setOnAction(e->changeTable());
		
		mainPanel=new BorderPane();
		mainPanel.setCenter(employeeTable());
		mainPanel.setTop(tableChoice);
		this.getChildren().addAll(mainPanel);
		
	}
	
	public void changeTable() {
		switch(tableChoice.getValue().toString()) {
		case "Employees":
			mainPanel.setCenter(employeeTable());
			break;
		case "Drugs":
			mainPanel.setCenter(drugsTable());
			break;
		case "Clients":
			mainPanel.setCenter(clientTable());
			break;
		case "Arrivals":
			mainPanel.setCenter(arrivalsTable());
			break;
		case "Deliveries":
			mainPanel.setCenter(deliveriesTable());
			break;
		case "Users":
			mainPanel.setCenter(usersTable());
			break;
		case "Providers":
			mainPanel.setCenter(providerTable());
			break;
		case "Warehouses":
			mainPanel.setCenter(warehouseTable());
			break;
		case "Corridors":
			mainPanel.setCenter(corridorTable());
			break;		
		}
	}
	
	
	public TableView employeeTable() {
		
		TableColumn <pojos.Employee,String> name= new TableColumn("Name");
		TableColumn <pojos.Employee,Float> salary=new TableColumn("Salary");
		TableColumn <pojos.Employee,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojos.Employee,String> position=new TableColumn("Position");
		TableColumn <pojos.Employee,String> warehouse=new TableColumn("Warehouse");
		TableColumn <pojos.Employee,byte []> picture=new TableColumn("Picture");
		
		TableView <pojos.Employee> employeeTable=new TableView<pojos.Employee>();
		employeeTable.getColumns().addAll(name,salary,phone,position,warehouse,picture);

		return employeeTable;
	}
	
	public TableView clientTable() {
		
		TableColumn <pojos.Client,String> name=new TableColumn("Name");
		TableColumn <pojos.Client,String> adress=new TableColumn("Adress");
		TableColumn <pojos.Client,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojos.Client,String> email=new TableColumn("Email");
		TableColumn <pojos.Client,String> paymentMethod=new TableColumn("Payment Method");
		
		TableView <pojos.Client> clientTable=new TableView<pojos.Client>();
		clientTable.getColumns().addAll(name,adress,phone,email,paymentMethod);
		
		return clientTable;
	}
	
	public TableView arrivalsTable() {
		
		TableColumn <pojos.Arrival,Integer> cost=new TableColumn("Cost");
		TableColumn <pojos.Arrival,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojos.Arrival,String> provider=new TableColumn("Provider");
		
		TableView <pojos.Arrival> arrivalsTable=new TableView<pojos.Arrival>();
		arrivalsTable.getColumns().addAll(provider,dateOfOrder,cost);
		
		return arrivalsTable;		
	}
	
	public TableView drugsTable() {
		
		TableColumn <pojos.Drug,String> name=new TableColumn("Name");
		TableColumn <pojos.Drug,Integer> stock=new TableColumn("Stock");
		TableColumn <pojos.Drug,Integer> price=new TableColumn("Price");
		TableColumn <pojos.Drug,String> activePrinciple=new TableColumn("Active Principle");
		TableColumn <pojos.Drug,String> corridor=new TableColumn("Corridor");
		
		TableView <pojos.Drug> drugsTable=new TableView<pojos.Drug>();
		drugsTable.getColumns().addAll(name,activePrinciple,stock,corridor,price);
		
		return drugsTable;
	}
	
	public TableView corridorTable() {
		
		TableColumn <pojos.Corridor,String> name=new TableColumn("Corridor ID");
		TableColumn <pojos.Corridor,Integer> temp=new TableColumn("Temperature");
		TableColumn <pojos.Corridor,String> warehouse=new TableColumn("Warehouse");
		
		TableView <pojos.Corridor> corridorTable=new TableView<pojos.Corridor>();
		corridorTable.getColumns().addAll(name,temp,warehouse);
		
		return corridorTable;
	}
	
	public TableView deliveriesTable() {
		
		TableColumn <pojos.Delivery,Integer> sellingPrice=new TableColumn("Cost");
		TableColumn <pojos.Delivery,Integer> amount=new TableColumn("Amount");
		TableColumn <pojos.Delivery,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojos.Delivery,pojos.Client> client=new TableColumn("Client");
		
		TableView <pojos.Delivery> deliveriesTable=new TableView <pojos.Delivery>();
		deliveriesTable.getColumns().addAll(sellingPrice,amount,dateOfOrder,client);
		
		return deliveriesTable;
	}
	
	public TableView providerTable() {
		
		TableColumn <pojos.Provider,String> name=new TableColumn("Name");
		TableColumn <pojos.Provider,String> adress=new TableColumn("Adress");
		TableColumn <pojos.Provider,Integer> telephone=new TableColumn("Phone Number");
		TableColumn <pojos.Provider,String> email=new TableColumn("Email");
		
		TableView <pojos.Provider> providerTable=new TableView <pojos.Provider>();
		providerTable.getColumns().addAll(name,adress,telephone,email);
		
		return providerTable;		
	}
	
	public TableView warehouseTable() {
		
		TableColumn <pojos.Warehouse,Integer> pc=new TableColumn("Postal Code");
		TableColumn <pojos.Warehouse,String> country=new TableColumn("Country");
		TableColumn <pojos.Warehouse,String> adress=new TableColumn("Adress");
		TableColumn <pojos.Warehouse,Integer> phone=new TableColumn("Phone Number");
		
		TableView <pojos.Warehouse> warehouseTable=new TableView <pojos.Warehouse>();
		warehouseTable.getColumns().addAll(pc,country,adress,phone);
		
		return warehouseTable;
	}
	
	public TableView usersTable() {
		
		TableColumn <pojos.User,String> name=new TableColumn("User");
		TableColumn <pojos.User,String> password=new TableColumn("Password");
		TableColumn <pojos.User,pojos.User.UserClass> type=new TableColumn("Type");
		TableColumn <pojos.User,Integer> id=new TableColumn("User ID");
		
		TableView <pojos.User> usersTable=new TableView<pojos.User>();
		usersTable.getColumns().addAll(name,password,type,id);
		
		return usersTable;
		
	}
	
}
