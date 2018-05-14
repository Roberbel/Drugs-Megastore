package gui.employeePanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DB.JPAManager;
import DB.SQLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojos.Arrival;
import pojos.Arrives;
import pojos.Client;
import pojos.Delivery;
import pojos.Drug;
import pojos.Packaged;

public class AddDeliveryPane implements Initializable {

    @FXML
    private ComboBox<Client> clientComboBox;

    @FXML
    private DatePicker dateNewArrival;

    @FXML
    private JFXCheckBox yesCheckBox;

    @FXML
    private JFXCheckBox noCheckBox;

    @FXML
    private TableView<Packaged> newInventoryTable;

    @FXML
    private TableColumn<Packaged, Drug> newDrugs;

    @FXML
    private TableColumn<Packaged, Integer> newStocks;

    @FXML
    private ComboBox<Drug> drugPicker;

    @FXML
    private JFXButton newGroupButtom;

    @FXML
    private JFXButton addDeliveryButton;

    @FXML
    private TextField stockPicker;

    @FXML
    void createsPackaged(MouseEvent event) {
    		Packaged createdPackages = new Packaged();
		
		createdPackages.setDrug(drugPicker.getSelectionModel().getSelectedItem());
		createdPackages.setAmount(Integer.parseInt(stockPicker.getText()));
		newInventoryTable.getItems().add(createdPackages);
		
		drugPicker.getSelectionModel().clearSelection();
		stockPicker.clear();
    
    }

    @FXML
    void handleNoCheckBox() {
    	if (noCheckBox.isSelected()) {
			yesCheckBox.setSelected(false);
		}
    }

    @FXML
    void handleYesCheckBox() {
    	if (yesCheckBox.isSelected()) {
			noCheckBox.setSelected(false);
		}
    }

    @FXML
    void newDelivery(MouseEvent event) {
    		Delivery toBeAdded = new Delivery();
	    
	    toBeAdded.setClient(clientComboBox.getSelectionModel().getSelectedItem());
	    toBeAdded.setTransactionDate((java.sql.Date.valueOf(dateNewArrival.getValue())));
	    if(yesCheckBox.isSelected()) {
	    		toBeAdded.setSent(true);
	    }else {
	    		toBeAdded.setSent(false);
	    }
		
	    JPAManager.connect();
	    JPAManager.insertDelivery(toBeAdded);
	    JPAManager.disconnect();
	    
	  
	    
	    for (Packaged item : newInventoryTable.getItems()) {
	        Drug droga = newDrugs.getCellObservableValue(item).getValue();
	        Integer stock = newStocks.getCellObservableValue(item).getValue();
	        Packaged packages = new Packaged(droga.getId(),toBeAdded.getTransactionId(),stock);
	        
	        try {
				SQLManager.insertPackaged(packages);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			clientComboBox.getItems().addAll(SQLManager.getAllClients());
			drugPicker.getItems().addAll(SQLManager.getAllDrugs());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		newDrugs.setCellValueFactory(new PropertyValueFactory <Packaged,Drug>("drug"));
		newStocks.setCellValueFactory(new PropertyValueFactory <Packaged,Integer>("amount"));
	}

}
