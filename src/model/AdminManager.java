package model;

import java.sql.SQLException;

import DB.SQLManager;
import pojos.Client;
import pojos.User;

public class AdminManager {
	
	
		public void insertClient(Client client,User usersClient) throws SQLException {
			
			Client databaseClient = new Client();
			
			SQLManager.insertClientEntrance(client);
			SQLManager.getClientById
			
			usersClient.setId(databaseClient.getId());
			SQLManager.insertUserEntrance(usersClient);
	
		}
		
		
	
	
}
