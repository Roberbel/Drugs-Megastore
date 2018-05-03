package gui.clientPanel;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Client;
import pojos.Client.PaymentMethod;

public class ProfilePanel extends BorderPane {
	
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
	ChoiceBox<PaymentMethod> paymentMethod;
	
	ProfilePanel(Client client){
		this.client = client;
		
		/*
		 * Instance of components
		 */
		nameLab = new Label("Name");
		addressLab = new Label("Address");
		telephoneLab = new Label("Telephone");
		emailLab = new Label("Email");
		paymentMethodLab = new Label("Payment Method");
		
		nameField = new TextField(client.getName());
		addressField = new TextField(client.getAddress());
		telephoneField = new TextField(client.getTelephone().toString());
		emailField = new TextField(client.getEmail());
		paymentMethod = new ChoiceBox<PaymentMethod>();
		paymentMethod.getItems().addAll(PaymentMethod.AMERICAN_EXPRESS,
										PaymentMethod.MASTERCARD,
										PaymentMethod.PAYPAL,
										PaymentMethod.VISA, 
										PaymentMethod.ORGANS);
		paymentMethod.setValue(client.getPaymentMethod());
		
		/*
		 * now we set the event managers
		 */
		nameField.setOnInputMethodTextChanged(e -> nameChanged());
		addressField.setOnInputMethodTextChanged(e -> addressChanged());
		telephoneField.setOnInputMethodTextChanged(e -> telephoneChanged());
		emailField.setOnInputMethodTextChanged(e -> emailChanged());
		paymentMethod.setOnInputMethodTextChanged(e -> paymentMethodChanged());
		
		
		/*
		 * Adding components to Panels
		 */
		GridPane grid = new GridPane();
		grid.add(nameLab, 0, 0);
		grid.add(nameField, 1, 0);
		grid.add(addressLab, 1, 0);
		grid.add(addressField, 1, 1);
		grid.add(telephoneLab, 2, 0);
		grid.add(telephoneField, 2, 1);
		grid.add(emailLab, 3, 0);
		grid.add(emailField, 3, 1);
		grid.add(paymentMethodLab, 4, 0);
		grid.add(paymentMethod, 4, 1);
		setCenter(grid);
		
		
	}
	
	private void nameChanged() {
		client.setName(nameField.getText());
	}
	
	private void addressChanged(){
		client.setAddress(addressField.getText());
	}
	
	private void telephoneChanged() {
		client.setTelephone(Integer.parseInt(telephoneField.getText()));
	}
	
	private void emailChanged() {
		client.setEmail(emailField.getText());
	}
	
	private void paymentMethodChanged() {
		client.setPaymentMethod(paymentMethod.getValue());
	}
}
