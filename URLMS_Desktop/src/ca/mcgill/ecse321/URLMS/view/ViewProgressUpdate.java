package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;

import javax.swing.*;

import ca.mcgill.ecse321.URLMS.model.*;

public class ViewProgressUpdate extends JFrame {
	
	private LaboratoryManager thisLab;
	private ProgressUpdate thisPU;
	
	private JLabel dateLabel;
	private JLabel objLabel;
	private JLabel workLabel;
	
	private JButton closeButton;

	public ViewProgressUpdate(ProgressUpdate pu, LaboratoryManager aLab) {
		thisLab = aLab;
		thisPU = pu;
		initAll();
	}
	
	public void initAll() {
		dateLabel = new JLabel();
		dateLabel.setText("Date: " + thisPU.getDate());
		dateLabel.setForeground(Color.DARK_GRAY);
		
		objLabel = new JLabel();
		objLabel.setText("Objectives: "+thisPU.getObjective());
		dateLabel.setForeground(Color.DARK_GRAY);
		
		workLabel = new JLabel();
		workLabel.setText("Work Done: "+thisPU.getActualWork());
		workLabel.setForeground(Color.DARK_GRAY);
		
		closeButton = new JButton();
		closeButton.setText("Close");
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed();
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(dateLabel)
				.addComponent(objLabel)
				.addComponent(workLabel)
				.addComponent(closeButton)
				.addGap(100));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(dateLabel)
				.addComponent(objLabel)
				.addComponent(workLabel)
				.addComponent(closeButton)
				.addGap(100));
		pack();	
		setLocationRelativeTo(null);
		
	}
	
	public void closeButtonActionPerformed() {
		this.dispose();
	}
	
}
