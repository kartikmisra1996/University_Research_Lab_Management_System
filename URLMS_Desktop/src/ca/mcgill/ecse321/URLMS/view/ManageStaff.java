package ca.mcgill.ecse321.URLMS.view;

import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.controller.*;
import ca.mcgill.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ManageStaff extends JFrame {

	int numberOfPU = 0;

	//UI elements
	private LaboratoryManager thisLab;
	private Employee thisEmployee;

	private JLabel manageStaffTitle;
	private JLabel addStaffLabel;
	private JLabel nameLabel;
	private JLabel jobLabel;
	private JLabel roleLabel;
	private JLabel salaryLabel;
	private JLabel idLabel;
	private JLabel curEmpLabel;

	private JTextField nameTextField;
	private JTextField jobTextField;
	private JTextField roleTextField;
	private JTextField salaryTextField;
	private JTextField idTextField;


	private JButton addStaffButton;
	private JButton returnToMenuButton;
	private JButton updateButton;
	private JButton switchToProgressButton;
	private JButton editButton;
	private JButton saveStaffChangesButton;
	private JButton removeEmployeeButton;

	//Table elements
	private JTable employeeList;
	private JScrollPane employeeListScroll;
	private String[] tableTitles = {"Name", "Job", "Role", "Salary", "ID"};
	private String[][] tableData = {{""},{""},{""},{""},{""}};
	private Employee selectedEmployee;
	private int index = 1;
	private List<Employee> empList;

	//Table elements
	//	private JTable staffTable;
	//	private JComboBox roleComboBox;
	//	private String[] roleOptions = {"Research Assosicate", "Research Assistant"};

	private String error = "";

	public ManageStaff(LaboratoryManager aLab){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thisLab = aLab;
		populateTableData();
		initAll();
		refreshData();
	}

	private void initAll(){
		curEmpLabel = new JLabel();
		curEmpLabel.setText("All Current Employees");
		curEmpLabel.setForeground(Color.DARK_GRAY);
		
		removeEmployeeButton = new JButton();
		removeEmployeeButton.setText("Remove");
		removeEmployeeButton.setBackground(Color.LIGHT_GRAY);
		removeEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeEmployeeActionPerformed();
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

		updateButton = new JButton();
		updateButton.setText("Update");
		updateButton.setBackground(Color.LIGHT_GRAY);
		updateButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				updateActionPerformed();
			}
		});

		switchToProgressButton = new JButton();
		switchToProgressButton.setText("View Employee Progress");
		switchToProgressButton.setBackground(Color.LIGHT_GRAY);
		switchToProgressButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				switchToProgressButtonActionPerformed();
			}
		});

		editButton = new JButton();
		editButton.setText("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editButtonActionPerformed();
			}
		});

		manageStaffTitle = new JLabel();
		manageStaffTitle.setText("Staff Management");
		manageStaffTitle.setForeground(Color.DARK_GRAY);
		manageStaffTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 25)));

		addStaffLabel = new JLabel();
		addStaffLabel.setText("Add Staff");
		addStaffLabel.setForeground(Color.DARK_GRAY);
		addStaffLabel.setBackground(Color.LIGHT_GRAY);

		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setForeground(Color.DARK_GRAY);
		nameTextField = new JTextField();

		jobLabel = new JLabel();
		jobLabel.setText("Job:");
		jobLabel.setForeground(Color.DARK_GRAY);
		jobTextField = new JTextField();

		roleLabel = new JLabel();
		roleLabel.setText("Role:");
		roleLabel.setForeground(Color.DARK_GRAY);
		roleTextField = new JTextField();

		salaryLabel = new JLabel();
		salaryLabel.setText("Salary:");
		salaryLabel.setForeground(Color.DARK_GRAY);
		salaryTextField = new JTextField();
		
		idLabel = new JLabel();
		idLabel.setText("ID");
		idLabel.setForeground(Color.DARK_GRAY);
		idTextField = new JTextField();

		addStaffButton = new JButton();
		addStaffButton.setText("Add");
		addStaffButton.setBackground(Color.LIGHT_GRAY);
		addStaffButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addStaffButtonActionPerformed();
			}
		});

		employeeList = new JTable(tableData, tableTitles) {
			private static final long serialVersionUID = 2L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		employeeList.setGridColor(Color.DARK_GRAY);
		employeeList.setForeground(Color.WHITE);
		employeeList.setBackground(Color.DARK_GRAY);
		employeeList.setSelectionBackground(Color.WHITE);
		employeeList.setSelectionForeground(Color.BLACK);
		employeeList.getTableHeader().setBackground(Color.DARK_GRAY);
		employeeList.getTableHeader().setForeground(Color.WHITE);
		employeeList.getTableHeader().setBorder(null);
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeList.setColumnSelectionAllowed(false);
		employeeList.setCellSelectionEnabled(false);
		employeeList.setRowSelectionAllowed(true);
		employeeList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				index = employeeList.getSelectedRow();
				//System.out.println(focus);
				refreshData();
			}
		});

		employeeListScroll = new JScrollPane(employeeList);
		employeeListScroll.setBorder(null);
		employeeListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		employeeListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		employeeListScroll.setPreferredSize(new Dimension(400, 150));
		employeeList.setFillsViewportHeight(true);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(returnToMenuButton)
				.addGap(10)
				.addComponent(manageStaffTitle)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(jobLabel)
								.addComponent(roleLabel)
								.addComponent(salaryLabel)
								.addComponent(idLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(nameTextField, 20, 20, 250)
								.addComponent(jobTextField, 20, 20, 250)
								.addComponent(roleTextField, 20, 20, 250)
								.addComponent(salaryTextField, 20, 20, 250)
								.addComponent(idTextField,20,20,250))
						.addGroup(layout.createParallelGroup()
								.addGap(10)))
				.addComponent(curEmpLabel)
				.addComponent(employeeListScroll)
				.addGap(1000)
				.addComponent(addStaffButton)
				.addComponent(updateButton)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(removeEmployeeButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(editButton)))
				.addComponent(switchToProgressButton));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(returnToMenuButton)
				.addComponent(manageStaffTitle)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(nameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(jobLabel)
						.addComponent(jobTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(roleLabel)
						.addComponent(roleTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(salaryLabel)
						.addComponent(salaryTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(idLabel)
						.addComponent(idTextField))
				.addGap(10)
				.addComponent(addStaffButton)
				.addGap(10)
				.addComponent(updateButton)
				.addGap(25)
				.addComponent(curEmpLabel)
				.addComponent(employeeListScroll)
				.addGroup(layout.createParallelGroup()
						.addComponent(removeEmployeeButton)
						.addComponent(editButton))

				.addGap(10)
				.addComponent(switchToProgressButton));
		pack();	
		setLocationRelativeTo(null);

	}

	private void returnToMenuActionPerformed(){
		new MainPage(thisLab).setVisible(true);
		this.dispose();
	}

	private void addStaffButtonActionPerformed(){

		URLMSController aController = new URLMSController(thisLab);

		double id = 123456789;
		String name = nameTextField.getText();
		String job = jobTextField.getText();
		String role = roleTextField.getText();
		double salary = Double.parseDouble(salaryTextField.getText());
		double ID = Double.parseDouble(idTextField.getText());

		nameTextField.setText("");
		jobTextField.setText("");
		roleTextField.setText("");
		salaryTextField.setText("");

		try{
			aController.createEmployee(name, job, role, salary, ID);
			populateTableData();
			updateActionPerformed();
		}
		catch(Exception e){
			error = error + e.getMessage();
		}
	}

	public void switchToProgressButtonActionPerformed() {
		new ProgressPage(getSelectedEmployee(), thisLab).setVisible(true);
		this.dispose();
	}

	public void editButtonActionPerformed() {
		new EditEmployee(thisLab, getSelectedEmployee()).setVisible(true);
		this.dispose();
	}

//	public void saveStaffChangesButtonActionPerformed(Employee employee) {
//		int x = thisLab.getEmployees().size();
//		for (int i = 0; i<thisLab.getEmployees().size(); i++){
//			if (thisLab.getEmployee(i) == employee) {
//				x = i;
//			}
//		}
//		URLMSController aController = new URLMSController (thisLab);
//		aController.takeOutEmployee(employee);
//		String name = editNameTextField.getText();
//		String job = editJobTextField.getText();
//		String role = editRoleTextField.getText();
//		double salary = Double.parseDouble(editSalaryTextField.getText());
//		aController.createEmployeeAt(name, job, role, salary, x);
//	}

	private void updateActionPerformed(){
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}

	private void removeEmployeeActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		aController.takeOutEmployee(getSelectedEmployee());
		refreshData();
		updateActionPerformed();
	}

	private Employee getSelectedEmployee() {
		return thisLab.getEmployee(thisLab.indexOfEmployee(empList.get(index)));
	}

	private void populateTableData() {
		//		for (int i = 0; i<thisLab.getEmployees().size(); i++) {
		//			if(thisLab.getEmployee(i) != null) {
		//				empList.add(thisLab.getEmployee(i));
		//			}
		//		}
		empList = thisLab.getEmployees();
		tableData = new String[thisLab.getEmployees().size()][5];
		for (int i = 0; i<thisLab.getEmployees().size();i++) {
			tableData[i][0] = thisLab.getEmployee(i).getName();
			tableData[i][1] = thisLab.getEmployee(i).getJob();
			tableData[i][2] = thisLab.getEmployee(i).getRole();
			tableData[i][3] = Double.toString(empList.get(i).getSalary());
			tableData[i][4] = Double.toString(empList.get(i).getId());
		}
	}

	private void refreshData(){
		populateTableData();
		employeeList.repaint();
		pack();
	}
}
