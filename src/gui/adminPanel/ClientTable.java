package gui.adminPanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import pojos.*;

public class ClientTable extends VBox{
	
	TableView table;
	TextField nameField,adressField,teleField,mailField;
	ComboBox payBox;
	ScrollPane deliveryPane;
	
	public ClientTable() {
		
		table=new TableView<Client>();
		table.setEditable(true);
		
		TableColumn <Client,String> name=new TableColumn <Client,String> ("Name");
		name.setCellValueFactory(new PropertyValueFactory <Client,String>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_onEditCommit(e));
		TableColumn <Client,String> adress=new TableColumn <Client,String> ("Name");
		adress.setCellValueFactory(new PropertyValueFactory <Client,String>("adress"));
		adress.setCellFactory(TextFieldTableCell.forTableColumn());
		adress.setOnEditCommit(e->adress_onEditCommit(e));
		TableColumn <Client,Integer> phone=new TableColumn <Client,Integer> ("Phone Number");
		phone.setCellValueFactory(new PropertyValueFactory <Client,Integer>("telephone"));
		phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		phone.setOnEditCommit(e->phone_onEditCommit(e));
		TableColumn <Client,String> email=new TableColumn <Client,String> ("Email");
		email.setCellValueFactory(new PropertyValueFactory <Client,String>("email"));
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(e->email_onEditCommit(e));
		TableColumn <Client,>
		
		
		
	}

	private Object email_onEditCommit(CellEditEvent<Client, String> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object phone_onEditCommit(CellEditEvent<Client, Integer> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object adress_onEditCommit(CellEditEvent<Client, String> e) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object name_onEditCommit(CellEditEvent<Client, String> e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
