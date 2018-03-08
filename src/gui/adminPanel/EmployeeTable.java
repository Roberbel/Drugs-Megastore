package gui.adminPanel;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import pojos.*;

public class EmployeeTable extends VBox {

	TableView <pojos.Employee> Table;
	TextField nameField,salaryField,phoneField,posField,wareField;
	Button addBut,delBut;
	
	public EmployeeTable() {
		
		ArrayList <pojos.Warehouse> wareArray=new ArrayList <pojos.Warehouse> ();
		wareArray.add(new pojos.Warehouse());
		
		//Primero creo la tabla
		Table=new TableView<pojos.Employee>();
		Table.setEditable(true);//Necesario para poder editar celdas
		
		TableColumn <pojos.Employee,String> name= new TableColumn("Name");
		//Hace que cada celda de la columna sea editable y maneja el evento de que se edite
		name.setCellValueFactory(new PropertyValueFactory<pojos.Employee,String>("Name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_OnEditCommit(e));	
		TableColumn <pojos.Employee,Float> salary=new TableColumn("Salary");
		salary.setCellValueFactory(new PropertyValueFactory<pojos.Employee,Float>("Salary"));
		salary.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		salary.setOnEditCommit(e->salary_OnEditCommit(e));
		TableColumn <pojos.Employee,Integer> phone=new TableColumn("Phone Number");
		phone.setCellValueFactory(new PropertyValueFactory<pojos.Employee,Integer>("phone"));
		phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		phone.setOnEditCommit(e->phone_OnEditCommit(e));
		phone.setMinWidth(100);
		TableColumn <pojos.Employee,String> position=new TableColumn("Position");
		position.setCellValueFactory(new PropertyValueFactory<pojos.Employee,String>("Position"));
		position.setCellFactory(TextFieldTableCell.forTableColumn());
		position.setOnEditCommit(e->position_OnEditCommit(e));	
		TableColumn <pojos.Employee,pojos.Warehouse> warehouse=new TableColumn("Warehouse");
		warehouse.setCellValueFactory(new PropertyValueFactory<pojos.Employee,pojos.Warehouse>("WarehouseId"));
		warehouse.setCellFactory(ChoiceBoxTableCell.forTableColumn(items));
		TableColumn <pojos.Employee,byte []> picture=new TableColumn("Picture");
		
		Table.getColumns().addAll(name,salary,phone,position,warehouse,picture);

		//Panel para añadir o borrar elementos de la tabla
		HBox paneAdd=new HBox();
		//Textfields
		nameField=new TextField();
		nameField.setPromptText("Name");
		salaryField=new TextField();
		salaryField.setPromptText("Salary");
		phoneField=new TextField();
		phoneField.setPromptText("Phone Number");
		posField=new TextField();
		posField.setPromptText("Position");
		wareField=new TextField();
		wareField.setPromptText("Warehouse");
		//Botones
		addBut=new Button("Add");
		addBut.setOnAction(e->addClicked());
		delBut=new Button("Delete");
		paneAdd.getChildren().addAll(nameField,salaryField,phoneField,posField,wareField,addBut,delBut);
		
		this.getChildren().addAll(Table,paneAdd);

	}
	
	public void name_OnEditCommit(Event e) {
		CellEditEvent<Employee, String> ce;
		ce= (TableColumn.CellEditEvent<pojos.Employee,String>) e;
		Employee ex=ce.getRowValue();
		ex.setName(ce.getNewValue());
	}
	
	public void salary_OnEditCommit(Event e) {
		CellEditEvent<Employee, Float> ce;
		ce= (TableColumn.CellEditEvent<pojos.Employee,Float>) e;
		Employee ex=ce.getRowValue();
		ex.setSalary(ce.getNewValue());
	}
	
	public void phone_OnEditCommit(Event e) {
		CellEditEvent<Employee, Integer> ce;
		ce= (TableColumn.CellEditEvent<pojos.Employee,Integer>) e;
		Employee ex=ce.getRowValue();
		ex.setPhone(ce.getNewValue());
	}
	
	public void position_OnEditCommit(Event e) {
		CellEditEvent<Employee, String> ce;
		ce= (TableColumn.CellEditEvent<pojos.Employee,String>) e;
		Employee ex=ce.getRowValue();
		ex.setPosition(ce.getNewValue());
	}
	
	
	
	public void addClicked() {
		pojos.Employee newEmployee=new pojos.Employee();
		newEmployee.setName(nameField.getText());
		newEmployee.setSalary(Float.parseFloat(salaryField.getText()));
		newEmployee.setPhone(Integer.parseInt(phoneField.getText()));
		newEmployee.setPosition(posField.getText());
		newEmployee.setWarehouseId(wareField.get);
		Table.getItems().add(newEmployee);
		nameField.clear();
		salaryField.clear();
		phoneField.clear();
		posField.clear();
		
	}
}
