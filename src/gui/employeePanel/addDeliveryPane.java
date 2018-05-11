package gui.employeePanel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class addDeliveryPane {

    @FXML
    private ComboBox<?> ClientComboBox;

    @FXML
    private DatePicker dateNewDelivery;

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
    private JFXButton addPackagesButton;

}
