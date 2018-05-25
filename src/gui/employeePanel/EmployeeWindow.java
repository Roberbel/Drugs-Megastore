package gui.employeePanel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DB.SQLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPaneBuilder;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Arrival;
import pojos.Arrives;
import pojos.Delivery;
import pojos.Drug;
import pojos.Packaged;

public class EmployeeWindow implements Initializable {

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
	private TableView<Arrives> arrivalInventory;

	@FXML
	private TableColumn<Arrives, String> arrivalDrugs;

	@FXML
	private TableColumn<Arrives, Integer> arrivalStocks;

	@FXML
	private Tab deliveries;

	@FXML
	private ListView<Delivery> deliveryList;

	@FXML
	private JFXButton addDelivery;

	@FXML
	private JFXButton deleteDelivery;

	@FXML
	private DatePicker deliveryDate;

	@FXML
	private TextField deliveryClient;

	@FXML
	private TableView<Packaged> deliveryInventory;

	@FXML
	private TableColumn<Packaged, String> deliveryDrugs;

	@FXML
	private TableColumn<Packaged, Integer> deliveryStocks;

	@FXML
	private Tab inventory;

	@FXML
	private ListView<Drug> inventoryList;

	@FXML
	private JFXButton addInventory;

	@FXML
	private TextField drugActivePrinciple;
	
	@FXML
	private TextField drugCorridor;
	
	@FXML
	private TextField drugStock;
	
	@FXML
	private ImageView drugPhoto;
	
	@FXML
	private SplitPane roberbelPanel;
	
	@FXML
	private BorderPane rigthArrivalPane;
	
	@FXML
	private GridPane showArrivalPane;
	
	@FXML
	private BorderPane rigthDeliveryPane;
	
	@FXML
	private GridPane showDeliveryPane;
	
	@FXML
	private JFXCheckBox yesCheckBox;

	@FXML
	private JFXCheckBox noCheckBox;
	
	@FXML
	private JFXCheckBox yesCheckBox1;

	@FXML
	private JFXCheckBox noCheckBox1;
	
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
	private void handleYesCheckBox1() {
		if (yesCheckBox1.isSelected()) {
			noCheckBox1.setSelected(false);
		}
	}

	@FXML
	private void handleNoCheckBox1() {
		if (noCheckBox1.isSelected()) {
			yesCheckBox1.setSelected(false);
		}
	}
	
	

	@FXML
	void addArrival(ActionEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/gui/employeePanel/AddArrivalPane.fxml"));
        BorderPane panelMostrar=null;
        panelMostrar=loader.load();
        panelMostrar.prefHeightProperty().bind(rigthArrivalPane.heightProperty());
        panelMostrar.prefWidthProperty().bind(rigthArrivalPane.widthProperty());
        rigthArrivalPane.setCenter(panelMostrar);
	}

	@FXML
	void addDelivery(ActionEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/gui/employeePanel/AddDeliveryPane.fxml"));
        BorderPane panelMostrar=null;
        panelMostrar=loader.load();
        panelMostrar.prefHeightProperty().bind(rigthArrivalPane.heightProperty());
        panelMostrar.prefWidthProperty().bind(rigthArrivalPane.widthProperty());
        rigthDeliveryPane.setCenter(panelMostrar);
	}


	@FXML
	void deleteArrival(ActionEvent event) {

		Arrival toBeRemoved = arrivalList.getSelectionModel().getSelectedItem();
		toBeRemoved.getArrives();
		try {
			SQLManager.deleteArrival(toBeRemoved);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		arrivalList.getItems().remove(toBeRemoved);

	}
	
	

	@FXML
	void deleteDelivery(ActionEvent event) {
		Delivery toBeRemoved = deliveryList.getSelectionModel().getSelectedItem();
		try {
			SQLManager.deleteDelivery(toBeRemoved);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		deliveryList.getItems().remove(toBeRemoved);
	}

	@FXML
	void deleteDrug(ActionEvent event) {
		Drug toBeRemoved = inventoryList.getSelectionModel().getSelectedItem();
		try {
			SQLManager.deleteDrug(toBeRemoved);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		inventoryList.getItems().remove(toBeRemoved);
	}
	
	@FXML
	void showArrival(MouseEvent event) {
		
		
        GridPane panelMostrar=showArrivalPane;
        panelMostrar.prefHeightProperty().bind(rigthArrivalPane.heightProperty());
        panelMostrar.prefWidthProperty().bind(rigthArrivalPane.widthProperty());
        rigthArrivalPane.setCenter(panelMostrar);		
        
		yesCheckBox.setSelected(false);
		noCheckBox.setSelected(false);
		
		Arrival toBeShown = arrivalList.getSelectionModel().getSelectedItem();
		arrivalProvider.setText(toBeShown.getProvider().getName());
		arrivalDate.setPromptText(toBeShown.getDate().toString());
		if(toBeShown.isReceived()) {		
			yesCheckBox.setSelected(true);
		}else{
			noCheckBox.setSelected(true);
		}
		arrivalInventory.getItems().clear();
		arrivalInventory.getItems().addAll(toBeShown.getArrives());
	}
	
	@FXML
	void showDelivery(MouseEvent event) {
	
		yesCheckBox1.setSelected(false);
		noCheckBox1.setSelected(false);
		
		Delivery toBeShown = deliveryList.getSelectionModel().getSelectedItem();
		deliveryClient.setText(toBeShown.getClient().getName());
		deliveryDate.setPromptText(toBeShown.getTransactionDate().toString());
		if(toBeShown.isSent()) {		
			yesCheckBox1.setSelected(true);
		}else{
			noCheckBox1.setSelected(true);
		}
		deliveryInventory.getItems().clear();
		deliveryInventory.getItems().addAll(toBeShown.getPackages());
		
		
	}
	
	@FXML
	void showInventory(MouseEvent event) {
		Drug toBeShown = inventoryList.getSelectionModel().getSelectedItem();
		drugActivePrinciple.setText(toBeShown.getActivePrinciple());
		drugCorridor.setText(toBeShown.getCorridor().getId().toString());
		drugStock.setText(toBeShown.getStock().toString());
		if(toBeShown.getPhoto()!= null) {
			drugPhoto.setImage(new Image(new ByteArrayInputStream(toBeShown.getPhoto())));
		}else {
			drugPhoto.setImage(new Image("https://www.ecured.cu/images/thumb/f/f9/Pomo-medicina-icon-azul.png/390px-Pomo-medicina-icon-azul.png"));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {

			arrivalList.getItems().addAll(SQLManager.getAllArrivals());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			deliveryList.getItems().addAll(SQLManager.getAllDeliveries());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			inventoryList.getItems().addAll(SQLManager.getAllDrugs());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arrivalDrugs.setCellValueFactory(new PropertyValueFactory <Arrives,String>("drug"));
		arrivalStocks.setCellValueFactory(new PropertyValueFactory <Arrives, Integer>("amount"));
		deliveryDrugs.setCellValueFactory(new PropertyValueFactory <Packaged,String>("drug"));
		deliveryStocks.setCellValueFactory(new PropertyValueFactory <Packaged, Integer>("amount"));
		drugPhoto.setImage(new Image("https://www.ecured.cu/images/thumb/f/f9/Pomo-medicina-icon-azul.png/390px-Pomo-medicina-icon-azul.png"));
		
	}

}
