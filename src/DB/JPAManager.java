package DB;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

public class JPAManager implements Manager{

		private static EntityManager em;

		public static void main(String[] args) {
			
			
			JPAManager.connect();
			
			for (int i = 0; i < 50; i++) {
				
				JPAManager.insertDrug(new Drug());
				
			}
			JPAManager.insertClient(new Client());
			 
			JPAManager.disconnect();
		}
		
		public static void connect(){
			
			em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			System.out.println("Database connection started");
			
		}
		
		public static void disconnect()  {
			if(em != null) {
				em.close();
			}
			System.out.println("Database connection closed.");
		}
		
/*
 * =====================================================================================================
 * 								Creates
 * =====================================================================================================
 */		
		
		public static void insertArrival(Arrival arrival) {
			
			em.getTransaction().begin();
			em.persist(arrival);
			em.getTransaction().commit();
			
		}
		
		public static void insertArrive(Arrives arrive) {
			
			em.getTransaction().begin();
			em.persist(arrive);
			em.getTransaction().commit();
			
		}
		
		public static void insertClient(Client client) {
			
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
			
		}
		
		public static void insertCorridor(Corridor corridor) {
			
			em.getTransaction().begin();
			em.persist(corridor);
			em.getTransaction().commit();
			
		}
		
		public static void insertDelivery(Delivery delivery){
			
			em.getTransaction().begin();
			em.persist(delivery);
			em.getTransaction().commit();
			
		}
		
		public static void insertDrug(Drug drug){
			
			em.getTransaction().begin();
			em.persist(drug);
			em.getTransaction().commit();
	
		}
		
		public static void insertEmployee(Employee employee) {
			
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
			
		}
		
		public static void insertPackaged(Packaged packaged) {
			
			em.getTransaction().begin();
			em.persist(packaged);
			em.getTransaction().commit();
			
		}
		
		public static void insertProvider(Provider provider) {
			
			em.getTransaction().begin();
			em.persist(provider);
			em.getTransaction().commit();
			
		}
		
		public static void insertWarehouse(Warehouse warehouse) {
			
			em.getTransaction().begin();
			em.persist(warehouse);
			em.getTransaction().commit();
			
		}
		
/*
 * =====================================================================================================
 * 								READS
 * =====================================================================================================
 */
		
