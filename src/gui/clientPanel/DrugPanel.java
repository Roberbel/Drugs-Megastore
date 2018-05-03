package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import DB.SQLManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pojos.Delivery;
import pojos.Drug;

public class DrugPanel extends BorderPane {

	private Delivery delivery;
	private Drug drug;
	
	private ImageView photo;
	private Label name;
	private Label activePrinciple;
	private Label price;
	private Label stock;
	private TextField amount;
	private Button add;
	
	public DrugPanel(Drug drug, Delivery delivery) {
		
		this.drug = drug;
		this.delivery = delivery;
		
		BorderPane center = new BorderPane();
				
		photo = new ImageView(new Image(new ByteArrayInputStream(drug.getPhoto())));
		center.setCenter(photo);
		
		VBox centerLeft = new VBox(10);
		name = new Label("Name: " + drug.getName());
		
		activePrinciple = new Label("Active Principle: "+ drug.getActivePrinciple());
		centerLeft.getChildren().addAll(name, activePrinciple);
		center.setLeft(centerLeft);
		
		HBox centerBottom = new HBox(10);
		price = new Label("Price: "+ drug.getSellingPrice());
		
		stock = new Label("Stock: "+ drug.getStock());
		
		centerBottom.getChildren().addAll(price, stock);
		center.setBottom(centerBottom);
		this.setCenter(center);
		
		HBox bottom = new HBox(10);
		amount = new TextField();
		amount.setOnKeyTyped(e -> checkAmount());
		
		add = new Button("Add");
		add.setOnMouseClicked(e -> addToCart());
		bottom.getChildren().addAll(amount, add);
		this.setBottom(bottom);
		
		
		
		
	}
	
	private void addToCart() {
		int sellingAmount =  Integer.parseInt(amount.getText());
		delivery.addPackaged(drug, sellingAmount);
		//we won't update the delivery in the Database until the client has confirmed the shopping
		try {
			SQLManager.updateDrugStock(drug, (Integer)(drug.getStock()-sellingAmount));
		}catch(SQLException e) {
			System.out.println("There was an error updating the drug: "+ drug.getName());
			e.printStackTrace();			
		}
		
	}
	
	
	private void checkAmount() {
		
		int quantity = Integer.parseInt(amount.getText());
		if(quantity > drug.getStock()) {
			
			amount.setText("" + drug.getStock());
			
		}
		
	}
}
