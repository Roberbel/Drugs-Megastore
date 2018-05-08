package gui.employeePanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import DB.SQLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojos.Arrival;
import pojos.Arrives;
import pojos.Drug;

public class employeeWindow implements Initializable {

	@FXML
	private Tab arrivals;

	@FXML
	private ListView<Arrival> arrivalList;

	@FXML
	private JFXButton addArrival;

	@FXML
	private JFXButton deleteArrival;

	@FXML
	private DatePicker arrivalDate;

	@FXML
	private TextField arrivalProvider;

	@FXML
	private TextField arrivalStatus;

	@FXML
	private TableView<Arrives> arrivalInventory;

	@FXML
	private TableColumn<Arrives, String> arrivalDrugs;

	@FXML
	private TableColumn<Arrives, Integer> arrivalStocks;

	@FXML
	private Tab deliveries;

	@FXML
	private ListView<?> deliveryList;

	@FXML
	private JFXButton addDelivery;

	@FXML
	private JFXButton deleteDelivery;

	@FXML
	private DatePicker deliveryDate;

	@FXML
	private TextField deliveryClient;

	@FXML
	private TextField deliveryStatus;

	@FXML
	private TableView<?> deliveryInventory;

	@FXML
	private TableColumn<?, ?> deliveryDrugs;

	@FXML
	private TableColumn<?, ?> deliveryStocks;

	@FXML
	private Tab inventory;

	@FXML
	private ListView<?> inventoryList;

	@FXML
	private JFXButton addInventory;

	@FXML
	private JFXButton deleteInventory;

	@FXML
	void addArrival(ActionEvent event) {

	}

	@FXML
	void addDelivery(ActionEvent event) {

	}

	@FXML
	void addDrug(ActionEvent event) {

	}

	@FXML
	void deleteArrival(ActionEvent event) {

		Arrival toBeRemoved = arrivalList.getSelectionModel().getSelectedItem();
		try {
			SQLManager.deleteArrival(toBeRemoved);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		arrivalList.getItems().remove(toBeRemoved);

	}

	@FXML
	void deleteDelivery(ActionEvent event) {

	}

	@FXML
	void deleteDrug(ActionEvent event) {

	}
	
	@FXML
	void showArrival(MouseEvent event) {
		Arrival toBeShown = arrivalList.getSelectionModel().getSelectedItem();
		arrivalProvider.setText(toBeShown.getProvider().getName());
		arrivalDate.setPromptText(toBeShown.getDate().toString());
		arrivalStatus.setText(""+toBeShown.isReceived());
		arrivalInventory.getItems().clear();
		arrivalInventory.getItems().addAll(toBeShown.getArrives());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			arrivalList.getItems().addAll(SQLManager.getAllArrivals());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arrivalDrugs.setCellValueFactory(new PropertyValueFactory <Arrives,String>("drug"));
		arrivalStocks.setCellValueFactory(new PropertyValueFactory <Arrives, Integer>("amount"));
	}

}
