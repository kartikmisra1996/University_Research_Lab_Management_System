package ca.mcgill.ecse321.URLMS.controller;

import java.util.*;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.URLMS.model.Employee;

public class URLMSController {
	
	private LaboratoryManager thisLab;
	
	public URLMSController (LaboratoryManager thisLab) {
		this.thisLab = thisLab;
	}
	
	public void createEmployee ( String name, String job, String role, double salary){
		//catching errors
		String error = "";
//		if (Double.toString(aID) == null){
//			error = error + "Must enter staff id";
//		}
		if (name == null){
			error = error + "Must enter name";
		}
		if (job == null){
			error = error + "Must enter new staff member's job title";
		}
		if (Double.toString(salary) == null){
			error = error + "Must enter staff's weekly salary";
		}
		
		Employee newGuy = new Employee(name, job, salary, role);
		thisLab.addEmployee(newGuy);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}
	
//	public void createDirector (String id){
//		Director newDirector = new Director (thisLab);
//	}
	
}
