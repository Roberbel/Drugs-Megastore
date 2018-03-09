package model;

import java.sql.SQLException;

import DB.SQLManager;
import pojos.User;

public class LogInManager {

	
		private User trialUser;
		private User completeUser;
		
		
		
		public LogInManager() {
			super();
		}

		public LogInManager(User trialUser) {
			super();
			this.trialUser=trialUser;
		}

		public User getTrialUser() {
			return trialUser;
		}

		public void setTrialUser(User trialUser) {
			this.trialUser = trialUser;
		}

		public User getCompleteUser() {
			return completeUser;
		}

		public void setCompleteUser(User completeUser) {
			this.completeUser = completeUser;
		}
		
		
		public boolean checkExistance() throws SQLException, ClassNotFoundException{
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Users TEST.db");
			
			completeUser=SQLManager.extractUserByName(trialUser);
			if(completeUser!=null){	
				SQLManager.disconnect();
				return true;
			}else {
				SQLManager.disconnect();
				return false;
			}
		}
		
		public boolean checkPassword () {
			if(trialUser.getPassword().equals(completeUser.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
		
}