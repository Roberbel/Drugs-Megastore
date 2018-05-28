package gui.clientPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pojos.Client;
import pojos.Client.PaymentMethod;
import pojos.Delivery;
import pojos.Packaged;


public class ProfilePanelSB {
	
	private Client client;

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Label paymentAmount;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField addressTextField;

    @FXML
    private ListView<Delivery> deliveriesList;

    @FXML
    private ListView<Packaged> deliveryDrugs;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<Client.PaymentMethod> paymentMethodChoiceBox;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button updateButton;


    @FXML
    void showDelivery(MouseEvent event) {
    	Delivery toShow = deliveriesList.getSelectionModel().getSelectedItem();
    	deliveryDrugs.getItems().setAll(toShow.getPackages());
    	paymentAmount.setText("You payed: "+toShow.getSellingPrice());
    }

    @FXML
    void updateClientInfo(MouseEvent event) {
    	String address = addressTextField.getText();
    	String email = emailTextField.getText();
    	PaymentMethod paymentMethod = paymentMethodChoiceBox.getSelectionModel().getSelectedItem();
    	Integer phone = Integer.parseInt((telephoneTextField.getText()));
		try {
			SQLManager.updateClient(client.getId(), address, email, phone, paymentMethod);
		} catch (SQLException e) {
			Alert alert=new Alert(AlertType.ERROR, "Error updating the CLient Info");
			alert.showAndWait();
		}
    }

    @FXML
    void initialize() {
        
    	assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert deliveriesList != null : "fx:id=\"deliveriesList\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert deliveryDrugs != null : "fx:id=\"deliveryDrugs\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert paymentAmount != null : "fx:id=\"paymentAmount\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert paymentMethodChoiceBox != null : "fx:id=\"paymentMethodChoiceBox\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert telephoneTextField != null : "fx:id=\"telephoneTextField\" was not injected: check your FXML file 'ProfilePanel.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'ProfilePanel.fxml'.";


        deliveriesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
     
    protected void setClient(Client client){
    	this.client = client;
    	addressTextField.setText(client.getAddress());
    	emailTextField.setText(client.getEmail());
    	nameTextField.setText(client.getName());
    	paymentMethodChoiceBox.getItems().clear();
		paymentMethodChoiceBox.getItems().addAll( PaymentMethod.AMERICAN_EXPRESS,
				PaymentMethod.MASTERCARD,
				PaymentMethod.PAYPAL,
				PaymentMethod.VISA, 
				PaymentMethod.ORGANS);
    	paymentMethodChoiceBox.getSelectionModel().select(client.getPaymentMethod());
    	telephoneTextField.setText(""+client.getTelephone());
    	try {
			client.setDeliveries(SQLManager.searchDeliveryByClientId((client.getId())));
		} catch (SQLException e) {
			Alert alert=new Alert(AlertType.ERROR, "Error retrieving the client");
			alert.showAndWait();
		}
		deliveriesList.getItems().setAll(client.getDeliveries());
		
    }


}
