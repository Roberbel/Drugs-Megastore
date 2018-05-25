package gui.clientPanel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.JPAManager;
import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import pojos.Client;
import pojos.Delivery;
import pojos.Drug;
import pojos.Packaged;


public class CartPanelSB {
	
	private Delivery delivery;
	
	private ClientPanelSB parentPanel;

	@FXML
	private VBox vbox;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private ScrollPane packagedScrollPanel;

    @FXML
    private Label paymentMethodLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label priceLabel;


    @FXML
    void confirmDelivery(MouseEvent event) {
    	
    	delivery.setTransactionDate(new Date(System.currentTimeMillis()));
    	JPAManager.insertDelivery(delivery);
    	//we clear the delivery.
    	parentPanel.clearDelivery();
    	parentPanel.showShopPanel(null);

    	
    }

    @FXML
    void initialize() {
        assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert packagedScrollPanel != null : "fx:id=\"packagedScrollPanel\" was not injected: check your FXML file 'Untitled 1'.";
        assert paymentMethodLabel != null : "fx:id=\"paymentMethodLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert phoneLabel != null : "fx:id=\"phoneLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert priceLabel != null : "fx:id=\"priceLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'CartPanel.fxml'.";

        vbox.prefHeightProperty().bind(packagedScrollPanel.heightProperty());
        vbox.prefWidthProperty().bind(packagedScrollPanel.widthProperty());

    }
    
    protected void removePanel(PackagedPanelSB toRemove) {
    	
    	//when we remove a panel we also remove the packaged from the list 
    	System.out.println("Removing panel");
    	
    	if(delivery.removePackaged(toRemove.getPackaged()) ) {
    		vbox.getChildren().clear();
	    	parentPanel.decreaseCart();
	    	delivery.setSellingPrice(0);
	    	for(Packaged p : delivery.getPackages()) {
	    	
	    		delivery.setSellingPrice(delivery.getSellingPrice() + p.getAmount() * p.getDrug().getSellingPrice());
	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/PackagedPanel.fxml"));
				try {
					
					AnchorPane packagedPanel = loader.load();
					PackagedPanelSB controller = (PackagedPanelSB)loader.getController();
					controller.setPackaged(p);
					controller.setParent(this);
					vbox.getChildren().add(packagedPanel);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}else{
    		
    		System.out.println("That panel doesn't exist");
    	}
    		
		
    	priceLabel.setText("Price: " + delivery.getSellingPrice());
    	packagedScrollPanel.setContent(vbox);
    	
    }
    
    protected void recalculateTotalPrice() {
    	
    	delivery.setSellingPrice(0);
    	
    	for(Packaged p : delivery.getPackages()) {
    		
    		delivery.setSellingPrice(delivery.getSellingPrice() + p.getAmount() * p.getDrug().getSellingPrice());
    		
    	}
    	
    	priceLabel.setText("Price: " + delivery.getSellingPrice());
    	
    }
    
    protected void setDelivery(Delivery delivery) {
    	
    	this.delivery = delivery;
    	delivery.setSellingPrice(0);
    	for(Packaged p : delivery.getPackages()) {
    		
    		delivery.setSellingPrice(delivery.getSellingPrice() + p.getAmount() * p.getDrug().getSellingPrice());
    			    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/PackagedPanel.fxml"));
			try {
				
				AnchorPane packagedPanel = loader.load();
				PackagedPanelSB controller = (PackagedPanelSB)loader.getController();
				controller.setPackaged(p);
				controller.setParent(this);
				FlowPane fp = new FlowPane();
				fp.getChildren().add(packagedPanel);
				vbox.getChildren().add(fp);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			
    	}
    	
    	
    	packagedScrollPanel.setContent(vbox);
    	Client client = delivery.getClient();
    	priceLabel.setText("Price: " + delivery.getSellingPrice());
    	addressLabel.setText("Address: " + client.getAddress());
    	paymentMethodLabel.setText("Payment Method: " + client.getPaymentMethod());
    	phoneLabel.setText("Phone: " + client.getTelephone());
    	
    }
    
    public void setParent(ClientPanelSB parentPanel) {
    	
    	this.parentPanel = parentPanel;
    	
    }

}
