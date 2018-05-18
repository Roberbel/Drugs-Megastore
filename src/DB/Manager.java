package DB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import pojos.Arrival;
import pojos.Arrives;
import pojos.Client;
import pojos.Corridor;
import pojos.Delivery;
import pojos.Drug;
import pojos.Employee;
import pojos.Packaged;
import pojos.Provider;
import pojos.Warehouse;

public interface Manager {
	
	public static void connect(String directory) {}
	
	public static void disconnect() {}
	
	/*
	 * This interface should have inserts and selects.
	 * QUIERO M�TODOS QUE ME DEVUELVAN TODOS LOS POJOS QUE QUERAIS MOSTRAR EN LA INTERFAZ
	 */
/*
 * =====================================================================================================
 * 					Creates
 * =====================================================================================================
 */
	
	public static void insertArrivals(Arrival arrival) {}
	
	public static void insertArrives(Arrives arrive) {}
	
	public static void insertClient(Client client) {}
	
	public static void insertCorridor(Corridor corridor) {}
	
	public static void insertDeliveries(Delivery delivery) {}
	
	public static void insertDrug(Drug drug) {}
	
	public static void insertEmployee(Employee employees) {}
	
	public static void insertPackaged(Packaged pack) {}
	
	public static void insertProvider(Provider provider) {}
	
	public static void insertWarehouse(Warehouse warehouse) {}	
	
/*
 * =====================================================================================================
 * 					Reads
 * =====================================================================================================
 */
	

	public static List<Arrival> getAllArrivals(){
		return null;
	}
	
	public static List<Arrives> getAllArrives(){
		return null;
	}
	
	public static List<Client> getAllClients(){
		return null;
	}
	
	public static List<Corridor> getAllCorridors(){
		return null;
	}
	
	public static List<Delivery> getAllDeliveries(){
		return null;
	}
	
	public static List<Drug> getAllDrugs(){
		return null;
	}
	
	public static List<Employee> getAllEmployees(){
		return null;
	}
	
	public static List<Packaged> getAllPackaged(){
		return null;
	}
	
	public static List<Provider> getAllProviders(){
		return null;
	}
	
	public static List<Warehouse> getAllWarehouses(){
		return null;
	}
	
	public static Arrival searchArrivalById (Integer id) {
		return null;
	}
	
	public static List<Arrives> searchArrivesByArrivalId(Integer id){
		return null;
	}

	public static List<Arrives> searchArrivesByDrugId(Integer id){
		return null;
	}
	
	public static Corridor searchCorridorById(Integer id) {
		return null;
	}
	
	public static Client searchClientById(Integer id) {
		return null;
	}
	
	public static Client searchClientByUsername(String username) {
		return null;
	}
	
	public static Delivery searchDeliveryById(Integer id) {
		return null;
	}
	
	public static List<Delivery> searchDeliveryByClientId(Integer id) {
		return null;
	}
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple) {
		return null;
	}
	
	public static List<Drug> searchDrugByActivePrinciple(String activePrinciple, Integer maxPrice) {
		return null;
	}
	
	public static List<Drug> searchDrugByMaxPrice(Integer maxPrice) {
		return null;
	}
	
	public static List<Drug> searchDrugByName(String name) {
		return null;
	}
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple) {
		return null;
	}
	
	public static List<Drug> searchDrugByName(String name, Integer maxPrice) {
		return null;
	}
	
	public static List<Drug> searchDrugByName(String name, String activePrinciple, Integer maxPrice) {
		return null;
	}
	
	public static Employee searchEmployeeById(Integer id) {
		return null;
	}
	
	public static Employee searchEmployeeByUsername(String username) {
		return null;
	}
	
	public static List<Packaged> searchPackagedByDeliveryId(Integer id) {
		return null;
	}
	
	public static List<Packaged> searchPackagedByDrugId(Integer id) {
		return null;
	}
	
	public static Provider searchProviderById(Integer id) {
		return null;
	}
	
	public static Warehouse searchWarehouseById(Integer id) {
		return null;
	}

/*
 * =====================================================================================================
 * 					Updates
 * =====================================================================================================
 */    
	//CLIENT
	public static void updateClient(Integer id, String address, String email, Integer telephone, 
												String paymentMethod) {}
	
	public static void updateClient(Integer id, String address, String email, Integer telephone, 
												String paymentMethod, String username, String password) {}

	public static void updateDrug(Integer id, Integer stock, Integer sellingPrice, String name, 
											String activePrinciple, Corridor corridor, byte[] photo) {}
	
	public static void updateDrugStock(Integer id, Integer stock) {}
	
	public static void updateDrugPhoto(Integer id, byte[] photo) {}
		
	public static void updateEmployee(Integer id, String name, float salary, Integer phone, String position, 
													Boolean isAdmin, Warehouse warehouse, byte[] photo ) {}
		
	public static void updateEmployeePhoto(Integer id, byte[] photo) {}

	public static void updateDeliverySent(Integer id, Boolean sent){}	
	 
	 
/*
 * =====================================================================================================
 * 					Deletes
 * =====================================================================================================
 */
     
     
 	 public static void deleteArrival(Integer id) {}
 	
     public static void deleteArrival(Arrival arrival) {}
     
     public static void deleteClient(Integer id) {}

     public static void deleteClient(Client client) {}
     
     public static void deleteCorridor(Integer id) {}
     
     public static void deleteCorridor(Corridor corridor) {}
     
     public static void deleteDelivery(Integer id) {}
     
     public static void deleteDelivery(Delivery delivery) {}
     
     public static void deleteDrug(Integer id) {}
     
     public static void deleteDrug(Drug drug) {}
     
     public static void deleteEmployee(Integer id) {}
     
     public static void deleteEmployee(Employee employee) {}
     
     public static void deleteProvider(Integer id) {}
     
     public static void deleteProvider(Provider provider) {}
     
     public static void deleteWarehouse(Integer id) {}
     
     public static void deleteWarehouse(Warehouse warehouse) {}
}
