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

public class employeeWindow implements Initializable {

    @FXML
    private Tab arrivals;

    @FXML
    private ListView<?> arrivalList;

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
    private TableView<?> arrivalInventory;

    @FXML
    private TableColumn<?, ?> arrivalDrugs;

    @FXML
    private TableColumn<?, ?> arrivalStocks;

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

    }

    @FXML
    void deleteDelivery(ActionEvent event) {

    }

    @FXML
    void deleteDrug(ActionEvent event) {

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
			ObservableList methods = FXCollections.observableArrayList(SQLManager.getAllArrivals());
			arrivalList.getItems().addAll(methods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

