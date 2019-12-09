package ca.mcgill.ecse321.URLMS.view;

import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.controller.*;
import ca.mcgill.*;

import javax.swing.*;
import java.awt.*;

public class ManageStaff extends JFrame {

	private LaboratoryManager thisLab;

	private JLabel manageStaffTitle;
	private JLabel addStaffLabel;
	private JLabel nameLabel;
	private JLabel jobLabel;
	private JLabel roleLabel;
	private JLabel salaryLabel;

	private JTextField nameTextField;
	private JTextField jobTextField;
	private JTextField roleTextField;
	private JTextField salaryTextField;

	private JButton addStaffButton;
	private JButton returnToMenuButton;
	private JButton updateButton;
	
	private String error = "";

	public ManageStaff(LaboratoryManager aLab){
		thisLab = aLab;
		initAll();
		refreshData();
	}

	private void initAll(){
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
		
		manageStaffTitle = new JLabel();
		manageStaffTitle.setText("Staff Management");
		manageStaffTitle.setForeground(Color.BLUE);

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

		addStaffButton = new JButton();
		addStaffButton.setText("Add");
		addStaffButton.setBackground(Color.LIGHT_GRAY);
		addStaffButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addStaffButtonActionPerformed();
			}
		});

		//laying it all out
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
								.addComponent(salaryLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(nameTextField, 20, 20, 250)
								.addComponent(jobTextField, 20, 20, 250)
								.addComponent(roleTextField, 20, 20, 250)
								.addComponent(salaryTextField, 20, 20, 250)))
								.addGap(1000)
				.addComponent(addStaffButton)
				.addComponent(updateButton));


		//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameLabel, EmailLabel, IDLabel, PasswordLabel });
		//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameTextField, EmailTextField, IDTextField, PasswordTextField });
		//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { RegisterButton });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(returnToMenuButton)
				.addComponent(manageStaffTitle).addGap(10)
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
				.addGap(10)
				.addComponent(addStaffButton)
				.addGap(10)
				.addComponent(updateButton)
				.addGap(100));

		pack();	
		setLocationRelativeTo(null);

	}
	
	//ManageStaffTable
	String[] columnNames = {"ID", "Name", "Job", "Role", "Salary"};
	
	Object[][] data = {};
	

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
		
		try{
		aController.createEmployee(name, job, role, salary);
		}
		catch(Exception e){
			error = error + e.getMessage();
		}
		
	}
	
	private void updateActionPerformed(){
		
	}

	private void refreshData(){
		
	}
}
