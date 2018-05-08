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
import javafx.scene.layout.BorderPane;

public class AdminWindow implements Initializable {
	
	@FXML
	private BorderPane window;
	
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
    private TableColumn<Drug,Corridor> drugCorridor;

    @FXML
    private TextField drugNameField;

    @FXML
    private TextField drugPrincipleField;

    @FXML
    private TextField drugPriceField;

    @FXML
    private TextField drugStockField;
    
    @FXML
    private ComboBox <Corridor> comboCorridor;

    @FXML
    private JFXButton addDrugButton;

    @FXML
    private JFXButton deleteDrugButton;

    @FXML
    private Tab warehouseTab;

    @FXML
    private TableView<Warehouse> warehouseTable;

    @FXML
    private TableColumn<Warehouse,Integer> warePc;

    @FXML
    private TableColumn<Warehouse,String> wareCountry;

    @FXML
    private TableColumn<Warehouse,String> wareCity;

    @FXML
    private TableColumn<Warehouse,String> wareAddress;

    @FXML
    private TableColumn<Warehouse,Integer> warePhone;

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
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> employeeName;

    @FXML
    private TableColumn<Employee, Integer> employeeSalary;

    @FXML
    private TableColumn<Employee, Integer> employeePhone;

    @FXML
    private TableColumn<Employee, String> employeePosition;

    @FXML
    private TableColumn<Employee, Warehouse> employeeWare;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField employeeSalaryField;

    @FXML
    private TextField employeePhoneField;

    @FXML
    private TextField employeePositionField;

