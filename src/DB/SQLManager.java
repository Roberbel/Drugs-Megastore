package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import pojo.*;

public abstract class SQLManager {

	public static void main(String[] args) {

		try {

		//generateDataBase("jdbc:sqlite:./db/Drug Megastore Data Base TEST.db");	
			
		Provider testProvider = new Provider();
		testProvider.setProviderId(1);
		Client testClient = new Client();
		
		testClient.setAdress("Avenida Monasterio de Silos 36");
		testClient.setName("Jaime");
		testClient.setEmail("Jimmyviniegra@gmail.com");
		testClient.setTelephone(638079977);
		testClient.setPaymentMethod("Credit card");
		Connection c;
		
		c = connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST.db");
		
		insertClientEntrance(c,testClient);
		
		disconnect(c);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection connect(String directory) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection(directory);
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		return c;
	}

	public static void disconnect(Connection c) throws SQLException {
		c.close();
		System.out.println("Database connection closed.");
	}

	public static void generateDataBase(String directory) {

		try {
			Connection c = connect(directory);

			createClientTable(c);
			createEmployeeTable(c);
			createCorridorTable(c);
			createWarehouseTable(c);
			createDeliveriesTable(c);
			createPackagedTable(c);
			createDrugTable(c);
			createArrivesTable(c);
			createArrivalsTable(c);
			createProvidersTable(c);

			disconnect(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void createClientTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE client" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "adress TEXT NOT NULL," + "telephone INT," + "email TEXT, payment_method TEXT NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createEmployeeTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE employee" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "photo BLOB," + "salary FLOAT NOT NULL," + "phone INT NOT NULL," + "position TEXT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id))";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createCorridorTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE corridor" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "temperature FLOAT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id))";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createWarehouseTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE warehouse" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "phone INT NOT NULL,"
				+ "city TEXT NOT NULL," + "country TEXT NOT NULL," + "address TEXT NOT NULL," + "pc INT NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createDeliveriesTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE deliveries  " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " selling_price INT NOT NULL," + " ammount INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+ " client_id INT REFERENCES client (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createPackagedTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE packaged " + "(drug_id INT," + " transaction_id INT," + " PRIMARY KEY (drug_id,"
				+ " transaction_id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createDrugTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE drug " + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL UNIQUE,"
				+ " photo BLOB," + " stock INT NOT NULL," + " active_principle TEXT,"
				+ " corridor_id INT REFERENCES corridor (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createArrivesTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrives " + "(drug_id INT," + " transaction_id INT,"
				+ " PRIMARY KEY (drug_id, transaction_id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createArrivalsTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrivals " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " buying_price INT NOT NULL," + " transaction_date DATE NOT NULL," + " ammount INT NOT NULL,"
				+ " provider_id REFERENCES provider (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createProvidersTable(Connection c) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE provider" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "adress TEXT NOT NULL," + "telephone INTEGER," + "email TEXT)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void insertArrivalsEntrance(Connection c, Arrivals arrival) throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "INSERT INTO arrivals(buying_price, transaction_date, ammount, provider_id)"
				+ "VALUES( '" + arrival.getBuyingPrice() + "' , '" + arrival.getDate() + "' , '" +
				arrival.getAmmount() + "' , '" + arrival.getProvider().getProviderId() + "');";
		stmt1.executeUpdate(sql1);
		stmt1.close();
		
	}
	
	public static void insertProviderEntrance(Connection c, Provider provider) throws SQLException {
		Statement stmt1= c.createStatement();
		String sql1 = "INSERT INTO provider(name, adress, telephone, email)" +
				"VALUES( '" + provider.getName() + "' , '" + provider.getAdress() + "' , '" + provider.getTelephone() +
				"' , '" + provider.getEmail() + "');";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public static void insertClientEntrance(Connection c, Client client) throws SQLException {
		Statement stmt1= c.createStatement();
		String sql1 = "INSERT INTO client(name, adress, telephone, email, payment_method)" +
				"VALUES( '" + client.getName() + "' , '" + client.getAdress() + "' , '" + client.getTelephone() +
				"' , '" + client.getEmail() +"' , '"+ client.getPaymentMethod() + "');";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public static void insertEmployeesEntrance(Connection c, Employee employees) throws SQLException {
		Statement stmt1=c.createStatement();
		String sql1="INSERT INTO employee(name,salary, phone,position,warehouse_id)"
				+ "VALUES('"+ employees.getName() + "','"+ employees.getPhoto()+"','"+
				employees.getSalary()+ "','"+ employees.getPhone() + "','"+ employees.getPosition() +"','"+
				employees.getWarehouseId().getId() +"');";
			stmt1.executeUpdate(sql1);
			stmt1.close();	
	}

	public static void createTable(Connection c, String statement) throws SQLException {
		Statement stmt1 = c.createStatement();
		stmt1.executeUpdate(statement);
		stmt1.close();
	}

}
