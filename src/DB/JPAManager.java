package DB;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import pojos.Arrival;
import pojos.Corridor;
import pojos.Delivery;
import pojos.Drug;

public class JPAManager {

		private static EntityManager em;
		
		public static void getEntityManager() {
			try{
			
			em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public static void disconnect()  {
			em.close();
			System.out.println("Database connection closed.");
		}
		public static void createDrugTable(String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridor corridor, byte[] photo){
			
			Drug drug=new Drug(name, stock,sellingPrice,activePrinciple,corridor, photo);
			em.getTransaction().begin();
			em.persist(drug);
			em.getTransaction().commit();
	
		}


		}
