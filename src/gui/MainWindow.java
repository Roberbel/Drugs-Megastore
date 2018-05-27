package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import DB.SQLManager;
import gui.adminPanel.AdminWindow;
import gui.clientPanel.ClientPanelSB;
import gui.employeePanel.EmployeeWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private JFXTextField passField;

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
						try {
							Parent root =FXMLLoader.load(getClass().getResource("/gui/adminPanel/adminWindow.fxml"));
							main.updateScene(new Scene(root));
						} catch (IOException e) {
							e.printStackTrace();
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
							e.printStackTrace();
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
    
	public boolean checkPassword () {
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
	
	public TYPE getType(){
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
	
	public boolean checkExistance() throws SQLException, ClassNotFoundException{
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

