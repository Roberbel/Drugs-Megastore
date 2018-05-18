package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pojos.Delivery;
import pojos.Drug;
import pojos.Packaged;


public class DrugPanelSB {
	
	private Drug drug;
	private Packaged packaged;
	private ShopPanelSB shopPanel;
	private Delivery delivery;
	private int stock;
	
    @FXML
    private ResourceBundle resources;
    
    
    @FXML
    private URL location;

    @FXML
    private Label activePrincipleLabel;

    @FXML
    private Button addToCart;

    @FXML
    private TextField amountTextField;

    @FXML
    private ImageView drugPhoto;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label stockLabel;


    @FXML
    void addToCart(MouseEvent event) {
    	
    	int sellingAmount =  Integer.parseInt(amountTextField.getText());
		stock = stock - sellingAmount;
		stockLabel.setText("Stock: "+ stock);
		//if the package already exist in the delivery, we will update it's amount instead.
		if(packaged != null) {

			packaged.setAmount(packaged.getAmount() + sellingAmount);
			amountTextField.setText("" + packaged.getAmount());
		
		}else {
			
			packaged = new Packaged(drug, delivery ,sellingAmount);
			delivery.addPackaged(packaged);
			shopPanel.updateCart();
		}
    }

    @FXML
    void checkAmount(KeyEvent event) {
    	
    	String amountString = amountTextField.getText();
		//we only check the last char typed, because we had already checked the rest
	    if( amountTextField.getLength() > 0) {
			char character = amountString.charAt(amountString.length()-1);
			if(!(character > 47 && character < 58)) {
				amountTextField.deleteText(amountTextField.getLength()-1, amountTextField.getLength());
			}
			
			if(!amountTextField.getText().equals("")) {
				int quantity = Integer.parseInt(amountTextField.getText());
				if(quantity > stock) {
					
					amountTextField.setText("" + stock);
					amountTextField.end();
					
				}
				
			}
	    }
    }

    @FXML
    void initialize() {
        assert activePrincipleLabel != null : "fx:id=\"activePrincipleLabel\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert addToCart != null : "fx:id=\"addToCart\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert amountTextField != null : "fx:id=\"amountTextField\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert drugPhoto != null : "fx:id=\"drugPhoto\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert priceLabel != null : "fx:id=\"priceLabel\" was not injected: check your FXML file 'DrugPanel.fxml'.";
        assert stockLabel != null : "fx:id=\"stockLabel\" was not injected: check your FXML file 'DrugPanel.fxml'.";


    }
    

	protected void setDrug(Drug drug) {
		this.drug = drug;
		nameLabel.setText("Name: " + drug.getName());
		activePrincipleLabel.setText("Active Principle: " + drug.getActivePrinciple());
		priceLabel.setText("Price: " + drug.getSellingPrice());
		stock = drug.getStock();
		stockLabel.setText("Stock: " + stock);
		if(drug.getPhoto()!= null) {
			drugPhoto.setImage(new Image(new ByteArrayInputStream(drug.getPhoto())));
		}
	}

	protected void setShoppingPanel(ShopPanelSB clientPanel) {
		this.shopPanel = clientPanel;
	}
	
	protected void setDelivery (Delivery delivery) {
		
		this.delivery = delivery;
		
	}


}
