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
	
	public static void main(String[] args) {

		try {
			
			connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			//SQLManager.insertClient(new Client());
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
		if(c != null) {
			c.close();
			System.out.println("Database connection closed.");
		}
		
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
		String query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('client', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employee', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('corridor', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('warehouse', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('deliveries', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('drug', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('arrivals', 1);";
		statement.executeUpdate(query);
		query = "INSERT INTO sqlite_sequence (name, seq) VALUES ('provider', 1);";
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
				+ "received BOOLEAN NOT NULL DEFAULT FALSE" + ", provider_id REFERENCES provider (id) ON UPDATE CASCADE ON DELETE CASCADE );";
		stmt1.executeUpdate(sql1);
		stmt1.close();

	}
	
	public static void createArrivesTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE arrives " + "(drug_id INT," + " transaction_id INT," + " amount INT NOT NULL,"
				+ " PRIMARY KEY (drug_id, transaction_id) );";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createClientTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE client" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "address TEXT NOT NULL," + "telephone INT," + "email TEXT, payment_method TEXT NOT NULL, username STRING NOT NULL UNIQUE,"
				+"password STRING NOT NULL);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
		
	}

	public static void createCorridorTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE corridor" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "temperature FLOAT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id) ON UPDATE CASCADE ON DELETE CASCADE);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}
	
	public static void createDeliveriesTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE deliveries  " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " selling_price INT NOT NULL," + " transaction_date DATE NOT NULL,"
				+"sent BOOLEAN NOT NULL DEFAULT FALSE, " + "client_id INT REFERENCES client (id) ON UPDATE CASCADE ON DELETE CASCADE );";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createDrugTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE drug " + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL UNIQUE,"
				+ " photo BLOB," + " stock INT NOT NULL," + " active_principle TEXT," + " selling_price INTEGER NOT NULL,"
				+ " corridor_id INT REFERENCES corridor (id)  ON UPDATE CASCADE ON DELETE CASCADE);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createEmployeeTable() throws SQLException {
		
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE employee" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "photo BLOB," + "salary FLOAT NOT NULL," + "phone INT NOT NULL," + "position TEXT NOT NULL,"
				+ "warehouse_id REFERENCES warehouse (id) ON UPDATE CASCADE ON DELETE CASCADE, username STRING NOT NULL UNIQUE,password STRING NOT NULL,isAdmin BOOLEAN);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createPackagedTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE packaged " + "(drug_id INT," + " transaction_id INT," + " amount INT NOT NULL,"
				+ " PRIMARY KEY (drug_id," + " transaction_id) );";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createProvidersTable() throws SQLException {

		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE provider" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
				+ "address TEXT NOT NULL," + "telephone INTEGER," + "email TEXT);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

	public static void createWarehouseTable() throws SQLException {
	
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE warehouse" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "phone INT NOT NULL,"
				+ "city TEXT NOT NULL," + "country TEXT NOT NULL," + "address TEXT NOT NULL," + "pc INT NOT NULL);";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	
	}

//===========================================================================================================
//									INSERT ENTRANCE
//===========================================================================================================
	
	public static void insertArrivals(Arrival arrival) throws SQLException {
		
		c.setAutoCommit(false);

		String sql1 = "INSERT INTO arrivals(buying_price, transaction_date, provider_id, arrived) VALUES( ?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, arrival.getBuyingPrice());
		prep.setDate(2, arrival.getDate());
		prep.setInt(3, arrival.getProvider().getProviderId());
		prep.setBoolean(4, arrival.isReceived());
		prep.executeUpdate();
		prep.close();
		List <Arrives> arrivesList=arrival.getArrives();
		
		String query = "SELECT last_insert_rowid() AS lastId;";
		PreparedStatement ps = c.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Integer id = rs.getInt("lastId");	
		
		for(Arrives a: arrivesList) {
			a.setArrivalId(id);
			insertArrives(a);			
		}
		
		c.commit();
		c.setAutoCommit(true);
	
	}
	
	public static void insertArrives(Arrives arrive) throws SQLException {
		
		String sql1="INSERT INTO arrives(drug_id, transaction_id ,amount) VALUES(?,?,?);";
		PreparedStatement prep=c.prepareStatement(sql1);
		prep.setInt(1,arrive.getDrugId());
		prep.setInt(2, arrive.getArrivalId());
		prep.setInt(3,arrive.getAmount());
		prep.executeUpdate();
		prep.close();
		

		SQLManager.updateDrugStock(arrive.getDrugId(), arrive.getDrug().getStock() + arrive.getAmount());
		
	}
	
	public static void insertClient(Client client) throws SQLException {

		String sql1 = "INSERT INTO client(name, address ,telephone, email, payment_method,username,password) VALUES(?,?,?,?,?,?,?);";
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
		String sql1 = "INSERT INTO corridor(temperature, warehouse_id) VALUES(?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setFloat(1, corridor.getTemperature());
		prep.setInt(2, corridor.getWarehouse().getId());
		prep.executeUpdate();
		prep.close();

	}
		
	public static void insertDeliveries(Delivery delivery) throws SQLException {

		c.setAutoCommit(false);
		
		String sql1 = "INSERT INTO deliveries(selling_price, transaction_date, client_id, sent) VALUES(?,?,?, ?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, delivery.getSellingPrice());
		prep.setDate(2, delivery.getTransactionDate());
		prep.setInt(3, delivery.getClient().getId());
		prep.setBoolean(4, delivery.isSent());
		List <Packaged> packList=delivery.getPackages();
		prep.executeUpdate();
		prep.close();
		
		String query = "SELECT last_insert_rowid() AS lastId;";
		PreparedStatement ps = c.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Integer id = rs.getInt("lastId");		
		
		for(Packaged p: packList) {
			
			p.setDeliveryId(id);
			insertPackaged(p);
			
		}
		
		c.commit();
		c.setAutoCommit(true);
	
	}
		
	public static void insertDrug(Drug drug) throws SQLException {

		String sql1 = "INSERT INTO drug(name, photo, stock, active_principle,corridor_id ) VALUES(?,?.?,?,?);";
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

		String sql1 = "INSERT INTO employee(name,salary, phone,position,warehouse_id,username,password,type) VALUES(?,?,?,?,?);";
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
		String sql1= "INSERT INTO packaged (drug_id,transaction_id,amount) VALUES(?,?,?);";
		PreparedStatement prep=c.prepareStatement(sql1);
		prep.setInt(1,pack.getDrugId());
		prep.setInt(2,pack.getDeliveryId());
		prep.setInt(3,pack.getAmount());
		prep.executeUpdate();
		prep.close();

		SQLManager.updateDrugStock(pack.getDrugId(), pack.getDrug().getStock() - pack.getAmount());
	}

	public static void insertProvider(Provider provider) throws SQLException {

		String sql1 = "INSERT INTO provider(name, adress, telephone, email) VALUES(?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setString(1, provider.getName());
		prep.setString(2, provider.getAddress());
		prep.setInt(3, provider.getTelephone());
		prep.setString(4, provider.getEmail());
		prep.executeUpdate();
		prep.close();

	}

	public static void insertWarehouse(Warehouse warehouse) throws SQLException {

		String sql1 = "INSERT INTO warehouse (phone,city,country, address, pc) VALUES(?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql1);
		prep.setInt(1, warehouse.getPhone());
		prep.setString(2, warehouse.getCity());
		prep.setString(3, warehouse.getCity());
		prep.setString(4, warehouse.getAddress());
		prep.setInt(5, warehouse.getPc());
		prep.executeUpdate();
		prep.close();
	
	}

//===========================================================================================================
//									Reads
//===========================================================================================================

	
	public static Arrival searchArrivalById(Integer id)throws SQLException{
		
		String sql = "SELECT * FROM arrivals WHERE id = ? ;";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs1 = prep.executeQuery();
		
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		Arrival arrival = getArrival(rs1);
			
		if(id==arrival.getArrivalId()) {
			prep.close();
			rs1.close();
			return arrival;
		}else {
			prep.close();
			rs1.close();
			return null;
		}
	}
	
	public static List<Arrival> searchArrivalsByProviderId(Integer providerId) throws SQLException{
		
		String sql = "SELECT * FROM arrivals WHERE provider_id = ? ;";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, providerId);
		List<Arrival> arrivals = new ArrayList<Arrival>();
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs1.next()) {
			arrivals.add(getArrival(rs1));
		}
		prep.close();
		rs1.close();
		return arrivals;
		
	}
	
	public static List<Arrives> searchArrivesByArrivalId(Integer id) throws SQLException{

		String sql = "SELECT * FROM arrives WHERE transaction_id = ? ;";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		List<Arrives> arrives = new ArrayList<Arrives>();
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs1.next()) {
			arrives.add(getArrive(rs1));
		}
		prep.close();
		rs1.close();
		return arrives;
				
	}
	
	public static List<Arrives> searchArrivesByDrugId(Integer id) throws SQLException{

		String sql = "SELECT * FROM arrives WHERE drug_id = ? ;";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		List<Arrives> arrives = new ArrayList<Arrives>();
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs1.next()) {
			arrives.add(getArrive(rs1));
		}
		prep.close();
		rs1.close();		
		return arrives;
		
	}
	
	public static Corridor searchCorridorById(Integer id) throws SQLException {
	
		String sql = "SELECT * FROM corridor WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs1 = prep.executeQuery();
		
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		Corridor corridor = getCorridor(rs1);
			
		if(id == corridor.getId()) {
			prep.close();
			rs1.close();
			return corridor;
		}else {
			prep.close();
			rs1.close();
			return null;
		}		
	}
	
	public static List<Corridor> searchCorridorByWarehouseId(Integer warehouseId) throws SQLException{
		
		System.out.println("QUERY: SELECT * FROM corridor WHERE warehouse_id = " + warehouseId);
		String sql = "SELECT * FROM corridor WHERE warehouse_id = ? ;";
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, warehouseId);
		List<Corridor> corridors = new ArrayList<Corridor>();
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		while(rs1.next()) {
			corridors.add(getCorridor(rs1));
		}
		prep.close();
		rs1.close();		
		return corridors;
		
	}
		
	
	public static Client searchClientById(Integer id) throws SQLException{
	
		String sql="SELECT * FROM client WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Client client = getClient(rs1);
		
		if	(id == client.getId()) {
			prep.close();
			rs1.close();
			return client;
		}else {
				
			prep.close();
			rs1.close();
			return null;
		}	
		
	
	}

	public static Client searchClientByUsername(String username) throws SQLException {
		
		String sql="SELECT * FROM client WHERE username = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		
		
		ResultSet rs1 = prep.executeQuery();	
		Client client = getClient(rs1);
				
		if	(username.equals(client.getUsername())) {
			prep.close();
			rs1.close();
			return client;
		}else {
			System.out.println("Wrong username\nUsername: "+username+"\nclient: "+client.getUsername());
			prep.close();
			rs1.close();
			return null;
		}

	}
	
	public static Delivery searchDeliveryById(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM deliveries WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			return null;
		}
		
		Delivery delivery = getDelivery(rs1);
		
		if	(id == delivery.getTransactionId()) {
			prep.close();
			rs1.close();
			return delivery;
		}else {
			
			prep.close();
			rs1.close();
			return null;
		}	
		
		
	}
	
	public static List<Delivery> searchDeliveryByClientId(Integer id) throws SQLException{
		
		String sql="SELECT * FROM deliveries WHERE client_id = ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		List<Delivery> drugs = new ArrayList<Delivery>();
		while(rs1.next()) {
			Delivery delivery= getDelivery(rs1);
			drugs.add(delivery);
		}
		prep.close();
		rs1.close();
		return drugs;
		
	}
	
	
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple) throws SQLException{ 
		
		String sql="SELECT * FROM drug WHERE active_principle LIKE ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+activePrinciple+"%");
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple, Integer maxPrice) throws SQLException{ 
		
		String sql="SELECT * FROM drug WHERE active_principle LIKE ? AND selling_price <= ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+activePrinciple+"%");
		prep.setInt(2, maxPrice);
		ResultSet rs1=prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByCorridorId(Integer corridorId) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE corridor_id = ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, corridorId);
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
		
	}
	
	
	public static List<Drug> searchDrugByMaxPrice(Integer maxPrice) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE selling_price <= ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, maxPrice);
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
		
	}
	
	
	public static List<Drug> searchDrugByName(String name) throws SQLException{
		
		String sql="SELECT * FROM drug WHERE name LIKE ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+name+"%");
		ResultSet rs1=prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
	
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple) throws SQLException{
		
		System.out.println("name = "+name+"\nA.P. = "+activePrinciple);
		String sql="SELECT * FROM drug WHERE name LIKE ? AND active_principle LIKE ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+name+"%");
		prep.setString(2, "%"+activePrinciple+"%");
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByName(String name, Integer maxPrice) throws SQLException{
		
		System.out.println("name = "+name+"\nMaxPrice = "+maxPrice);
		String sql="SELECT * FROM drug WHERE name LIKE ? AND selling_price <= ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+name+"%");
		prep.setInt(2, maxPrice);
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple, Integer maxPrice) throws SQLException{
		
		System.out.println("name = "+name+"\nA.P. = "+activePrinciple+"\nMaxPrice = "+maxPrice);
		String sql="SELECT * FROM drug WHERE name LIKE '%?%' AND active_principle LIKE '%?%' AND selling_price <= ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setString(1, "%"+name+"%");
		prep.setString(2, "%"+activePrinciple+"%");
		prep.setInt(3, maxPrice);
		ResultSet rs1 =prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return new ArrayList<Drug>();
		}
		List<Drug> drugs = new ArrayList<Drug>();
		while(rs1.next()) {
			Drug drug = getDrug(rs1);
			drugs.add(drug);
		}
		prep.close();
		rs1.close();
		return drugs;
	}
    
	
	public static Employee searchEmployeeById(Integer id) throws SQLException {
	
		String sql = "SELECT * FROM employee WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		ResultSet rs1 = prep.executeQuery();
		
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Employee employee = getEmployee(rs1);
	
		if(id == employee.getId()) {
			prep.close();
			rs1.close();
			return employee;
		}else {
			prep.close();
			rs1.close();
			return null;
		}
		
	}
	
	public static Employee searchEmployeeByUsername(String username) throws SQLException {
	
		String sql = "SELECT * FROM employee WHERE username = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, username);
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		Employee employee = getEmployee(rs1);
			
		if(username.equals(employee.getUsername())) {
			prep.close();
			rs1.close();
			return employee;
		}else {
			prep.close();
			rs1.close();
			return null;
		}
	}

	public static List<Packaged> searchPackagedByDeliveryId(Integer id) throws SQLException{

    	String sql = "SELECT * FROM packaged WHERE transaction_id = ? ;";
    	PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		
		List<Packaged> packages = new ArrayList<Packaged>();
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		while(rs1.next()) {
			packages.add(getPackaged(rs1));
		}
		rs1.close();
		prep.close();
		return packages;
	}

	public static List<Packaged> searchPackagedByDrugId(Integer id) throws SQLException{

    	String sql = "SELECT * FROM packaged WHERE drug_id = ? ;";
    	PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, id);
		
		List<Packaged> packages = new ArrayList<Packaged>();
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		while(rs1.next()) {
			packages.add(getPackaged(rs1));
		}
		rs1.close();
		prep.close();
		return packages;
	}
	
	public static Provider searchProviderById(Integer id) throws SQLException{
	
		String sql="SELECT * FROM provider WHERE id = ? ;";
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
		
		String sql = "SELECT * FROM warehouse WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setInt(1, id);
		
		ResultSet rs1 = prep.executeQuery();
		if(!rs1.isBeforeFirst()) {
			prep.close();
			return null;
		}
		
		Warehouse warehouse =  getWarehouse(rs1);
	
		if(id == warehouse.getId()) {
			prep.close();
			rs1.close();
			return warehouse;
		}else {
			prep.close();
			rs1.close();
			return null;
		}
		
	}


