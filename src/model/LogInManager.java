package model;

import java.sql.SQLException;

import DB.SQLManager;
import pojos.User;

public class LogInManager {

	
		private User trialUser;
		private User completeUser;
		private SQLManager manager = new SQLManager();
		
		
		
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
			manager.connect("jdbc:sqlite:./db/Drug Megastore Users TEST.db");
			
			if(manager.checkUser(trialUser)) {
				completeUser=manager.extractUserByName(trialUser);
				manager.disconnect();
				return true;
			}else {
				manager.disconnect();
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
