package gui.clientPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class ProfilePanelSB {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressTextField;

    @FXML
    private ListView<?> deliveriesList;

    @FXML
    private ListView<?> deliveryDrugs;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<?> paymentMethodChoiceBox;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button updateButton;


    @FXML
    void updateClientInfo(MouseEvent event) {
    }

    @FXML
    void initialize() {
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'Untitled'.";
        assert deliveriesList != null : "fx:id=\"deliveriesList\" was not injected: check your FXML file 'Untitled'.";
        assert deliveryDrugs != null : "fx:id=\"deliveryDrugs\" was not injected: check your FXML file 'Untitled'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'Untitled'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'Untitled'.";
        assert paymentMethodChoiceBox != null : "fx:id=\"paymentMethodChoiceBox\" was not injected: check your FXML file 'Untitled'.";
        assert telephoneTextField != null : "fx:id=\"telephoneTextField\" was not injected: check your FXML file 'Untitled'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'Untitled'.";


    }

}
