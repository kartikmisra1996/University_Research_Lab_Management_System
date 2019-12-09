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
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//title when first launching the application
		mainTitle = new JLabel ("University Reseach Lab Management System");
		mainTitle.setForeground(Color.darkGray);
		mainTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 25)));

		secondTitle = new JLabel ("Please select one of the 3 options below");
		secondTitle.setForeground(Color.darkGray);
		secondTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 15)));

		//staff management button
		manageStaff = new JButton();
		manageStaff.setText("Staff");
		manageStaff.setBackground(Color.LIGHT_GRAY);
		manageStaff.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				manageStaffButtonActionPerformed();
			}
		});

		//equipment management button
		manageEquipment = new JButton();
		manageEquipment.setText("Items");
		manageEquipment.setBackground(Color.LIGHT_GRAY);
		manageEquipment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event){
				manageEquipmentButtonActionPerformed();
			}
		});

		//funding management button
		manageFunding = new JButton();
		manageFunding.setText("Funding");
		manageFunding.setBackground(Color.LIGHT_GRAY);
		manageFunding.addActionListener(new java.awt.event.ActionListener() {
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
						.addGap(20)
						.addComponent(mainTitle))
				.addGroup(layout.createSequentialGroup()
						.addGap(120)
						.addComponent(secondTitle))
				.addGroup(layout.createSequentialGroup()
						.addGap(110)
						.addGroup(layout.createParallelGroup()
								.addComponent(manageStaff))
						.addGap(70)
						.addGroup(layout.createParallelGroup()
								.addComponent(manageEquipment))
						.addGap(70)
						.addGroup(layout.createParallelGroup()
								.addComponent(manageFunding))
						.addGap(20)));

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
						.addComponent(manageEquipment)
						.addComponent(manageFunding)));		

		pack();
		setLocationRelativeTo(null);
	}

	private void manageStaffButtonActionPerformed() {
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}

	private void manageEquipmentButtonActionPerformed() {
		new ManageEquipment(thisLab).setVisible(true);
		this.dispose();
	}

	private void manageFundingButtonActionPerformed() {
		new ManageFunding(thisLab).setVisible(true);
		this.dispose();
	}
}
