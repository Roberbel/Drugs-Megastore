package DB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pojos.Drug;

public class XMLManager {
	
	public static void main(String[] args) {
		
		try {
			XMLManager.marshallDrug(new Drug(), "D:\\Documents\\GitHub\\Drugs-Megastore\\xml");
			System.out.println(XMLManager.unmarshallDrug("D:\\Documents\\GitHub\\Drugs-Megastore\\xml"));
		} catch (JAXBException|IOException e) {
			
			e.printStackTrace();
		}
		
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

}
