package gui.adminPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import pojos.*;
import pojos.Client.PaymentMethod;
import model.*;
import DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminWindow implements Initializable {
	
    @FXML
    private TabPane pojosTabPane;

    @FXML
    private Tab tabClients;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> clientName;

    @FXML
    private TableColumn<Client, String> clientAdress;

    @FXML
    private TableColumn<Client,Integer> clientPhone;

    @FXML
    private TableColumn<Client,String> clientMail;

    @FXML
    private TableColumn<Client,PaymentMethod> clientPayment;

    @FXML
    private TableColumn<Client, ?> clientDeliveries;

    @FXML
    private TextField clientNameField;

    @FXML
    private TextField clientAdressField;

    @FXML
    private TextField clientPhoneField;
    
    @FXML
    private TextField clientEmailField;

    @FXML
    private ComboBox<?> comboPayment;

    @FXML
    private JFXButton addClientButton;

    @FXML
    private JFXButton deleteClientButton;

    @FXML
    private Tab drugTab;

    @FXML
    private TableView<Drug> drugTable;

    @FXML
    private TableColumn<Drug, String> drugName;

    @FXML
    private TableColumn<Drug, String> drugPrinciple;

    @FXML
    private TableColumn<Drug, Integer> drugPrice;

    @FXML
    private TableColumn<Drug, Integer> drugStock;

    @FXML
    private TextField drugNameField;

    @FXML
    private TextField drugPrincipleField;

    @FXML
    private TextField drugPriceField;

    @FXML
    private TextField drugStockField;

    @FXML
    private JFXButton addDrugButton;

    @FXML
    private JFXButton deleteDrugButton;

    @FXML
    private Tab warehouseTab;

    @FXML
    private TableView<?> warehouseTable;

    @FXML
    private TableColumn<?, ?> warePc;

    @FXML
    private TableColumn<?, ?> wareCountry;

    @FXML
    private TableColumn<?, ?> wareCity;

    @FXML
    private TableColumn<?, ?> wareAdress;

    @FXML
    private TableColumn<?, ?> warePhone;

    @FXML
    private TextField warePcField;

    @FXML
    private TextField wareCountryField;

    @FXML
    private TextField wareCityField;

    @FXML
    private TextField wareAdressField;

    @FXML
    private TextField warePhoneField;

    @FXML
    private JFXButton addWareButton;

    @FXML
    private JFXButton deleteWareClicked;

    @FXML
    private Tab arrivalsTab;

    @FXML
    private Tab deliveriesTab;

    @FXML
    private Tab employeesTab;

    @FXML
    private TableView<?> employeeTable;

    @FXML
    private TableColumn<?, ?> employeeName;

    @FXML
    private TableColumn<?, ?> employeeSalary;

    @FXML
    private TableColumn<?, ?> employeePhone;

    @FXML
    private TableColumn<?, ?> employeePosition;

    @FXML
    private TableColumn<?, ?> employeeWare;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField employeeSalaryField;

    @FXML
    private TextField employeePhoneField;

    @FXML
    private TextField employeePositionField;

    @FXML
    private ComboBox<?> comboWarehouse;

    @FXML
    private JFXButton addEmployeeButton;

    @FXML
    private JFXButton deleteEmployeeButton;

    @FXML
    private Tab corrdirorsTab;

    @FXML
    private TableView<Corridor> corridorsTable;

    @FXML
    private TableColumn<Corridor, Integer> corridorId;

    @FXML
    private TableColumn<Corridor, Warehouse> corridorWarehouse;

    @FXML
    private TableColumn<Corridor, Float> corridorTemperature;

    @FXML
    private TextField corridorIdField;

    @FXML
    private ComboBox<Warehouse> comboWare;

    @FXML
    private TextField corridorTempField;

    @FXML
    private JFXButton addCorridorButton;

    @FXML
    private JFXButton deleteCorridorButton;

    @FXML
    private ImageView imageView;

    @FXML
    void addClientClicked(ActionEvent event) {
    	Client newClient =new Client();
    	try {
    		newClient.setName(clientNameField.getText());
	    	newClient.setAddress(clientAdressField.getText());
	    	newClient.setEmail(clientEmailField.getText());
	    	newClient.setTelephone(Integer.parseInt(clientPhoneField.getText()));
	    	if(comboPayment.getSelectionModel().getSelectedItem().equals("PAYPAL")) {
	    		newClient.setPaymentMethod(PaymentMethod.PAYPAL);
	    	}else if(comboPayment.getSelectionModel().getSelectedItem().equals("VISA")) {
	    		newClient.setPaymentMethod(PaymentMethod.VISA);
	    	}else if(comboPayment.getSelectionModel().getSelectedItem().equals("MASTERCARD")) {
	    		newClient.setPaymentMethod(PaymentMethod.MASTERCARD);
	    	}else if(comboPayment.getSelectionModel().getSelectedItem().equals("AMERICAN EXPRESS")) {
	    		newClient.setPaymentMethod(PaymentMethod.AMERICAN_EXPRESS);
	    	}else {
	    		newClient.setPaymentMethod(PaymentMethod.ORGANS);
	    	}
	    	newClient.setUsername("Morgoth");
	    	newClient.setPassword("Sauron");
	    	clientTable.getItems().add(newClient);

	    	JPAManager.insertClient(newClient);
    	}catch(NumberFormatException | NullPointerException ex) {
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.show();		
    	}
  	
    }

    @FXML
    void addCorridorClicked(ActionEvent event) {
    	
    	try {
    		Corridor newCorridor=new Corridor();
    		newCorridor.setTemperature(Float.parseFloat(corridorTempField.getText()));
    		newCorridor.setWarehouse(comboWare.getSelectionModel().getSelectedItem());
    		corridorsTable.getItems().add(newCorridor);
    		JPAManager.insertCorridor(newCorridor);
    	}catch(NumberFormatException | NullPointerException ex) {
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.show();	
    	}   	
    	
    }

    @FXML
    void addDrugClicked(ActionEvent event) {
    	Drug newDrug=new Drug();
    	newDrug.setName(drugNameField.getText());
    	newDrug.setActivePrinciple(drugPrincipleField.getText());
    	newDrug.setSellingPrice(Integer.parseInt(drugPriceField.getText()));
    	newDrug.setStock(Integer.parseInt(drugStockField.getText()));
    	
    }

    @FXML
    void addEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void addWareClicked(ActionEvent event) {

    }

    @FXML
    void deleteClientClicked(ActionEvent event) {
    	ObservableList <Client> items,sel;
    	items=clientTable.getItems();
    	sel=clientTable.getSelectionModel().getSelectedItems();
    	for(Client c:sel) {
    		items.remove(c);
    	}
    	
    }

    @FXML
    void deleteCorridorClicked(ActionEvent event) {

    }

    @FXML
    void deleteDrugClicked(ActionEvent event) {

    }

    @FXML
    void deleteEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void deleteWareClicked(ActionEvent event) {

    }

    @FXML
    void loadEmployeeImage(MouseEvent event) {

    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Starts Connection with Database
		JPAManager.connect();
		//Clients Table
		ObservableList methods=FXCollections.observableArrayList();
		methods.addAll("PAYPAL", "VISA", "MASTERCARD", "AMERICAN EXPRESS", "ORGANS");
		comboPayment.getItems().addAll(methods);		
		clientName.setCellValueFactory(new PropertyValueFactory <Client,String>("name"));
		clientAdress.setCellValueFactory(new PropertyValueFactory <Client,String>("address"));
		clientPhone.setCellValueFactory(new PropertyValueFactory <Client,Integer>("telephone"));
		clientMail.setCellValueFactory(new PropertyValueFactory <Client,String>("email"));
		clientPayment.setCellValueFactory(new PropertyValueFactory <Client,PaymentMethod>("paymentMethod"));
		
		clientTable.getItems().addAll(JPAManager.getAllClients());
		
		//Corridor Table
		
		
		
		
	}
    
    

}
