package gui;

import java.sql.SQLException;

import gui.adminPanel.AdminPane;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import pojos.*;
import pojos.User.UserClass;
import model.*;



public class MainWindow extends Application {
	private Stage stage;
	private TextField user;
	private PasswordField password;
	private BorderPane mainPane;
	private AdminPane adminPanel;
	
	
	
	public static void main (String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		mainPane = new BorderPane();
		stage.setScene(new Scene(logInPanel()));
		stage.show();
	}
	
	public GridPane logInPanel() {
		
		GridPane logInPanel =new GridPane();
			
		Button logInButton=new Button ("Log In");
		user=new TextField ();
		user.setPromptText("User");
		password=new PasswordField ();
		password.setPromptText("Password");
		
		logInButton.setOnAction (e-> tryLogIn());
				
		logInPanel.setVgap(20);
		logInPanel.addColumn(0, user,password,logInButton);
		logInPanel.setAlignment(Pos.CENTER);
		logInPanel.setHalignment(logInButton, HPos.CENTER);
		logInPanel.setMinSize(400,300);
		
		return logInPanel;	
	}
	
	public User logIn(String _user,String _pass) {
		User testUser = new User(_user,_pass);
		LogInManager logInManager = new LogInManager(testUser);

		try {
			if (logInManager.checkExistance()) {
				if (logInManager.checkPassword()) {
					System.out.println("Log in correcto");
					System.out.println(logInManager.getCompleteUser().toString());
					return logInManager.getCompleteUser();
				} else {
					System.out.println("Introduzca la Contraseña correcta");
				}
			} else {
				System.out.println("El usuario no existe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void tryLogIn() {
		User returned;
		returned=logIn(user.getText(),password.getText());
		
		if (!returned.equals(null)) {
			switch (returned.getType().toString()) {
				case "ADMIN":
					adminPanel=new AdminPane();
					stage.setScene(new Scene(adminPanel,800,400));
					stage.show();
					break;
				case "CLIENT":
					break;
				case "EMPLOYEE":
					break;
		}
		
	}
				
	}
	
}
