package gui.adminPanel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import pojos.*;
import pojos.Client.PaymentMethod;
import DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AdminWindow implements Initializable {
	
	Stage stage;
	byte [] imageLoaded;
	
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
    private JFXListView<?> arrivalsList;

    @FXML
    private TableView<?> inventoryTable;

    @FXML
    private TableColumn<?, ?> inventoryDrug;

    @FXML
    private TableColumn<?, ?> inventoryAmount;
    
    @FXML
    private TextField arrivalProviderField;

    @FXML
    private TextField arrivalProviderDate;

    @FXML
    private TextField arrivedField;

    @FXML
    private TextField arrivalPriceField;

    @FXML
    private JFXListView<?> deliveriesList;

    @FXML
    private TableView<?> itemsBoughtTable;

    @FXML
    private TableColumn<?, ?> itemsDrug;

    @FXML
    private TableColumn<?, ?> itemsAmount;

    @FXML
    private TextField deliveryClientIdField;

    @FXML
    private TextField deliveryDateField;

    @FXML
    private TextField deliveredField;

    @FXML
    private TextField deliveryPriceField;
    
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
    private JFXButton browseButton;
    
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
    private TableColumn<Warehouse,String> wareAdress;

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
    private TableView<Provider> providerTable;

    @FXML
    private TableColumn<Provider,String> providerName;

    @FXML
    private TableColumn<Provider,String> providerAddress;

    @FXML
    private TableColumn<Provider,Integer> providerPhone;

    @FXML
    private TableColumn<Provider,String> providerMail;

    @FXML
    private TextField providerNameField;

    @FXML
    private TextField providerAddressField;

    @FXML
    private TextField providerPhoneField;

    @FXML
    private TextField providerMailField;

    @FXML
    private JFXButton addProviderButton;

    @FXML
    private JFXButton deleteProviderButton;

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
    	
    	try {
    		Corridor newCorridor=new Corridor();
    		newCorridor.setTemperature(Float.parseFloat(corridorTempField.getText()));
    		newCorridor.setWarehouse(comboWare.getSelectionModel().getSelectedItem());
    		corridorsTable.getItems().add(newCorridor);
    		SQLManager.insertCorridor(newCorridor);
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
    	if(this.imageLoaded!=null) {
    		newDrug.setPhoto(imageLoaded);
    		this.imageLoaded=null;
    	}else {
    		File photo = new File("utils/no_photo_1x.jpg");
			InputStream streamBlob;
			try {
				streamBlob = new FileInputStream(photo);
				byte[] bytesBlob = new byte[streamBlob.available()];
				newDrug.setPhoto(bytesBlob);
			} catch ( IOException e) {
				e.printStackTrace();
			}
    	}
    	try {
    		drugTable.getItems().add(newDrug);
    		SQLManager.insertDrug(newDrug);
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
    	newWarehouse.setAddress(wareAdressField.getText());
    	newWarehouse.setPc(Integer.parseInt(warePcField.getText()));
    	newWarehouse.setCity(wareCityField.getText());
    	newWarehouse.setCountry(wareCountryField.getText());
    	newWarehouse.setPhone(Integer.parseInt(warePhoneField.getText()));
    	try {
    		SQLManager.insertWarehouse(newWarehouse);
    		warehouseTable.getItems().add(newWarehouse);
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    }
    
    @FXML
    void addProviderClicked(ActionEvent event) {
    	Provider newProvider=new Provider();
    	newProvider.setAddress(providerAddressField.getText());
    	newProvider.setName(providerNameField.getText());
    	newProvider.setTelephone(Integer.parseInt(providerPhoneField.getText()));
    	newProvider.setEmail(providerMailField.getText());
    	try {
    		SQLManager.insertProvider(newProvider);
    		providerTable.getItems().add(newProvider);
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
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    
    @FXML
    void deleteProviderClicked(ActionEvent event) {
    	ObservableList <Provider> items,sel;
    	items=providerTable.getItems();
    	sel=providerTable.getSelectionModel().getSelectedItems();
    	for(Provider p:sel) {
    		items.remove(p);
    		try {
    			SQLManager.deleteProvider(p);
    		}catch(SQLException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

    //ComboWare and ComboWarehouse are the same fucking thing so I will change it later
    void refreshComboBoxes() {
    	comboWare.getItems().clear();
    	comboCorridor.getItems().clear();
    	comboWarehouse.getItems().clear();
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
    void browseButtonClicked(ActionEvent event) {
    	FileChooser filechooser=new FileChooser(); 
    	FileChooser.ExtensionFilter extFilter=new FileChooser.ExtensionFilter("JPG Files", "*.jpg");
    	filechooser.getExtensionFilters().add(extFilter);
    	File url=filechooser.showOpenDialog(stage);
    	try {
			InputStream blob=new FileInputStream(url);
			byte [] byteBlob=new byte[blob.available()];
			blob.read(byteBlob);
			blob.close();
			this.imageLoaded=byteBlob;
		} catch (IOException e) {
			e.printStackTrace();
		}
   	
    }
    

    @FXML
    void deliveryListClicked(MouseEvent event) {

    }
    
    @FXML
    void arrivalsListClicked(MouseEvent event) {
    	
    }


    @FXML
    void loadEmployeeImage(MouseEvent event) {

    }
    
    @FXML
    void showDrugImage(MouseEvent event) {
    	try {
    		Drug drugtoShow=drugTable.getSelectionModel().getSelectedItem();
    		if(drugtoShow.getPhoto()!=null) {
    			BufferedImage img=ImageIO.read(new ByteArrayInputStream(drugtoShow.getPhoto()));
    			Image picture=SwingFXUtils.toFXImage(img, null);
    			imageView.setImage(picture);
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void updateClient(Event e) {
    	
    	Client c= clientTable.getSelectionModel().getSelectedItem();
    	
    	TableColumn.CellEditEvent<Client, String> ce;
    	ce=(TableColumn.CellEditEvent<Client, String>) e;

    	try {
    		c=SQLManager.searchClientByUsername(c.getUsername());
    		c.setName(ce.getNewValue());
			SQLManager.updateClient(c.getId(), c.getAddress(), c.getEmail(), c.getTelephone(),c.getPaymentMethod(), c.getUsername(), c.getPassword());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void updateCorridorTemp(Event e) {    	
    	Corridor c=corridorsTable.getSelectionModel().getSelectedItem();  	
    	TableColumn.CellEditEvent<Corridor,Float> ce;
    	ce=(TableColumn.CellEditEvent<Corridor, Float>) e; 	    	
    	try {
    		c.setTemperature(ce.getNewValue());
    		SQLManager.updateCorridor(c.getId(), c.getTemperature(), c.getWarehouse());
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    public void updateCorridorWarehouse(Event e) {
    	Corridor c=corridorsTable.getSelectionModel().getSelectedItem();  	
    	TableColumn.CellEditEvent<Corridor,Warehouse> ce;
    	ce=(TableColumn.CellEditEvent<Corridor, Warehouse>) e;
    	try {
    		c.setWarehouse(ce.getNewValue());
    		SQLManager.updateCorridor(c.getId(),c.getTemperature(),c.getWarehouse());
    	}catch(SQLException ex) {
    		
    	}
    }
    
    public void updateDrugStock(Event e) {
    	Drug d=drugTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Drug,Integer> ce;
    	ce=(TableColumn.CellEditEvent<Drug, Integer>)e;
    	try {
    		d.setStock(ce.getNewValue());
    		SQLManager.updateDrug(d.getId(), d.getStock(), d.getSellingPrice(), d.getName(), d.getActivePrinciple(), d.getCorridor(), d.getPhoto());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateDrugPrinciple(Event e) {
    	Drug d=drugTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Drug,String> ce;
    	ce=(TableColumn.CellEditEvent<Drug, String>)e;
    	try {
    		d.setActivePrinciple(ce.getNewValue());
    		SQLManager.updateDrug(d.getId(), d.getStock(), d.getSellingPrice(), d.getName(), d.getActivePrinciple(), d.getCorridor(), d.getPhoto());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateDrugPrice(Event e) {
    	Drug d=drugTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Drug,Integer> ce;
    	ce=(TableColumn.CellEditEvent<Drug, Integer>)e;
    	try {
    		d.setSellingPrice(ce.getNewValue());
    		SQLManager.updateDrug(d.getId(), d.getStock(), d.getSellingPrice(), d.getName(), d.getActivePrinciple(), d.getCorridor(), d.getPhoto());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateDrugCorridor(Event e) {
    	Drug d=drugTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Drug,Corridor> ce;
    	ce=(TableColumn.CellEditEvent<Drug, Corridor>)e;
    	try {
    		d.setCorridor(ce.getNewValue());
    		SQLManager.updateDrug(d.getId(), d.getStock(), d.getSellingPrice(), d.getName(), d.getActivePrinciple(), d.getCorridor(), d.getPhoto());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateDrugName(Event e) {
    	Drug d=drugTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Drug,String> ce;
    	ce=(TableColumn.CellEditEvent<Drug, String>)e;
    	try {
    		d.setName(ce.getNewValue());
    		SQLManager.updateDrug(d.getId(), d.getStock(), d.getSellingPrice(), d.getName(), d.getActivePrinciple(), d.getCorridor(), d.getPhoto());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateWarePc(Event e) {
    	Warehouse w=warehouseTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Warehouse,Integer> ce;
    	ce=(TableColumn.CellEditEvent<Warehouse, Integer>)e;
    	try {
    		w.setPc(ce.getNewValue());
    		SQLManager.updateWarehouse(w.getId(), w.getPc(), w.getCity(), w.getCountry(), w.getAddress(), w.getPhone());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateWareCity(Event e) {
    	Warehouse w=warehouseTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Warehouse,String> ce;
    	ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
    	try {
    		w.setCity(ce.getNewValue());
    		SQLManager.updateWarehouse(w.getId(), w.getPc(), w.getCity(), w.getCountry(), w.getAddress(), w.getPhone());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateWareCountry(Event e) {
    	Warehouse w=warehouseTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Warehouse,String> ce;
    	ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
    	try {
    		w.setCountry(ce.getNewValue());
    		SQLManager.updateWarehouse(w.getId(), w.getPc(), w.getCity(), w.getCountry(), w.getAddress(), w.getPhone());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateWarePhone(Event e) {
    	Warehouse w=warehouseTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Warehouse,Integer> ce;
    	ce=(TableColumn.CellEditEvent<Warehouse, Integer>)e;
    	try {
    		w.setPhone(ce.getNewValue());
    		SQLManager.updateWarehouse(w.getId(), w.getPc(), w.getCity(), w.getCountry(), w.getAddress(), w.getPhone());
    	}catch(SQLException ex) {
    		
    	}
    }
    public void updateWareAddress(Event e) {
    	Warehouse w=warehouseTable.getSelectionModel().getSelectedItem();
    	TableColumn.CellEditEvent<Warehouse,String> ce;
    	ce=(TableColumn.CellEditEvent<Warehouse, String>)e;
    	try {
    		w.setAddress(ce.getNewValue());
    		SQLManager.updateWarehouse(w.getId(), w.getPc(), w.getCity(), w.getCountry(), w.getAddress(), w.getPhone());
    	}catch(SQLException ex) {
    		
    	}
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Starts Connection with Database
		//JPAManager.connect();
		/*try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		try {
			comboWare.getItems().addAll(SQLManager.getAllWarehouses());
			corridorsTable.getItems().addAll(SQLManager.getAllCorridors());
			corridorId.setCellValueFactory(new PropertyValueFactory <Corridor,Integer>("id"));
			corridorWarehouse.setCellValueFactory(new PropertyValueFactory <Corridor,Warehouse>("warehouse"));
			corridorWarehouse.setCellFactory(ComboBoxTableCell.forTableColumn(comboWare.getItems()));
			corridorWarehouse.setOnEditCommit(e->updateCorridorWarehouse(e));
			corridorTemperature.setCellValueFactory(new PropertyValueFactory <Corridor,Float>("temperature"));
			corridorTemperature.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
			corridorTemperature.setOnEditCommit(e->updateCorridorTemp(e));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Drug Table		
		try {
			comboCorridor.getItems().addAll(SQLManager.getAllCorridors());
			drugTable.getItems().addAll(SQLManager.getAllDrugs());
			drugName.setCellValueFactory(new PropertyValueFactory <Drug,String>("name"));
			drugName.setCellFactory(TextFieldTableCell.forTableColumn());
			drugName.setOnEditCommit(e->updateDrugName(e));
			drugPrinciple.setCellValueFactory(new PropertyValueFactory <Drug,String>("activePrinciple"));
			drugPrinciple.setCellFactory(TextFieldTableCell.forTableColumn());
			drugPrinciple.setOnEditCommit(e->updateDrugPrinciple(e));
			drugPrice.setCellValueFactory(new PropertyValueFactory <Drug,Integer>("sellingPrice"));
			drugPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			drugPrice.setOnEditCommit(e->updateDrugPrice(e));
			drugStock.setCellValueFactory(new PropertyValueFactory <Drug,Integer>("stock"));
			drugStock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			drugStock.setOnEditCommit(e->updateDrugStock(e));
			drugCorridor.setCellValueFactory(new PropertyValueFactory <Drug,Corridor>("corridor"));
			drugCorridor.setCellFactory(ComboBoxTableCell.forTableColumn(comboCorridor.getItems()));
			drugCorridor.setOnEditCommit(e->updateDrugCorridor(e));
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
			comboWarehouse.getItems().addAll(SQLManager.getAllWarehouses());
			employeeTable.getItems().addAll(SQLManager.getAllEmployees());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		//Warehouse Table
		warePc.setCellValueFactory(new PropertyValueFactory <Warehouse,Integer>("pc"));
		warePc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		warePc.setOnEditCommit(e->updateWarePc(e));
		wareCountry.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("country"));
		wareCountry.setCellFactory(TextFieldTableCell.forTableColumn());
		wareCountry.setOnEditCommit(e->updateWareCountry(e));
		wareCity.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("city"));
		wareCity.setCellFactory(TextFieldTableCell.forTableColumn());
		wareCity.setOnEditCommit(e->updateWareCity(e));
		wareAdress.setCellValueFactory(new PropertyValueFactory <Warehouse,String>("address"));
		wareAdress.setCellFactory(TextFieldTableCell.forTableColumn());
		wareAdress.setOnEditCommit(e->updateWareAddress(e));
		warePhone.setCellValueFactory(new PropertyValueFactory <Warehouse,Integer>("phone"));
		warePhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		warePhone.setOnEditCommit(e->updateWarePhone(e));
		try {
			warehouseTable.getItems().addAll(SQLManager.getAllWarehouses());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		//Providers Table
		providerName.setCellValueFactory(new PropertyValueFactory<Provider,String>("name"));
		providerName.setCellFactory(TextFieldTableCell.forTableColumn());
		providerName.setOnEditCommit(e->updateProviderName(e));
		providerPhone.setCellValueFactory(new PropertyValueFactory <Provider,Integer>("telephone"));
		providerPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		providerPhone.setOnEditCommit(e->updateProviderPhone(e));
		providerAddress.setCellValueFactory(new PropertyValueFactory<Provider,String>("address"));
		providerAddress.setCellFactory(TextFieldTableCell.forTableColumn());
		providerAddress.setOnEditCommit(e->updateProviderAddress(e));
		providerMail.setCellValueFactory(new PropertyValueFactory <Provider,String>("email"));
		providerMail.setCellFactory(TextFieldTableCell.forTableColumn());
		providerMail.setOnEditCommit(e->updateProviderMail(e));
		try {
			providerTable.getItems().addAll(SQLManager.getAllProviders());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
    
    

}
