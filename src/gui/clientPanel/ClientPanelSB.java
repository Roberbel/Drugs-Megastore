package gui.clientPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Client;
import pojos.Delivery;
import gui.clientPanel.ShopPanelSB;


public class ClientPanelSB {
	
	private Client client;
	private int drugAmount;
	private Delivery delivery;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cart;

    @FXML
    private Label companyName;

    @FXML
    private ImageView logo;

    @FXML
    private BorderPane mainPanel;

    @FXML
    private Label profile;

    @FXML
    private GridPane topPanel;


    @FXML
    void highlightCart(MouseEvent event) {
    }

    @FXML
    void highlightProfile(MouseEvent event) {
    }

    @FXML
    void returnCartToNormal(MouseEvent event) {
    }

    @FXML
    void returnProfileToNormal(MouseEvent event) {
    }

    @FXML
    void showCartPanel(MouseEvent event) {
    }

    @FXML
    public void showShopPanel(MouseEvent event) {
    	try {
	    	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ShopPanel.fxml"));
			AnchorPane shopPanel = loader.load();
			ShopPanelSB controller = (ShopPanelSB)loader.getController();
			controller.setClientPanel(this);
			controller.setDelivery(delivery);
			controller.searchDrugs(null);
			mainPanel.setCenter(shopPanel);
			    	
        }catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Error loading  shop panel");
    		
    	}
    }
    
    @FXML
     protected void showProfilePanel(MouseEvent event) {
    	
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ProfilePanel.fxml"));
			AnchorPane profilePanel = loader.load();
			ProfilePanelSB controller = loader.<ProfilePanelSB>getController();
			
			mainPanel.setCenter(profilePanel);
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Error loading profile panel");
    		
    	}
    	
    }

    @FXML
    void initialize() {
    	
    	drugAmount = 0;
    	delivery = new Delivery();
        assert cart != null : "fx:id=\"cart\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert companyName != null : "fx:id=\"companyName\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert topPanel != null : "fx:id=\"topPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        logo.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Deutsche_Apotheke_Logo.svg/827px-Deutsche_Apotheke_Logo.svg.png"));
        //showShopPanel(null);
    }
    
    public void setClient(Client client) {
    	
    	this.client = client;
    	delivery.setClient(client);
    	
    	profile.setText(client.getName());
    	
    }
    
    protected void updateCart() {
		
		cart.setText("Cart: "+ ++drugAmount+ " items"); 
		
	}

}

