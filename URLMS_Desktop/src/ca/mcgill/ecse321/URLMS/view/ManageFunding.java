package ca.mcgill.ecse321.URLMS.view;

import ca.mcgill.ecse321.URLMS.application.*;
import ca.mcgill.ecse321.URLMS.controller.*;
import ca.mcgill.ecse321.URLMS.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManageFunding extends JFrame {

	String error = "";

	private LaboratoryManager thisLab;

	private JLabel fundingPageTitle;
	private JLabel allFundsLabel;
	private JLabel actualFundsLabel;
	private JLabel actualExpensesLabel;
	private JLabel allExpensesLabel;
	private JLabel changeFundsLabel;
	private JLabel balanceLabel;
	private JLabel actualBalanceLabel;
	private JLabel itemTransactions;

	private JButton addFundsButton;
	private JButton removeFundsButton;
	private JButton viewReportButton;
	private JButton returnToMenuButton;
	private JButton viewTransactionInfoButton;
	private JButton payAllEmployeesButton;
	private JButton createTransactionButton;

	private JTable transactionList;
	private JScrollPane transactionListScroll;
	private String[] tableTitles = {"Date", "Description", "Amount"};
	private String[][] tableData = {{""},{""},{""}};
	private int index = 1;
	private List<Transaction> traList;

	//	private JTable paystubList;
	//	private JScrollPane paystubListScroll;
	//	private String[] paystubTableTitles = {"Date", "Description", "Salary Paid", "Employee"};
	//	private String[][] paystubTableData = {{""},{""},{""}};
	//	private int paystubIndex = 1;
	//	private List<Paystub> payList;

	private JTextField changeFundsTextField;

	URLMSController aController = new URLMSController(thisLab);

	public ManageFunding(LaboratoryManager aLab){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thisLab = aLab;
		populateTableData();
		initAll();
		refreshData();
	}

	public void initAll(){
		itemTransactions = new JLabel();
		itemTransactions.setText("Item Transactions");
		itemTransactions.setForeground(Color.DARK_GRAY);

		fundingPageTitle = new JLabel();
		fundingPageTitle.setText("Funds Management");
		fundingPageTitle.setForeground(Color.DARK_GRAY);
		fundingPageTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 25)));

		actualExpensesLabel = new JLabel();
		actualExpensesLabel.setText(Double.toString(getExpenses()));
		actualExpensesLabel.setForeground(Color.RED);

		actualFundsLabel = new JLabel();
		actualFundsLabel.setText(Double.toString(thisLab.getFundingAccount(0).getBalance()));
		actualFundsLabel.setForeground(Color.GREEN);

		allFundsLabel = new JLabel();
		allFundsLabel.setText("All Funds: ");
		allFundsLabel.setForeground(Color.DARK_GRAY);

		changeFundsLabel = new JLabel();
		changeFundsLabel.setText("Edit Funding");
		changeFundsLabel.setForeground(Color.DARK_GRAY);

		allExpensesLabel = new JLabel();
		allExpensesLabel.setText("All Expenses: ");
		allExpensesLabel.setForeground(Color.DARK_GRAY);

		balanceLabel = new JLabel();
		balanceLabel.setText("Balance: ");
		balanceLabel.setForeground(Color.DARK_GRAY);

		actualBalanceLabel = new JLabel();
		actualBalanceLabel.setText(Double.toString(thisLab.getFundingAccount(0).getBalance() - getExpenses()));
		actualBalanceLabel.setForeground(Color.DARK_GRAY);

		changeFundsTextField = new JTextField();

