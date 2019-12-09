package ca.mcgill.ecse321.URLMS.view;

import ca.mcgill.ecse321.URLMS.model.*;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame{

	private LaboratoryManager thisLab;

	private JLabel mainTitle;
	private JLabel secondTitle;

	private JButton manageStaff;
	private JButton manageEquipment;
	private JButton manageFunding;
	
	public MainPage (LaboratoryManager aLab){
		thisLab = aLab;
		initAll();
	}
	
	public void initAll(){

		//title when first launching the application
		mainTitle = new JLabel ("University Reseach Lab Management System");
		mainTitle.setForeground(Color.darkGray);
		secondTitle = new JLabel ("Please select one of the 3 options below");
		secondTitle.setForeground(Color.darkGray);

		//staff management button
		manageStaff = new JButton();
		manageStaff.setForeground(Color.BLACK);
		manageStaff.setBackground(Color.LIGHT_GRAY);
		manageStaff.setText("Manage Staff");
		manageStaff.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				manageStaffButtonActionPerformed();
			}
		});

		//equipment management button
		manageEquipment = new JButton();
		manageEquipment.setForeground(Color.BLACK);
		manageEquipment.setBackground(Color.LIGHT_GRAY);
		manageEquipment.setText("Manage Equipment");
		manageEquipment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event){
				manageEquipmentButtonActionPerformed();
			}
		});

		//funding management button
		manageFunding = new JButton();
		manageFunding.setForeground(Color.BLACK);
		manageEquipment.setBackground(Color.LIGHT_GRAY);
		manageEquipment.setText("Manage Funding");
		manageEquipment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event){
				manageFundingButtonActionPerformed();
			}
		});

		//organizing it all
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup().addGroup(layout.createSequentialGroup()
						.addGap(30)
						.addComponent(mainTitle))
				.addGroup(layout.createSequentialGroup()
						.addGap(45)
						.addComponent(secondTitle))
				.addGroup(layout.createSequentialGroup()
						.addGap(10)
						.addComponent(manageStaff)
						.addGap(10)
						.addComponent(manageEquipment)
						.addGap(10)
						.addComponent(manageFunding)
						.addGap(10)));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { manageStaff });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { manageEquipment });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { manageFunding });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(30)
				.addComponent(mainTitle)
				.addComponent(secondTitle)
				.addGap(30)
				.addGroup(layout.createParallelGroup()
						.addComponent(manageStaff)
				.addGap(40)
				.addGroup(layout.createParallelGroup()
						.addComponent(manageEquipment)
				.addGap(40)
				.addGroup(layout.createParallelGroup()
						.addComponent(manageFunding)))));

		pack();
		setLocationRelativeTo(null);

	}

	private void manageStaffButtonActionPerformed() {
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}

	private void manageEquipmentButtonActionPerformed() {

	}

	private void manageFundingButtonActionPerformed() {

	}
}
