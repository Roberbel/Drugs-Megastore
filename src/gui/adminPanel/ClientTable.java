package gui.adminPanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import pojos.*;

public class ClientTable extends VBox{
	
	TableView table;
	TextField nameField,adressField,teleField,mailField;
	ComboBox payBox;
	ScrollPane deliveryPane;
	
	public ClientTable() {
		
		table=new TableView<Client>();
		table.setEditable(true);
		
		TableColumn <User,String> name=new TableColumn <User,String> ("Name");
		name.setCellValueFactory(new PropertyValueFactory <User,String>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_onEditCommit(e));
		TableColumn <User,String> adress=new TableColumn <User,String> ("Name");
		adress.setCellValueFactory(new PropertyValueFactory <User,String>("adress"));
		adress.setCellFactory(TextFieldTableCell.forTableColumn());
		adress.setOnEditCommit(e->adress_onEditCommit(e));
	}
	
	
}
