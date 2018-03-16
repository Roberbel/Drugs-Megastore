package gui.adminPanel;

import pojos.*;
import javafx.collections.ObservableList;
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
		delBut.setOnAction(e->delClicked());
		
		addPan.getChildren().addAll(pcField,countryField,cityField,adressField,phoneField,addBut,delBut);
		this.getChildren().addAll(table,addPan);
	}

	public void addClicked() {
		pojos.Warehouse newWarehouse=new Warehouse();
		newWarehouse.setPc(Integer.parseInt(pcField.getText()));
		newWarehouse.setCountry(cityField.getText());
		newWarehouse.setAdress(adressField.getText());
		newWarehouse.setPhone(Integer.parseInt(phoneField.getText()));
		table.getItems().add(newWarehouse);
		pcField.clear();
		cityField.clear();
		adressField.clear();
		phoneField.clear();
	}
	
	public void delClicked() {
		ObservableList <pojos.Warehouse> items,sel;
		items=table.getItems();
		sel=table.getSelectionModel().getSelectedItems();
		
		for(pojos.Warehouse w:sel) {
			items.remove(w);
		}
	}
	
	private void phone_onEditCommit(Event e) {
		CellEditEvent<Warehouse, Integer> ce;
		ce= (TableColumn.CellEditEvent<Warehouse,Integer>)e;
		Warehouse ex=ce.getRowValue();
		ex.setPhone(ce.getNewValue());
	}

	private void adress_onEditCommit(Event e) {
		CellEditEvent<Warehouse,String> ce;
		ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
		Warehouse ex=ce.getRowValue();
		ex.setAdress(ce.getNewValue());
	}

	private void city_onEditCommit(Event e) {
		CellEditEvent <Warehouse,String> ce;
		ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
		Warehouse ex=ce.getRowValue();
		ex.setAdress(ce.getNewValue());
	}

	private void country_onEditCommit(Event e) {
		CellEditEvent <Warehouse,String> ce;
		ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
		Warehouse ex=ce.getRowValue();
		ex.setAdress(ce.getNewValue());
	}

	public void pc_onEditCommit(Event e) {
		CellEditEvent <Warehouse,String> ce;
		ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
		Warehouse ex=ce.getRowValue();
		ex.setAdress(ce.getNewValue());
	}
}
