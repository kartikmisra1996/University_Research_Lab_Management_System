package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;

import javax.swing.*;

import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.Employee;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;

public class EditEmployee extends JFrame {

	//List of all UI and system elements used for this page
	private LaboratoryManager thisLab;
	private Employee thisGuy;

	private JButton returnButton;
	private JButton saveButton;

	private JLabel editEmployeeTitle;

	private JTextField editNameTextField;
	private JTextField editJobTextField;
	private JTextField editRoleTextField;
	private JTextField editSalaryTextField;

	private JLabel editNameLabel;
	private JLabel editJobLabel;
	private JLabel editRoleLabel;
	private JLabel editSalaryLabel;

	public EditEmployee(LaboratoryManager aLab, Employee employee) {
		thisLab = aLab;
		thisGuy = employee;
		initAll();
	}

	public void initAll() {
		//Initialize all UI elements
		editEmployeeTitle = new JLabel();
		editEmployeeTitle.setText("Modify Employee Information");
		editEmployeeTitle.setForeground(Color.DARK_GRAY);

		returnButton = new JButton();
		returnButton.setText("Return");
		returnButton.setBackground(Color.LIGHT_GRAY);
		returnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed();
			}
		});

		saveButton = new JButton();
		saveButton.setText("Save");
		saveButton.setBackground(Color.LIGHT_GRAY);
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed();
			}
		});

		editNameLabel = new JLabel();
		editNameLabel.setText("New Name: ");
		editNameLabel.setForeground(Color.DARK_GRAY);

		editRoleLabel = new JLabel();
		editRoleLabel.setText("New Role: ");
		editRoleLabel.setForeground(Color.DARK_GRAY);

		editJobLabel = new JLabel();
		editJobLabel.setText("New Job: ");
		editJobLabel.setForeground(Color.DARK_GRAY);

		editSalaryLabel = new JLabel();
		editSalaryLabel.setText("New Salary: ");
		editSalaryLabel.setForeground(Color.DARK_GRAY);

		editNameTextField = new JTextField();
		editNameTextField.setText(thisGuy.getName());

		editRoleTextField = new JTextField();
		editRoleTextField.setText(thisGuy.getRole());

		editJobTextField = new JTextField();
		editJobTextField.setText(thisGuy.getJob());

		editSalaryTextField = new JTextField();
		editSalaryTextField.setText(Double.toString(thisGuy.getSalary()));

		//UI Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(returnButton)
				.addGap(10)
				.addComponent(editEmployeeTitle)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(editNameLabel)
								.addComponent(editJobLabel)
								.addComponent(editRoleLabel)
								.addComponent(editSalaryLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(editNameTextField, 20, 20, 250)
								.addComponent(editJobTextField, 20, 20, 250)
								.addComponent(editRoleTextField, 20, 20, 250)
								.addComponent(editSalaryTextField, 20, 20, 250)))
				.addGap(300)
				.addComponent(saveButton));
		
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(returnButton)
				.addComponent(editEmployeeTitle)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(editNameLabel)
						.addComponent(editNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(editJobLabel)
						.addComponent(editJobTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(editRoleLabel)
						.addComponent(editRoleTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(editSalaryLabel)
						.addComponent(editSalaryTextField))
				.addGap(10)
				.addComponent(saveButton)
				.addGap(100));
		pack();	
		setLocationRelativeTo(null);

	}
	
	//Below are all the button action performed and update methods
	public void saveButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		String nName = editNameTextField.getText();
		String nJob = editJobTextField.getText();
		String nRole = editRoleTextField.getText();
		double nSalary = Double.parseDouble(editSalaryTextField.getText());
		aController.editEmployee(thisGuy, nName, nJob, nRole, nSalary);
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}

	public void returnButtonActionPerformed() {
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}
}
