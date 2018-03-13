package gui.clientPanel;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import pojos.Client;

public class ProfilePanel extends FlowPane {
	
	Client client;
	
	Label nameLab;
	Label addressLab;
	Label telephoneLab;
	Label emailLab;
	Label paymentMethodLab;
	
	TextField nameField;
	TextField addressField;
	TextField telephoneField;
	TextField emailField;
	ChoiceBox<String> paymentMethod;
	
	ProfilePanel(Client client){
		this.client = client;
		
		nameLab = new Label("Name");
		addressLab = new Label("Address");
		telephoneLab = new Label("Telephone");
		emailLab = new Label("Email");
		paymentMethodLab = new Label("Payment Method");
		
		nameField = new TextField(client.getName());
		addressField = new TextField(client.getAdress());
		telephoneField = new TextField(client.getTelephone().toString());
		emailField = new TextField(client.getEmail());
		paymentMethod = new ChoiceBox<String>();
		
	}
}
