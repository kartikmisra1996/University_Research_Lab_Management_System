package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;

import javax.swing.*;

import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.*;

public class EditItem extends JFrame {
	
	private LaboratoryManager thisLab;
	private Item thisItem;

	private JButton returnButton;
	private JButton saveButton;

	private JLabel editItemTitle;

	private JTextField editNameTextField;
	private JTextField editQuantityTextField;
	private JTextField editPriceTextField;

	private JLabel editNameLabel;
	private JLabel editPriceLabel;
	private JLabel editQuantityLabel;

	public EditItem(LaboratoryManager aLab, Item item) {
		thisLab = aLab;
		thisItem = item;
		initAll();
	}

	public void initAll() {
		editItemTitle = new JLabel();
		editItemTitle.setText("Modify Item Information");
		editItemTitle.setForeground(Color.DARK_GRAY);

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

		editQuantityLabel = new JLabel();
		editQuantityLabel.setText("New Quantity: ");
		editQuantityLabel.setForeground(Color.DARK_GRAY);

		editPriceLabel = new JLabel();
		editPriceLabel.setText("New Price: ");
		editPriceLabel.setForeground(Color.DARK_GRAY);

		editNameTextField = new JTextField();
		editNameTextField.setText(thisItem.getName());

		editQuantityTextField = new JTextField();
		editQuantityTextField.setText(Integer.toString(thisItem.getQuantity()));

		editPriceTextField = new JTextField();
		editPriceTextField.setText(Double.toString(thisItem.getPrice()));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(returnButton)
				.addGap(10)
				.addComponent(editItemTitle)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(editNameLabel)
								.addComponent(editQuantityLabel)
								.addComponent(editPriceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(editNameTextField, 20, 20, 250)
								.addComponent(editQuantityTextField, 20, 20, 250)
								.addComponent(editPriceTextField, 20, 20, 250)))
				.addGap(300)
				.addComponent(saveButton));
		//.addComponent(employeeListScroll));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(10)
				.addComponent(returnButton)
				.addComponent(editItemTitle)
				.addGap(10)
				.addGroup(layout.createParallelGroup()
						.addComponent(editNameLabel)
						.addComponent(editNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(editQuantityLabel)
						.addComponent(editQuantityTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(editPriceLabel)
						.addComponent(editPriceTextField))
				.addGap(10)
				.addComponent(saveButton)
				.addGap(100));
		pack();	
		setLocationRelativeTo(null);

	}
	
	public void saveButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		String nName = editNameTextField.getText();
		double nPrice = Double.parseDouble(editPriceTextField.getText());
		int nQuantity = Integer.parseInt(editQuantityTextField.getText());
		aController.editItem(thisItem, nName, nPrice, nQuantity);
		this.dispose();
		new ManageEquipment(thisLab).setVisible(true);
	}

	public void returnButtonActionPerformed() {
		new ManageEquipment(thisLab).setVisible(true);
		this.dispose();
	}
	
}
