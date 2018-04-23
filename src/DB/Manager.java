package DB;

import java.sql.Date;

import pojos.Arrival;
import pojos.Client;
import pojos.Corridor;
import pojos.Delivery;
import pojos.Drug;
import pojos.Employee;
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
	
	
	
	
/*
 * =====================================================================================================
 * 					Reads
 * =====================================================================================================
 */
	

     public static Employee extractEmployeeById() {
		return null;
     }


/*
 * =====================================================================================================
 * 					Updates
 * =====================================================================================================
 */    
     public static void updateArrivalBuyingPrice(Arrival arrival, Integer buyingPrice) {}
     
     public static void updateArrivalDate(Arrival arrival, Date date) {}
     
     public static void updateArrivalProvider(Arrival arrival, Provider provider) {}
     
     
     public static void updateDrugActivePrinciple(Drug drug, String activePrinciple) {}
     
     public static void updateDrugCorridor(Drug drug, Corridor corridor) {}
     
     public static void updateDrugName(Drug drug, String name) {}
     
     public static void updateDrugSellingPrice(Drug drug, Integer sellingPrice) {}
     
     public static void updateDrugStock(Drug drug, Integer stock) {}
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
