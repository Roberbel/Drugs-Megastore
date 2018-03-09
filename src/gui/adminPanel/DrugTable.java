package gui.adminPanel;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import pojos.*;

public class DrugTable  extends VBox {

	TableView <pojos.Drug> table;
	TextField nameField,stockField,priceField,prinField;
	Button addBut,delBut;
	
	public DrugTable() {
		
		table=new TableView<pojos.Drug>();
		table.setEditable(true);
		TableColumn <pojos.Drug,String> name=new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory <pojos.Drug,String>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_OnEditCommit(e));
		TableColumn <pojos.Drug,Integer> stock=new TableColumn("Stock");
		stock.setCellValueFactory(new PropertyValueFactory <pojos.Drug,Integer>("stock"));
		stock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stock.setOnEditCommit(e->stock_OnEditCommit(e));
		TableColumn <pojos.Drug,Integer> price=new TableColumn("Price");
		price.setCellValueFactory(new PropertyValueFactory <pojos.Drug,Integer>("price"));
		price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		price.setOnEditCommit(e->price_OnEditCommit(e));
		TableColumn <pojos.Drug,String> activePrinciple=new TableColumn("Active Principle");
		activePrinciple.setCellValueFactory(new PropertyValueFactory <pojos.Drug,String>("name"));
		activePrinciple.setCellFactory(TextFieldTableCell.forTableColumn());
		activePrinciple.setOnEditCommit(e->principle_OnEditCommit(e));
		TableColumn <pojos.Drug,String> corridor=new TableColumn("Corridor");
			
		table.getColumns().addAll(name,activePrinciple,stock,corridor,price);
	}
	
	public void name_OnEditCommit(Event e) {
		
	}
	
	public void stock_OnEditCommit(Event e) {
		
	}
	
	public void price_OnEditCommit(Event e) {
		
	}
	
	public void principle_OnEditCommit(Event e) {
		
	}
}
