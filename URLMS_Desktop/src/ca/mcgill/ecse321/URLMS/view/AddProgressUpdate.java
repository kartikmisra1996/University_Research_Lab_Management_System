package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.swing.*;

import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.Employee;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;

public class AddProgressUpdate extends JFrame {
	
	private Employee thisGuy;
	private LaboratoryManager thisLab;
	
	private JLabel objectiveLabel;
	private JLabel workDoneLabel;
	
	private JTextField objTextField;
	private JTextField workTextField;
	
	private JButton saveButton;
	private JButton returnButton;

	public AddProgressUpdate (Employee employee, LaboratoryManager aLab) {
		thisGuy = employee;
		thisLab = aLab;
		initAll();
	}
	
	public void initAll() {
		objectiveLabel = new JLabel();
		objectiveLabel.setText("Set Objectives: ");
		objectiveLabel.setForeground(Color.DARK_GRAY);
		
		objTextField = new JTextField();
		
		workDoneLabel = new JLabel();
		workDoneLabel.setText("Set Work Done ");
		workDoneLabel.setForeground(Color.DARK_GRAY);
		
		workTextField = new JTextField();
		
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
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(returnButton)
				.addGap(10)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(objectiveLabel)
								.addComponent(workDoneLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(objTextField,20,20,250)
								.addComponent(workTextField,20,20,250)))
				.addComponent(saveButton)
				.addGap(300));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(returnButton)
				.addGroup(layout.createParallelGroup()
						.addComponent(objectiveLabel)
						.addComponent(objTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(workDoneLabel)
						.addComponent(workTextField))
				.addComponent(saveButton)
				.addGap(100));
		pack();	
		setLocationRelativeTo(null);
		
	}
	
	public void saveButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		Date date = aController.getSqlDate();
		String obj = objTextField.getText();
		String work = workTextField.getText();
		try {
			aController.createProgressUpdate(obj, work, date, thisGuy);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
		new ProgressPage(thisGuy, thisLab).setVisible(true);
	}
	
	public void returnButtonActionPerformed() {
		this.dispose();
		new ProgressPage(thisGuy, thisLab).setVisible(true);
	}
	
}
