package DB;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import pojos.Client;
import pojos.Corridor;
import pojos.Database;
import pojos.Drug;
import pojos.Provider;
import pojos.Warehouse;


public class XMLManager {
	
	public static void main(String[] args) throws TransformerException, JAXBException {
		String dir = "./xml/DatabaseTest.xml";
		try {
			SQLManager.connect("jdbc:sqlite:./db/Drug Megastore Data Base TEST 2.db");
			
			List<Client> clients = SQLManager.getAllClients();			
			List<Provider> providers = SQLManager.getAllProviders();
			List<Warehouse> warehouses = SQLManager.getAllWarehouses();
			
			for(Client c: clients) {
				
				c.setDeliveries(SQLManager.searchDeliveryByClientId(c.getId()));
				
			}
			
			for(Provider p: providers) {
				
				p.setArrivals(SQLManager.searchArrivalsByProviderId(p.getProviderId()));
				
			}
			
			for(Warehouse w: warehouses) {
				
				System.out.println("warehouse ID = "+ w.getId());
				List<Corridor> corridors = SQLManager.searchCorridorByWarehouseId(w.getId());
				//List<Corridor> corridors = SQLManager.getAllCorridors();
				for(Corridor c: corridors) {
					
					System.out.println(c);
					c.setDrugs(SQLManager.searchDrugByCorridorId(c.getId()));
					
				}
				w.setCorridors(corridors);
				w.setEmployees(SQLManager.getAllEmployees());
				
			}
			
			Database database = new Database(clients, providers, warehouses);
			
			try {
				XMLManager.marshallDatabase(database, dir);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println("error marshalling");
			}
			XMLManager.databasexml2Html(dir, "./xml/Datanuevo.html");
			
			try {
				XMLManager.marshallDatabase(database, dir);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				XMLManager.databasexml2Html(dir, "./xml/databasehtml.html");
				try {
					File file2 = new File("./xml/databasehtml.html");
					Desktop.getDesktop().browse(file2.toURI());
				} catch (IOException e) {
					System.out.println("Error opening the HTML file");
				}
			} catch (TransformerException e) {
				e.printStackTrace();
				System.out.println("Error turning XML into HTML");
			}
			SQLManager.disconnect();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void databasexml2Html(String origin, String destiny) throws TransformerException {

		String xsltPath = "./xml/Database.xslt";
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
        transformer.transform(new StreamSource(new File(origin)),new StreamResult(new File(destiny)));

    }
	
	
	public static void drugxml2Html(String origin, String destiny) throws TransformerException {
		
		String xsltPath = "./xml/Database.xslt";
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
		transformer.transform(new StreamSource(new File(origin)),new StreamResult(new File(destiny)));
	
	}
	
	public static void marshallDrug(Drug drug, String directory) throws JAXBException, IOException {
		
		JAXBContext jaxbC = JAXBContext.newInstance(Drug.class);
		Marshaller jaxbM = jaxbC.createMarshaller();
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		jaxbM.marshal(drug, new BufferedWriter(new FileWriter(directory)));

		
	}
	
	public static Drug unmarshallDrug(String directory) throws JAXBException, FileNotFoundException {

		JAXBContext jaxbC = JAXBContext.newInstance(Drug.class);
		Unmarshaller jaxbU = (Unmarshaller) jaxbC.createUnmarshaller();
		Drug drug = (Drug) jaxbU.unmarshal(new BufferedReader(new FileReader(directory)));
		return drug;
		
	}
	
	public static void marshallDatabase(Database database, String directory) throws JAXBException, IOException {
		
		JAXBContext jaxbC = JAXBContext.newInstance(Database.class);
		Marshaller jaxbM = jaxbC.createMarshaller();
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		jaxbM.marshal(database, new BufferedWriter(new FileWriter(directory)));
		
	}
	
	public static Database unmarshallDatabase(String directory) throws JAXBException, FileNotFoundException {
		
		JAXBContext jaxbC = JAXBContext.newInstance(Database.class);
		Unmarshaller jaxbU = (Unmarshaller) jaxbC.createUnmarshaller();
		Database database = (Database) jaxbU.unmarshal(new BufferedReader(new FileReader(directory)));
		return database;
		
	}

}