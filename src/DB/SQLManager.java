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
import pojos.Client.PaymentMethod;


public class SQLManager implements Manager {

	private static Connection c;
	/*
	 * The get[POJO] methods were giving errors if we passed the result set as a parameter,
	 * so I made the object result set static, and close it once I have finished with a query.
	 */
	private static ResultSet rs;
	
	public static void main(String[] args) {

		try {
			
			connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			generateDataBase();
			disconnect();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			try {
				disconnect();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		}
	}

	public static void connect(String directory) throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection(directory);
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		
	}

	public static void disconnect() throws SQLException {
		
		c.close();
		System.out.println("Database connection closed.");
		
	}

	public static void generateDataBase() throws SQLException {

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
		Statement statement  = c.createStatement();
		String query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('client', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employee', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('corridor', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('warehouse', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('deliveries', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('drug', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('arrivals', 1)";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('provider', 1)";
		statement.executeUpdate(query);
		c.close();
			
	}
//================================================================================================
//									CREATE TABLE
//================================================================================================

	public static void createArrivalsTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrivals " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " buying_price INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+ "received BOOLEAN NOT NULL DEFAULT FALSE" + ", provider_id REFERENCES provider (id) )";
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

	public static void createClientTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE client" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "address TEXT NOT NULL," + "telephone INT," + "email TEXT, payment_method TEXT NOT NULL, username STRING NOT NULL UNIQUE,"
				+"password STRING NOT NULL)";
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
	
	public static void createDeliveriesTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE deliveries  " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " selling_price INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+"sent BOOLEAN NOT NULL DEFAULT FALSE, " + "client_id INT REFERENCES client (id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createDrugTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE drug " + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL UNIQUE,"
				+ " photo BLOB," + " stock INT NOT NULL," + " active_principle TEXT," + " selling_price INTEGER NOT NULL,"
				+ " corridor_id INT REFERENCES corridor (id) )";
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

	public static void createPackagedTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE packaged " + "(drug_id INT," + " transaction_id INT," + " amount INT NOT NULL,"
				+ " PRIMARY KEY (drug_id," + " transaction_id) )";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createProvidersTable() throws SQLException {

		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE provider" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "address TEXT NOT NULL," + "telephone INTEGER," + "email TEXT)";
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

//===========================================================================================================
//									INSERT ENTRANCE
//===========================================================================================================
	
	public static void insertArrivals(Arrival arrival) throws SQLException {
		//if(searchArrivalById(arrival.getArrivalId()) == null) {
			String sql1 = "INSERT INTO arrivals(buying_price, transaction_date, provider_id, arrived)" + "VALUES( ?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql1);
			prep.setInt(1, arrival.getBuyingPrice());
			prep.setDate(2, arrival.getDate());
			prep.setInt(3, arrival.getProvider().getProviderId());
			prep.setBoolean(4, arrival.isReceived());
			prep.executeUpdate();
			prep.close();
			List <Arrives> arrivesList=arrival.getArrives();
			for(Arrives a: arrivesList) {
				insertArrives(a);			
			}
		//}else {
		//	updateArrival(arrival);
		//}
	
	}
	
	public static void insertArrives(Arrives arrive) throws SQLException {
		
		String sql1="INSERT INTO arrives(drug_id,transaction_id,amount) VALUE(?,?,?)";
		PreparedStatement prep=c.prepareStatement(sql1);
		prep.setInt(1,arrive.getDrugId());
		prep.setInt(2, arrive.getArrivalId());
		prep.setInt(3,arrive.getAmount());
		prep.executeUpdate();
		prep.close();
		
	}
	
	public static void insertClient(Client client) throws SQLException {

		String sql1 = "INSERT INTO client(name, address ,telephone, email, payment_method,username,password)" + "VALUES(?,?,?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, client.getName());
		prep.setString(2, client.getAddress());
		prep.setInt(3, client.getTelephone());
		prep.setString(4, client.getEmail());
		prep.setString(5, client.getPaymentMethod().toString());
		prep.setString(6,client.getUsername());
		prep.setString(7, client.getPassword());
		prep.executeUpdate();
		prep.close();

	}
	
	public static void insertCorridor(Corridor corridor) throws SQLException {
		String sql1 = "INSERT INTO corridor(temperature, warehouse_id)" + "VALUES(?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setFloat(1, corridor.getTemperature());
		prep.setInt(2, corridor.getWarehouse().getId());
		prep.executeUpdate();
		prep.close();

	}
		
	public static void insertDeliveries(Delivery delivery) throws SQLException {

		String sql1 = "INSERT INTO deliveries(selling_price, transaction_date, client_id, delivered)" + "VALUES(?,?,?, ?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, delivery.getSellingPrice());
		prep.setInt(2, delivery.getTransactionId());
		prep.setInt(3, delivery.getClient().getId());
		prep.setBoolean(4, delivery.isSent());
		List <Packaged> packList=delivery.getPackages();
		prep.executeUpdate();
		prep.close();
		for(Packaged p: packList) {
			insertPackaged(p);
		}
	
	}
		
	public static void insertDrug(Drug drug) throws SQLException {

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

	public static void insertEmployee(Employee employees) throws SQLException {

		String sql1 = "INSERT INTO employee(name,salary, phone,position,warehouse_id,username,password,type)" + "VALUES(?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, employees.getName());
		prep.setFloat(2, employees.getSalary());
		prep.setInt(3, employees.getPhone());
		prep.setString(4, employees.getPosition());
		prep.setInt(5, employees.getWarehouse().getId());
		prep.setString(6, employees.getUsername());
		prep.setString(7, employees.getPassword());
		prep.setBoolean(8, employees.getIsAdmin());
		prep.executeUpdate();

		prep.close();
	
	}
	
	public static void insertPackaged(Packaged pack) throws SQLException{
		String sql1= "INSERT INTO packaged (drug_id,transaction_id,amount) VALUES(?,?,?)";
		PreparedStatement prep=c.prepareStatement(sql1);
		prep.setInt(1,pack.getDrugId());
		prep.setInt(2,pack.getDeliveryId());
		prep.setInt(3,pack.getAmount());
		prep.executeUpdate();
		prep.close();
	}

	public static void insertProvider(Provider provider) throws SQLException {

		String sql1 = "INSERT INTO provider(name, adress, telephone, email)" + "VALUES(?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, provider.getName());
		prep.setString(2, provider.getAdress());
		prep.setInt(3, provider.getTelephone());
		prep.setString(4, provider.getEmail());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertWarehouse(Warehouse warehouse) throws SQLException {

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

//===========================================================================================================
//									Reads
//===========================================================================================================

	
	//We have to talk how to do this 
	public static Arrival searchArrivalById(Integer id)throws SQLException{
		
		String sql = "SELECT * FROM arrivals WHERE id = ? ";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		Arrival arrival = getArrival();
			
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
	
	public static List<Arrives> searchArrivesByArrivalId(Integer id) throws SQLException{

		String sql = "SELECT * FROM arrives WHERE transaction_id = ?";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		List<Arrives> arrives = new ArrayList<Arrives>();
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs.next()) {
			arrives.add(getArrive());
		}
		prep.close();
		rs.close();
		return arrives;
				
	}
	
	public static List<Arrives> searchArrivesByDrugId(Integer id) throws SQLException{

		String sql = "SELECT * FROM arrives WHERE drug_id = ?";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		List<Arrives> arrives = new ArrayList<Arrives>();
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs.next()) {
			arrives.add(getArrive());
		}
		prep.close();
		rs.close();		
		return arrives;
		
	}
	
	public static Corridor searchCorridorById(Integer id) throws SQLException {
	
		String sql = "SELECT * FROM corridor WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		rs = prep.executeQuery();
		
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		Corridor corridor = getCorridor();
			
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
		
	
	public static Client searchClientById(Integer id)throws SQLException{
	
		String sql="SELECT * FROM client WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Client client = getClient();
		
		if	(id == client.getId()) {
			prep.close();
			rs.close();
			return client;
		}else {
				
			prep.close();
			rs.close();
			return null;
		}	
		
	
	}

	public static Client searchClientByUsername(String username) throws SQLException {
		
		String sql="SELECT * FROM client WHERE username = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		
		
		rs = prep.executeQuery();	
		System.out.println("searching in Client");
		Client client = getClient();
				
		if	(username.equals(client.getUsername())) {
			prep.close();
			rs.close();
			return client;
		}else {
			System.out.println("Wrong username\nUsername: "+username+"\nclient: "+client.getUsername());
			prep.close();
			rs.close();
			return null;
		}

	}
	
	public static Delivery searchDeliveryById(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM deliveries WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		
		Delivery delivery = getDelivery();
		
		if	(id == delivery.getTransactionId()) {
			prep.close();
			rs.close();
			return delivery;
		}else {
			
			prep.close();
			rs.close();
			return null;
		}	
		
		
	}
	
	public static List<Delivery> searchDeliveryByClientId(Integer id) throws SQLException{
		
		String sql="SELECT * FROM deliveries WHERE client_id = ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, id);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		
		List<Delivery> drugs = new ArrayList<Delivery>();
		while(rs.next()) {
			Delivery delivery= getDelivery();
			drugs.add(delivery);
		}
		prep.close();
		rs.close();
		return drugs;
		
	}
	
	
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple)throws SQLException{ 
		
		String sql="SELECT * FROM drug WHERE active_principle LIKE %?%";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, activePrinciple);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple, Integer maxPrice)throws SQLException{ 
		
		String sql="SELECT * FROM drug WHERE active_principle LIKE %?% AND selling_price <= ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, activePrinciple);
		prep.setInt(2, maxPrice);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
	
	
	public static List<Drug> searchDrugByMaxPrice(Integer maxPrice) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE selling_price <= ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, maxPrice);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
		
	}
	
	
	public static List<Drug> searchDrugByName(String name) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE name LIKE %?%";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, name);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
	
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE name LIKE %?% AND active_principle LIKE %?%";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, activePrinciple);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByName(String name, Integer maxPrice) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE name LIKE %?% AND selling_price <= ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setInt(2, maxPrice);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple, Integer maxPrice) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE name LIKE %?% AND active_principle LIKE %?% AND selling_price <= ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, activePrinciple);
		prep.setInt(3, maxPrice);
		rs=prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs.next()) {
			Drug drug = getDrug();
			drugs.add(drug);
		}
		prep.close();
		rs.close();
		return drugs;
	}
    
	
	public static Employee searchEmployeeById(Integer id) throws SQLException {
	
		String sql = "SELECT * FROM employee WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Employee employee = getEmployee();
	
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
	
	public static Employee searchEmployeeByUsername(String username) throws SQLException {
	
		String sql = "SELECT * FROM employee WHERE username = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		System.out.println("looking into employees");
		Employee employee = getEmployee();
			
		if(username.equals(employee.getUsername())) {
			System.out.println("Found the employee!");
			prep.close();
			rs.close();
			return employee;
		}else {
			prep.close();
			rs.close();
			return null;
		}
	}

	public static List<Packaged> searchPackagedByDeliveryId(Integer id) throws SQLException{

    	String sql = "SELECT * FROM packaged WHERE transaction_id = ?";
    	PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		
		List<Packaged> packages = new ArrayList<Packaged>();
		
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		while(rs.next()) {
			packages.add(getPackaged());
		}
		rs.close();
		prep.close();
		return packages;
	}

	public static List<Packaged> searchPackagedByDrugId(Integer id) throws SQLException{

    	String sql = "SELECT * FROM packaged WHERE drug_id = ?";
    	PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		
		List<Packaged> packages = new ArrayList<Packaged>();
		
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		while(rs.next()) {
			packages.add(getPackaged());
		}
		rs.close();
		prep.close();
		return packages;
	}
	
	public static Provider searchProviderById(Integer id)throws SQLException{
	
		String sql="SELECT * FROM provider WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Provider provider = getProvider(rs1);
		
		if	(id == provider.getProviderId()) {
			prep.close();
			rs1.close();
			return provider;
		}else {
			
			prep.close();
			rs1.close();
			return null;
		}	
	
	}
	
	public static Warehouse searchWarehouseById(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM warehouse WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Warehouse warehouse =  getWarehouse();
	
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


//=====================================================================================================
// 								Updates
//=====================================================================================================
	
	//CLIENT
	public static void updateClientAdress(int id, String newAdress) throws SQLException {
		
		String sql = "UPDATE client SET adress = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, newAdress);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}

	public static void updateClientPhone(int id, int phone) throws SQLException {
		
		String sql = "UPDATE client SET telephone = ? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, phone);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}

	public static void updateClientEmail(int id, String email) throws SQLException {
		
		String sql = "UPDATE client SET email = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, email);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}

	public static void updateClientPaymethod(int id, String paymethod) throws SQLException {
		
		String sql = "UPDATE client SET payMethod = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, paymethod);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}

	public static void updateClientPassword(int id, String password) throws SQLException {
		
		String sql = "UPDATE client SET password = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, password);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}
	
	//Drug
	
	public static void updateDrugStock(Integer id, Integer stock) throws SQLException {
		
		String sql = "UPDATE client SET password = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, stock);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
		
	}
	
	public static void updateDrugStock(Drug drug, Integer stock) throws SQLException {
		
		String sql = "UPDATE drug SET stock = ? WHERE id = ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, stock);
		prep.setInt(2, drug.getId());
		prep.executeUpdate();
		prep.close();
		
	}
	
	
		
	//Employee
		
		
	public static void updateEmployeePhoto(Employee employee, byte[] photo) throws SQLException {
		
		String sql = "UPDATE employee SET photo =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setBytes(1, photo);
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateEmployeePhone(Employee employee, int phone) throws SQLException {
		
		String sql = "UPDATE employee SET phone =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, phone);
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateEmployeeSalary(Employee employee, int salary) throws SQLException {
		
		String sql = "UPDATE employee SET salary =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, salary);
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateEmployeePosition(Employee employee, String position) throws SQLException {
		
		String sql = "UPDATE employee SET position =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, position);
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateEmployeeWarehouse(Employee employee, Warehouse warehouse) throws SQLException {
		
		String sql = "UPDATE employee SET warehouse =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, warehouse.getId());
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateEmployeePassword(Employee employee, int password) throws SQLException {
		
		String sql = "UPDATE employee SET password =? WHERE id =?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, password);
		prep.setInt(2, employee.getId());
		prep.executeUpdate();
		prep.close();
	}

	public static void updateAdmin(int id, boolean admin) throws SQLException {

		String sql = "UPDATE employee SET type= ? WHERE id = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setBoolean(1, admin);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}
	
	//Arrives 
		
	public static void updateArrivesAmmount(Arrives arrives, int ammount)throws SQLException{
		
		String sql="UPDATE arrives SET ammount= ? WHERE transaction_id = ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1,ammount);
		prep.setInt(2, arrives.getArrivalId());
		prep.executeUpdate();
		prep.close();
	}
	
	//Packaged
	
	public static void updatePackagedAmmount(Packaged packaged, int ammount)throws SQLException{
		
		String sql="UPDATE packaged SET ammount= ? WHERE id = ?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1,ammount);
		prep.setInt(2, packaged.getDeliveryId());
		prep.executeUpdate();
		prep.close();
	}
			
	//Deliveries
	
	public static void updateDeliverySellingPrice(Delivery delivery, int sellingPrice)throws SQLException{
		
		String sql="UPDATE deliveries SET sellingPrice=? WHERE id=?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1,sellingPrice);
		prep.setInt(2,delivery.getTransactionId());
		prep.executeUpdate();
		prep.close();
	}
	
	public static void updateDeliveryDate(Delivery delivery, Date date)throws SQLException{
		
		String sql="UPDATE deliveries SET transaction_date=? WHERE id=?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setDate(1,date);
		prep.setInt(2, delivery.getTransactionId());
		prep.executeUpdate();
		prep.close();
	}
			
// ===========================================================================================================


	public static void createTable(String statement) throws SQLException {
		
		Statement stmt1 = c.createStatement();
		stmt1.executeUpdate(statement);
		stmt1.close();
	
	}

/*
 *=====================================================================================================
 * 						Delete
 * =====================================================================================================
 * 
 */
	
	public static void deleteArrival(Integer id)  throws SQLException {
    	
    	String sql = "DELETE FROM arrivals WHERE transaction_id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();		
	}
	
    public static void deleteArrival(Arrival arrival)  throws SQLException{
		
    	String sql = "DELETE FROM arrivals WHERE transaction_id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,arrival.getArrivalId());
		prep.executeUpdate();
		prep.close();
		
		
	}
    
    public static void deleteClient(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM client WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		
    	
    	
    }

    public static void deleteClient(Client client) throws SQLException {
    	
    	String sql = "DELETE FROM client WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,client.getId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteCorridor(Integer id)  throws SQLException {
    	
    	String sql = "DELETE FROM corridor WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		   	
    	
    }
    
    public static void deleteCorridor(Corridor corridor)  throws SQLException {
    	String sql = "DELETE FROM corridor WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,corridor.getId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteDelivery(Integer id)  throws SQLException {
    	
    	String sql = "DELETE FROM deliveries WHERE transactionId=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		
    	
    	
    }
    
    public static void deleteDelivery(Delivery delivery) throws SQLException {
    	
    	String sql = "DELETE FROM deliveries WHERE transactionId=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,delivery.getTransactionId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteDrug(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM drug WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		
    	
    }
    
    public static void deleteDrug(Drug drug) throws SQLException {
    	String sql = "DELETE FROM drug WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,drug.getId());
		prep.executeUpdate();
		prep.close();
    	
    	
    }
    
    public static void deleteEmployee(Integer id) throws SQLException {
    	String sql = "DELETE FROM employee WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		
    	
    	
    }
    
    public static void deleteEmployee(Employee employee)  throws SQLException{
    		
    	String sql = "DELETE FROM employee WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, employee.getId());
		prep.executeUpdate();
		prep.close();
		
    	
    }
    
    public static void deleteProvider(Integer id) throws SQLException{
    	String sql = "DELETE FROM provider WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		
    	
    		
    }
    
    public static void deleteProvider(Provider provider)  throws SQLException{
    	
    	String sql = "DELETE FROM provider WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,provider.getProviderId());
		prep.executeUpdate();
		prep.close();
		
    	
    }
    
    public static void deleteWarehouse(Integer id) throws SQLException {
    	String sql = "DELETE FROM warehouse WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
	
    }
    
    public static void deleteWarehouse(Warehouse warehouse) throws SQLException {
    	String sql = "DELETE FROM warehouse WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,warehouse.getId());
		prep.executeUpdate();
		prep.close();
       	

    }
    
    //Getting the pojos, more methods needed
    
    public static List<Arrival> getAllArrivals() throws SQLException{
    	
    	String sql="SELECT * FROM arrivals";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1 =prep.executeQuery();
    	List <Arrival> clientList=new ArrayList <Arrival> ();
    	while(rs1.next()) {
    		clientList.add(getArrival(rs1));
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Arrives> getAllArrives() throws SQLException{
    	
    	String sql="SELECT * FROM arrives";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Arrives> clientList=new ArrayList <Arrives> ();
    	while(rs.next()) {
    		clientList.add(getArrive());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Client> getAllClients() throws SQLException {
    	String sql="SELECT * FROM client";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Client> clientList=new ArrayList <Client> ();
    	while(rs.next()) {
    		clientList.add(getClient());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    }

    public static List<Corridor> getAllCorridors() throws SQLException {
    	String query="SELECT * FROM corridor";
    	PreparedStatement prep=c.prepareStatement(query);
    	rs=prep.executeQuery();
    	List <Corridor> corridorList=new ArrayList <Corridor>();
    	while(rs.next()) {
    		corridorList.add(getCorridor());
    	}
    	rs.close();
    	prep.close();
    	return corridorList;
    }
    
	public static List<Delivery> getAllDeliveries() throws SQLException{
		
		String sql="SELECT * FROM deliveries";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Delivery> clientList=new ArrayList <Delivery> ();
    	while(rs.next()) {
    		clientList.add(getDelivery());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
		
	}
    
    public static List<Drug> getAllDrugs() throws SQLException{
    	String query="SELECT * FROM drug";
    	PreparedStatement prep=c.prepareStatement(query);
    	rs=prep.executeQuery();
    	List <Drug> drugList=new ArrayList <Drug>();
    	while(rs.next()) {
    		drugList.add(getDrug());
    		System.out.println(drugList.get(drugList.size()-1));
    	}
    	rs.close();
    	prep.close();
    	return drugList;
    }
        
    public static List<Employee> getAllEmployee() throws SQLException{
    	
    	String sql="SELECT * FROM employee";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Employee> clientList=new ArrayList <Employee> ();
    	while(rs.next()) {
    		clientList.add(getEmployee());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Packaged> getAllPackaged() throws SQLException{
    	
    	String sql="SELECT * FROM packaged";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Packaged> clientList=new ArrayList <Packaged> ();
    	while(rs.next()) {
    		clientList.add(getPackaged());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Provider> getAllProvider() throws SQLException{
    	
    	String sql="SELECT * FROM provider";
    	PreparedStatement prep=c.prepareStatement(sql);
    	rs=prep.executeQuery();
    	List <Provider> clientList=new ArrayList <Provider> ();
    	while(rs.next()) {
    		clientList.add(getProvider());
    	}
    	rs.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List <Warehouse> getAllWarehouses() throws SQLException {
    	String query="SELECT * FROM warehouse";
    	PreparedStatement prep=c.prepareStatement(query);
    	rs=prep.executeQuery();
    	List <Warehouse> wareList=new ArrayList <Warehouse>();
    	while(rs.next()) {
    		wareList.add(getWarehouse());   		
    	}
    	rs.close();
    	prep.close();
    	return wareList;
    }
    
//===========================================================================================
//					private get methods
//===========================================================================================
    
    private static Arrival getArrival(ResultSet rs1) throws SQLException {
    	
    	Provider providerWanted= searchProviderById(rs1.getInt("provider_id"));
		Arrival arrival =new Arrival(rs1.getInt("transaction_id"), rs1.getInt("buying_price"), rs1.getDate("transaction_date"), providerWanted, rs1.getBoolean("received"));
		arrival.setArrives(searchArrivesByArrivalId(arrival.getArrivalId()));
		
		return arrival;
    	
    }
    
    private static Arrives getArrive() throws SQLException {

    	return new Arrives(rs.getInt("drug_id") , rs.getInt("transaction_id"), rs.getInt("amount"));
		
    }
    
    private static Client getClient () throws SQLException {
    	
    	Client client = new Client();
		client.setId(rs.getInt("id"));
		client.setName(rs.getString("name"));
		client.setAddress(rs.getString("address"));
		client.setTelephone(rs.getInt("telephone"));
		client.setEmail(rs.getString("email"));    		
		if(rs.getString("payment_method").equals("MASTERCARD")) {
			client.setPaymentMethod(PaymentMethod.MASTERCARD);
		}else if(rs.getString("payment_method").equals("VISA")) {
			client.setPaymentMethod(PaymentMethod.VISA);
		}else if(rs.getString("payment_method").equals("PAYPAL")) {
			client.setPaymentMethod(PaymentMethod.PAYPAL);
		}else if(rs.getString("payment_method").equals("AMERICAN_EXPRESS")) {
			client.setPaymentMethod(PaymentMethod.AMERICAN_EXPRESS);
		}else {
			client.setPaymentMethod(PaymentMethod.ORGANS);
		}    		
		client.setUsername(rs.getString("username"));
		client.setPassword(rs.getString("password"));
		client.setDeliveries(searchDeliveryByClientId(client.getId()));
		return client;
    	
    }
    
    private static Corridor getCorridor() throws SQLException{
    	
    	Corridor corridor=new Corridor();
		corridor.setId(rs.getInt("id"));
		corridor.setTemperature(rs.getFloat("temperature"));
		corridor.setWarehouse(searchWarehouseById(rs.getInt("warehouse_id")));
    	return corridor;
    
    }
    
    private static Delivery getDelivery() throws SQLException{
    	    	
    	Delivery delivery = new Delivery();
    	delivery.setClient(searchClientById(rs.getInt("client_id")));
    	delivery.setSellingPrice(rs.getInt("selling_price"));
    	delivery.setTransactionDate(rs.getDate("transaction_date"));
    	delivery.setTransactionId(rs.getInt("transaction_id"));
    	delivery.setSent(rs.getBoolean("sent"));
    	delivery.setPackages(searchPackagedByDeliveryId(delivery.getTransactionId()));
    	return delivery;
    	
    }
    
    private static Drug getDrug() throws SQLException {
    	
    	Drug drug = new Drug();
		drug.setId(rs.getInt("id"));
		drug.setName(rs.getString("name"));
		drug.setActivePrinciple(rs.getString("active_principle"));
		drug.setSellingPrice(rs.getInt("selling_price"));
		drug.setStock(rs.getInt("stock"));
		drug.setPhoto(rs.getBytes("photo"));
		drug.setCorridor(searchCorridorById(rs.getInt("corridor_id")));
		return drug;
    	
    }
    
    private static Employee getEmployee() throws SQLException {
    	
    	Employee employee = new Employee();
    	employee.setId(rs.getInt("id"));
    	employee.setName(rs.getString("name"));
    	employee.setSalary(rs.getFloat("salary"));
    	employee.setPhone(rs.getInt("phone"));
    	employee.setPosition(rs.getString("position"));
    	employee.setPhoto(rs.getBytes("photo"));
    	employee.setUsername(rs.getString("username"));
    	employee.setPassword(rs.getString("password"));
    	employee.setIsAdmin(rs.getBoolean("isAdmin"));
    	int id = rs.getInt("warehouse_id");
    	Warehouse warehouseWanted = searchWarehouseById(id);
    	employee.setWarehouse(warehouseWanted);
		return employee;
    	
    }
    
    private static Packaged getPackaged() throws SQLException {

    	return new Packaged(rs.getInt("drug_id") , rs.getInt("transaction_id"), rs.getInt("amount"));
		
    }
    
    private static Provider getProvider(ResultSet rs1) throws SQLException {
    	
    	//we don't really need to know which arrives has a provider. We don't use it in the GUI
    	return new Provider (rs1.getInt("id"), rs1.getString("name"), rs1.getString("address"), rs1.getInt("telephone"),
				rs1.getString("email"));
    	
    }
    
    private static Warehouse getWarehouse() throws SQLException {
    	
    	//we don't really need to know which corridors or employees a warehouse has. We don't use it in the GUI
    	return new Warehouse(rs.getInt("id"), rs.getInt("pc"), rs.getString("country"),
				rs.getString("city"), rs.getString("address"),rs.getInt("phone"));
    	
    }

    
}