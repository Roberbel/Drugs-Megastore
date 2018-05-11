package gui.employeePanel;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojos.Client;

public class AddDeliveryPane implements Initializable {

    @FXML
    private ComboBox<Client> ClientComboBox;

    @FXML
    private DatePicker dateNewDelivery;

    @FXML
    private JFXCheckBox yesCheckBox;

    @FXML
    private JFXCheckBox noCheckBox;

    @FXML
    private TableView<?> newInventoryTable;

    @FXML
    private TableColumn<?, ?> newDrugs;

    @FXML
    private TableColumn<?, ?> newStocks;

    @FXML
    private ComboBox<?> drugPicker;

    @FXML
    private ComboBox<?> stockPicker;

    @FXML
    private JFXButton addPackagesButton;
    
    @FXML  
    private void handleYesCheckBox() {
    		if(yesCheckBox.isSelected()) {
    			noCheckBox.setSelected(false);
    		}   	
    }
    
    @FXML  
    private void handleNoCheckBox() {
    		if(noCheckBox.isSelected()) {
    			yesCheckBox.setSelected(false);
    		}   	
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    		
  
    		
    	
    }

}
