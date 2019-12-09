package ca.mcgill.ecse321.URLMS.application;

import ca.mcgill.ecse321.URLMS.*;
import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.URLMS.view.*;

public class URLMS_System {

	public static LaboratoryManager thisLab;
	public static String fileName = "data.xml";

	public static void main(String[] args) {
		final LaboratoryManager thisLab = PersistenceXStream.initializeModelManager(fileName);
		URLMSController defaultController = new URLMSController(thisLab);
		
		if (thisLab.hasEmployees() == false){
			defaultController.createEmployee("Jean-Pierre", "Chemist", "Research Associate", 1000);
			defaultController.createEmployee("Pierre-Andre", "Physicist", "Reseach Assistant", 800);
		}	
		
		if (thisLab.hasItems() == false){
			
		}
		

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainPage(thisLab).setVisible(true);
			}
		});

	}

	public static LaboratoryManager getLab() {
		if (thisLab == null) {
			thisLab = new LaboratoryManager();
		}
		return thisLab;
	}

}
