package GUI;

import java.util.Date;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

public class AdminPane extends FlowPane {

	public TableView employeeTable() {
		
		TableColumn <pojo.Employee,String> name= new TableColumn("Name");
		TableColumn <pojo.Employee,Float> salary=new TableColumn("Salary");
		TableColumn <pojo.Employee,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojo.Employee,String> position=new TableColumn("Position");
		TableColumn <pojo.Employee,String> warehouse=new TableColumn("Warehouse");
		TableColumn <pojo.Employee,byte []> picture=new TableColumn("Picture");
		
		TableView <pojo.Employee> employeeTable=new TableView<pojo.Employee>();
		employeeTable.getColumns().addAll(name,salary,phone,position,warehouse,picture);

		return employeeTable;
	}
	
	public TableView clientTable() {
		
		TableColumn <pojo.Client,String> name=new TableColumn("Name");
		TableColumn <pojo.Client,String> adress=new TableColumn("Adress");
		TableColumn <pojo.Client,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojo.Client,String> email=new TableColumn("Email");
		TableColumn <pojo.Client,String> paymentMethod=new TableColumn("Payment Method");
		
		TableView <pojo.Client> clientTable=new TableView<pojo.Client>();
		clientTable.getColumns().addAll(name,adress,phone,email,paymentMethod);
		
		return clientTable;
	}
	
	public TableView arrivalsTable() {
		
		TableColumn <pojo.Arrivals,Integer> cost=new TableColumn("Cost");
		TableColumn <pojo.Arrivals,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojo.Arrivals,String> provider=new TableColumn("Provider");
		
		TableView <pojo.Arrivals> arrivalsTable=new TableView<pojo.Arrivals>();
		arrivalsTable.getColumns().addAll(provider,dateOfOrder,cost);
		
		return arrivalsTable;		
	}
	
	public TableView drugsTable() {
		
		TableColumn <pojo.Drugs,String> name=new TableColumn("Name");
		TableColumn <pojo.Drugs,Integer> stock=new TableColumn("Stock");
		TableColumn <pojo.Drugs,Integer> price=new TableColumn("Price");
		TableColumn <pojo.Drugs,String> activePrinciple=new TableColumn("Active Principle");
		TableColumn <pojo.Drugs,String> corridor=new TableColumn("Corridor");
		
		TableView <pojo.Drugs> drugsTable=new TableView<pojo.Drugs>();
		drugsTable.getColumns().addAll(name,activePrinciple,stock,corridor,price);
		
		return drugsTable;
	}
	
	public TableView corridorTable() {
		
		TableColumn <pojo.Corridors,String> name=new TableColumn("Corridor ID");
		TableColumn <pojo.Corridors,Integer> temp=new TableColumn("Temperature");
		TableColumn <pojo.Corridors,String> warehouse=new TableColumn("Warehouse");
		
		TableView <pojo.Corridors> corridorTable=new TableView<pojo.Corridors>();
		corridorTable.getColumns().addAll(name,temp,warehouse);
		
		return corridorTable;
	}
	
	public TableView deliveriesTable() {
		
		TableColumn <pojo.Deliveries,Integer> sellingPrice=new TableColumn("Cost");
		TableColumn <pojo.Deliveries,Integer> amount=new TableColumn("Amount");
		TableColumn <pojo.Deliveries,Date> dateOfOrder=new TableColumn("Date of Order");
		TableColumn <pojo.Deliveries,pojo.Client> client=new TableColumn("Client");
		
		TableView <pojo.Deliveries> deliveriesTable=new TableView <pojo.Deliveries>();
		deliveriesTable.getColumns().addAll(sellingPrice,amount,dateOfOrder,client);
		
		return deliveriesTable;
	}
	
	public TableView providerTable() {
		
		TableColumn <pojo.Provider,String> name=new TableColumn("Name");
		TableColumn <pojo.Provider,String> adress=new TableColumn("Adress");
		TableColumn <pojo.Provider,Integer> telephone=new TableColumn("Phone Number");
		TableColumn <pojo.Provider,String> email=new TableColumn("Email");
		
		TableView <pojo.Provider> providerTable=new TableView <pojo.Provider>();
		providerTable.getColumns().addAll(name,adress,telephone,email);
		
		return providerTable;		
	}
	
	public TableView warehouseTable() {
		
		TableColumn <pojo.Warehouse,Integer> pc=new TableColumn("Postal Code");
		TableColumn <pojo.Warehouse,String> country=new TableColumn("Country");
		TableColumn <pojo.Warehouse,String> adress=new TableColumn("Adress");
		TableColumn <pojo.Warehouse,Integer> phone=new TableColumn("Phone Number");
		
		TableView <pojo.Warehouse> warehouseTable=new TableView <pojo.Warehouse>();
		warehouseTable.getColumns().addAll(pc,country,adress,phone);
		
		return warehouseTable;
	}
	
}
