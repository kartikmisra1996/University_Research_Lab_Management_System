package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.swing.*;

import ca.mcgill.ecse321.URLMS.application.InvalidInputException;
import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.Employee;
import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;

public class AddTransaction extends JFrame {
	private LaboratoryManager thisLab;

	private JLabel descriptionLabel;
	private JLabel amountLabel;

	private JTextField descTextField;
	private JTextField amountTextField;

	private JButton saveButton;
	private JButton returnButton;

	public AddTransaction (LaboratoryManager aLab) {
		thisLab = aLab;
		initAll();
	}

	public void initAll() {
		descriptionLabel = new JLabel();
		descriptionLabel.setText("Description: ");
		descriptionLabel.setForeground(Color.DARK_GRAY);

		descTextField = new JTextField();

		amountLabel = new JLabel();
		amountLabel.setText("Amount: ");
		amountLabel.setForeground(Color.DARK_GRAY);

		amountTextField = new JTextField();

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
								.addComponent(descriptionLabel)
								.addComponent(amountLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(descTextField,20,20,250)
								.addComponent(amountTextField,20,20,250)))
				.addComponent(saveButton)
				.addGap(300));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(returnButton)
				.addGroup(layout.createParallelGroup()
						.addComponent(descriptionLabel)
						.addComponent(descTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(amountLabel)
						.addComponent(amountTextField))
				.addComponent(saveButton)
				.addGap(100));
		pack();	
		setLocationRelativeTo(null);

	}

	public void saveButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		String date = " ";
		String description = descTextField.getText();
		double amount = Double.parseDouble(amountTextField.getText());
		try {
			aController.createTransaction(date, description, amount);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		this.dispose();
		new ManageFunding(thisLab).setVisible(true);
	}

	public void returnButtonActionPerformed() {
		this.dispose();
		new ManageFunding(thisLab).setVisible(true);
	}

}
