package gui.clientPanel;

import java.io.ByteArrayInputStream;
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
	private ShopPanelSB shopPanel;
	private Delivery delivery;

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
		Packaged packaged = new Packaged(drug, delivery ,sellingAmount);
		//it the package already exist in the delivery, we will update it's amount.
		if(delivery.addPackaged(packaged)) {
			//we won't update the delivery in the Database until the client has confirmed the shopping
			try {
				SQLManager.updateDrugStock(drug, (Integer)(drug.getStock()-sellingAmount));
				drug.setStock(drug.getStock()-sellingAmount);
				stockLabel.setText("Stock: "+ drug.getStock());
				shopPanel.updateCart();
			}catch(SQLException e) {
				System.out.println("There was an error updating the drug: "+ drug.getName());
				e.printStackTrace();			
			}
		}else {
			try {
				SQLManager.updateDrugStock(drug, (Integer)(drug.getStock()-sellingAmount));
				drug.setStock(drug.getStock()-sellingAmount);
				stockLabel.setText("Stock: "+ drug.getStock());
				int pos = delivery.positionPackaged(packaged);
				//we put the new amount of that drug in the text field so the buyer knows how many they already have ordered.
				System.out.println(packaged);
				packaged = delivery.getPackage(pos);
				
				System.out.println(packaged +"\nPosition: "+ pos);
				amountTextField.setText(""+(packaged.getAmount()+sellingAmount));
				packaged.setAmount(packaged.getAmount()+sellingAmount);
				//we won't update the cart here, as there hasn't been added a new drug
			}catch(SQLException e) {
				System.out.println("There was an error updating the drug: "+ drug.getName());
				e.printStackTrace();			
			}
			
		}
    }

    @FXML
    void checkAmount(KeyEvent event) {
    	String amountString = amountTextField.getText();
		//we only check the last char typed, because we had already checked the rest
		char character = amountString.charAt(amountString.length()-1);
		if(!(character > 47 && character < 58)) {
			amountTextField.deleteText(amountTextField.getLength()-1, amountTextField.getLength());
		}
		if(!amountTextField.getText().equals("")) {
			int quantity = Integer.parseInt(amountTextField.getText());
			if(quantity > drug.getStock()) {
				
				amountTextField.setText("" + drug.getStock());
				amountTextField.end();
				
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
		stockLabel.setText("Stock: " + drug.getStock());
		if(drug.getPhoto()!= null) {
			drugPhoto.setImage(new Image(new ByteArrayInputStream(drug.getPhoto())));
		}else {
			drugPhoto.setImage(new Image("https://www.ecured.cu/images/thumb/f/f9/Pomo-medicina-icon-azul.png/390px-Pomo-medicina-icon-azul.png"));
		}
	}

	protected void setShoppingPanel(ShopPanelSB clientPanel) {
		this.shopPanel = clientPanel;
	}
	
	protected void setDelivery (Delivery delivery) {
		
		this.delivery = delivery;
		
	}


}
