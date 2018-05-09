package gui.clientPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DB.SQLManager;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import pojos.Client;
import pojos.Delivery;
import pojos.Drug;


public class ClientPanelSB {
	
	private List<Drug> drugs;
	private Client client;
	private int drugAmount;
	private Delivery delivery;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField activePrincipleTextField;

    @FXML
    private Label cart;

    @FXML
    private Label companyName;

    @FXML
    private ScrollPane drugsScrollPanel;
    
    @FXML
    private FlowPane drugsFlowPanel;

    @FXML
    private GridPane leftPanel;

    @FXML
    private ImageView logo;

    @FXML
    private BorderPane mainPanel;

    @FXML
    private TextField maxPriceTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label profile;

    @FXML
    private Button searchButton;

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

    @FXML
    void showCartPanel(MouseEvent event) {
    }

    @FXML
    void showProfilePanel(MouseEvent event) {
    }

    @FXML
    void initialize() {
    	drugAmount = 0;
    	delivery = new Delivery();
    	drugs = new ArrayList<Drug>();
    	delivery = new Delivery();
    	
    	try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assert activePrincipleTextField != null : "fx:id=\"ActivePrincipleTextField\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert cart != null : "fx:id=\"cart\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert companyName != null : "fx:id=\"companyName\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert drugsScrollPanel != null : "fx:id=\"drugsScrollPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert leftPanel != null : "fx:id=\"leftPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert maxPriceTextField != null : "fx:id=\"maxPriceTextField\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        assert topPanel != null : "fx:id=\"topPanel\" was not injected: check your FXML file 'ClientPanel.fxml'.";
        logo.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Deutsche_Apotheke_Logo.svg/827px-Deutsche_Apotheke_Logo.svg.png"));
        searchDrugs(null);

    }
    
    private void createDrugsPanels(){
    	drugsFlowPanel.getChildren().clear();
    	for (Drug d: drugs) {
    		
    		DrugPanel drugPanel = new DrugPanel(d, delivery,  this);
    		drugsFlowPanel.getChildren().add(drugPanel);
    		
    	}
    	
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
