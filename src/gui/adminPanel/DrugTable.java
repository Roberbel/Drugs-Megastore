package gui.adminPanel;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
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
		price.setCellValueFactory(new PropertyValueFactory <pojos.Drug,Integer>("sellingPrice"));
		price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		price.setOnEditCommit(e->price_OnEditCommit(e));
		TableColumn <pojos.Drug,String> activePrinciple=new TableColumn("Active Principle");
		activePrinciple.setCellValueFactory(new PropertyValueFactory <pojos.Drug,String>("activePnrinciple"));
		activePrinciple.setCellFactory(TextFieldTableCell.forTableColumn());
		activePrinciple.setOnEditCommit(e->principle_OnEditCommit(e));
		TableColumn <pojos.Drug,String> corridor=new TableColumn("Corridor");
		TableColumn <pojos.Drug,pojos.Arrival> arrivals=new TableColumn("Arrivals");
		arrivals.setCellValueFactory(new PropertyValueFactory <pojos.Drug,pojos.Arrival>("arrivals"));
		//arrivals.setCellFactory(new ScrollPane().setContent());;
			
		table.getColumns().addAll(name,activePrinciple,stock,corridor,price);
		
		HBox addDelBox=new HBox();
		nameField=new TextField();
		nameField.setPromptText("Name");
		stockField=new TextField();
		stockField.setPromptText("Stock");
		priceField=new TextField();
		priceField.setPromptText("Price");
		prinField=new TextField();
		prinField.setPromptText("Active Principle");
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked());
		delBut=new Button("Delete");
		delBut.setOnAction(e->delClicked());
		addDelBox.getChildren().addAll(nameField,stockField,priceField,prinField,addBut,delBut);
		
		this.getChildren().addAll(table,addDelBox);
	}
	
	public void addClicked() {
		pojos.Drug newDrug=new pojos.Drug();
		newDrug.setName(nameField.getText());
		newDrug.setStock(Integer.parseInt(stockField.getText()));
		newDrug.setSellingPrice(Integer.parseInt(priceField.getText()));
		newDrug.setActivePrinciple(prinField.getText());
		table.getItems().add(newDrug);
		nameField.clear();
		stockField.clear();
		priceField.clear();
		prinField.clear();
	}
	
	public void delClicked() {
		ObservableList <pojos.Drug> items,sel;
		items=table.getItems();
		sel=table.getSelectionModel().getSelectedItems();
		
		for(pojos.Drug d:sel) {
			items.remove(d);
		}
		
		
	}
	
	public void name_OnEditCommit(Event e) {
		CellEditEvent<pojos.Drug, String> ce;
		ce= (TableColumn.CellEditEvent<pojos.Drug,String>) e;
		Drug ex=ce.getRowValue();
		ex.setName(ce.getNewValue());
	}
	
	public void stock_OnEditCommit(Event e) {
		CellEditEvent<Drug, Integer> ce;
		ce= (TableColumn.CellEditEvent<pojos.Drug,Integer>) e;
		Drug ex=ce.getRowValue();
		ex.setStock(ce.getNewValue());
	}
	
	public void price_OnEditCommit(Event e) {
		CellEditEvent<Drug, Integer> ce;
		ce= (TableColumn.CellEditEvent<pojos.Drug,Integer>) e;
		Drug ex=ce.getRowValue();
		ex.setSellingPrice(ce.getNewValue());
	}
	
	public void principle_OnEditCommit(Event e) {
		CellEditEvent<Drug, String> ce;
		ce= (TableColumn.CellEditEvent<pojos.Drug,String>) e;
		Drug ex=ce.getRowValue();
		ex.setActivePrinciple(ce.getNewValue());
	}
}