    @FXML
    private ComboBox<Warehouse> comboWarehouse;

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
	    	newClient.setUsername(newClient.getEmail());
	    	newClient.setPassword("Sauron");
	    	clientTable.getItems().add(newClient);
	    	SQLManager.insertClient(newClient);	    	    	
    	}catch(NumberFormatException | NullPointerException | SQLException ex) {
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.show();		
    		ex.printStackTrace();
    	}
  	
    }

    @FXML
    void addCorridorClicked(ActionEvent event) {
    	Corridor newCorridor=new Corridor();
    	newCorridor.setTemperature(Float.parseFloat(corridorTempField.getText()));
    	newCorridor.setWarehouse(comboWare.getSelectionModel().getSelectedItem());
    	try {    	
    		SQLManager.insertCorridor(newCorridor);
    		corridorsTable.getItems().add(newCorridor);
    		refreshComboBoxes();
    	}catch(NumberFormatException | NullPointerException | SQLException ex) {
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.show();	
    		ex.printStackTrace();
    	}   	
    	
    }

    @FXML
    void addDrugClicked(ActionEvent event) {
    	Drug newDrug=new Drug();
    	newDrug.setName(drugNameField.getText());
    	newDrug.setActivePrinciple(drugPrincipleField.getText());
    	newDrug.setSellingPrice(Integer.parseInt(drugPriceField.getText()));
    	newDrug.setStock(Integer.parseInt(drugStockField.getText()));
    	newDrug.setCorridor(comboCorridor.getSelectionModel().getSelectedItem());
    	try {
    		SQLManager.insertDrug(newDrug);
    		drugTable.getItems().add(newDrug);
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    }

    @FXML
    void addEmployeeClicked(ActionEvent event) {
    	Employee newEmployee= new Employee();
    	newEmployee.setName(employeeNameField.getText());
    	newEmployee.setSalary(Integer.parseInt(employeeSalaryField.getText()));
    	newEmployee.setPhone(Integer.parseInt(employeePhoneField.getText()));
    	newEmployee.setPosition(employeePositionField.getText());
    	newEmployee.setWarehouse(comboWarehouse.getSelectionModel().getSelectedItem());
    	newEmployee.setUsername("Thor");
    	newEmployee.setPassword("god of thunder");
    	try {
			SQLManager.insertEmployee(newEmployee);
			employeeTable.getItems().add(newEmployee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void addWareClicked(ActionEvent event) {
    	Warehouse newWarehouse = new Warehouse();
    	newWarehouse.setAdress(wareAdressField.getText());
    	newWarehouse.setPc(Integer.parseInt(warePcField.getText()));
    	newWarehouse.setCity(wareCityField.getText());
    	newWarehouse.setCountry(wareCountryField.getText());
    	newWarehouse.setPhone(Integer.parseInt(warePhoneField.getText()));
    	try {
    		SQLManager.insertWarehouse(newWarehouse);
    		warehouseTable.getItems().add(newWarehouse);
    		refreshComboBoxes();
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    }

    @FXML
    void deleteClientClicked(ActionEvent event) {
    	ObservableList <Client> items,sel;
    	items=clientTable.getItems();
    	sel=clientTable.getSelectionModel().getSelectedItems();
    	for(Client c:sel) {
    		items.remove(c);
    		try {
				SQLManager.deleteClient(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}    	
    }

    @FXML
    void deleteCorridorClicked(ActionEvent event) {
    	ObservableList <Corridor> items,sel;
    	items=corridorsTable.getItems();
    	sel=corridorsTable.getSelectionModel().getSelectedItems();
    	for(Corridor c:sel) {
    		items.remove(c);
    		try {
    			SQLManager.deleteCorridor(c);
    			refreshComboBoxes();
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

    @FXML
    void deleteDrugClicked(ActionEvent event) {
    	ObservableList <Drug> items, sel;
    	items=drugTable.getItems();
    	sel=drugTable.getSelectionModel().getSelectedItems();
    	for(Drug d:sel) {
    		items.remove(d);
    		try {
    			SQLManager.deleteDrug(d);
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

    @FXML
    void deleteEmployeeClicked(ActionEvent event) {
    	ObservableList <Employee> items,sel;
    	items=employeeTable.getItems();
    	sel=employeeTable.getSelectionModel().getSelectedItems();
    	for(Employee e:sel) {
    		items.remove(e);
    		try {
    			SQLManager.deleteEmployee(e);
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

    @FXML
    void deleteWareClicked(ActionEvent event) {
    	ObservableList <Warehouse> items,sel;
    	items=warehouseTable.getItems();
    	sel=warehouseTable.getSelectionModel().getSelectedItems();
    	for(Warehouse w:sel) {
    		items.remove(w);
    		try {
    			SQLManager.deleteWarehouse(w);
    			refreshComboBoxes();
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    //ComboWare and ComboWarehouse are the same fucking thing so I will change it later
    void refreshComboBoxes() {
    	comboWare.getItems().removeAll();
    	comboCorridor.getItems().removeAll();
    	comboWarehouse.getItems().removeAll();
    	try {
			comboWare.getItems().addAll(SQLManager.getAllWarehouses());
			comboCorridor.getItems().addAll(SQLManager.getAllCorridors());
	    	comboWarehouse.getItems().addAll(SQLManager.getAllWarehouses());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void loadEmployeeImage(MouseEvent event) {
    	
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Starts Connection with Database
		//JPAManager.connect();
		try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Clients Table
		ObservableList methods=FXCollections.observableArrayList();
		methods.addAll("PAYPAL", "VISA", "MASTERCARD", "AMERICAN EXPRESS", "ORGANS");
		comboPayment.getItems().addAll(methods);		
		clientName.setCellValueFactory(new PropertyValueFactory <Client,String>("name"));
		clientAdress.setCellValueFactory(new PropertyValueFactory <Client,String>("address"));
		clientPhone.setCellValueFactory(new PropertyValueFactory <Client,Integer>("telephone"));
		clientMail.setCellValueFactory(new PropertyValueFactory <Client,String>("email"));
		clientPayment.setCellValueFactory(new PropertyValueFactory <Client,PaymentMethod>("paymentMethod"));
		try {
			clientTable.getItems().addAll(SQLManager.getAllClients());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Corridors Table
		corridorId.setCellValueFactory(new PropertyValueFactory <Corridor,Integer>("id"));
		corridorWarehouse.setCellValueFactory(new PropertyValueFactory <Corridor,Warehouse>("warehouse"));
		corridorTemperature.setCellValueFactory(new PropertyValueFactory <Corridor,Float>("temperature"));
		try {
			//comboWare.getItems().addAll(SQLManager.getAllWarehouses());
			corridorsTable.getItems().addAll(SQLManager.getAllCorridors());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Drug Table		
		drugName.setCellValueFactory(new PropertyValueFactory <Drug,String>("name"));
		drugPrinciple.setCellValueFactory(new PropertyValueFactory <Drug,String>("activePrinciple"));
		drugPrice.setCellValueFactory(new PropertyValueFactory <Drug,Integer>("sellingPrice"));
		drugStock.setCellValueFactory(new PropertyValueFactory <Drug,Integer>("stock"));	
		drugCorridor.setCellValueFactory(new PropertyValueFactory <Drug,Corridor>("corridor"));
		try {
			//comboCorridor.getItems().addAll(SQLManager.getAllCorridors());
			drugTable.getItems().addAll(SQLManager.getAllDrugs());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Employee Table
		employeeName.setCellValueFactory(new PropertyValueFactory <Employee,String>("name"));
		employeeSalary.setCellValueFactory(new PropertyValueFactory <Employee,Integer>("salary"));
		employeePhone.setCellValueFactory(new PropertyValueFactory <Employee,Integer>("phone"));
		employeePosition.setCellValueFactory(new PropertyValueFactory <Employee,String>("position"));
		employeeWare.setCellValueFactory(new PropertyValueFactory <Employee,Warehouse>("warehouse"));
		try {
			//comboWarehouse.getItems().addAll(SQLManager.getAllWarehouses());
			employeeTable.getItems().addAll(SQLManager.getAllEmployee());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		//Warehouse Table
		warePc.setCellValueFactory(new PropertyValueFactory <Warehouse,Integer>("pc"));
		wareCountry.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("country"));
		wareCity.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("city"));
		wareAddress.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("address"));
		warePhone.setCellValueFactory(new PropertyValueFactory <Warehouse,Integer>("phone"));
		try {
			warehouseTable.getItems().addAll(SQLManager.getAllWarehouses());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		refreshComboBoxes();
		
		
	}
	
	
    
    

}
