import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		
		
		public static void createClientTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE client"
					+"(id INT PRIMARY KEY,"
					+"name TEXT NOT NULL,"
					+"adress TEXT NOT NULL,"
					+"telephone INT,"
					+"email TEXT,"
					+"payment_method TEXT NOT NULL)"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createEployeeTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE employee"
					+"(id INT PRIMARY KEY,"
					+"name TEXT NOT NULL,"
					+"salary FLOAT NOT NULL,"
					+"phone INT NOT NULL,"
					+"position TEXT NOT NULL,"
					+"warehouse_id REFERENCES warehouse (id))"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createCorridorTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE corridor"
					+"(id INT PRIMARY KEY AUTOINCREMENT,"
					+"temperature FLOAT NOT NULL,"
					+"warehouse_id REFERENCES warehouse (id))"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createWarehouseTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE warehouse"
					+"(id INT PRIMARY KEY AUTOINCREMENT,"
					+"phone INT NOT NULL,"
					+"country TEXT NOT NULL,"
					+"address TEXT NOT NULL,"
					+"pc INT NOT NULL)"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createDeliveriesTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE deliveries  "
					+ "(transaction_id INT PRIMARY KEY AUTOINCREMENT,"
					+ " selling_price INT NOT NULL,"
					+ " amount INT NOT NULL,"
					+ " transaction _date DATE NOT NULL,"
					+ " client_id INT REFERENCES client (id) )"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createPackagedTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE packaged "
					+ "(drug_id INT,"
					+ " transaction_id INT,"
					+ " PRIMARY KEY (drug_id,"
					+ " transaction_id) )"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createDrugTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE drug "
					+ "(id INT PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL UNIQUE,"
					+ " stock INT NOT NULL,"
					+ " active_principle TEXT,"
					+ " corridor_id INT REFERENCES corridor (id) )"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createArrivesTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE arrives "
					+ "(drug_id INT,"
					+ " transaction_id INT,"
					+ " PRIMARY KEY (drug_id, transaction_id) )"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
		
		public static void createArrivalsTable(Connection c) throws SQLException{
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE arrivals "
					+ "(transaction_id INT PRIMARY KEY AUTOINCREMENT,"
					+ " buying_price INT NOT NULL,"
					+ " transaction_date DATE NOT NULL,"
					+ " amount INT NOT NULL,"
					+ " provider_id REFERENCES provider (id) )"
					;
			stmt1.executeUpdate(sql1);
			stmt1.close();
		}
}
		
		
