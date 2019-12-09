package ca.mcgill.ecse321.URLMS.application;

import java.sql.Date;

import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.URLMS.view.*;

public class URLMS_System {

	public static LaboratoryManager thisLab;
	public static FundingAccount thisFundingAccount;
	public static String fileName = "data.xml";

	public static void main(String[] args) {
		final LaboratoryManager thisLab = PersistenceXStream.initializeModelManager(fileName);
		URLMSController defaultController = new URLMSController(thisLab);

		//adding default employees
		if (thisLab.hasEmployees() == false){
			try {
				defaultController.createEmployee("Jean-Pierre", "Chemist", "Research Associate", 1000, 123456889);
				defaultController.createEmployee("Pierre-Andre", "Physicist", "Reseach Assistant", 800, 987654321);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//adding default paystubs if there are none
		boolean checker = true;
		for (int i = 0; i<thisLab.getTransactions().size(); i++) {
			if(Paystub.class.isInstance(thisLab.getTransaction(i))) {
				checker = false;
			}
		}
		if (checker) {
			Date today = defaultController.getSqlDate();
			double amount1 = thisLab.getEmployee(0).getSalary();
			Employee employee1 = thisLab.getEmployee(0);
			defaultController.createPaystub(today, amount1, employee1);

			double amount2 = thisLab.getEmployee(1).getSalary();
			Employee employee2 = thisLab.getEmployee(1);
			defaultController.createPaystub(today, amount2, employee2);
		}

		//adding default lab items if there are none
		if (thisLab.hasItems() == false){
			try {
				defaultController.createSupply("Paper (500 sheets)", 10, 5);
				defaultController.createEquipment("Super Computer", 30000, 2);
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}

		}

		//adding default progress updates if there are none
		if (thisLab.hasEmployees()) {
			for (int i = 0; i<thisLab.numberOfEmployees(); i++) {
				if (!thisLab.getEmployee(i).hasProgressUpdates()) {
					Date date = defaultController.getSqlDate();
					String obj = "Finish report on water pollution test";
					String work = "Water pollution test completed";
					try {
						defaultController.createProgressUpdate(obj, work, date, thisLab.getEmployee(i));
					} catch (InvalidInputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Date date2 = defaultController.getSqlDate();
					String obj2 = "Start last segment on air pollution test";
					String work2 = "4/5 sections completed on air pollution tests";
					try {
						defaultController.createProgressUpdate(obj2, work2, date2, thisLab.getEmployee(i));
					} catch (InvalidInputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		//adding default funding account
		if(!thisLab.hasFundingAccount()) {
			defaultController.createFundingsAccount(100000);
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
