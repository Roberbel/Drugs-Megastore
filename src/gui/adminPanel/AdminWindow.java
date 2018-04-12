package gui.adminPanel;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminWindow {

    @FXML
    private TabPane pojosTabPane;

    @FXML
    private Tab tabClients;

    @FXML
    private TableView<?> clientTable;

    @FXML
    private TableColumn<?, ?> clientName;

    @FXML
    private TableColumn<?, ?> clientAdress;

    @FXML
    private TableColumn<?, ?> clientPhone;

    @FXML
    private TableColumn<?, ?> clientMail;

    @FXML
    private TableColumn<?, ?> clientPayment;

    @FXML
    private TableColumn<?, ?> clientDeliveries;

    @FXML
    private TextField clientNameField;

    @FXML
    private TextField clientAdressField;

    @FXML
    private TextField clientPhoneField;

    @FXML
    private ComboBox<?> comboPayment;

    @FXML
    private JFXButton addClientButton;

    @FXML
    private JFXButton deleteClientButton;

    @FXML
    private Tab drugTab;

    @FXML
    private TableView<?> drugTable;

    @FXML
    private TableColumn<?, ?> drugName;

    @FXML
    private TableColumn<?, ?> drugPrinciple;

    @FXML
    private TableColumn<?, ?> drugPrice;

    @FXML
    private TableColumn<?, ?> drugStock;

    @FXML
    private TextField drugNameField;

    @FXML
    private TextField drugPrincipleField;

    @FXML
    private TextField drugPriceField;

    @FXML
    private TextField drugStockField;

    @FXML
    private JFXButton addDrugButton;

    @FXML
    private JFXButton deleteDrugButton;

    @FXML
    private Tab warehouseTab;

    @FXML
    private TableView<?> warehouseTable;

    @FXML
    private TableColumn<?, ?> warePc;

    @FXML
    private TableColumn<?, ?> wareCountry;

    @FXML
    private TableColumn<?, ?> wareCity;

    @FXML
    private TableColumn<?, ?> wareAdress;

    @FXML
    private TableColumn<?, ?> warePhone;

    @FXML
    private TextField warePcField;

    @FXML
    private TextField wareCountryField;

    @FXML
    private TextField wareCityField;

    @FXML
    private TextField wareAdressField;

    @FXML
    private TextField warePhoneField;

    @FXML
    private JFXButton addWareButton;

    @FXML
    private JFXButton deleteWareClicked;

    @FXML
    private Tab arrivalsTab;

    @FXML
    private Tab deliveriesTab;

    @FXML
    private Tab employeesTab;

    @FXML
    private TableView<?> employeeTable;

    @FXML
    private TableColumn<?, ?> employeeName;

    @FXML
    private TableColumn<?, ?> employeeSalary;

    @FXML
    private TableColumn<?, ?> employeePhone;

    @FXML
    private TableColumn<?, ?> employeePosition;

    @FXML
    private TableColumn<?, ?> employeeWare;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField employeeSalaryField;

    @FXML
    private TextField employeePhoneField;

    @FXML
    private TextField employeePositionField;

    @FXML
    private ComboBox<?> comboWarehouse;

    @FXML
    private JFXButton addEmployeeButton;

    @FXML
    private JFXButton deleteEmployeeButton;

    @FXML
    private Tab corrdirorsTab;

    @FXML
    private TableView<?> corridorsTable;

    @FXML
    private TableColumn<?, ?> corridorId;

    @FXML
    private TableColumn<?, ?> corridorWarehouse;

    @FXML
    private TableColumn<?, ?> corridorTemperature;

    @FXML
    private TextField corridorIdField;

    @FXML
    private ComboBox<?> comboWare;

    @FXML
    private TextField corridorTempField;

    @FXML
    private JFXButton addCorridorButton;

    @FXML
    private JFXButton deleteCorridorButton;

    @FXML
    private ImageView imageView;

    @FXML
    void addClientClicked(ActionEvent event) {

    }

    @FXML
    void addCorridorClicked(ActionEvent event) {

    }

    @FXML
    void addDrugClicked(ActionEvent event) {

    }

    @FXML
    void addEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void addWareClicked(ActionEvent event) {

    }

    @FXML
    void deleteClientClicked(ActionEvent event) {

    }

    @FXML
    void deleteCorridorClicked(ActionEvent event) {

    }

    @FXML
    void deleteDrugClicked(ActionEvent event) {

    }

    @FXML
    void deleteEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void deleteWareClicked(ActionEvent event) {

    }

    @FXML
    void loadEmployeeImage(MouseEvent event) {

    }

}
