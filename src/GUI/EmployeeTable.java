package GUI;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pojos.*;

public class EmployeeTable extends VBox {

	TableView <pojos.Employee> Table;
	TextField nameField,salaryField,phoneField,posField,wareField;
	Button addBut,delBut;
	
	public EmployeeTable() {
		//Primero creo la tabla
		Table=new TableView<pojos.Employee>();
		Table.setEditable(true);//Necesario para poder editar celdas
		
		TableColumn <pojos.Employee,String> name= new TableColumn("Name");
		//Hace que cada celda de la columna sea editable y maneja el evento de que se edite
		name.setCellValueFactory(new PropertyValueFactory<pojos.Employee,String>("Name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(e->name_OnEditCommit(e));	
		TableColumn <pojos.Employee,Float> salary=new TableColumn("Salary");
		TableColumn <pojos.Employee,Integer> phone=new TableColumn("Phone Number");
		phone.setMinWidth(100);
		TableColumn <pojos.Employee,String> position=new TableColumn("Position");
		TableColumn <pojos.Employee,String> warehouse=new TableColumn("Warehouse");
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
	
	public void addClicked() {
		pojos.Employee newEmployee=new pojos.Employee();
		newEmployee.setName(nameField.getText());
		newEmployee.setSalary(Float.parseFloat(salaryField.getText()));
		newEmployee.setPhone(Integer.parseInt(phoneField.getText()));
		newEmployee.setPosition(posField.getText());
		//newEmployee.setWarehouseId(Integer.parseInt(wareField.getText()));
		Table.getItems().add(newEmployee);
		nameField.clear();
		salaryField.clear();
		phoneField.clear();
		posField.clear();
		
	}
}
