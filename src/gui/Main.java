package gui;

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
		try {
			this.window=stage;
			//Remember remember the fifth of November and to change the resource to MainWindow
			Parent root=FXMLLoader.load(getClass().getResource("/gui/clientPanel/ClientPanel.fxml"));
			this.window.setScene(new Scene(root));
			this.window.setResizable(true);
			this.window.show();
			this.window.setOnCloseRequest(e->closeConnection());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void closeConnection(){
		try {
			SQLManager.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
