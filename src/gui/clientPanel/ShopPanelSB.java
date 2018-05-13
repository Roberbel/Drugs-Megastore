package gui.clientPanel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import DB.JPAManager;
import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
    private FlowPane drugsFlowPanel;

    @FXML
    private ScrollPane drugsScrollPanel;

    @FXML
    private AnchorPane anchorPanelScroll;


    @FXML
    private FlowPane mainFlowPanel;

    @FXML
    private GridPane mainGridPanel;
    
    @FXML
    private TextField activePrincipleTextField;

    @FXML
    private TextField maxPriceTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button searchButton;


    @FXML
    void searchDrugs(InputEvent event) {
try {
			
			String name = nameTextField.getText();
			String activePrinciple = activePrincipleTextField.getText();
			System.out.println(activePrinciple);
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
					
						drugs = SQLManager.searchDrugByName(name, activePrinciple, Integer.parseInt(stringMaxPrice));
					
					}else {
						
						drugs = SQLManager.searchDrugByName(name, activePrinciple);
					
					}
				}else if(!stringMaxPrice.equals("")) {
					
					drugs = SQLManager.searchDrugByName(name, Integer.parseInt(stringMaxPrice));
				
				}else {
					
					System.out.println("getting drugs starting with '"+name+"'");
					drugs = SQLManager.searchDrugByName(name);
				
				}
								
			}else if (!activePrinciple.equals("")) {
				
				if(!stringMaxPrice.equals("")) {
					
					drugs = SQLManager.searchDrugByActivePrinciple(activePrinciple, Integer.parseInt(stringMaxPrice));
					
				}else {
					
					drugs = SQLManager.searchDrugByActivePrinciple(activePrinciple);
				
				}
				
			}else if(!stringMaxPrice.equals("")) {
				
				drugs = SQLManager.searchDrugByMaxPrice(Integer.parseInt(stringMaxPrice));
				
			}else {
				
				drugs = SQLManager.getAllDrugs();
				
			}
			createDrugsPanels();
		}catch (SQLException e) {
			System.out.println("Error retrieving all the Drugs from the database");		
			e.printStackTrace();
		}
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
				System.out.println("error");
			}
    	}
    	
    	drugsScrollPanel.setContent(drugsFlowPanel);
    	
    }

    @FXML
    void initialize() {
        assert activePrincipleTextField != null : "fx:id=\"activePrincipleTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert maxPriceTextField != null : "fx:id=\"maxPriceTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'ShopPanel.fxml'.";
        drugsFlowPanel.prefHeightProperty().bind(drugsScrollPanel.heightProperty());
        drugsFlowPanel.prefWidthProperty().bind(drugsScrollPanel.widthProperty());
        
        mainGridPanel.prefHeightProperty().bind(mainFlowPanel.heightProperty());
        mainGridPanel.prefWidthProperty().bind(mainFlowPanel.widthProperty());
        
        anchorPanelScroll.prefHeightProperty().bind(mainGridPanel.heightProperty());
        anchorPanelScroll.prefWidthProperty().bind(mainGridPanel.widthProperty());
        
        drugsScrollPanel.prefHeightProperty().bind(anchorPanelScroll.heightProperty());
        drugsScrollPanel.prefWidthProperty().bind(anchorPanelScroll.widthProperty());
        
        
        
    }
    
    public void setDelivery(Delivery delivery) {
    	
    	this.delivery = delivery;
    	
    }
    
    public void setClientPanel(ClientPanelSB clientPanel) {
    	this.clientPanel = clientPanel;
    }
    
    protected void updateCart() {
    	
    	clientPanel.updateCart();
    	
    }

}