//		viewReportButton = new JButton();
//		viewReportButton.setText("Generate Report");
//		viewReportButton.setBackground(Color.LIGHT_GRAY);
//		viewReportButton.setForeground(Color.DARK_GRAY);
//		viewReportButton.addActionListener(new java.awt.event.ActionListener(){
//			public void actionPerformed(java.awt.event.ActionEvent evt){
//				viewReportButtonActionPerformed();
//			}
//		});
		
		createTransactionButton = new JButton();
		createTransactionButton.setText("Create Transaction");
		createTransactionButton.setBackground(Color.LIGHT_GRAY);
		createTransactionButton.setForeground(Color.DARK_GRAY);
		createTransactionButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				createTransactionButtonActionPerformed();
			}
		});

		viewTransactionInfoButton = new JButton();
		viewTransactionInfoButton.setText("View Transaction Information");
		viewTransactionInfoButton.setBackground(Color.LIGHT_GRAY);
		viewTransactionInfoButton.setForeground(Color.DARK_GRAY);
		viewTransactionInfoButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				viewTransactionInfoButtonActionPerformed();
			}
		});
		
		payAllEmployeesButton = new JButton();
		payAllEmployeesButton.setText("Pay All Employees");
		payAllEmployeesButton.setBackground(Color.LIGHT_GRAY);
		payAllEmployeesButton.setForeground(Color.DARK_GRAY);
		payAllEmployeesButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				payAllEmployeesButtonActionPerformed();
			}
		});

		addFundsButton = new JButton();
		addFundsButton.setText("Add Funds");
		addFundsButton.setBackground(Color.LIGHT_GRAY);
		addFundsButton.setForeground(Color.DARK_GRAY);
		addFundsButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addFundsButtonActionPerformed();
			}
		});

		returnToMenuButton = new JButton();
		returnToMenuButton.setText("Main Menu");
		returnToMenuButton.setBackground(Color.LIGHT_GRAY);
		returnToMenuButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMenuActionPerformed();
			}
		});

		removeFundsButton = new JButton();
		removeFundsButton.setText("Remove Funds");
		removeFundsButton.setBackground(Color.LIGHT_GRAY);
		removeFundsButton.setForeground(Color.DARK_GRAY);
		removeFundsButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeFundsButtonActionPerformed();
			}
		});

		//Items Transaction Table
		transactionList = new JTable(tableData, tableTitles) {
			private static final long serialVersionUID = 2L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		transactionList.setGridColor(Color.DARK_GRAY);
		transactionList.setForeground(Color.WHITE);
		transactionList.setBackground(Color.DARK_GRAY);
		transactionList.setSelectionBackground(Color.WHITE);
		transactionList.setSelectionForeground(Color.BLACK);
		transactionList.getTableHeader().setBackground(Color.DARK_GRAY);
		transactionList.getTableHeader().setForeground(Color.WHITE);
		transactionList.getTableHeader().setBorder(null);
		transactionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transactionList.setColumnSelectionAllowed(false);
		transactionList.setCellSelectionEnabled(false);
		transactionList.setRowSelectionAllowed(true);
		transactionList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				index = transactionList.getSelectedRow();
				//System.out.println(focus);
				refreshData();
			}
		});

		transactionListScroll = new JScrollPane(transactionList);
		transactionListScroll.setBorder(null);
		transactionListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		transactionListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		transactionListScroll.setPreferredSize(new Dimension(400, 150));
		transactionList.setFillsViewportHeight(true);

		//Employee Salary Transaction Table
		//		paystubList = new JTable(tableData, tableTitles) {
		//			private static final long serialVersionUID = 2L;
		//
		//			public boolean isCellEditable(int row, int column) {
		//				return false;
		//			};
		//		};
		//
		//		paystubList.setGridColor(Color.DARK_GRAY);
		//		paystubList.setForeground(Color.WHITE);
		//		paystubList.setBackground(Color.DARK_GRAY);
		//		paystubList.setSelectionBackground(Color.WHITE);
		//		paystubList.setSelectionForeground(Color.BLACK);
		//		paystubList.getTableHeader().setBackground(Color.DARK_GRAY);
		//		paystubList.getTableHeader().setForeground(Color.WHITE);
		//		paystubList.getTableHeader().setBorder(null);
		//		paystubList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//		paystubList.setColumnSelectionAllowed(false);
		//		paystubList.setCellSelectionEnabled(false);
		//		paystubList.setRowSelectionAllowed(true);
		//		paystubList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		//			public void valueChanged(ListSelectionEvent event) {
		//				index = paystubList.getSelectedRow();
		//				//System.out.println(focus);
		//				refreshData();
		//			}
		//		});
		//
		//		paystubListScroll = new JScrollPane(transactionList);
		//		paystubListScroll.setBorder(null);
		//		paystubListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		//		paystubListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		//		paystubListScroll.setPreferredSize(new Dimension(400, 150));
		//		paystubList.setFillsViewportHeight(true);


		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(returnToMenuButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(fundingPageTitle)))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(allExpensesLabel)
								.addComponent(allFundsLabel)
								.addComponent(balanceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(actualExpensesLabel)
								.addComponent(actualFundsLabel)
								.addComponent(actualBalanceLabel)))
				.addComponent(itemTransactions)
				.addComponent(transactionListScroll)
				.addGap(1000)
				.addComponent(viewTransactionInfoButton)
				.addComponent(payAllEmployeesButton)
				.addComponent(createTransactionButton)
				.addComponent(changeFundsLabel)
				.addComponent(changeFundsTextField,20,20,217)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(addFundsButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(removeFundsButton))));
		//.addComponent(employeeListScroll));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(returnToMenuButton)
						.addComponent(fundingPageTitle))
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(allExpensesLabel)
						.addComponent(actualExpensesLabel))
				.addGroup(layout.createParallelGroup()
						.addComponent(allFundsLabel)
						.addComponent(actualFundsLabel))
				.addGroup(layout.createParallelGroup()
						.addComponent(balanceLabel)
						.addComponent(actualBalanceLabel))
				.addGap(15)
				.addComponent(itemTransactions)
				.addComponent(transactionListScroll)
				.addGap(10)
				.addComponent(viewTransactionInfoButton)
				.addGap(10)
				.addComponent(payAllEmployeesButton)
				.addGap(10)
				.addComponent(createTransactionButton)
				.addGap(10)
				.addComponent(changeFundsLabel)
				.addGap(10)
				.addComponent(changeFundsTextField)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(addFundsButton)
						.addComponent(removeFundsButton))
				.addGap(10));
		pack();	
		setLocationRelativeTo(null);
	}

	public void addFundsButtonActionPerformed(){
		URLMSController aController = new URLMSController(thisLab);
		try{
			aController.insertFunds(Double.parseDouble(changeFundsTextField.getText()));
			refreshData();
			update();
		}
		catch (Exception e){
			error = error + e.getMessage();
		}
	}

	private void returnToMenuActionPerformed(){
		new MainPage(thisLab).setVisible(true);
		this.dispose();
	}

	public void removeFundsButtonActionPerformed(){
		URLMSController aController = new URLMSController(thisLab);
		try{
			aController.removeFunds(Double.parseDouble(changeFundsTextField.getText()));
			refreshData();
			update();
		}
		catch (Exception e){
			error = error + e.getMessage();
		}
	}

	public void viewReportButtonActionPerformed(){
		URLMSController aController = new URLMSController (thisLab);
		try{

		}
		catch(Exception e){
			error = error + e.getMessage();
		}
	}

	public void viewTransactionInfoButtonActionPerformed() {
		new TransactionInfo (getSelectedTransaction(), thisLab).setVisible(true);
	}

	public Transaction getSelectedTransaction() {
		return thisLab.getTransaction(thisLab.indexOfTransaction(traList.get(index)));
	}

	private void populateTableData() {
		traList = thisLab.getTransactions();

		tableData = new String[thisLab.getTransactions().size()][3];

		Format format = new SimpleDateFormat ("yyyy-MM-dd");

		for (int i = 0; i<thisLab.getTransactions().size();i++) {
			tableData[i][0] = format.format(thisLab.getTransaction(i).getDate());
			tableData[i][1] = thisLab.getTransaction(i).getDescription();
			tableData[i][2] = Double.toString(thisLab.getTransaction(i).getAmount());
		}
	}

	//	private void populatePaystubData() {
	//		for (int i = 0; i<thisLab.getTransactions().size(); i++) {
	//			if (Paystub.class.isInstance(thisLab.getTransaction(i))) {
	//				Paystub paystub = (Paystub) thisLab.getTransaction(i);
	//				payList.add(paystub);
	//			}
	//		}
	//		paystubTableData = new String [payList.size()][4];
	//		Format format = new SimpleDateFormat ("yyyy-MM-dd");
	//		for (int i = 0; i<payList.size(); i++) {
	//			paystubTableData[i][0] = format.format(payList.get(i).getDate());
	//			paystubTableData[i][1] = payList.get(i).getDescription();
	//			paystubTableData[i][2] = Double.toString(payList.get(i).getAmount());
	//			paystubTableData[i][3] = (getEmployeeFromID(payList.get(i).getEmployeeID())).getName();
	//		}
	//	}

	public Employee getEmployeeFromID (double id) {
		for(int i = 0; i<thisLab.getEmployees().size(); i++) {
			if(thisLab.getEmployee(i).getId() == id) {
				return thisLab.getEmployee(i);
			}
		}
		return null;
	}
	
	public void payAllEmployeesButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		for (int i = 0; i<thisLab.getEmployees().size();i++) {
			double payment = thisLab.getEmployee(i).getSalary();
			Date date = aController.getSqlDate();
			aController.createPaystub(date, payment, thisLab.getEmployee(i));
		}
		updateActionPerformed();
		refreshData();
	}
	
	public void createTransactionButtonActionPerformed() {
		new AddTransaction(thisLab).setVisible(true);
		this.dispose();
	}

	public void updateActionPerformed() {
		new ManageFunding(thisLab).setVisible(true);
		this.dispose();
	}
	
	public double getExpenses() {
		double result = 0;
		//		for (Item item : thisLab.getItems()) {
		//			result = result + (item.getPrice()*item.getQuantity());
		//		}
		for (Transaction transaction : thisLab.getTransactions()) {
			result += transaction.getAmount();
		}
		return result;
	}

	private void update(){
		new ManageFunding(thisLab).setVisible(true);
		this.dispose();
	}

	public void refreshData(){
		populateTableData();
		transactionList.repaint();
		pack();
	}

}
