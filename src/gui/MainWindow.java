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
import javafx.stage.Stage;
import model.*;
import pojos.*;

public class MainWindow {

	private Main main;
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
							main.updateScene(new Scene(root));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						break;
					case "EMPLOYEE":
						try {
							Parent root =FXMLLoader.load(getClass().getResource("/gui/employeePanel/employeeWindow.fxml"));
							main.updateScene(new Scene(root));
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case "CLIENT":

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ClientPanel.fxml"));	
						try {
							AnchorPane panel = loader.load();
							ClientPanelSB controller = loader.<ClientPanelSB>getController();
							controller.setClient(user.getExtractedClient());
							main.updateScene(new Scene(panel));
							controller.setMainWindow(this);
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
	
	public void setMain(Main main) {
		
		this.main = main;
		
	}
	
	public void logout() {
		
		main.loadLogin();
		
	}
    
    

}

