package DB;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Arrival;
import pojos.Arrives;
import pojos.Client;
import pojos.Client.PaymentMethod;
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
			for(Arrives a: arrival.getArrives()) {
				
				Drug d = JPAManager.searchDrugById(a.getDrugId());
				d.setStock(d.getStock() + a.getAmount());				
				
			}			
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
			for(Packaged p: delivery.getPackages()) {
				
				Drug d = JPAManager.searchDrugById(p.getDrugId());
				d.setStock(d.getStock() - p.getAmount());				
				//p.setDeliveryId(delivery.getTransactionId());
				//em.persist(p);
			}
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
			
			Query q1 = em.createNativeQuery("SELECT * FROM arrivals WHERE transaction_id = ?;", Arrival.class);
			q1.setParameter(1, id);
			try {
				return (Arrival) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Arrives> searchArrivesByArrivalId(Integer id){
		
			Query q1 = em.createNativeQuery("SELECT * FROM arrives WHERE drug_id = ?;", Arrives.class);
			q1.setParameter(1, id);
			return (List<Arrives>) q1.getResultList();
		
		}

		@SuppressWarnings("unchecked")
		public static List<Arrives> searchArrivesByDrugId(Integer id){
			
			Query q1 = em.createNativeQuery("SELECT * FROM arrives WHERE transaction_id = ?;", Arrives.class);
			q1.setParameter(1, id);
			return (List<Arrives>) q1.getResultList();
	
		}
		
		public static Corridor searchCorridorById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM corridor WHERE id = ?;", Corridor.class);
			q1.setParameter(1, id);
			try {
				return (Corridor) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		public static Client searchClientById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM client WHERE id = ?;", Client.class);
			q1.setParameter(1, id);
			try {
				return (Client) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		public static Client searchClientByUsername(String username) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM client WHERE username = ?;", Client.class);
			q1.setParameter(1, username);
			try {
				return (Client) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
		
		}
		
		public static Delivery searchDeliveryById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM deliveries WHERE transaction_id = ?;", Delivery.class);
			q1.setParameter(1, id);
			try {
				return (Delivery) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Delivery> searchDeliveryByClientId(Integer id) {
		
			Query q1 = em.createNativeQuery("SELECT * FROM deliveries WHERE client_id = ?;", Delivery.class);
			q1.setParameter(1, id);
			return (List<Delivery>) q1.getResultList();
		
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByActivePrinciple(String activePrinciple) {
		
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE active_principle LIKE ?;");
			q1.setParameter(1, "%"+activePrinciple+"%");
			return (List<Drug>) q1.getResultList();
		
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByActivePrinciple(String activePrinciple, Integer maxPrice) {
		
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE active_principle LIKE ? AND selling_price <= ?;");
			q1.setParameter(1, "%"+activePrinciple+"%");
			q1.setParameter(2, maxPrice);
			return (List<Drug>) q1.getResultList();
		
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByMaxPrice(Integer maxPrice) {

			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE selling_price <= ?;");
			q1.setParameter(1, maxPrice);
			return (List<Drug>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByName(String name) {

			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE name like ?;");
			q1.setParameter(1, "%"+name+"%");
			return (List<Drug>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByName(String name, String activePrinciple) {

			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE name like ? AND active_principle LIKE ?;");
			q1.setParameter(1, "%"+name+"%");
			q1.setParameter(2, "%"+activePrinciple+"%");
			return (List<Drug>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByName(String name, Integer maxPrice) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE name like ? AND selling_price <= ?;");
			q1.setParameter(1, "%"+name+"%");
			q1.setParameter(2, maxPrice);
			return (List<Drug>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> searchDrugByName(String name, String activePrinciple, Integer maxPrice) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE name like ? AND active_principle LIKE ? AND selling_price <= ?;");
			q1.setParameter(1, "%"+name+"%");
			q1.setParameter(2, "%"+activePrinciple+"%");
			q1.setParameter(3, maxPrice);
			return (List<Drug>) q1.getResultList();
		
		}
		
		public static Employee searchEmployeeById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM employee WHERE id = ?;", Employee.class);
			q1.setParameter(1, id);
			try {
				return (Employee) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		public static Employee searchEmployeeByUsername(String username) {

			Query q1 = em.createNativeQuery("SELECT * FROM employee WHERE username LIKE ?;", Employee.class);
			q1.setParameter(1, username);
			try {
				return (Employee) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Packaged> searchPackagedByDeliveryId(Integer id) {

			Query q1 = em.createNativeQuery("SELECT * FROM packaged WHERE drug_id = ?;", Packaged.class);
			q1.setParameter(1, id);
			return (List<Packaged>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Packaged> searchPackagedByDrugId(Integer id) {

			Query q1 = em.createNativeQuery("SELECT * FROM packaged WHERE transaction_id = ?;", Packaged.class);
			q1.setParameter(1, id);
			return (List<Packaged>) q1.getResultList();
			
		}
		
		public static Provider searchProviderById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM provider WHERE id = ?", Provider.class);
			q1.setParameter(1, id);
			try {
				return (Provider) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
		
		public static Warehouse searchWarehouseById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM warehouse WHERE id = ?", Warehouse.class);
			q1.setParameter(1, id);
			try {
				return (Warehouse) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
				
		public static Drug searchDrugById(Integer id) {
			
			Query q1 = em.createNativeQuery("SELECT * FROM drug WHERE id = ?", Drug.class);
			q1.setParameter(1, id);
			try {
				return (Drug) q1.getSingleResult();
			}catch (NoResultException e) {
				return null;
			}
			
		}
			
		
		/*
		 *  SELECT EVERYTHING FROM EACH TABLE
		 */
		
		@SuppressWarnings("unchecked")
		public static List<Arrival> getAllArrivals(){
			
			Query q1 = em.createNativeQuery("SELECT * FROM arrivals", Arrival.class);
			return (List<Arrival>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Arrives> getAllArrives(){
			
			Query q1 = em.createNativeQuery("Select * FROM arrives", Arrives.class);
			return (List<Arrives>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Client> getAllClients(){
			
			Query q1 = em.createNativeQuery("Select * FROM client", Client.class);
			return (List<Client>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Corridor> getAllCorridors(){
			
			Query q1 = em.createNativeQuery("Select * FROM corridor", Corridor.class);
			return (List<Corridor>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Delivery> getAllDeliveries(){
			
			Query q1 = em.createNativeQuery("Select * FROM deliveries", Delivery.class);
			return (List<Delivery>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Drug> getAllDrugs(){
			
			Query q1 = em.createNativeQuery("Select * FROM drug", Drug.class);
			return (List<Drug>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Employee> getAllEmployees(){
			
			Query q1 = em.createNativeQuery("Select * FROM employee", Employee.class);
			return (List<Employee>) q1.getResultList();
			
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Packaged> getAllPackaged(){
			
			Query q1 = em.createNativeQuery("Select * FROM packaged", Packaged.class);
			return (List<Packaged>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Provider> getAllProviders(){
			
			Query q1 = em.createNativeQuery("Select * FROM provider", Provider.class);
			return (List<Provider>) q1.getResultList();
			
		}
		
		@SuppressWarnings("unchecked")
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
		
		//CLIENT
		public static void updateClient(Integer id, String address, String email, Integer telephone, PaymentMethod paymentMethod) throws SQLException {
			
			Client client = JPAManager.searchClientById(id);
			em.getTransaction().begin();
			client.setAddress(address);
			client.setEmail(email);
			client.setTelephone(telephone);
			client.setPaymentMethod(paymentMethod);
			em.getTransaction().commit();
			
		}
		
		public static void updateClient(Integer id, String address, String email, Integer telephone, PaymentMethod paymentMethod, String username, String password) throws SQLException {
			
			Client client = JPAManager.searchClientById(id);
			em.getTransaction().begin();
			client.setAddress(address);
			client.setEmail(email);
			client.setTelephone(telephone);
			client.setPaymentMethod(paymentMethod);
			client.setUsername(username);
			client.setPassword(password);
			em.getTransaction().commit();
		}

		
		//Drug
		
		public static void updateDrug(Integer id, Integer stock, Integer sellingPrice, String name, String activePrinciple, Corridor corridor, byte[] photo) throws SQLException {
			
			Drug drug = JPAManager.searchDrugById(id);
			em.getTransaction().begin();
			drug.setStock(stock);
			drug.setSellingPrice(sellingPrice);
			drug.setName(name);
			drug.setActivePrinciple(activePrinciple);
			drug.setCorridor(corridor);
			drug.setPhoto(photo);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugStock(Integer id, Integer stock) throws SQLException {
			
			Drug drug = JPAManager.searchDrugById(id);
			em.getTransaction().begin();
			drug.setStock(stock);
			em.getTransaction().commit();
			
		}
		
		public static void updateDrugPhoto(Integer id, byte[] photo) throws SQLException {
			
			Drug drug = JPAManager.searchDrugById(id);
			em.getTransaction().begin();
			drug.setPhoto(photo);
			em.getTransaction().commit();
					
		}
			
		//Employee
		public static void updateEmployee(Integer id, String name, float salary, Integer phone, String position, Boolean isAdmin, Warehouse warehouse, byte[] photo ) throws SQLException {
			
			Employee employee = JPAManager.searchEmployeeById(id);
			em.getTransaction().begin();
			employee.setName(name);
			employee.setSalary(salary);
			employee.setPhone(phone);
			employee.setPosition(position);
			employee.setIsAdmin(isAdmin);
			employee.setWarehouse(warehouse);
			employee.setPhoto(photo);
			em.getTransaction().commit();
			
		}
			
		public static void updateEmployeePhoto(Integer id, byte[] photo) throws SQLException {
			
			Employee employee = JPAManager.searchEmployeeById(id);
			em.getTransaction().begin();
			employee.setPhoto(photo);
			em.getTransaction().commit();
			
		}
	
		//Deliveries
		public static void updateDeliverySent(Integer id, Boolean sent) throws SQLException {
			
			Delivery delivery = JPAManager.searchDeliveryById(id);
			em.getTransaction().begin();
			delivery.setSent(sent);
			em.getTransaction().commit();
			
			
		}	
		
		//Arrival
				public static void updateArrivalSent(Integer id, Boolean sent) throws SQLException {
					
					Arrival arrival = JPAManager.searchArrivalById(id);
					em.getTransaction().begin();
					arrival.setReceived(sent);
					em.getTransaction().commit();
					
					
				}	
/*
 * =====================================================================================================
 * 								DELETE
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