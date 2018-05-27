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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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

		try {
			if (drugPicker.getSelectionModel().getSelectedItem() != null) {
				createdArrives.setDrug(drugPicker.getSelectionModel().getSelectedItem());
			} else {
				throw new NumberFormatException();
			}
			createdArrives.setAmount(Integer.parseInt(stockPicker.getText()));
			newInventoryTable.getItems().add(createdArrives);

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
	void newArrival(MouseEvent event) {
		Arrival toBeAdded = new Arrival();

		try {
			if(providerComboBox.getSelectionModel().getSelectedItem()!=null) {	
			toBeAdded.setProvider(providerComboBox.getSelectionModel().getSelectedItem());
			}else {
				throw new NullPointerException();
			}
			toBeAdded.setDate(java.sql.Date.valueOf(dateNewArrival.getValue()));
			if (yesCheckBox.isSelected()) {
				toBeAdded.setReceived(true);
			} else if(noCheckBox.isSelected()){
				toBeAdded.setReceived(false);
			} else {
				throw new NullPointerException();
			}

			// JPAManager.connect();
			JPAManager.insertArrival(toBeAdded);
			// JPAManager.disconnect();

			for (Arrives item : newInventoryTable.getItems()) {
				Drug droga = newDrugs.getCellObservableValue(item).getValue();
				Integer stock = newStocks.getCellObservableValue(item).getValue();
				Arrives arrive = new Arrives(droga.getId(), toBeAdded.getArrivalId(), stock);
				arrive.setDrug(droga);

				try {
					SQLManager.insertArrives(arrive);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/employeePanel/employeeWindow.fxml"));
				BorderPane root = loader.load();
				EmployeeWindow controller = loader.<EmployeeWindow>getController();
				Scene scene = addArrivalButton.getScene();
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
		try {
			providerComboBox.getItems().addAll(SQLManager.getAllProviders());
			drugPicker.getItems().addAll(SQLManager.getAllDrugs());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		newDrugs.setCellValueFactory(new PropertyValueFactory<Arrives, Drug>("drug"));
		newStocks.setCellValueFactory(new PropertyValueFactory<Arrives, Integer>("amount"));
	}

}
