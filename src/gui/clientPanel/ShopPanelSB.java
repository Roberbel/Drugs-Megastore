package gui.clientPanel;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import DB.JPAManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import pojos.Delivery;
import pojos.Drug;


public class ShopPanelSB {
	private Delivery delivery;
	private List<Drug> drugs;
	private ClientPanelSB clientPanel;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField activePrincipleTextField;

    @FXML
    private FlowPane drugsFlowPanel;

    @FXML
    private ScrollPane drugsScrollPanel;

    @FXML
    private TextField maxPriceTextField;

    @FXML
    private TextField nameTextField;
    
    @FXML
    void checkPrice(KeyEvent event) {
    
    	String amountString = maxPriceTextField.getText();
		//we only check the last char typed, because we had already checked the rest
	    if( maxPriceTextField.getLength() > 0) {
			char character = amountString.charAt(amountString.length()-1);
			if(!(character > 47 && character < 58)) {
				maxPriceTextField.deleteText(maxPriceTextField.getLength()-1, maxPriceTextField.getLength());
			}
	    }
    	
    	searchDrugs(null);
    }

    @FXML
    void searchDrugs(InputEvent event) {
    	
		String name = nameTextField.getText();
		String activePrinciple = activePrincipleTextField.getText();
		String stringMaxPrice = maxPriceTextField.getText();
		/*
		 * if the name isn't empty we will search mainly by it
		 * if the name is empty we will search mainly by Active principle
		 * if both the active principle and name are empty, we will just look by max price
		 * if everything is empty, we will just retrieve the full list of drugs
		 * 
		 */
			
		if(!name.equals("")) {
				
			if(!activePrinciple.equals("")) {
				if(!stringMaxPrice.equals("")) {
					
					drugs = JPAManager.searchDrugByName(name, activePrinciple, Integer.parseInt(stringMaxPrice));
					
				}else {
						
					drugs = JPAManager.searchDrugByName(name, activePrinciple);
					
				}
			}else if(!stringMaxPrice.equals("")) {
					
				drugs = JPAManager.searchDrugByName(name, Integer.parseInt(stringMaxPrice));
				
			}else {
					
				drugs = JPAManager.searchDrugByName(name);
				
			}
								
		}else if (!activePrinciple.equals("")) {
				
			if(!stringMaxPrice.equals("")) {
					
				drugs = JPAManager.searchDrugByActivePrinciple(activePrinciple, Integer.parseInt(stringMaxPrice));
					
			}else {
					
				drugs = JPAManager.searchDrugByActivePrinciple(activePrinciple);
				
			}
				
		}else if(!stringMaxPrice.equals("")) {
				
			drugs = JPAManager.searchDrugByMaxPrice(Integer.parseInt(stringMaxPrice));
				
		}else {
				
			drugs = JPAManager.getAllDrugs();
				
		}
		createDrugsPanels();

    }
    
    private void createDrugsPanels(){
    	drugsFlowPanel.getChildren().clear();
    	for (Drug d: drugs) {
    		
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/DrugPanel.fxml"));
				AnchorPane drugPanel = loader.load();
				DrugPanelSB controller = (DrugPanelSB) loader.getController();
				controller.setDelivery(delivery);
				controller.setShoppingPanel(this);
				controller.setDrug(d);
				drugsFlowPanel.getChildren().add(drugPanel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Alert alert=new Alert(AlertType.ERROR, "Error loading drug panels");
				alert.showAndWait();
			}
    	}
    	
    	drugsScrollPanel.setContent(drugsFlowPanel);
    	
    }

    @FXML
    void initialize() {
    	assert activePrincipleTextField != null : "fx:id=\"activePrincipleTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert drugsFlowPanel != null : "fx:id=\"drugsFlowPanel\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert drugsScrollPanel != null : "fx:id=\"drugsScrollPanel\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert maxPriceTextField != null : "fx:id=\"maxPriceTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        
        drugsFlowPanel.prefHeightProperty().bind(drugsScrollPanel.heightProperty());
        drugsFlowPanel.prefWidthProperty().bind(drugsScrollPanel.widthProperty());       
        
    }
    
    public void setDelivery(Delivery delivery) {
    	
    	this.delivery = delivery;
    	
    }
    
    public void setClientPanel(ClientPanelSB clientPanel) {
    	this.clientPanel = clientPanel;
    }
    
    protected void updateCart() {
    	
    	clientPanel.increaseCart();
    	
    }

}
