package gui;

import java.io.IOException;
import java.sql.SQLException;

import DB.SQLManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		this.window=stage;
		loadLogin();
	}
	
	private void closeConnection(){
		try {
			System.exit(0);
			SQLManager.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateScene(Scene scene) {
		
		window.setScene(scene);
		
	}
	
	public void loadLogin() {
		
		Parent root;
		try {
			//Remember remember the fifth of November and to change the resource to MainWindow
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainWindow.fxml"));	
			root = loader.load();
			MainWindow controller = loader.getController();
			controller.setMain(this);
			this.window.setScene(new Scene(root));
			this.window.setResizable(true);
			this.window.show();
			this.window.setOnCloseRequest(e->closeConnection());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
