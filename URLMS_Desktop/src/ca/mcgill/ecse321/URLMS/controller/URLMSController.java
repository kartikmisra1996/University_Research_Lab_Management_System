package ca.mcgill.ecse321.URLMS.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.persistence.PersistenceXStream;

public class URLMSController{

	private LaboratoryManager thisLab;

	public URLMSController (LaboratoryManager thisLab) {
		this.thisLab = thisLab;
	}

	public boolean checkIDValidity (double id) {
		boolean result = true;
		for (Employee employee : thisLab.getEmployees()) {
			if(employee.getId() == id) {
				return false;
			}
		}
		return result;
	}

	public void createEmployee ( String name, String job, String role, double salary, double ID) throws InvalidInputException{
		String error = "";
		if (name == null || name.trim().length()==0){
			error = error + "Enter the name of the new employee";
		}
		if (job == null){
			error = error + "Enter the new staff member's job title";
		}
		if (role == null) {
			error = error + "Enter employee role";
		}
		if (salary == 0){
			error = error + "Enter the new staff member's weekly salary";
		}
		if (Double.toString(ID) == null) {
			error = error + "Enter a valid ID";
		}
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		Employee newGuy = new Employee(name, job, salary, role, ID);
		thisLab.addEmployee(newGuy);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void editEmployee(Employee employee, String newName, String newJob, String newRole, double newSalary) {
		employee.setName(newName);
		employee.setRole(newRole);
		employee.setJob(newJob);
		employee.setSalary(newSalary);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void editItem (Item item, String nName, double nPrice, int nQuantity) {
		item.setName(nName);
		item.setPrice(nPrice);
		item.setQuantity(nQuantity);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void editSupply(Supply supply, String newName, double newPrice, int newQuantity, boolean isAvailable) {
		supply.setName(newName);
		supply.setPrice(newPrice);
		supply.setQuantity(newQuantity);
		supply.setIsAvailable(isAvailable);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void editEquipment(Equipment equipment, String newName, double newPrice, int newQuantity) {
		equipment.setName(newName);
		equipment.setPrice(newPrice);
		equipment.setQuantity(newQuantity);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void takeOutItem (Item item) {
		thisLab.removeItem(item);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void takeOutEmployee (Employee employee) {
		thisLab.removeEmployee(employee);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}
	
	public void takeOutProgressUpdate (Employee employee,ProgressUpdate progressUpdate) {
	      employee.removeProgressUpdate(progressUpdate);
	      PersistenceXStream.saveToXMLwithXStream(thisLab);
	  }

	public void createSupply (String name, double price, int quantity) throws InvalidInputException {
		String error = "";
		boolean isAvailable;
		if (price >= 1) {
			isAvailable = true;
		}
		else {
			isAvailable = false;
		}
		if (name == null){
			error = error + "Enter the name of the supply item(s)";
		}
		if (price <= 0){
			error = error + "Enter the cost of the supply item(s)";
		}
		if (quantity <= 0){
			error = error + "Enter the quantity of the purchased supply item(s)";
		}
		if(error.length()>0) {
			throw new InvalidInputException(error);
		}
		Supply newSupply = new Supply(name, price, quantity, isAvailable);
		thisLab.addItem(newSupply);
		double totalCost = price*quantity;
		String description = "Type: Supplies, Name: "+name+", Amount: "+quantity+" ,Total Cost: "+totalCost;

		//Managing sql and util date types

		Transaction transaction = new Transaction(getSqlDate(), description, totalCost);
		thisLab.addTransaction(transaction);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void removeSupply (Supply supply) {
		String date = "today";
		String name = supply.getName();
		double price = supply.getPrice();
		double quantity = supply.getQuantity();
		double totalCost = price*quantity;
		supply.setIsAvailable(false);
		thisLab.removeItem(supply);
		String description = "Remove item of type supply, Name: "+name+", Amount: "+quantity+" ,Total Cost: "+totalCost;
		Transaction transaction = new Transaction(getSqlDate(), description, totalCost);
		thisLab.addTransaction(transaction);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void createEquipment (String name, double price, int quantity) throws InvalidInputException {
		String error = "";
		if (name == null){
			error = error + "Enter the name of the equipment item(s)";
		}
		if (price <=0 ){
			error = error + "Enter the cost of the equipment item(s)";
		}
		if (quantity <=0 ){
			error = error + "Enter the quantity of the purchased equipment item(s)";
		}
		if(error.length()>0) {
			throw new InvalidInputException(error);
		}
		Equipment newEquipment = new Equipment(name, price, quantity);
		thisLab.addItem(newEquipment);
		double totalCost = price*quantity;
		String description = "Type: Equipment, Name: "+name+", Amount: "+quantity+" ,Total Cost: "+totalCost;
		String date = "today";
		Transaction transaction = new Transaction(getSqlDate(), description, totalCost);
		thisLab.addTransaction(transaction);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void removeEquipment (Equipment equipment) {
		String date = "today";
		String name = equipment.getName();
		double price = equipment.getPrice();
		double quantity = equipment.getQuantity();
		double totalCost = price*quantity;
		thisLab.removeItem(equipment);
		String description = "Remove item of type supply, Name: "+name+", Amount: "+quantity+" ,Total Cost: "+totalCost;
		Transaction transaction = new Transaction(getSqlDate(), description, totalCost);
		thisLab.addTransaction(transaction);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void createProgressUpdate (String objective, String work, java.sql.Date date, Employee employee) throws InvalidInputException {
		String error = "";
		if(objective == null || work == null) {
			error = error + "Invalid Input";
		}
		if(objective.trim().length() == 0 || work.trim().length() == 0) {
			error = error + "You must type something";
		}
		if(error.length()>0) {
			throw new InvalidInputException(error);
		}
		java.sql.Date thisDate = date;
		ProgressUpdate proUp = new ProgressUpdate (objective, work, thisDate);
		employee.addProgressUpdate(proUp);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void editProgressUpdate(String objective, String work, java.sql.Date date, ProgressUpdate progressupdate) {
		progressupdate.setObjective(objective);
		progressupdate.setActualWork(work);
		progressupdate.setDate(date);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void createTransaction (String date, String description, double amount) throws InvalidInputException {
		String error = "";
		if (description == null || description.trim().length()<0) {
			error = error + "Enter valid description";
		}
		if(amount == 0) {
			error = error + "Enter the amount";
		}
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		Transaction transaction = new Transaction(getSqlDate(), description, amount);
		thisLab.addTransaction(transaction);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public double getAllExpenses(){
		String error = "";
		double result = 0;
		try {
			for (int i = 0; i<thisLab.getItems().size(); i++) {
				result = result + thisLab.getItem(i).getPrice()*thisLab.getItem(i).getQuantity();
			}
		}
		catch (Exception e){
			error = error + e.getMessage();
		}
		return result;
	}

	public void insertFunds (double funds){
		double newBalance = thisLab.getFundingAccount(0).getBalance() + funds;
		thisLab.getFundingAccount(0).setBalance(newBalance);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void removeFunds (double funds){
		double newBalance = thisLab.getFundingAccount(0).getBalance() - funds;
		thisLab.getFundingAccount(0).setBalance(newBalance);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public double getBudget(){
		double result = thisLab.getFundingAccount(0).getBalance() + getAllExpenses();
		return result;
	}

	public Date getSqlDate() {
		SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	public void createFundingsAccount(double balance) {
		FundingAccount aFundingAccount = new FundingAccount(balance);
		thisLab.addFundingAccount(aFundingAccount);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public void createPaystub (Date date, double amount, Employee employee) {
		double id = employee.getId();
		String description = "Payment to " + employee.getName() + ", ID: " + employee.getId();
		Paystub paystub = new Paystub(date, description, amount, id);
		thisLab.addTransaction(paystub);
		PersistenceXStream.saveToXMLwithXStream(thisLab);
	}

	public Employee getEmployeeFromID (double id) {
		for(int i = 0; i<thisLab.getEmployees().size(); i++) {
			if(thisLab.getEmployee(i).getId() == id) {
				return thisLab.getEmployee(i);
			}
		}
		return null;
	}

	public void updatePayRoll() {
		for (int i = 0; i<thisLab.getEmployees().size(); i++) {
			Employee employee = thisLab.getEmployee(i);
			double employeeID = employee.getId();
			LinkedList<Paystub> employeePayList = new LinkedList<Paystub>();
			for (int j = 0; j<thisLab.getTransactions().size(); j++){
				if (Paystub.class.isInstance(thisLab.getTransaction(j))) {
					Paystub paystub = (Paystub)thisLab.getTransaction(j);
					double id = paystub.getEmployeeID();
					if(id == employeeID) {
						employeePayList.add(paystub);
					}
				}
			}
			Paystub lastPay = employeePayList.getLast();
			Date lastDate = lastPay.getDate();
		}
	}

	public int getNumberOfThursdays (Date date) {
		int result = 0;
		Date curDate = getSqlDate();
		long diff = curDate.getTime() - date.getTime();
		return result;
	}

	public void autoPayment() {

	}
}
