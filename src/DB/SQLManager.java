package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.*;


public class SQLManager {

	private static Connection c;

	public static void main(String[] args) {

		try {

			
			  generateDataBase("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			 
			 

			// generateUsersDataBase("jdbc:sqlite:./db/Drug Megastore Users TEST.db");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void connect(String directory) throws ClassNotFoundException, SQLException {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(directory);
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() throws SQLException {
		c.close();
		System.out.println("Database connection closed.");
	}

	public static void generateDataBase(String directory) {

		try {
			connect(directory);

			createClientTable();
			createEmployeeTable();
			createCorridorTable();
			createWarehouseTable();
			createDeliveriesTable();
			createPackagedTable();
			createDrugTable();
			createArrivesTable();
			createArrivalsTable();
			createProvidersTable();
			disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// ===================================CREATE TABLE
	// METHODS.===============================================

	public static void createClientTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE client" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "adress TEXT NOT NULL," + "telephone INT," + "email TEXT, payment_method TEXT NOT NULL, username STRING NOT NULL UNIQUE,"
				+"password STRING NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createEmployeeTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE employee" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "photo BLOB," + "salary FLOAT NOT NULL," + "phone INT NOT NULL," + "position TEXT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id), username STRING NOT NULL UNIQUE,password STRING NOT NULL,isAdmin BOOLEAN)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createCorridorTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE corridor" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "temperature FLOAT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id))";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createWarehouseTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE warehouse" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "phone INT NOT NULL,"
				+ "city TEXT NOT NULL," + "country TEXT NOT NULL," + "address TEXT NOT NULL," + "pc INT NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createDeliveriesTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE deliveries  " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " selling_price INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+ " client_id INT REFERENCES client (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createPackagedTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE packaged " + "(drug_id INT," + " transaction_id INT," + " amount INT NOT NULL,"
				+ " PRIMARY KEY (drug_id," + " transaction_id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createDrugTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE drug " + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL UNIQUE,"
				+ " photo BLOB," + " stock INT NOT NULL," + " active_principle TEXT,"
				+ " corridor_id INT REFERENCES corridor (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createArrivesTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrives " + "(drug_id INT," + " transaction_id INT," + " amount INT NOT NULL,"
				+ " PRIMARY KEY (drug_id, transaction_id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createArrivalsTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrivals " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " buying_price INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+ " provider_id REFERENCES provider (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public static void createProvidersTable() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE provider" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "adress TEXT NOT NULL," + "telephone INTEGER," + "email TEXT)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	// ===========================================================================================================

	// ==========================INSERT ENTRANCE
	// METHODS==========================================================

	public static void insertDeliveriesEntrance(Delivery delivery) throws SQLException {

		String sql1 = "INSERT INTO deliveries(selling_price, transaction_date, client_id)" + "VALUES(?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, delivery.getSellingPrice());
		prep.setInt(2, delivery.getTransactionId());
		prep.setInt(3, delivery.getClient().getId());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertClientEntrance(Client client) throws SQLException {

		String sql1 = "INSERT INTO client(name, adress ,telephone, email, payment_method)" + "VALUES(?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, client.getName());
		prep.setString(2, client.getAdress());
		prep.setInt(3, client.getTelephone());
		prep.setString(4, client.getEmail());
		prep.setString(5, client.getPaymentMethod().toString());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertPackagedEntrance(Delivery delivery) throws SQLException {

		String sql1 = "INSERT INTO packaged (drug_id, transaction_id, amount)" + "VALUES( ?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		for (int i = 0; delivery.getDrugId().size() > i; i++) {
			prep.setInt(1, delivery.getDrugId().get(i).getId());
			prep.setInt(2, delivery.getTransactionId());
			//prep.setInt(3, delivery.getAmmount().get(i)); // CHECK-> HOW TO INTRODUCE AMOUNTS ON A TABLE IF IS STORED IN
															// A POJO AS A LIST
			prep.executeUpdate();
		}
		prep.close();
	}

	public static void insertProviderEntrance(Provider provider) throws SQLException {

		String sql1 = "INSERT INTO provider(name, adress, telephone, email)" + "VALUES(?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, provider.getName());
		prep.setString(2, provider.getAdress());
		prep.setInt(3, provider.getTelephone());
		prep.setString(4, provider.getEmail());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertArrivalsEntrance(Arrival arrival) throws SQLException {

		String sql1 = "INSERT INTO arrivals(buying_price, transaction_date, provider_id)" + "VALUES( ?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, arrival.getBuyingPrice());
		prep.setDate(2, arrival.getDate());
		prep.setInt(3, arrival.getProvider().getProviderId());
		prep.executeUpdate();
		prep.close();
	}

	
	//NOW WE HAVE A POJO for Arrives, so this should be redone, but should be easy
	public static void insertArrivesEntrance(Arrival arrival) throws SQLException {

		String sql1 = "INSERT INTO arrives(drug_id,transaction_id,amount)" + "VALUES(?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		for (int i = 0; arrival.getAmount().size() > i; i++) {
			prep.setInt(1, arrival.getDrugs().get(i).getId());
			prep.setInt(2, arrival.getArrivalId());
			prep.setInt(3, arrival.getAmount().get(i));
			prep.executeUpdate();
		}
		prep.close();

	}

	public static void insertDrugEntrance(Drug drug) throws SQLException {

		String sql1 = "INSERT INTO drug(name, photo, stock, active_principle,corridor_id )" + "VALUES(?,?.?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, drug.getName());
		prep.setBytes(2, drug.getPhoto());
		prep.setInt(3, drug.getStock());
		prep.setString(4, drug.getActivePrinciple());
		prep.setInt(5, drug.getCorridor().getId());

		prep.executeUpdate();
		prep.close();
	}

	public static void insertCorridorEntrance(Corridor corridor) throws SQLException {

		String sql1 = "INSERT INTO corridors(temperature, warehouse_id)" + "VALUES(?,?);";

		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setFloat(1, corridor.getTemperature());
		prep.setInt(2, corridor.getWarehouse().getId());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertWarehouseEntrance(Warehouse warehouse) throws SQLException {

		String sql1 = "INSERT INTO warehouse (phone,city,country, address, pc)" + "VALUES(?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, warehouse.getPhone());
		prep.setString(2, warehouse.getCity());
		prep.setString(3, warehouse.getCity());
		prep.setString(4, warehouse.getAdress());
		prep.setInt(5, warehouse.getPc());
		prep.executeUpdate();
		prep.close();
	}

	public static void insertEmployeesEntrance(Employee employees) throws SQLException {

		String sql1 = "INSERT INTO employee(name,salary, phone,position,warehouse_id,username,password,type)" + "VALUES(?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, employees.getName());
		prep.setFloat(2, employees.getSalary());
		prep.setInt(3, employees.getPhone());
		prep.setString(4, employees.getPosition());
		prep.setInt(5, employees.getWarehouseId().getId());
		prep.setString(6, employees.getUserName());
		prep.setString(7, employees.getPassword());
		prep.setBoolean(8, employees.getIsAdmin());
		prep.executeUpdate();

		prep.close();
	}

	// ===========================================================================================================
	public static Employee extractEmployeeById(Integer id) throws SQLException {
		String sql = "SELECT * FROM employee WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		Warehouse warehouseWanted = extractWarehouseById(rs.getInt("warehouse_id"));
		Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getFloat("salary"), rs.getInt("phone"),
				rs.getString("position"), warehouseWanted, rs.getBytes("photo"), rs.getString("username"), rs.getString("password"),
				rs.getBoolean("isAdmin"));
	
		if(id == employee.getId()) {
			prep.close();
			rs.close();
			return employee;
		}else {
			prep.close();
			rs.close();
			return null;
		}
		
		
	}
	
	public static Warehouse extractWarehouseById(Integer id) throws SQLException {
		String sql = "SELECT * FROM warehouse WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		Warehouse warehouse =  new Warehouse(rs.getInt("id"), rs.getInt("pc"), rs.getString("country"),
				rs.getString("city"), rs.getString("adress"),rs.getInt("phone"));
	
		if(id == warehouse.getId()) {
			prep.close();
			rs.close();
			return warehouse;
		}else {
			prep.close();
			rs.close();
			return null;
		}
		
	}
	
	
	//We have tot alk how to do this 
	public static Arrival extractArrivalById(Integer id)throws SQLException{
		
		String sql = "SELECT * FROM arrival WHERE id = ? ";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		
		Provider providerWanted=extractProviderById(rs.getInt("id"));
		Arrival arrival =new Arrival(rs.getInt("arrivalId"), rs.getInt("buyingPrice"), rs.getDate("date"),providerWanted);
		
		String sql2 = "SELECT * FROM arrives WHERE transaction_id = ?";
		PreparedStatement prep2= c.prepareStatement(sql2);
		prep2.setInt(1, arrival.getArrivalId());
		
		List<Drug> drugs = new ArrayList<Drug>();
		List<Integer> amounts = new ArrayList<Integer>();
		
		ResultSet rs2 = prep.executeQuery();
		while(rs2.next()) {
			Drug drug = extractDrugById(rs.getInt("drug_id"));
			Integer amount = rs.getInt("amount");
			
			drugs.add(drug);
			amounts.add(amount);
		}
		
		arrival.setDrugs(drugs);
		arrival.setAmount(amounts);
		
		
		if(id==arrival.getArrivalId()) {
			prep.close();
			rs.close();
			return arrival;
		}else {
			prep.close();
			rs.close();
			return null;
		}
		
	}
	
	public static Corridor extractCorridorById(Integer id) throws SQLException {
		String sql = "SELECT * FROM corridor WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		Corridor corridor = new Corridor (rs.getInt("id"), rs.getFloat("temperature"), extractWarehouseById(rs.getInt("warehouse_id")));
	
		if(id == corridor.getId()) {
			prep.close();
			rs.close();
			return corridor;
		}else {
			prep.close();
			rs.close();
			return null;
		}
		
	}
	public static Provider extractProviderById(Integer id)throws SQLException{
		String sql="SELECT * FROM provider WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		Provider provider = new Provider (rs.getInt("id"), rs.getString("name"), rs.getString("adress"), rs.getInt("telephone"),
				rs.getString("email"));
		
		if	(id == provider.getProviderId()) {
			prep.close();
			rs.close();
			return provider;
		}else {
			
		prep.close();
		rs.close();
		return null;
		}	
	}
	
	
	public static Client extractClientById(Integer id)throws SQLException{
		String sql="SELECT * FROM client WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
			int id_client=rs.getInt("id");
			String name=rs.getString("name");
			String adress=rs.getString("adress");
			int telephone=rs.getInt("telephone");
			String email=rs.getString("email");
			String payMethod=rs.getString("payMethod");
			String username = rs.getString("username");
			String password = rs.getString("password");
			Client client=new Client(id_client,name,adress, telephone, email, payMethod,username,password);
		
		if	(id == id_client) {
			prep.close();
			rs.close();
			return client;
		}else {
			
		prep.close();
		rs.close();
		return null;
		}	
	}
	
	public static Employee extractEmployeeByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM employee WHERE username = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		
		ResultSet rs = prep.executeQuery();
		
		Warehouse warehouseWanted = extractWarehouseById(rs.getInt("warehouse_id"));
		Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getFloat("salary"), rs.getInt("phone"),
				rs.getString("position"), warehouseWanted, rs.getBytes("photo"), rs.getString("username"), rs.getString("password"),
				rs.getBoolean("isAdmin"));
	
		if(username == employee.getUserName()) {
			prep.close();
			rs.close();
			return employee;
		}else {
			prep.close();
			rs.close();
			return null;
		}
		
	}

	public static Client extractClientByUsername(String username) throws SQLException {
		String sql="SELECT * FROM client WHERE username = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		
		ResultSet rs = prep.executeQuery();
		
			int id_client=rs.getInt("id");
			String name=rs.getString("name");
			String adress=rs.getString("adress");
			int telephone=rs.getInt("telephone");
			String email=rs.getString("email");
			String payMethod=rs.getString("payMethod");
			String username1 = rs.getString("username");
			String password = rs.getString("password");
			Client client=new Client(id_client,name,adress, telephone, email, payMethod,username1,password);
		
		if	(username == client.getUserName()) {
			prep.close();
			rs.close();
			return client;
		}else {
			
		prep.close();
		rs.close();
		return null;
		}	
	}

	// ===========================================================================================================

	public static void createTable(String statement) throws SQLException {
		Statement stmt1 = c.createStatement();
		stmt1.executeUpdate(statement);
		stmt1.close();
	}

	
}