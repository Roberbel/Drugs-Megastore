package DB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

import pojos.Drug;

public class XMLManager {
	
	private static final String xsltPath = "D:/Documents/GitHub/Drugs-Megastore/xml/Report-Style.xslt";
	
	public static void main(String[] args) {
		String dir = "D:/Documents/GitHub/Drugs-Megastore/xml/DrugTest.txt";
		try {
			JPAManager.connect();
			XMLManager.marshallDrug(JPAManager.searchDrugById(8), dir);
			System.out.println(XMLManager.unmarshallDrug(dir));
			//next one doesn't work just yet
			XMLManager.xml2Html(dir , "D:/Documents/GitHub/Drugs-Megastore/xml/drugTestHtml");
			System.out.println("finished");
		} catch (JAXBException|IOException|TransformerException  e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void xml2Html(String origin, String destiny) throws TransformerException {
		
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

}
