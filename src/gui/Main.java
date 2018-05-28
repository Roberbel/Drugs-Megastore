package gui;

import java.io.IOException;
import java.sql.SQLException;

import DB.JPAManager;
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
		try {

			this.window=stage;
			//Remember remember the fifth of November and to change the resource to MainWindow
			Parent root=FXMLLoader.load(getClass().getResource("/gui/MainWindow.fxml"));
			this.window.setScene(new Scene(root));
			this.window.setResizable(true);
			this.window.show();
			this.window.setOnCloseRequest(e->closeConnection());
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			JPAManager.connect();
			loadLogin();
		}catch(SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void closeConnection(){
		try {
			
			SQLManager.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			JPAManager.disconnect();
			System.exit(0);
		}
	}
	
	public void updateScene(Scene scene) {
		
		window.setScene(scene);
		
	}
	
	public void loadLogin() {
		
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainWindow.fxml"));	
			root = loader.load();
			MainWindow controller = loader.getController();
			controller.setMain(this);
			this.window.setScene(new Scene(root));
			this.window.setResizable(true);
			this.window.show();
			this.window.setOnCloseRequest(e->closeConnection());
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("There was some kind of error and we haven't even started!");
		}
		
		
	}

}
