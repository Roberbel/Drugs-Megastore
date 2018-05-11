package gui.employeePanel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class addArrivalPane {

    @FXML
    private ComboBox<?> providerComboBox;

    @FXML
    private DatePicker dateNewArrival;

    @FXML
    private JFXCheckBox YesCheckBox;

    @FXML
    private JFXCheckBox NoCheckBox;

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
    private JFXButton addArrivesButton;

}
