package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pojos.Drug;
import pojos.Packaged;


public class PackagedPanelSB {
	
	private Packaged packaged;
	
	private Drug drug;

	private CartPanelSB parentController;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label activePrincipleLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button removeButton;
    
    @FXML 
    private ImageView drugPhoto;


    @FXML
    void removePanel(MouseEvent event) {
    	
    	parentController.removePanel(this);
    	
    }

    @FXML
    void updateAmount(KeyEvent event) {
    	
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
				quantity = drug.getStock();
				
			}
			if(quantity == 0) {
				
				removePanel(null);
			
			}else {
			
				packaged.setAmount(quantity);
				parentController.recalculateTotalPrice();
			
			}
			
			
		}
    	
    }

    @FXML
    void initialize() {
        assert activePrincipleLabel != null : "fx:id=\"activePrincipleLabel\" was not injected: check your FXML file 'PackagedPanel.fxml'.";
        assert amountTextField != null : "fx:id=\"amountTextField\" was not injected: check your FXML file 'PackagedPanel.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PackagedPanel.fxml'.";
        assert priceLabel != null : "fx:id=\"priceLabel\" was not injected: check your FXML file 'PackagedPanel.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'PackagedPanel.fxml'.";
        assert drugPhoto != null : "fx:id=\"drugPhoto\" was not injected: check your FXML file 'PackagedPanel.fxml'.";

    }
    
    public void setPackaged(Packaged packaged) {
    	
    	drug = packaged.getDrug();
    	this.packaged = packaged;
    	nameLabel.setText("Name: " + drug.getName());
    	activePrincipleLabel.setText("Active Peinciple: " + drug.getActivePrinciple());
    	if(drug.getPhoto()!= null) {
			drugPhoto.setImage(new Image(new ByteArrayInputStream(drug.getPhoto())));
		}else {
			drugPhoto.setImage(new Image("https://www.ecured.cu/images/thumb/f/f9/Pomo-medicina-icon-azul.png/390px-Pomo-medicina-icon-azul.png"));
		}
    	priceLabel.setText("Price per unit: " + drug.getSellingPrice());
    	amountTextField.setText("" + packaged.getAmount());
    	
    	
    }
    
    public Packaged getPackaged() {
    	
    	return packaged;
    	
    }
    
    protected void setParent(CartPanelSB parent) {
    	
    	this.parentController = parent;
    	
    }

}
