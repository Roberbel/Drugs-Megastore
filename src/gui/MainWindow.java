package gui;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import DB.SQLManager;
import gui.adminPanel.AdminWindow;
import gui.clientPanel.ClientPanelSB;
import gui.employeePanel.EmployeeWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pojos.*;

public class MainWindow {

	private Main main;
	private Stage stage;
	private String username;
	private String password;
	private Employee extractedEmployee;
	private Client extractedClient;
	
	public enum TYPE{
		ADMIN,EMPLOYEE,CLIENT
	}
	
    @FXML
    private JFXTextField userField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private JFXButton signInButton;

    @FXML
    void signInClicked(ActionEvent event) {
    	username = userField.getText();
    	password = passField.getText();
    	this.stage=new Stage();
    	try {
			if(checkExistance()){
				if(checkPassword()) {
					switch(getType().toString()) {
					case "ADMIN":
						FXMLLoader loaderAdmin=new FXMLLoader(getClass().getResource("/gui/adminPanel/adminWindow.fxml"));
						try {
							BorderPane panel= loaderAdmin.load();
							AdminWindow controller=loaderAdmin.<AdminWindow>getController();
							main.updateScene(new Scene(panel));
							controller.setMainWindow(this);
						} catch (IOException e) {
							Alert alert=new Alert(AlertType.ERROR, "Error loading the admin view");
							alert.showAndWait();
						}
						
						break;
					case "EMPLOYEE":
						FXMLLoader loaderEmp = new FXMLLoader(getClass().getResource("/gui/employeePanel/employeeWindow.fxml"));	
						try {
							BorderPane panel = loaderEmp.load();
							EmployeeWindow controller = loaderEmp.<EmployeeWindow>getController();
							main.updateScene(new Scene(panel));
							controller.setMainWindow(this);
						} catch (IOException e) {
							Alert alert=new Alert(AlertType.ERROR, "Error loading Employee window");
							alert.showAndWait();
						}
						break;
					case "CLIENT":

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/clientPanel/ClientPanel.fxml"));	
						try {
							AnchorPane panel = loader.load();
							ClientPanelSB controller = loader.<ClientPanelSB>getController();
							controller.setClient(extractedClient);
							main.updateScene(new Scene(panel));
							controller.setMainWindow(this);
							controller.showShopPanel(null);
						} catch (IOException e) {
							Alert alert=new Alert(AlertType.ERROR, "Error loading Client view");
							alert.showAndWait();
						}
						break;
					
					}
				}else {
					Alert alert=new Alert(AlertType.ERROR, "Wrong username or password");
					alert.showAndWait();
				}
			}else {
				Alert alert=new Alert(AlertType.ERROR, "Wrong username or password");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert=new Alert(AlertType.ERROR, "Error in the database");
			alert.showAndWait();
		}
    	
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
    
	private boolean checkPassword () {
		if(extractedEmployee != null) {
			if(extractedEmployee.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		} else {
			if(extractedClient.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	private TYPE getType(){
		if(extractedClient != null) {
			return TYPE.CLIENT;
		}else {
			if(extractedEmployee.getIsAdmin()) {
				return TYPE.ADMIN;
			}else{
				return TYPE.EMPLOYEE;
			}
		}
	}
	
	private boolean checkExistance() throws SQLException{
		extractedClient = null;
		extractedEmployee = null;
		
		
		extractedEmployee = SQLManager.searchEmployeeByUsername(username);
		
		if(extractedEmployee == null) {
			extractedClient = SQLManager.searchClientByUsername(username);
			
			if(extractedClient == null) {
				return false;
			}else {
				return true;
			}
		}else {
			return true;
		}
		
	}

}