//=====================================================================================================
// 								Updates
//=====================================================================================================
	
	//CLIENT
	public static void updateClient(Integer id, String address, String email, Integer telephone, PaymentMethod paymentMethod) throws SQLException {
		
		String sql = "UPDATE client SET address = ?, email = ?, telephone = ?, paymentMethod = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, address);
		prep.setString(2, email);
		prep.setInt(3, telephone);
		prep.setString(4, paymentMethod.toString());
		prep.setInt(5, id);
		
	}
	
	public static void updateClient(Integer id, String address, String email, Integer telephone, PaymentMethod paymentMethod, String username, String password) throws SQLException {
		
		String sql = "UPDATE client SET address = ?, email = ?, telephone = ?, paymentMethod = ?, username = ?, password = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, address);
		prep.setString(2, email);
		prep.setInt(3, telephone);
		prep.setString(4, paymentMethod.toString());
		prep.setString(5, username);
		prep.setString(6, password);
		prep.setInt(7, id);
		
		prep.executeUpdate();
		prep.close();
	}

	
	//Drug
	
	public static void updateDrug(Integer id, Integer stock, Integer sellingPrice, String name, String activePrinciple, Corridor corridor, byte[] photo) throws SQLException {
		
		String sql = "UPDATE drug SET stock = ?, selling_price = ?, name = ?, active_principle = ?, corridor_id = ?, photo = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, stock);
		prep.setInt(2, sellingPrice);
		prep.setString(3, name);
		prep.setString(4, activePrinciple);
		prep.setInt(5, corridor.getId());
		prep.setBytes(6, photo);
		prep.setInt(7, id);
		prep.executeUpdate();
		prep.close();
		
	}
	
	public static void updateDrugStock(Integer id, Integer stock) throws SQLException {
		
		String sql = "UPDATE drug SET stock = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, stock);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
		
	}
	
	public static void updateDrugPhoto(Integer id, byte[] photo) throws SQLException {
		
		String sql = "UPDATE drug SET photo= ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setBytes(1, photo);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
				
	}
		
	//Employee
	public static void updateEmployee(Integer id, String name, float salary, Integer phone, String position, Boolean isAdmin, Warehouse warehouse, byte[] photo ) throws SQLException {
		
		String sql = "UPDATE employee SET name = ?, salary = ?, phone = ?, position = ?, isAdmin = ?, warehouse_id = ?, photo = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setFloat(2, salary);
		prep.setInt(3, phone);
		prep.setString(4, position);
		prep.setBoolean(5, isAdmin);
		prep.setInt(6, warehouse.getId());
		prep.setBytes(7, photo);
		prep.setInt(8, id);
		prep.executeUpdate();
		prep.close();
		
	}
		
	public static void updateEmployeePhoto(Integer id, byte[] photo) throws SQLException {
		
		String sql = "UPDATE employee SET photo = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setBytes(1, photo);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
	}

	//Deliveries
	public static void updateDeliverySent(Integer id, Boolean sent) throws SQLException {
		
		String sql = "UPDATE deliveries SET sent = ? WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setBoolean(1, sent);
		prep.setInt(2, id);
		prep.executeUpdate();
		prep.close();
		
		
	}	

