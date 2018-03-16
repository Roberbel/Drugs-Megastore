package gui.adminPanel;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import pojos.*;

public class ProviderTable extends VBox {

	TableView table;
	TextField nameField,adressField,phoneField,mailField;
	Button addBut,delBut;
	
	public ProviderTable() {
		table=new TableView <Provider>();
		table.setEditable(true);
		TableColumn <Provider,String> name=new TableColumn <Provider,String> ("Name");
		name.setCellValueFactory(new PropertyValueFactory<Provider,String>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_onEditCommit(e));
		TableColumn <Provider,String> adress=new TableColumn <Provider,String> ("Adress");
		adress.setCellValueFactory(new PropertyValueFactory <Provider,String>("adress"));
		adress.setCellFactory(TextFieldTableCell.forTableColumn());
		adress.setOnEditCommit(e->adress_onEditCommit(e));
		TableColumn <Provider,Integer> phone=new TableColumn <Provider,Integer> ("Phone Number");
		phone.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("telephone"));
		phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		phone.setOnEditCommit(e->phone_onEditCommit(e));
		TableColumn <Provider,String> mail=new TableColumn <Provider,String> ("Email");
		mail.setCellValueFactory(new PropertyValueFactory <Provider,String>("email"));
		mail.setCellFactory(TextFieldTableCell.forTableColumn());
		mail.setOnEditCommit(e->mail_onEditCommit(e));
		
		table.getColumns().addAll(name,adress,phone,mail);
		
		HBox addBox=new HBox();
		nameField=new TextField();
		nameField.setPromptText("Name");
		adressField=new TextField();
		adressField.setPromptText("Adress");
		phoneField=new TextField();
		phoneField.setPromptText("Phone Number");
		mailField=new TextField();
		mailField.setPromptText("Mail");
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked());
		delBut=new Button("Delete");
		delBut.setOnAction(e->delClicked());
		addBox.getChildren().addAll(nameField,adressField,phoneField,mailField,addBut,delBut);
		
	}
	
	public void mail_onEditCommit(Event e) {
		
	}
	
	public void phone_onEditCommit(Event e) {
		
	}
	
	public void name_onEditCommit(Event e) {
		
	}
	
	public void adress_onEditCommit(Event e) {
		
	}
}
