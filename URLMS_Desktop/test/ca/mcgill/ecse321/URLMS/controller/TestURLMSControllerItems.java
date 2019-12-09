package ca.mcgill.ecse321.URLMS.controller;

import static org.junit.Assert.*;

import java.io.File;

import java.sql.*;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.URLMS.*;
import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;

public class TestURLMSControllerItems {
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

		String name = "Paper";
		double price = 5;
		int quantity = 5;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createSupply(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			fail();
		}

		checkResultItemName(name, thisLab);
		checkResultItemPrice(price, thisLab);
		checkResultItemQuantity(quantity, thisLab);

		LaboratoryManager manager2 = (LaboratoryManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultItemName(name, manager2);
		checkResultItemPrice(price, manager2);
		checkResultItemQuantity(quantity, manager2);

		manager2.delete();
	}
	
	@Test
	public void testCreateEquipment() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Paper";
		double price = 5;
		int quantity = 5;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEquipment(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			fail();
		}

		checkResultItemName(name, thisLab);
		checkResultItemPrice(price, thisLab);
		checkResultItemQuantity(quantity, thisLab);

		LaboratoryManager manager2 = (LaboratoryManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultItemName(name, manager2);
		checkResultItemPrice(price, manager2);
		checkResultItemQuantity(quantity, manager2);

		manager2.delete();
	}
	
	@Test
	public void testCreateEquipmentNullName() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = null;
		double price = 5;
		int quantity = 5;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEquipment(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the name of the equipment item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateSupplyNullName() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = null;
		double price = 5;
		int quantity = 5;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createSupply(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the name of the supply item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateSupplyZeroPrice() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Paper";
		double price = 0;
		int quantity = 5;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createSupply(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the cost of the supply item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateEquipmentZeroPrice() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Paper";
		double price = 0;
		int quantity = 5;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEquipment(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the cost of the equipment item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateSupplyZeroQuantity() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Paper";
		double price = 5;
		int quantity = 0;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createSupply(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the quantity of the purchased supply item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateEquipmentZeroQuantity() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Paper";
		double price = 5;
		int quantity = 0;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEquipment(name, price, quantity);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the quantity of the purchased equipment item(s)", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	

	private void checkResultItemPrice(double price, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getItems().size());
		assertEquals(price, checkLab.getItem(0).getPrice(), 0.01);
	}

	private void checkResultItemName(String name, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getItems().size());
		assertEquals(name, checkLab.getItem(0).getName());
	}
	
	private void checkResultItemQuantity(int quantity, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getItems().size());
		assertEquals(quantity, checkLab.getItem(0).getQuantity());
	}
	
}
