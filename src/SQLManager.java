import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLManager {

		public static void main(String[] args) {
			
		
			
		}
		
		public static Connection connect(String directory) throws ClassNotFoundException, SQLException {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection(directory);
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			return c;
		}
	
}
