package gui.clientPanel;

import java.sql.SQLException;

import DB.SQLManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
		nameField.setEditable(false);
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
		addressField.setOnInputMethodTextChanged(e -> addressChanged());
		telephoneField.setOnInputMethodTextChanged(e -> telephoneChanged());
		telephoneField.setOnKeyReleased(e -> checkPhone());
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
		
	private void addressChanged(){
		try {
			SQLManager.updateClientAdress(client.getId(),addressField.getText());
			client.setAddress(addressField.getText());
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert=new Alert(AlertType.ERROR, "Couldn't save the changes");
			alert.showAndWait();
		}
		
	}
	
	private void telephoneChanged() {
		if(!telephoneField.getText().equals("")) {
			try {
				SQLManager.updateClientPhone(client.getId(), Integer.parseInt(telephoneField.getText()));
				client.setTelephone(Integer.parseInt(telephoneField.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
				Alert alert=new Alert(AlertType.ERROR, "Couldn't save the changes");
				alert.showAndWait();
			}
		}
			
	}
	
	private void emailChanged() {
		try {
			SQLManager.updateClientEmail(client.getId(), emailField.getText());
			client.setEmail(emailField.getText());
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert=new Alert(AlertType.ERROR, "Couldn't save the changes");
			alert.showAndWait();
		}
		
		
	}
	
	private void paymentMethodChanged() {
		try {
			SQLManager.updateClientPaymethod(client.getId(), paymentMethod.getValue().toString());
			client.setPaymentMethod(paymentMethod.getValue());
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert=new Alert(AlertType.ERROR, "Couldn't save the changes");
			alert.showAndWait();
		}
		
		
	}
	
	/*
	 * This method makes sure only numbers are typed in the phone field
	 */
	private void checkPhone() {
		String phone = telephoneField.getText();
		//we only check the last char typed, because we had already checked the rest
		char character = phone.charAt(phone.length());
		if(!(character > 47 && character < 58)) {
			telephoneField.deleteText(telephoneField.getLength()-1, telephoneField.getLength());
		}
		
				
	}
}
