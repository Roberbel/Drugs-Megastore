package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;

import DB.SQLManager;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pojos.Client;
import pojos.Delivery;
import pojos.Drug;

public class ClientPane extends BorderPane {

	private Client client;
	private Delivery delivery;
	
	private ImageView logo;
	private Label profile;
	private Label cart;

	private ScrollPane drugsPane;

	private TextField searchName;
	private TextField searchActivePrinciple;
	private TextField searchMaxPrice;
	private Button searchButton;
	
	public ClientPane(Client client){
		
		this.client = client;
		delivery = new Delivery(client);
		
		//CHANGE THIS ICON!
		logo = new ImageView(new Image("data:image/png;base64,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"));
		HBox top = new HBox(20);
		profile = new Label(client.getName());
		profile.setOnMouseClicked(e -> showProfile());
		cart = new Label("Cart");
		cart.setOnMouseClicked(e -> showCart());
		top.getChildren().addAll(logo, new Region(), profile, cart);
		
		
		searchName = new TextField();
		searchName.setPromptText("Search Bar");
		searchButton = new Button("Search");
		
		searchActivePrinciple = new TextField();
		searchActivePrinciple.setPromptText("Active principle");
		searchMaxPrice = new TextField();
		searchMaxPrice.setPromptText("Maximun Price per unit");
		
		//we will only search when the name field is changed or the button is pressed.
		searchButton.setOnMouseClicked(e -> searchDrugs());
		searchName.setOnKeyTyped(e -> searchDrugs());
		
		drugsPane = new ScrollPane();
		try {
			drugsPane.setContent(createDrugsPanels(SQLManager.getAllDrugs()));
		}catch (SQLException e) {
			System.out.println("Error retrieving all the Drugs from the database");			
		}
		
		this.setCenter(drugsPane);
	}
	
	private void showCart() {
		
		
	}
	
	private void showProfile() {
		
	}
	
	private void searchDrugs() {
		
		try {
			List<Drug> drugs;
			String name = searchName.getText();
			String activePrinciple = searchActivePrinciple.getText();
			String stringMaxPrice = searchMaxPrice.getText();
			/*
			 * if the name isn't empty we will search mainly by it
			 * if the name is empty we will search mainly by Active principle
			 * if both the active principle and name are empty, we will just look by max price
			 * if everything is empty, we will just retrieve the full list of drugs
			 * 
			 */
			
			if(name != "") {
				
				if(activePrinciple != "") {
					if(stringMaxPrice != "") {
					
						drugs = SQLManager.searchDrugByName(name, activePrinciple, Integer.parseInt(stringMaxPrice));
					
					}else {
						
						drugs = SQLManager.searchDrugByName(name, activePrinciple);
					
					}
				}else if(stringMaxPrice != "") {
					
					drugs = SQLManager.searchDrugByName(name, Integer.parseInt(stringMaxPrice));
				
				}else {
					
					drugs = SQLManager.searchDrugByName(name);
				
				}
								
			}else if (activePrinciple != "") {
				
				if(stringMaxPrice != "") {
					
					drugs = SQLManager.searchDrugByActivePrinciple(activePrinciple, Integer.parseInt(stringMaxPrice));
					
				}else {
					
					drugs = SQLManager.searchDrugByActivePrinciple(activePrinciple);
				
				}
				
			}else if(stringMaxPrice != "") {
				
				drugs = SQLManager.searchDrugByMaxPrice(Integer.parseInt(stringMaxPrice));
				
			}else {
				
				drugs = SQLManager.getAllDrugs();
				
			}
			
			drugsPane.setContent(createDrugsPanels(drugs));
		}catch (SQLException e) {
			System.out.println("Error retrieving all the Drugs from the database");			
		}
	}
	
	private VBox createDrugsPanels(List<Drug> drugs) {
		
		VBox box = new VBox();
		
		for (int i = 0; i < drugs.size(); i = i+5) {

			HBox hbox = new HBox();
			for(int j = 0; j < 5; j++ ) {
				hbox.getChildren().add(new DrugPanel(drugs.get(i+j), delivery));				
			}
			box.getChildren().add(hbox);
		
		}
		return box;
		
	}
	
}
