package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;

import DB.JPAManager;
import DB.SQLManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
		logo = new ImageView(new Image("https://avatars0.githubusercontent.com/u/16308258?s=460&v=4"));
		logo.setFitHeight(100);
		logo.setFitWidth(100);
		logo.setPreserveRatio(true);
		HBox top = new HBox(20);
		top.setAlignment(Pos.CENTER_LEFT);
		profile = new Label(client.getName());
		profile.setFont(Font.font ("Verdana", 20));
		profile.setOnMouseEntered(e->highlightProfile());
		profile.setOnMouseExited(e->returnProfileToNormal());
		profile.setOnMouseClicked(e -> showProfile());
		cart = new Label("Cart");
		cart.setOnMouseClicked(e -> showCart());
		top.getChildren().addAll(logo, new Region(), profile, cart);
		this.setTop(top);
		
		VBox left = new VBox(40);
		left.setAlignment(Pos.CENTER);
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

		left.getChildren().addAll(searchName, searchActivePrinciple, searchMaxPrice, searchButton);
		this.setLeft(left);
		
		drugsPane = new ScrollPane();
		//try {
			drugsPane.setContent(createDrugsPanels(JPAManager.getAllDrugs()));
		//}catch (SQLException e) {
			//System.out.println("Error retrieving all the Drugs from the database");			
		//}
		
		this.setCenter(drugsPane);
		
	}
	
	private void highlightProfile(){
		
		profile.setFont(Font.font ("Verdana", 22));
		profile.setTextFill(Color.DARKBLUE);
	}
	
	private void returnProfileToNormal() {
		
		profile.setFont(Font.font ("Verdana", 20));
		profile.setTextFill(Color.BLACK);
		
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
		System.out.println("Drug list size = "+drugs.size());
		for (int i = 0; i < drugs.size(); i = i+5) {

			HBox hbox = new HBox();
			for(int j = 0; j < 5 && (j+i)<drugs.size(); j++ ) {
				hbox.getChildren().add(new DrugPanel(drugs.get(i+j), delivery));
				System.out.println("creating a new drugpanel for "+drugs.get(i+j));
			}
			box.getChildren().add(hbox);
		
		}
		return box;
		
	}
	
}
