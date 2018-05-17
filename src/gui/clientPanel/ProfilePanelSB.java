package gui.clientPanel;

import java.net.URL;
import java.sql.ClientInfoStatus;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.SQLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pojos.Client;
import pojos.Client.PaymentMethod;
import pojos.Delivery;
import pojos.Drug;
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
    	System.out.println("Selected: " + toShow);
    	//deliveryDrugs = new ListView<Packaged>(FXCollections.<Packaged>observableArrayList(toShow.getPackages()));
    	deliveryDrugs.getItems().setAll(toShow.getPackages());
    	paymentAmount.setText("You payed: "+toShow.getSellingPrice());
    }

    @FXML
    void updateClientInfo(MouseEvent event) {
    	String address = addressTextField.getText();
    	String email = emailTextField.getText();
    	String name = nameTextField.getText();
    	String paymentMethod = paymentMethodChoiceBox.getSelectionModel().getSelectedItem().toString();
    	Integer phone = Integer.parseInt((telephoneTextField.getText()));
    	try {
			SQLManager.updateClient(client.getId(), address, email, phone, paymentMethod);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("There was an error updating the client info");
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
    	if(client.getDeliveries().isEmpty()) {
	    	try {
				client.setDeliveries(SQLManager.searchDeliveryByClientId(client.getId()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
		deliveriesList.getItems().setAll(client.getDeliveries());
		
    }
    
    protected void setClientPanel(ClientPanelSB clientPanel) {
    	
    	
    	
    }

}
