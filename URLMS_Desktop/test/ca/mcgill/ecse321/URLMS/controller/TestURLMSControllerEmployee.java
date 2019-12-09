package ca.mcgill.ecse321.URLMS.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.*;
import org.junit.*;
import java.util.Calendar;

import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.persistence.*;
import ca.mcgill.ecse321.URLMS.controller.*;

public class TestURLMSControllerEmployee {
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
	public void testCreateEmployee() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Kartik Misra";
		double id = 12345.0;
		double salary = 500.0;
		String role = "Research Associate";
		String job = "Engineer";

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			fail();
		}

		checkResultEmployeeName(name, thisLab);
		checkResultEmployeeID(id, thisLab);
		checkResultEmployeeJob(job, thisLab);
		checkResultEmployeeRole(role, thisLab);
		checkResultEmployeeSalary(salary, thisLab);

		LaboratoryManager manager2 = (LaboratoryManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultEmployeeName(name, manager2);
		checkResultEmployeeID(id, manager2);
		checkResultEmployeeJob(job, manager2);
		checkResultEmployeeRole(role, manager2);
		checkResultEmployeeSalary(salary, manager2);

		manager2.delete();
	}


	@Test
	public void testCreateEmployeeNullName() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = null;
		double id = 12345;
		String job = "Engineer";
		String role = "Reseach Assistant";
		double salary = 1234;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the name of the new employee", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateEmployeeEmptyName() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "";
		double id = 12345;
		String job = "Engineer";
		String role = "Reseach Assistant";
		double salary = 1234;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the name of the new employee", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateEmployeeNullRole() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Etienne";
		double id = 12345;
		String job = "Engineer";
		String role = null;
		double salary = 1234;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter employee role", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	@Test
	public void testCreateEmployeeNullJob() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Etienne";
		double id = 12345;
		String job = null;
		String role = "Research Assistant";
		double salary = 1234;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the new staff member's job title", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}
	
	
	@Test
	public void testCreateEmployeeNullSalary() {
		assertEquals(0, thisLab.getEmployees().size());

		String name = "Etienne";
		double id = 12345;
		String job = "engineer";
		String role = "Research Assistant";
		double salary = 0;

		String error = null;

		URLMSController test = new URLMSController(thisLab);
		try {
			test.createEmployee(name, job, role, salary, id);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// Check that the appropriate error is thrown
		assertEquals("Enter the new staff member's weekly salary", error);

		// Check that the object was not actually created
		assertEquals(0, thisLab.getEmployees().size());
	}



	private void checkResultEmployeeName(String name, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getEmployees().size());
		assertEquals(name, checkLab.getEmployee(0).getName());
		assertEquals(0, checkLab.getProgressUpdates().size());
	}

	private void checkResultEmployeeID(double id, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getEmployees().size());
		assertEquals(id, checkLab.getEmployee(0).getId(), 0.001);
		assertEquals(0, checkLab.getProgressUpdates().size());
	}

	private void checkResultEmployeeJob(String job, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getEmployees().size());
		assertEquals(job, checkLab.getEmployee(0).getJob());
		assertEquals(0, checkLab.getProgressUpdates().size());
	}

	private void checkResultEmployeeRole(String role, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getEmployees().size());
		assertEquals(role, checkLab.getEmployee(0).getRole());
		assertEquals(0, checkLab.getProgressUpdates().size());
	}

	private void checkResultEmployeeSalary(Double salary, LaboratoryManager checkLab) {
		assertEquals(1, checkLab.getEmployees().size());
		assertEquals(salary, checkLab.getEmployee(0).getSalary(), 0.001);
		assertEquals(0, checkLab.getProgressUpdates().size());
	}
}
