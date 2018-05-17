package gui.clientPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.JPAManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Client;
import pojos.Delivery;
import gui.clientPanel.CartPanelSB;


public class ClientPanelSB {
	
	private Client client;
	private int drugAmount;
	private Delivery delivery;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button logOutButton;
    
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
    void logOut(MouseEvent event) {
    }

    @FXML
    void highlightCart(MouseEvent event) {
    }

    @FXML
    void highlightProfile(MouseEvent event) {
    }

    @FXML
    void returnCartToNormal(MouseEvent event) {
    	
    	//cart.setFont(new Font());
    	
    }

    @FXML
    void returnProfileToNormal(MouseEvent event) {
    }

    @FXML
    void showCartPanel(MouseEvent event) {
    	try {
	    	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/CartPanel.fxml"));
			AnchorPane cartPanel = loader.load();
			CartPanelSB controller = (CartPanelSB)loader.getController();
			cartPanel.prefWidthProperty().bind(this.mainPanel.widthProperty());
			cartPanel.prefHeightProperty().bind(this.mainPanel.heightProperty());
			controller.setDelivery(delivery);
			controller.setParent(this);
			mainPanel.setCenter(cartPanel);
			    	
        }catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Error loading  cart panel");
    		
    	}
    }

    @FXML
    public void showShopPanel(MouseEvent event) {
    	try {
	    	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ShopPanel.fxml"));
			AnchorPane shopPanel = loader.load();
			ShopPanelSB controller = (ShopPanelSB)loader.getController();
			shopPanel.prefWidthProperty().bind(this.mainPanel.widthProperty());
			shopPanel.prefHeightProperty().bind(this.mainPanel.heightProperty());
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
			profilePanel.prefWidthProperty().bind(this.mainPanel.widthProperty());
			profilePanel.prefHeightProperty().bind(this.mainPanel.heightProperty());
			controller.setClient(client);
			controller.setClientPanel(this);
			mainPanel.setCenter(profilePanel);
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Error loading profile panel");
    		
    	}
    	
    }

    @FXML
    void initialize() {
    	
    	
    	drugAmount = 0;
    	 assert cart != null : "fx:id=\"cart\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert companyName != null : "fx:id=\"companyName\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         assert topPanel != null : "fx:id=\"topPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
         
        logo.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Deutsche_Apotheke_Logo.svg/827px-Deutsche_Apotheke_Logo.svg.png"));
    }
    
    public void setClient(Client client) {
    	
    	this.client = client;

    	delivery = new Delivery(client);
    	profile.setText(client.getName());
    	
    }
    
    protected void increaseCart() {
		
		cart.setText("Cart: "+ ++drugAmount + " items"); 
		
	}
    
    protected void decreaseCart() {
    	
    	cart.setText("Cart: "+ --drugAmount + " items"); 
    }
    
    protected void clearDelivery() {
    	
    	delivery = new Delivery(client);
    	
    }

}

