package gui.clientPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pojos.Client;
import pojos.Delivery;
import gui.MainWindow;
import gui.clientPanel.CartPanelSB;


public class ClientPanelSB {
	
	private Client client;
	private int drugAmount;
	private Delivery delivery;
	private MainWindow mainWindow;
	private Font labelFont;

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
    	
    	mainWindow.logout();
    	
    }

    @FXML
    void highlightCart(MouseEvent event) {
    	
    	labelFont = cart.getFont();
    	cart.setFont(new Font("System", 20));    	
    	cart.setTextFill(Color.CRIMSON);
    	
    }

    @FXML
    void highlightProfile(MouseEvent event) {
    	
    	labelFont = profile.getFont();
    	profile.setFont(new Font("System", 20));  
    	profile.setTextFill(Color.CRIMSON);
    	
    }

    @FXML
    void returnCartToNormal(MouseEvent event) {
    	
    	cart.setFont(labelFont);
    	cart.setTextFill(Color.BLACK);
    	
    }

    @FXML
    void returnProfileToNormal(MouseEvent event) {
    	
    	profile.setFont(labelFont);
    	profile.setTextFill(Color.BLACK);
    	
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
    	drugAmount = 0;
    	cart.setText("Cart: "+ drugAmount + " items"); 
    	
    }
    
    public void setMainWindow(MainWindow mainWindow) {
    	
    	this.mainWindow = mainWindow;
    	
    }

}

