package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.*;
import ca.mcgill.ecse321.URLMS.model.*;

public class TransactionInfo extends JFrame {
	
	private LaboratoryManager thisLab;
	private Transaction thisTransaction;
	
	private JLabel dateLabel;
	private JLabel infoLabel;
	private JLabel amountLabel;
	
	private JButton closeButton;

	public TransactionInfo (Transaction aTransaction, LaboratoryManager aLab) {
		thisLab = aLab;
		thisTransaction = aTransaction;
		initAll();
	}
	
	public void initAll() {
		Format format = new SimpleDateFormat ("yyyy-MM-dd");
		dateLabel = new JLabel();
		dateLabel.setText("Date: " + format.format(thisTransaction.getDate()));
		dateLabel.setForeground(Color.DARK_GRAY);
		
		infoLabel = new JLabel();
		infoLabel.setText("Descripton: " + thisTransaction.getDescription());
		infoLabel.setForeground(Color.DARK_GRAY);
		
		amountLabel = new JLabel();
		amountLabel.setText("Amount: " + thisTransaction.getAmount());
		amountLabel.setForeground(Color.DARK_GRAY);
		
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
				.addComponent(infoLabel)
				.addComponent(amountLabel)
				.addComponent(closeButton)
				.addGap(100));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(dateLabel)
				.addComponent(infoLabel)
				.addComponent(amountLabel)
				.addGap(100)
				.addComponent(closeButton));
		pack();	
		setLocationRelativeTo(null);
	}
	public void closeButtonActionPerformed() {
		this.dispose();
	}
}
