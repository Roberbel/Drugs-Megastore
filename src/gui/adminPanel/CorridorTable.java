package gui.adminPanel;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import pojos.*;

public class CorridorTable extends VBox{

	private TableView <pojos.Corridor> table;
	private TextField tempField;
	private ChoiceBox wareBox;
	private Button addBut,delBut;
	
	public CorridorTable() {
		
		table=new TableView <pojos.Corridor>();
		TableColumn <pojos.Corridor,Integer> id=new TableColumn ("ID");
		TableColumn <pojos.Corridor,Integer> temperature=new TableColumn("Temperature");
		temperature.setCellValueFactory(new PropertyValueFactory <pojos.Corridor,Integer>("ID"));
		temperature.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		temperature.setOnEditCommit(e->temp_OnEditCommit(e));
		TableColumn <pojos.Corridor,pojos.Warehouse> warehouse=new TableColumn("Warehouse");
		table.getColumns().addAll(id,temperature,warehouse);
		
		HBox addDelPane=new HBox();
		//Nesesito una lista de Warehouses
		//warebox=new ChoiceBox(Mi puta lista);
		tempField=new TextField();
		tempField.setPromptText("Temperatura");
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked(e));
		delBut=new Button("Delete");
		delBut.setOnAction(e->delClicked(e));
		addDelPane.getChildren().addAll(tempField,addBut,delBut);
		
		this.getChildren().addAll(table,addDelPane);
}
	
	public void temp_OnEditCommit(Event e) {
		CellEditEvent<pojos.Corridor, Integer> ce;
		ce= (TableColumn.CellEditEvent<pojos.Corridor,Integer>) e;
		Corridor ex=ce.getRowValue();
		ex.setTemperature(ce.getNewValue());
	}
	
	public void addClicked(Event e) {
		pojos.Corridor newCorridor=new pojos.Corridor();
		
	}
	
	public void delClicked(Event e) {
		ObservableList <pojos.Corridor> sel, items;
		items=table.getItems();
		sel=table.getSelectionModel().getSelectedItems();
		
		for(pojos.Corridor c:sel) {
			items.remove(c);
		}
	}
	
	
	
}
