package gui.adminPanel;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pojos.*;
import pojos.User.UserClass;

public class UserTable extends VBox {

	TableView table;
	TextField nameField,passField;
	ComboBox typeBox;
	Button addBut,delBut;
	
	public UserTable() {
		table= new TableView<pojos.User>();
		table.setEditable(true);
		TableColumn <User,String> name=new TableColumn <User,String>("User Name");
		name.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_onEditCommit(e));
		TableColumn <User,String> password=new TableColumn <User,String> ("Password");
		password.setCellValueFactory(new PropertyValueFactory<User,String> ("password"));
		password.setCellFactory(TextFieldTableCell.forTableColumn());
		password.setOnEditCommit(e->password_onEditCommit(e));
		typeBox=new ComboBox();
		typeBox.getItems().addAll(UserClass.ADMIN,UserClass.CLIENT,UserClass.EMPLOYEE);
		TableColumn <User,UserClass> userClass=new TableColumn <User,UserClass> ("User Type");
		userClass.setCellValueFactory(new PropertyValueFactory<User,UserClass>("type"));
		userClass.setCellFactory(ComboBoxTableCell.forTableColumn(typeBox.getItems()));
		userClass.setOnEditCommit(e->type_onEditCommit(e));
		table.getColumns().addAll(name,password,userClass);
		
		HBox addBox=new HBox();
		nameField=new TextField();
		nameField.setPromptText("User Name");
		passField=new TextField();
		passField.setPromptText("Password");
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked());
		delBut=new Button("Delete");
		delBut.setOnAction(e->delClicked());
		addBox.getChildren().addAll(nameField,passField,typeBox,addBut,delBut);
		
		this.getChildren().addAll(table,addBox);
				
	}
	
	public void addClicked() {
		User newUser=new User();
		newUser.setUserName(nameField.getText());
		newUser.setPassword(passField.getText());
		newUser.setType((UserClass) typeBox.getSelectionModel().getSelectedItem());
		table.getItems().add(newUser);
		nameField.clear();
		passField.clear();
	}
	public void delClicked() {
		ObservableList <User> items,sel;
		items=table.getItems();
		sel=table.getSelectionModel().getSelectedItems();
		for(User u:sel) {
			items.remove(u);
		}
		
	}
	
	public void name_onEditCommit(Event e) {
		CellEditEvent <User,String> ce;
		ce=(TableColumn.CellEditEvent<User,String>)e;
		User ex=ce.getRowValue();
		ex.setUserName(ce.getNewValue());
	}
	
	public void password_onEditCommit(Event e) {
		CellEditEvent <User,String> ce;
		ce=(TableColumn.CellEditEvent<User, String>)e;
		User ex=ce.getRowValue();
		ex.setPassword(ce.getNewValue());
	}
	
	public void type_onEditCommit(Event e) {
		CellEditEvent <User,UserClass> ce;
		ce=(TableColumn.CellEditEvent<User, User.UserClass>)e;
		User ex=ce.getRowValue();
		ex.setType(ce.getNewValue());
		
	}
}
