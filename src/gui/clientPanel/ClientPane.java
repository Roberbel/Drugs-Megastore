package gui.clientPanel;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pojos.Client;
import pojos.Drug;

public class ClientPane extends BorderPane {

	Client client;
	
	//[PICTURE TYPE] logo;
	Label profile;
	Label cart;
	int cartNum;
	List<Drug> cartItems;
	List<Integer> cartAmmount;
	
	TextField search;
	ChoiceBox<String> activePrinciple;
	TextField maxPrice;
	
	List<BorderPane> panels;
	List<DrugPanel> drugPanels;
	List<TextField> textFields;
	List<Label> totalPrices;
	List<Button> buttons;
		
	
	ClientPane(Client client){
		this.client = client;
		
		profile = new Label(client.getName());
		profile.setOnMouseClicked(e -> showProfile());
		cartNum = 0;
		cart = new Label("Cart: " + cartNum);
		
		search = new TextField();
		search.setPromptText("Search Bar");
		/*
		 * initialization of active principle
		 */
		maxPrice = new TextField();
		maxPrice.setPromptText("Maximun Price per unit");
		
		search.setOnKeyTyped(e -> searchDrugs());
		maxPrice.setOnKeyTyped(e -> searchDrugs());
		
		
		
	}
	
	
	private void showProfile() {
		
	}
	
	private void searchDrugs() {
		
		
	}
	
	private GridPane createDrugsPanels(List<Drug> drugs) {
		
		return null;
	}
	
}
