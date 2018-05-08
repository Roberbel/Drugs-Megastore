package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.JPAManager;
import DB.SQLManager;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
	private int drugAmount;
	
	private ImageView logo;
	private Label profile;
	private Label cart;

	private ScrollPane drugsPane;
	private VBox left;
	
	private TextField searchName;
	private TextField searchActivePrinciple;
	private TextField searchMaxPrice;
	private Button searchButton;
	
	private List<Drug> drugs;
	
	public ClientPane(Client client){
		
		this.client = client;
		delivery = new Delivery(client);
		
		drugAmount = 0;
		
		//CHANGE THIS ICON!
		logo = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Deutsche_Apotheke_Logo.svg/827px-Deutsche_Apotheke_Logo.svg.png"));
		logo.setFitHeight(100);
		logo.setFitWidth(100);
		logo.setPreserveRatio(true);
		logo.setOnMouseClicked(e -> goToSearchTab());
		
		profile = new Label(client.getName());
		profile.setFont(Font.font ("Verdana", 20));
		profile.setOnMouseEntered(e->highlightProfile());
		profile.setOnMouseExited(e->returnProfileToNormal());
		profile.setOnMouseClicked(e -> showProfile());
		
		cart = new Label("Cart: "+drugAmount+ " drugs");
		cart.setFont(Font.font ("Verdana", 20));
		cart.setOnMouseEntered(e->highlightCart());
		cart.setOnMouseExited(e->returnCartToNormal());
		cart.setOnMouseClicked(e -> showCart());
		
		HBox top = new HBox(20);
		top.setAlignment(Pos.CENTER_LEFT);
		top.getChildren().addAll(logo, new Region(), profile, cart);
		this.setTop(top);
		
		left = new VBox(40);
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
		searchName.setOnKeyReleased(e -> searchDrugs());

		left.getChildren().addAll(searchName, searchActivePrinciple, searchMaxPrice, searchButton);
		this.setLeft(left);
		
		drugsPane = new ScrollPane();
		drugsPane.autosize();
		drugsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		drugsPane.setPrefSize(this.getWidth()-200, this.getHeight()-100);
		this.setCenter(drugsPane);
		this.setCursor(Cursor.WAIT);
		//searchDrugs();
		
		this.setRight(new FlowPane());
		
	}
	
	private void highlightProfile(){
		
		profile.setFont(Font.font ("Verdana", 22));
		profile.setTextFill(Color.DARKBLUE);
	}
	
	private void returnProfileToNormal() {
		
		profile.setFont(Font.font ("Verdana", 20));
		profile.setTextFill(Color.BLACK);
		
	}
	
	private void highlightCart() {
		
		cart.setFont(Font.font ("Verdana", 22));
		cart.setTextFill(Color.DARKBLUE);
		
	}
	
	private void returnCartToNormal() {
		
		cart.setFont(Font.font ("Verdana", 20));
		cart.setTextFill(Color.BLACK);
		
	}
	
	private void showCart() {
		
		this.setCenter(null);
		this.setLeft(null);
		
		this.setCenter(new CartPanel(delivery));
		
	}
	
	private void showProfile() {
		
		this.setCenter(null);
		this.setLeft(null);
		
		this.setCenter(new ProfilePanel(client));
		
	}
	
	private void searchDrugs() {
		
		try {
			
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
			this.setCursor(Cursor.WAIT);
			createDrugsPanels();
		}catch (SQLException e) {
			System.out.println("Error retrieving all the Drugs from the database");		
			e.printStackTrace();
		}
	}
	
	private void createDrugsPanels() {
		double width = drugsPane.getWidth();
		double drugPanelWidth = DrugPanel.GetDrugPanelWidth();
			
		System.out.println("ScrollPaneWidth = "+width+"\nDrugPanelWidth = "+drugPanelWidth);
		int n = calculateDrugsPanel(width, drugPanelWidth);
			
		double spacing = ((width-n*drugPanelWidth)/(n-1)) ;
		System.out.println("Spacing = "+spacing+"\nn = "+n);
		VBox box = new VBox(spacing);
		box.getChildren().add(new HBox());
		for (int i = 0; i < drugs.size(); i = i+n) {
	
			HBox hbox = new HBox(spacing);
			//hbox.getChildren().add(new Region());
			for(int j = 0; j < n && (j+i)<drugs.size(); j++ ) {
				hbox.getChildren().add(new DrugPanel(drugs.get(i+j), delivery, this));
			}
			//hbox.getChildren().add(new Region());
			box.getChildren().add(hbox);
			
		}
			
		drugsPane.setContent(box);
		this.setCursor(Cursor.DEFAULT);		
		
	}
	
	private void goToSearchTab() {
		
		this.setLeft(left);
		try {
			this.setCursor(Cursor.WAIT);
			drugs = SQLManager.getAllDrugs();
			createDrugsPanels();
		}catch (SQLException e) {
			System.out.println("Error retrieving all the Drugs from the database");			
		}
		this.setCenter(drugsPane);
		
	}
	
	protected void updateCart() {
		
		cart.setText("Cart: "+ ++drugAmount+ " drugs"); 
		
	}
	
	/*
	 * Algorithym to calculate the number of drugs panel that would fit in the scroll panel 
	 * and the spacing necesary for it
	 */
	private int calculateDrugsPanel(double scrollWidth, double panelWidth) {
		
		double a = panelWidth;
		double b = scrollWidth + panelWidth*2;
		double c = 2*scrollWidth;
		
		double r1 = (b + Math.sqrt( Math.pow(b, 2) - 4*a*c) ) / (2*a);
		double r2 = (b - Math.sqrt( Math.pow(b, 2) - 4*a*c) ) / (2*a);
		System.out.println("r1: \n"+r1+"\n"+(int)r1);
		System.out.println("r2: \n"+r2+"\n"+(int)r2);
		if(r1>r2) {
			return Math.abs((int)r1);
		}else {
			return Math.abs((int)r2);
		}
	}
	
	
}
