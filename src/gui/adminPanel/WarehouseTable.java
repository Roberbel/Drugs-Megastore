package gui.adminPanel;

import pojos.*;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import pojos.Warehouse;

public class WarehouseTable extends VBox{

	TableView table;
	TextField pcField,countryField,cityField,adressField,phoneField;
	Button addBut,delBut;
	
	public WarehouseTable() {
		
		table=new TableView<pojos.Warehouse>();
		table.setEditable(true);
		TableColumn <pojos.Warehouse,Integer> pc=new TableColumn("Zip Code");
		pc.setCellValueFactory(new PropertyValueFactory<pojos.Warehouse,Integer>("pc"));
		pc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		pc.setOnEditCommit(e->pc_onEditCommit(e));
		TableColumn <pojos.Warehouse,String> country=new TableColumn("Country");
		country.setCellValueFactory(new PropertyValueFactory<pojos.Warehouse,String>("country"));
		country.setCellFactory(TextFieldTableCell.forTableColumn());
		country.setOnEditCommit(e->country_onEditCommit(e));
		TableColumn <pojos.Warehouse,String> city=new TableColumn("City");
		city.setCellValueFactory(new PropertyValueFactory<pojos.Warehouse,String>("city"));
		city.setCellFactory(TextFieldTableCell.forTableColumn());
		city.setOnEditCommit(e->city_onEditCommit(e));
		TableColumn <pojos.Warehouse,String> adress=new TableColumn("Adress");
		adress.setCellValueFactory(new PropertyValueFactory<pojos.Warehouse,String>("adress"));
		adress.setCellFactory(TextFieldTableCell.forTableColumn());
		adress.setOnEditCommit(e->adress_onEditCommit(e));
		TableColumn <pojos.Warehouse,Integer> phone=new TableColumn("Phone");
		phone.setCellValueFactory(new PropertyValueFactory <pojos.Warehouse,Integer>("phone"));
		phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		phone.setOnEditCommit(e->phone_onEditCommit(e));
		TableColumn <pojos.Warehouse,pojos.Employee> employees=new TableColumn("Employees");
		TableColumn <pojos.Warehouse,pojos.Corridor> corridors=new TableColumn("Corridors");
		
		table.getColumns().addAll(pc,country,city,adress,phone,employees,corridors);
		
		HBox addPan=new HBox();
		pcField=new TextField();
		pcField.setPromptText("Zip Code");
		countryField=new TextField();
		countryField.setPromptText("Country");
		cityField=new TextField();
		cityField.setPromptText("City");
		adressField=new TextField();
		adressField.setPromptText("Adress");
		phoneField=new TextField();
		phoneField.setPromptText("Phone Number");
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked());
		delBut=new Button("Delete");
		delBut.setOnAction(e->delClicked);
		
		addPan.getChildren().addAll(pcField,countryField,cityField,adressField,phoneField,addBut,delBut);
		this.getChildren().addAll(table,addPan);
	}

	public void addClicked() {
		pojos.Warehouse newWarehouse=new Warehouse();
		newWarehouse.setPc(Integer.parseInt(pcField.getText()));
	}
	
	private Object phone_onEditCommit(CellEditEvent<Warehouse, Integer> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object adress_onEditCommit(CellEditEvent<Warehouse, String> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object city_onEditCommit(CellEditEvent<Warehouse, String> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object country_onEditCommit(CellEditEvent<Warehouse, String> e) {
		// TODO Auto-generated method stub
		return null;
	}

	public void pc_onEditCommit(Event e) {
		// TODO Auto-generated method stub
	
	}
}
