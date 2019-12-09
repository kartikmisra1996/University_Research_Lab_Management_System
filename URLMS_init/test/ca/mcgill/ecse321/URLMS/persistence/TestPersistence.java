package ca.mcgill.ecse321.URLMS.persistence;

import static org.junit.Assert.*;

import java.io.File;

import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPersistence {

	private LaboratoryManager lm;

	@Before
	public void setUp() throws Exception {
		lm = new LaboratoryManager();

		URLMSController testCtrl = new URLMSController(lm);
		String error = "";

		try{
			testCtrl.createEmployee("Edinson Cavani", "Forward","Research Associate", 40000000);
		}
		catch(Exception e){
			error = error + e.getMessage();
		}
		try{
			testCtrl.createEmployee("Angel Di Maria", "Right Wing", "Research Assistant", 35000000);
		}
		catch(Exception e){
			error = error + e.getMessage();
		}


	}

	@After
	public void tearDown() throws Exception {
		lm.delete();
	}

	@Test
	public void test() {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
		
		if (!PersistenceXStream.saveToXMLwithXStream(lm)){
			fail("Could not save file.");
		}
		
		// clear model in memory
		lm.delete();
		assertEquals(0, lm.getEmployees().size());
		
		//load model
		lm = (LaboratoryManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check employees
		assertEquals(2, lm.getEmployees().size());
		assertEquals("Edinson Cavani", lm.getEmployee(0).getName());
		assertEquals("Angel Di Maria", lm.getEmployee(1).getName());
		
	}

}
