package gui.employeePanel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DB.JPAManager;
import DB.SQLManager;
import gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Arrival;
import pojos.Arrives;
import pojos.Drug;
import pojos.Provider;

public class AddArrivalPane implements Initializable {

	
	@FXML
	private ComboBox<Provider> providerComboBox;

	@FXML
	private DatePicker dateNewArrival;

	@FXML
	private JFXCheckBox yesCheckBox;

	@FXML
	private JFXCheckBox noCheckBox;

	@FXML
	private TableView<Arrives> newInventoryTable;

	@FXML
	private TableColumn<Arrives, Drug> newDrugs;

	@FXML
	private TableColumn<Arrives, Integer> newStocks;

	@FXML
	private ComboBox<Drug> drugPicker;

	@FXML
	private TextField stockPicker;

	@FXML
	private JFXButton addArrivalButton;
	
	@FXML
	private JFXButton newGroupButton;

	@FXML
	private void handleYesCheckBox() {
		if (yesCheckBox.isSelected()) {
			noCheckBox.setSelected(false);
		}
	}

	@FXML
	private void handleNoCheckBox() {
		if (noCheckBox.isSelected()) {
			yesCheckBox.setSelected(false);
		}
	}
	
	@FXML
	void createArrives(MouseEvent event) {
		
		Arrives createdArrives = new Arrives();
		
		createdArrives.setDrug(drugPicker.getSelectionModel().getSelectedItem());
		createdArrives.setAmount(Integer.parseInt(stockPicker.getText()));
		newInventoryTable.getItems().add(createdArrives);
		
		drugPicker.getSelectionModel().clearSelection();
		stockPicker.clear();
	}
	
	@FXML
	void newArrival (MouseEvent event) {
	    Arrival toBeAdded = new Arrival();
	    
	    toBeAdded.setProvider(providerComboBox.getSelectionModel().getSelectedItem());
	    toBeAdded.setDate(java.sql.Date.valueOf(dateNewArrival.getValue()));
	    if(yesCheckBox.isSelected()) {
	    		toBeAdded.setReceived(true);
	    }else {
	    		toBeAdded.setReceived(false);
	    }
		
	    JPAManager.connect();
	    JPAManager.insertArrival(toBeAdded);
	    JPAManager.disconnect();
	    
	   
	    
	    for (Arrives item : newInventoryTable.getItems()) {
	        Drug droga = newDrugs.getCellObservableValue(item).getValue();
	        Integer stock = newStocks.getCellObservableValue(item).getValue();
	        Arrives arrive = new Arrives(droga.getId(),toBeAdded.getArrivalId(),stock);
	        
	        try {
				SQLManager.insertArrives(arrive);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	   Main main = new Main();
	   
	   
	   main.start(args);
	    
	   
}
	    
		
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			providerComboBox.getItems().addAll(SQLManager.getAllProvider());
			drugPicker.getItems().addAll(SQLManager.getAllDrugs());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		newDrugs.setCellValueFactory(new PropertyValueFactory <Arrives,Drug>("drug"));
		newStocks.setCellValueFactory(new PropertyValueFactory <Arrives,Integer>("amount"));
	}

}