		public static Arrival searchArrivalById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM arrivals WHERE transaction_id = ?", Arrival.class);
			q1.setParameter(1, id);
			return (Arrival) q1.getSingleResult();
			
		}
		
		public static Client searchClientById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM client WHERE id = ?", Client.class);
			q1.setParameter(1, id);
			return (Client) q1.getSingleResult();
			
		}
		
		public static Corridor searchCorridorById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM corridor WHERE id = ?", Corridor.class);
			q1.setParameter(1, id);
			return (Corridor) q1.getSingleResult();
			
		}
		
		public static Delivery searchDeliveryById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM deliveries WHERE transaction_id = ?", Delivery.class);
			q1.setParameter(1, id);
			return (Delivery) q1.getSingleResult();
			
		}
		
		public static Drug searchDrugById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE id = ?", Drug.class);
			q1.setParameter(1, id);
			return (Drug) q1.getSingleResult();
			
		}
		
		public static Employee searchEmployeeById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM employee WHERE id = ?", Employee.class);
			q1.setParameter(1, id);
			return (Employee) q1.getSingleResult();
			
		}
		
		public static Provider searchProviderById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM provider WHERE id = ?", Provider.class);
			q1.setParameter(1, id);
			return (Provider) q1.getSingleResult();
			
		}
		
		public static Warehouse searchWarehouseById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM warehouse WHERE id = ?", Warehouse.class);
			q1.setParameter(1, id);
			return (Warehouse) q1.getSingleResult();
			
		}
		
		
		/*
		 *  SELECT EVERYTHING FROM EACH TABLE
		 */
		
		public static List<Arrival> getAllArrivals(){
			
			Query q1 = em.createNativeQuery("SELECT * FROM arrivals", Arrival.class);
			return (List<Arrival>) q1.getResultList();
			
		}
		
		public static List<Arrives> getAllArrives(){
			
			Query q1 = em.createNativeQuery("Select * FROM arrives", Arrives.class);
			return (List<Arrives>) q1.getResultList();
			
		}
		
		public static List<Client> getAllClients(){
			
			Query q1 = em.createNativeQuery("Select * FROM client", Client.class);
			return (List<Client>) q1.getResultList();
			
		}
		
		public static List<Corridor> getAllCorridors(){
			
			Query q1 = em.createNativeQuery("Select * FROM corridor", Corridor.class);
			return (List<Corridor>) q1.getResultList();
			
		}
		
		public static List<Delivery> getAllDeliveries(){
			
			Query q1 = em.createNativeQuery("Select * FROM deliveries", Delivery.class);
			return (List<Delivery>) q1.getResultList();
			
		}
		
		public static List<Drug> getAllDrugs(){
			
			Query q1 = em.createNativeQuery("Select * FROM drug", Drug.class);
			return (List<Drug>) q1.getResultList();
			
		}
		
		public static List<Employee> getAllEmployees(){
			
			Query q1 = em.createNativeQuery("Select * FROM employee", Employee.class);
			return (List<Employee>) q1.getResultList();
			
			
		}
		
		public static List<Packaged> getAllPackaged(){
			
			Query q1 = em.createNativeQuery("Select * FROM packaged", Packaged.class);
			return (List<Packaged>) q1.getResultList();
			
		}
		
		public static List<Provider> getAllProviders(){
			
			Query q1 = em.createNativeQuery("Select * FROM provider", Provider.class);
			return (List<Provider>) q1.getResultList();
			
		}
		
		public static List<Warehouse> getAllWarehouses(){
			
			Query q1 = em.createNativeQuery("Select * FROM warehouse", Warehouse.class);
			return (List<Warehouse>) q1.getResultList();
			
		}
		

