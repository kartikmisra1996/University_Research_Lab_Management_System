package ca.mcgill.ecse321.URLMS.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;

public class TestURLMSControllerTransaction {
	private LaboratoryManager thisLab;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		thisLab = new LaboratoryManager();
	}

	@After
	public void tearDown() throws Exception {
		thisLab.delete();
	}

	@Test
	public void testCreateSupply() {
		assertEquals(0, thisLab.getEmployees().size());

		URLMSController test = new URLMSController(thisLab);

		String date = " ";
		String description = "nice transaction";
		double amount = 10.0;

		try {
			test.createTransaction(date, description, amount);
		} 
		catch (InvalidInputException e) {
			fail();
		}

		checkResultTransactionDescription(description, thisLab);
		checkResultITransactionAmount(amount, thisLab);

		LaboratoryManager manager2 = (LaboratoryManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultTransactionDescription(description, manager2);
		checkResultITransactionAmount(amount, manager2);

		manager2.delete();
	}

	@Test
	public void testCreateTransactionNullName() {
		assertEquals(0, thisLab.getEmployees().size());

		String date = " ";
		String description = null;
		double amount = 10.0;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createTransaction(date, description, amount);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter valid description", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getTransactions().size());
	}

	@Test
	public void testCreateTransactionZeroAmount() {
		assertEquals(0, thisLab.getEmployees().size());

		String date = " ";
		String description = "description";
		double amount = 0;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createTransaction(date, description, amount);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the amount", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getTransactions().size());
	}

	private void checkResultTransactionDescription(String description, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getTransactions().size());
		assertEquals(description, checkLab.getTransaction(0).getDescription());
	}

	private void checkResultITransactionAmount(double amount, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getTransactions().size());
		assertEquals(amount, checkLab.getTransaction(0).getAmount(), 0.01);
	}
}
