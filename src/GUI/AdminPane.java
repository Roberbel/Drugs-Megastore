package GUI;

import java.util.Date;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

public class AdminPane extends FlowPane {

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
		
		TableColumn <pojos.Arrivals,Integer> cost=new TableColumn("Cost");
		TableColumn <pojos.Arrivals,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojos.Arrivals,String> provider=new TableColumn("Provider");
		
		TableView <pojos.Arrivals> arrivalsTable=new TableView<pojos.Arrivals>();
		arrivalsTable.getColumns().addAll(provider,dateOfOrder,cost);
		
		return arrivalsTable;		
	}
	
	public TableView drugsTable() {
		
		TableColumn <pojos.Drugs,String> name=new TableColumn("Name");
		TableColumn <pojos.Drugs,Integer> stock=new TableColumn("Stock");
		TableColumn <pojos.Drugs,Integer> price=new TableColumn("Price");
		TableColumn <pojos.Drugs,String> activePrinciple=new TableColumn("Active Principle");
		TableColumn <pojos.Drugs,String> corridor=new TableColumn("Corridor");
		
		TableView <pojos.Drugs> drugsTable=new TableView<pojos.Drugs>();
		drugsTable.getColumns().addAll(name,activePrinciple,stock,corridor,price);
		
		return drugsTable;
	}
	
	public TableView corridorTable() {
		
		TableColumn <pojos.Corridors,String> name=new TableColumn("Corridor ID");
		TableColumn <pojos.Corridors,Integer> temp=new TableColumn("Temperature");
		TableColumn <pojos.Corridors,String> warehouse=new TableColumn("Warehouse");
		
		TableView <pojos.Corridors> corridorTable=new TableView<pojos.Corridors>();
		corridorTable.getColumns().addAll(name,temp,warehouse);
		
		return corridorTable;
	}
	
	public TableView deliveriesTable() {
		
		TableColumn <pojos.Deliveries,Integer> sellingPrice=new TableColumn("Cost");
		TableColumn <pojos.Deliveries,Integer> amount=new TableColumn("Amount");
		TableColumn <pojos.Deliveries,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojos.Deliveries,pojos.Client> client=new TableColumn("Client");
		
		TableView <pojos.Deliveries> deliveriesTable=new TableView <pojos.Deliveries>();
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
	
}