/*
 * =====================================================================================================
 * 								UPDATES
 * =====================================================================================================
 */
		
		/*
		 * Arrival updates
		 */
		public static void updateArrivalBuyingPrice(Arrival arrival, Integer buyingPrice) {
			
			em.getTransaction().begin();
			arrival.setBuyingPrice(buyingPrice);
			em.getTransaction().commit();
			
		}
		
		public static void updateArrivalDate(Arrival arrival, Date date) {
			
			em.getTransaction().begin();
			arrival.setDate(date);
			em.getTransaction().commit();
			
		}
		
		public static void updateArrivalProvider(Arrival arrival, Provider provider) {
			
			em.getTransaction().begin();
			arrival.setProvider(provider);
			em.getTransaction().commit();
			
		}
		
		
		/*
		 * Client updates
		 */
		public static void updateClientUsername(Client client , String username){
			
			em.getTransaction().begin();
			client.setUsername(username);
			em.getTransaction().commit();
			
		}
		
		
		
		/*
		 * Drug updates
		 */
		public static void updateDrugActivePrinciple(Drug drug, String activePrinciple) {
			
			em.getTransaction().begin();
			drug.setActivePrinciple(activePrinciple);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugCorridor(Drug drug, Corridor corridor) {
			
			em.getTransaction().begin();
			drug.setCorridor(corridor);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugName(Drug drug, String name) {
			
			em.getTransaction().begin();
			drug.setName(name);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugSellingPrice(Drug drug, Integer sellingPrice) {
			
			em.getTransaction().begin();
			drug.setSellingPrice(sellingPrice);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugStock(Drug drug, Integer stock) {
			
			em.getTransaction().begin();
			drug.setStock(stock);
			em.getTransaction().commit();
			
		}
		
		
		/*
		 * Employee updates
		 */
		public static void updateEmployeeAdminRights(Employee employee, Boolean isAdmin) {
			
			em.getTransaction().begin();
			employee.setIsAdmin(isAdmin);
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeeName(Employee employee, String name) {
			
			em.refresh(employee);
			employee.setName(name);
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeePassword(Employee employee, String password) {
			
			em.getTransaction().begin();
			employee.setPassword(password);
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeePhoto(Employee employee, byte[] photo) {
			
			em.getTransaction().begin();
			employee.setPhoto(photo);
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeePosition(Employee employee, String position) {
			//lets hope he got promoted
			
			em.getTransaction().begin();
			employee.setPosition(position);
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeeUsername(Employee employee, String username) {
			
			employee.setUsername(username);
			em.getTransaction().begin();
			em.flush();
			
			//em.flush();
			em.getTransaction().commit();
			
		}
		
		public static void updateEmployeeWarehouse(Employee employee, Warehouse warehouse) {
			
			em.getTransaction().begin();
			employee.setWarehouse(warehouse);
			em.getTransaction().commit();
			
		}
		
		
		/*
		 * Warehouse updates
		 */
		public static void updateWarehousePc(Warehouse warehouse, Integer pc) {
			
			em.getTransaction().begin();
			warehouse.setPc(pc);
			em.getTransaction().commit();
			
		}
		
		public static void updateWarehouseCountry(Warehouse warehouse, String  country) {
			
			em.getTransaction().begin();
			warehouse.setCountry(country);
			em.getTransaction().commit();
			
		}
		
		public static void updateWarehouseCity(Warehouse warehouse, String city) {
			
			em.getTransaction().begin();
			warehouse.setCity(city);
			em.getTransaction().commit();
			
		}
		
		public static void updateWarehouseAddress(Warehouse warehouse, String address) {
			
			em.getTransaction().begin();
			warehouse.setAddress(address);
			em.getTransaction().commit();
			
		}
		
		public static void updateWarehousePhone(Warehouse warehouse, Integer phone) {
			
			em.getTransaction().begin();
			warehouse.setPhone(phone);
			em.getTransaction().commit();
			
		}
		
		
		
		
/*
 * =====================================================================================================
 * 								DELETE
 * 
 * Might be missing Arrives and Packaged delete, not sure yet how to do it
 * =====================================================================================================
 */
		
		public static void deleteArrival(Integer id) {
			
			Arrival toDelete = searchArrivalById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteArrival(Arrival arrival) {
			
			Arrival toDelete = searchArrivalById(arrival.getArrivalId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteArrive(Arrives toDelete) {
			
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteClient(Integer id) {
			
			Client toDelete = searchClientById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteClient(Client client) {
			
			Client toDelete = searchClientById(client.getId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteCorridor(Integer id) {
			
			Corridor toDelete = searchCorridorById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
		
		}
		
		public static void deleteCorridor(Corridor corridor) {
			
			Corridor toDelete = searchCorridorById(corridor.getId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteDelivery(Integer id) {
			
			Delivery toDelete = searchDeliveryById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteDelivery(Delivery delivery) {
			
			Delivery toDelete = searchDeliveryById(delivery.getTransactionId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
		
		}
		
		public static void deleteDrug(Integer id) {
			
			Drug toDelete = searchDrugById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteDrug(Drug drug) {
			
			Drug toDelete = searchDrugById(drug.getId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		//this poor guy getting fired....He worked so hard for us
		public static void deleteEmployee(Integer id) {
			
			Employee toDelete = searchEmployeeById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteEmployee(Employee employee) {
			
			Employee toDelete = searchEmployeeById(employee.getId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteProvider(Integer id) {
			
			Provider toDelete = searchProviderById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteProvider(Provider provider) {
			
			Provider toDelete = searchProviderById(provider.getProviderId());
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteWarehouse(Integer id) {
			
			Warehouse toDelete = searchWarehouseById(id);
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}
		
		public static void deleteWarehouse(Warehouse warehouse) {
			
			Warehouse toDelete = searchWarehouseById(warehouse.getId());			
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
			
		}	
		

}