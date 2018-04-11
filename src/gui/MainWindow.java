package gui;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.*;
import pojos.*;

public class MainWindow {

	private LogInManager user;
	
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
    	try {
			if(user.checkExistance()) {
				if(user.checkPassword()) {
					switch(user.getType().toString()) {
					
					case "ADMIN":
						
						break;
					case "EMPLOYEE":
						break;
					case "CLIENT":
						break;
					
					}
				}else {
					throw new ClassNotFoundException();
				}
			}else {
				throw new ClassNotFoundException();
			}
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.show();
		}
    	
    }

}

