package model;

import java.sql.SQLException;

import DB.SQLManager;
import pojos.Client;
import pojos.Employee;

public class LogInManager {

	
		private String username;
		private String password;
		private Employee extractedEmployee;
		private Client extractedClient;
		
		public enum TYPE{
			ADMIN,EMPLOYEE,CLIENT
		}
		
		
		public LogInManager() {
			super();
		}

		
		
		
		public String getUsername() {
			return username;
		}




		public void setUsername(String username) {
			this.username = username;
		}




		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}




		public Employee getExtractedEmployee() {
			return extractedEmployee;
		}




		public void setExtractedEmployee(Employee extractedEmployee) {
			this.extractedEmployee = extractedEmployee;
		}




		public Client getExtractedClient() {
			return extractedClient;
		}




		public void setExtractedClient(Client extractedClient) {
			this.extractedClient = extractedClient;
		}




		public boolean checkExistance() throws SQLException, ClassNotFoundException{
			extractedClient = null;
			extractedEmployee = null;
			
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			
			extractedEmployee = SQLManager.extractEmployeeByUsername(username);
			
			if(extractedEmployee == null) {
				extractedClient = SQLManager.extractClientByUsername(username);
				
				if(extractedClient == null) {
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
			
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
		
}