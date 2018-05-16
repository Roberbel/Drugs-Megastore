package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import gui.adminPanel.AdminWindow;
import gui.clientPanel.ClientPanelSB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import pojos.*;

public class MainWindow {

	private LogInManager user;
	private Stage stage;
	
    @FXML
    private JFXTextField userField;

    @FXML
    private JFXTextField passField;

    @FXML
    private JFXButton signInButton;

    @FXML
    void signInClicked(ActionEvent event) {
    	user=new LogInManager ();
    	user.setUsername(userField.getText());
    	user.setPassword(passField.getText());
    	this.stage=new Stage();
    	try {
			if(user.checkExistance()){
				if(user.checkPassword()) {
					switch(user.getType().toString()) {
					case "ADMIN":
						try {
							Parent root =FXMLLoader.load(getClass().getResource("/gui/adminPanel/adminWindow.fxml"));
							this.stage.setScene(new Scene(root));
							this.stage.setResizable(true);
							this.stage.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						break;
					case "EMPLOYEE":
						break;
					case "CLIENT":
						/*ClientPane pane = new ClientPane(user.getExtractedClient());
						this.stage.setScene(new Scene(pane, 1000, 600));
						this.stage.setResizable(true);
						this.stage.show();*/
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ClientPanel.fxml"));	
						try {
							AnchorPane panel = loader.load();
							ClientPanelSB controller = loader.<ClientPanelSB>getController();
							controller.setClient(user.getExtractedClient());
				            this.stage.setScene(new Scene(panel));
							this.stage.setResizable(true);
							this.stage.show();
							controller.showShopPanel(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					
					}
				}else {
					throw new ClassNotFoundException();
				}
			}else {
				throw new ClassNotFoundException();
			}
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert=new Alert(AlertType.ERROR, "Wrong username or password");
			e.printStackTrace();
			alert.showAndWait();
		}
    	
    }

	public LogInManager getUser() {
		return user;
	}

	public void setUser(LogInManager user) {
		this.user = user;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
    
    

}