/*
 *=====================================================================================================
 * 						Delete
 * =====================================================================================================
 * 
 */
		
    public static void deleteArrival(Arrival arrival)  throws SQLException{

    	String sql = "DELETE FROM arrivals WHERE transaction_id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,arrival.getArrivalId());
		prep.executeUpdate();
		prep.close();	
		
	}
    
    public static void deleteArrive(Arrives a) throws SQLException {
    	
    	String sql = "DELETE FROM arrivaes WHERE transaction_id = ? AND drug_id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,a.getArrivalId());
		prep.setInt(2, a.getDrugId());
		prep.executeUpdate();
		prep.close();	
		
    }
    
    
    public static void deleteClient(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM client WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();	
    	
    }

    public static void deleteClient(Client client) throws SQLException {
    	
    	String sql = "DELETE FROM client WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,client.getId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteCorridor(Integer id)  throws SQLException {
    	
    	String sql = "DELETE FROM corridor WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		   		
    }
    
    public static void deleteCorridor(Corridor corridor)  throws SQLException {
    	
    	String sql = "DELETE FROM corridor WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,corridor.getId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    
    public static void deleteDelivery(Delivery delivery) throws SQLException {
    	
    	List<Packaged> packs = delivery.getPackages();
    	String sql = "DELETE FROM deliveries WHERE transaction_id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,delivery.getTransactionId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteDrug(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM drug WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteDrug(Drug drug) throws SQLException {
    	
    	String sql = "DELETE FROM drug WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,drug.getId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteEmployee(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM employee WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteEmployee(Employee employee)  throws SQLException{
    		
    	String sql = "DELETE FROM employee WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, employee.getId());
		prep.executeUpdate();
		prep.close();
		
    	
    }
    
    public static void deletePackaged(Packaged p) throws SQLException {
    	
    	String sql = "DELETE FROM packaged WHERE drug_id = ? AND transaction_id = ? ;";
    	PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, p.getDrugId());
		prep.setInt(2, p.getDeliveryId());
		prep.executeUpdate();
		prep.close();
    	
    }
    
    public static void deleteProvider(Integer id) throws SQLException{
    	
    	String sql = "DELETE FROM provider WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
			
    }
    
    public static void deleteProvider(Provider provider)  throws SQLException{
    	
    	String sql = "DELETE FROM provider WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,provider.getProviderId());
		prep.executeUpdate();
		prep.close();
		
    	
    }
    
    public static void deleteWarehouse(Integer id) throws SQLException {
    	
    	String sql = "DELETE FROM warehouse WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
	
    }
    
    public static void deleteWarehouse(Warehouse warehouse) throws SQLException {
    	
    	String sql = "DELETE FROM warehouse WHERE id = ? ;";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,warehouse.getId());
		prep.executeUpdate();
		prep.close();
       	
    }
    
    
    public static List<Arrival> getAllArrivals() throws SQLException{
    	
    	String sql="SELECT * FROM arrivals ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1 =prep.executeQuery();
    	List <Arrival> clientList=new ArrayList <Arrival> ();
    	while(rs1.next()) {
    		clientList.add(getArrival(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Arrives> getAllArrives() throws SQLException{
    	
    	String sql="SELECT * FROM arrives ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Arrives> clientList=new ArrayList <Arrives> ();
    	while(rs1.next()) {
    		clientList.add(getArrive(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Client> getAllClients() throws SQLException {
    	
    	String sql="SELECT * FROM client ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Client> clientList=new ArrayList <Client> ();
    	while(rs1.next()) {
    		clientList.add(getClient(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    }

    public static List<Corridor> getAllCorridors() throws SQLException {
    	
    	String query="SELECT * FROM corridor ;";
    	PreparedStatement prep=c.prepareStatement(query);
    	ResultSet rs1=prep.executeQuery();
    	List <Corridor> corridorList=new ArrayList <Corridor>();
    	while(rs1.next()) {
    		corridorList.add(getCorridor(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return corridorList;
    }
    
	public static List<Delivery> getAllDeliveries() throws SQLException{
		
		String sql="SELECT * FROM deliveries ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Delivery> clientList=new ArrayList <Delivery> ();
    	while(rs1.next()) {
    		clientList.add(getDelivery(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
		
	}
    
    public static List<Drug> getAllDrugs() throws SQLException{
    	
    	String query="SELECT * FROM drug ;";
    	PreparedStatement prep=c.prepareStatement(query);
    	ResultSet rs1=prep.executeQuery();
    	List <Drug> drugList=new ArrayList <Drug>();
    	while(rs1.next()) {
    		drugList.add(getDrug(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return drugList;
    }
        
    public static List<Employee> getAllEmployees() throws SQLException{
    	
    	String sql="SELECT * FROM employee ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Employee> clientList=new ArrayList <Employee> ();
    	while(rs1.next()) {
    		clientList.add(getEmployee(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Packaged> getAllPackaged() throws SQLException{
    	
    	String sql="SELECT * FROM packaged ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Packaged> clientList=new ArrayList <Packaged> ();
    	while(rs1.next()) {
    		clientList.add(getPackaged(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List<Provider> getAllProviders() throws SQLException{
    	
    	String sql="SELECT * FROM provider ;";
    	PreparedStatement prep=c.prepareStatement(sql);
    	ResultSet rs1=prep.executeQuery();
    	List <Provider> clientList=new ArrayList <Provider> ();
    	while(rs1.next()) {
    		clientList.add(getProvider(rs1));
    	}
    	rs1.close();
    	prep.close();
    	return clientList;
    	
    }
    
    public static List <Warehouse> getAllWarehouses() throws SQLException {
    	
    	String query="SELECT * FROM warehouse ;";
    	PreparedStatement prep=c.prepareStatement(query);
    	ResultSet rs1=prep.executeQuery();
    	List <Warehouse> wareList=new ArrayList <Warehouse>();
    	while(rs1.next()) {
    		wareList.add(getWarehouse(rs1));   		
    	}
    	rs1.close();
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
    
    private static Arrives getArrive(ResultSet rs1) throws SQLException {

    	return new Arrives(rs1.getInt("drug_id") , rs1.getInt("transaction_id"), rs1.getInt("amount"),getDrugNameById(rs1.getInt("drug_id")));
		
    }
    
	private static Client getClient (ResultSet rs1) throws SQLException {
    	
    	Client client = new Client();
		client.setId(rs1.getInt("id"));
		client.setName(rs1.getString("name"));
		client.setAddress(rs1.getString("address"));
		client.setTelephone(rs1.getInt("telephone"));
		client.setEmail(rs1.getString("email"));    		
		if(rs1.getString("payment_method").equals("MASTERCARD")) {
			client.setPaymentMethod(PaymentMethod.MASTERCARD);
		}else if(rs1.getString("payment_method").equals("VISA")) {
			client.setPaymentMethod(PaymentMethod.VISA);
		}else if(rs1.getString("payment_method").equals("PAYPAL")) {
			client.setPaymentMethod(PaymentMethod.PAYPAL);
		}else if(rs1.getString("payment_method").equals("AMERICAN_EXPRESS")) {
			client.setPaymentMethod(PaymentMethod.AMERICAN_EXPRESS);
		}else {
			client.setPaymentMethod(PaymentMethod.ORGANS);
		}    		
		client.setUsername(rs1.getString("username"));
		client.setPassword(rs1.getString("password"));
		return client;
    	
    }
    
    private static Corridor getCorridor(ResultSet rs1) throws SQLException{
    	
    	Corridor corridor=new Corridor();
		corridor.setId(rs1.getInt("id"));
		corridor.setTemperature(rs1.getFloat("temperature"));
		corridor.setWarehouse(searchWarehouseById(rs1.getInt("warehouse_id")));
    	return corridor;
    
    }
    
    private static Delivery getDelivery(ResultSet rs1) throws SQLException{
    	    	
    	Delivery delivery = new Delivery();
    	delivery.setClient(searchClientById(rs1.getInt("client_id")));
    	delivery.setSellingPrice(rs1.getInt("selling_price"));
    	delivery.setTransactionDate(rs1.getDate("transaction_date"));
    	delivery.setTransactionId(rs1.getInt("transaction_id"));
    	delivery.setSent(rs1.getBoolean("sent"));
    	delivery.setPackages(searchPackagedByDeliveryId(delivery.getTransactionId()));
    	return delivery;
    	
    }
    
    private static Drug getDrug(ResultSet rs1) throws SQLException {
    	
    	Drug drug = new Drug();
		drug.setId(rs1.getInt("id"));
		drug.setName(rs1.getString("name"));
		drug.setActivePrinciple(rs1.getString("active_principle"));
		drug.setSellingPrice(rs1.getInt("selling_price"));
		drug.setStock(rs1.getInt("stock"));
		drug.setPhoto(rs1.getBytes("photo"));
		drug.setCorridor(searchCorridorById(rs1.getInt("corridor_id")));
		return drug;
    	
    }
    
    private static Drug getDrugNameById(Integer id) throws SQLException {
		
    	
    	String sql="SELECT * FROM drug WHERE id = ? ;";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs2 =prep.executeQuery();
		rs2.next();

		if(rs2.isClosed()) {
		
			return null;
		
		}else {
		Drug drug = new Drug(rs2.getInt("id"),rs2.getString("name"));
		rs2.close();
		prep.close();
		
		return drug;
		}
	}

    
    private static Employee getEmployee(ResultSet rs1) throws SQLException {
    	
    	Employee employee = new Employee();
    	employee.setId(rs1.getInt("id"));
    	employee.setName(rs1.getString("name"));
    	employee.setSalary(rs1.getFloat("salary"));
    	employee.setPhone(rs1.getInt("phone"));
    	employee.setPosition(rs1.getString("position"));
    	employee.setPhoto(rs1.getBytes("photo"));
    	employee.setUsername(rs1.getString("username"));
    	employee.setPassword(rs1.getString("password"));
    	employee.setIsAdmin(rs1.getBoolean("isAdmin"));
    	int id = rs1.getInt("warehouse_id");
    	Warehouse warehouseWanted = searchWarehouseById(id);
    	employee.setWarehouse(warehouseWanted);
		return employee;
    	
    }
    
    private static Packaged getPackaged(ResultSet rs1) throws SQLException {

    	return new Packaged(rs1.getInt("drug_id") , rs1.getInt("transaction_id"), rs1.getInt("amount"),getDrugNameById(rs1.getInt("drug_id")));
		
    }
    
    private static Provider getProvider(ResultSet rs1) throws SQLException {
    	
    	//we don't really need to know which arrives has a provider. We don't use it in the GUI
    	return new Provider (rs1.getInt("id"), rs1.getString("name"), rs1.getString("address"), rs1.getInt("telephone"),
				rs1.getString("email"));
    	
    }
    
    private static Warehouse getWarehouse(ResultSet rs1) throws SQLException {
    	
    	//we don't really need to know which corridors or employees a warehouse has. We don't use it in the GUI
    	return new Warehouse(rs1.getInt("id"), rs1.getInt("pc"), rs1.getString("country"),
				rs1.getString("city"), rs1.getString("address"),rs1.getInt("phone"));
    	
    }

    
}