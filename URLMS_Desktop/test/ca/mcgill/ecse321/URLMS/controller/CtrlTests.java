package ca.mcgill.ecse321.URLMS.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

public class CtrlTests {

	@RunWith(Suite.class)
	@SuiteClasses({ TestURLMSControllerEmployee.class, TestURLMSControllerItems.class,
		TestURLMSControllerTransaction.class})
	public class AllTestsController {

	}
}
