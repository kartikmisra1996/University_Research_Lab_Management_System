package ca.mcgill.ecse321.URLMS.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.*;

import ca.mcgill.ecse321.URLMS.model.*;

public class PersistenceXStream {

	//private static final String ID_REFERENCES = null;
	private static XStream xstream = new XStream();
	private static String filename = "data.xml";
	

	public static LaboratoryManager initializeModelManager(String fileName) {
		// Initialization for persistence
		LaboratoryManager thisLab;
		setFilename(fileName);
		setAlias("item", Item.class);
		setAlias("theFundingAccount", FundingAccount.class);
		setAlias("lab", LaboratoryManager.class);
		setAlias("employee", Employee.class);

		// load model if exists
		File file = new File(fileName);
		if (file.exists()) {
			thisLab = (LaboratoryManager) loadFromXMLwithXStream();
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			thisLab = new LaboratoryManager();
			saveToXMLwithXStream(thisLab);
		}
		return thisLab;
	}

	//get model from persistence
	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		try {
			FileReader fileReader = new FileReader(filename);
			return xstream.fromXML(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	//save model to persistence
	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj);

		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(xml);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}
}
