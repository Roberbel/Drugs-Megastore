package gui.clientPanel;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import DB.SQLManager;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pojos.Delivery;
import pojos.Drug;
import pojos.Packaged;

public class DrugPanel extends BorderPane {

	private Delivery delivery;
	private ClientPane clientPanel;
	private Drug drug;
	
	private static final float width = 247;
	
	private ImageView photo;
	private Label name;
	private Label activePrinciple;
	private Label price;
	private Label stock;
	private TextField amount;
	private Button add;
	
	public DrugPanel(Drug drug, Delivery delivery, ClientPane clientPanel) {
		this.drug = drug;
		this.delivery = delivery;
		this.clientPanel = clientPanel;
		
		BorderPane center = new BorderPane();
		if(drug.getPhoto()!= null) {
			photo = new ImageView(new Image(new ByteArrayInputStream(drug.getPhoto())));
		}else {
			photo = new ImageView(new Image("https://www.ecured.cu/images/thumb/f/f9/Pomo-medicina-icon-azul.png/390px-Pomo-medicina-icon-azul.png"));
		}
		photo.setFitHeight(100);
		photo.setFitWidth(100);
		center.setCenter(photo);
		
		VBox centerRight = new VBox(10);
		name = new Label("Name: " + drug.getName());
		name.setWrapText(true);
		
		activePrinciple = new Label("Active Principle: \n"+ drug.getActivePrinciple());
		activePrinciple.setWrapText(true);
		centerRight.getChildren().addAll(name, activePrinciple);
		center.setRight(centerRight);
		
		HBox centerBottom = new HBox(10);
		price = new Label("Price: "+ drug.getSellingPrice());
		
		stock = new Label("Stock: "+ drug.getStock());
		
		centerBottom.getChildren().addAll(price, stock);
		centerBottom.setAlignment(Pos.CENTER);
		center.setBottom(centerBottom);
		this.setCenter(center);
		
		HBox bottom = new HBox(10);
		amount = new TextField();
		amount.setOnKeyReleased(e -> checkAmount());
		
		add = new Button("Add");
		add.setOnMouseClicked(e -> addToCart());
		bottom.getChildren().addAll(amount, add);
		this.setBottom(bottom);
		
		this.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		
	}
	
	private void addToCart() {
		int sellingAmount =  Integer.parseInt(amount.getText());
		Packaged packaged = new Packaged(drug, delivery ,sellingAmount);
		//it the package already exist in the delivery, we will update it's amount.
		if(delivery.addPackaged(packaged)) {
			//we won't update the delivery in the Database until the client has confirmed the shopping
			try {
				SQLManager.updateDrugStock(drug, (Integer)(drug.getStock()-sellingAmount));
				drug.setStock(drug.getStock()-sellingAmount);
				stock.setText("Stock: "+ drug.getStock());
				clientPanel.updateCart();
			}catch(SQLException e) {
				System.out.println("There was an error updating the drug: "+ drug.getName());
				e.printStackTrace();			
			}
		}else {
			try {
				SQLManager.updateDrugStock(drug, (Integer)(drug.getStock()-sellingAmount));
				drug.setStock(drug.getStock()-sellingAmount);
				stock.setText("Stock: "+ drug.getStock());
				//we put the new amount of that drug in the text field so the buyer knows how many they already have ordered.
				packaged = delivery.getPackage(delivery.positionPackaged(packaged));
				amount.setText(""+(packaged.getAmount()+sellingAmount));
				packaged.setAmount(packaged.getAmount()+sellingAmount);
				//we won't update the cart here, as there hasn't been added a new drug
			}catch(SQLException e) {
				System.out.println("There was an error updating the drug: "+ drug.getName());
				e.printStackTrace();			
			}
			
		}
	}
	
	
	private void checkAmount() {
		String amountString = amount.getText();
		//we only check the last char typed, because we had already checked the rest
		char character = amountString.charAt(amountString.length()-1);
		if(!(character > 47 && character < 58)) {
			amount.deleteText(amount.getLength()-1, amount.getLength());
		}
		if(!amount.getText().equals("")) {
			int quantity = Integer.parseInt(amount.getText());
			if(quantity > drug.getStock()) {
				
				amount.setText("" + drug.getStock());
				amount.end();
				
			}
			
		}
		
	}
	
	protected static float GetDrugPanelWidth() {
		
		return width;
		
	}
}
