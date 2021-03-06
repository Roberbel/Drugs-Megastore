package gui.employeePanel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DB.JPAManager;
import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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

		try {
			if (drugPicker.getSelectionModel().getSelectedItem() != null) {
				createdPackages.setDrug(drugPicker.getSelectionModel().getSelectedItem());
			} else {
				throw new NumberFormatException();
			}
			createdPackages.setAmount(Integer.parseInt(stockPicker.getText()));
			newInventoryTable.getItems().add(createdPackages);

			drugPicker.getSelectionModel().clearSelection();
			stockPicker.clear();
		} catch (NumberFormatException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong data type");
			alert.setContentText("Please select a drug and type a number");

			alert.show();
		}
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
		try {
			if (clientComboBox.getSelectionModel().getSelectedItem() != null) {
				toBeAdded.setClient(clientComboBox.getSelectionModel().getSelectedItem());
			} else {
				throw new NullPointerException();
			}
			toBeAdded.setTransactionDate((java.sql.Date.valueOf(dateNewArrival.getValue())));
			if (yesCheckBox.isSelected()) {
				toBeAdded.setSent(true);
			} else if(noCheckBox.isSelected()){
				toBeAdded.setSent(false);
			} else {
				throw new NullPointerException();
			}

			// JPAManager.connect();
			JPAManager.insertDelivery(toBeAdded);
			// JPAManager.disconnect();

			for (Packaged item : newInventoryTable.getItems()) {
				Drug droga = newDrugs.getCellObservableValue(item).getValue();
				Integer stock = newStocks.getCellObservableValue(item).getValue();
				Packaged packages = new Packaged(droga.getId(), toBeAdded.getTransactionId(), stock);
				packages.setDrug(droga);
				try {
					SQLManager.insertPackaged(packages);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/employeePanel/employeeWindow.fxml"));
				BorderPane root = loader.load();
				Scene scene = addDeliveryButton.getScene();
				Stage stage = (Stage) scene.getWindow();
				stage.setScene(new Scene(root));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Empty fields");
			alert.setContentText("Please fill all the fields");

			alert.show();

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// try {
		// JPAManager.connect();
		clientComboBox.getItems().addAll(JPAManager.getAllClients());
		drugPicker.getItems().addAll(JPAManager.getAllDrugs());

		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		newDrugs.setCellValueFactory(new PropertyValueFactory<Packaged, Drug>("drug"));
		newStocks.setCellValueFactory(new PropertyValueFactory<Packaged, Integer>("amount"));
	}

}
