package gui.employeePanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private ComboBox<Integer> stockPicker;

	@FXML
	private JFXButton addArrivesButton;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			providerComboBox.getItems().addAll(SQLManager.getAllProvider());
			drugPicker.getItems().addAll(SQLManager.getAllDrugs());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
