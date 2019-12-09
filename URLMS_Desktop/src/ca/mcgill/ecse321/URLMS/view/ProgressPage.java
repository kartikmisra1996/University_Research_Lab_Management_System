package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.mcgill.ecse321.URLMS.controller.URLMSController;
import ca.mcgill.ecse321.URLMS.model.*;

public class ProgressPage extends JFrame {

	String error = "";

	private LaboratoryManager thisLab;
	private Employee thisGuy;

	private JLabel progressPageTitle;
	private JLabel employeeInfoLabel;
	private JLabel nameLabel;
	private JLabel jobLabel;
	private JLabel roleLabel;
	private JLabel salaryLabel;
	private JLabel curProgressLabel;
	private JLabel dateLabel;
	private JLabel objectivesLabel;
	private JLabel workDoneLabel;
	private JLabel progressUpdateTableLabel;

	private JLabel actualName;
	private JLabel actualJob;
	private JLabel actualRole;
	private JLabel actualSalary;
	//	private JLabel actualDate;
	//	private JLabel actualObj;
	//	private JLabel actualWorkDone;

	private JButton doneButton;
	private JButton editButton;
	private JButton newPUButton;
	private JButton viewButton;
	private JButton removePUButton;

	//	private JTextField dayTextField;
	//	private JTextField monthTextField;
	//	private JTextField yearTextField;
	//	private JTextField objectivesTextField;
	//	private JTextField workDoneTextField;

	private JTable progressUpdateList;
	private JScrollPane progressUpdateListScroll;
	private String[] tableTitles = {"Date"};
	private String[][] tableData = {{""}};
	private int index = 1;
	private List<ProgressUpdate> prUpList;

	public ProgressPage (Employee employee, LaboratoryManager aLab) {
		thisGuy = employee;
		thisLab = aLab;
		populateTableData();
		initAll();
		refreshData();
	}

	public void initAll() {
		URLMSController aController = new URLMSController(thisLab);
		
		Date latestDate = aController.getSqlDate();
		String latestObj = " ";
		String latestWork = " ";
		if (thisGuy.hasProgressUpdates()) {
			ProgressUpdate latestPU = thisGuy.getProgressUpdate(thisGuy.getProgressUpdates().size()-1);
			latestDate = latestPU.getDate();
			latestObj = latestPU.getObjective();
			latestWork = latestPU.getActualWork();
		}
		
		progressPageTitle = new JLabel();
		progressPageTitle.setText("Employee Progress");
		progressPageTitle.setForeground(Color.DARK_GRAY);
		progressPageTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 25)));

		employeeInfoLabel = new JLabel();
		employeeInfoLabel.setText("Employee Information");
		employeeInfoLabel.setForeground(Color.DARK_GRAY);
		employeeInfoLabel.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 15)));

		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setForeground(Color.DARK_GRAY);

		jobLabel = new JLabel();
		jobLabel.setText("Job:");
		jobLabel.setForeground(Color.DARK_GRAY);

		roleLabel = new JLabel();
		roleLabel.setText("Role:");
		roleLabel.setForeground(Color.DARK_GRAY);

		salaryLabel = new JLabel();
		salaryLabel.setText("Salary:");
		salaryLabel.setForeground(Color.DARK_GRAY);

		curProgressLabel = new JLabel();
		curProgressLabel.setText("Latest Progress Update Information ");
		curProgressLabel.setForeground(Color.DARK_GRAY);
		curProgressLabel.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 15)));

		dateLabel = new JLabel();
		dateLabel.setText("Date:               " + latestDate);
		dateLabel.setForeground(Color.DARK_GRAY);

		objectivesLabel = new JLabel();
		objectivesLabel.setText("Objectives:    " + latestObj);
		objectivesLabel.setForeground(Color.DARK_GRAY);

		workDoneLabel = new JLabel();
		workDoneLabel.setText("Work Done:   " + latestWork);
		workDoneLabel.setForeground(Color.DARK_GRAY);

		progressUpdateTableLabel = new JLabel();
		progressUpdateTableLabel.setText("All Progress Updates For " + thisGuy.getName());
		progressUpdateTableLabel.setForeground(Color.DARK_GRAY);

		doneButton = new JButton();
		doneButton.setText("Return");
		doneButton.setBackground(Color.LIGHT_GRAY);
		doneButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToStaffPageActionPerformed();
			}
		});

		removePUButton = new JButton();
		removePUButton.setText("Remove");
		removePUButton.setBackground(Color.LIGHT_GRAY);
		removePUButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removePUButtonActionPerformed();
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

		newPUButton = new JButton();
		newPUButton.setText("Add Progress Update");
		newPUButton.setBackground(Color.LIGHT_GRAY);
		newPUButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newPUButtonActionPerformed();
			}
		});

		viewButton = new JButton();
		viewButton.setText("View Info");
		viewButton.setBackground(Color.LIGHT_GRAY);
		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewButtonAtionPerformed();
			}
		});

		actualName = new JLabel();
		actualName.setText(thisGuy.getName());

		actualRole = new JLabel();
		actualRole.setText(thisGuy.getRole());

		actualJob = new JLabel();
		actualJob.setText(thisGuy.getJob());

		actualSalary = new JLabel();
		actualSalary.setText(Double.toString(thisGuy.getSalary()));

		progressUpdateList = new JTable(tableData, tableTitles) {
			private static final long serialVersionUID = 2L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		progressUpdateList.setGridColor(Color.DARK_GRAY);
		progressUpdateList.setForeground(Color.WHITE);
		progressUpdateList.setBackground(Color.DARK_GRAY);
		progressUpdateList.setSelectionBackground(Color.WHITE);
		progressUpdateList.setSelectionForeground(Color.BLACK);
		progressUpdateList.getTableHeader().setBackground(Color.DARK_GRAY);
		progressUpdateList.getTableHeader().setForeground(Color.WHITE);
		progressUpdateList.getTableHeader().setBorder(null);
		progressUpdateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		progressUpdateList.setColumnSelectionAllowed(false);
		progressUpdateList.setCellSelectionEnabled(false);
		progressUpdateList.setRowSelectionAllowed(true);
		progressUpdateList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				index = progressUpdateList.getSelectedRow();
				refreshData();
			}
		});

		progressUpdateListScroll = new JScrollPane(progressUpdateList);
		progressUpdateListScroll.setBorder(null);
		progressUpdateListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		progressUpdateListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		progressUpdateListScroll.setPreferredSize(new Dimension(400, 150));
		progressUpdateList.setFillsViewportHeight(true);

		//Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(doneButton)
				.addGap(10)
				.addComponent(progressPageTitle)
				.addGap(10)
				.addComponent(employeeInfoLabel)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(jobLabel)
								.addComponent(roleLabel)
								.addComponent(salaryLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(actualName)
								.addComponent(actualJob)
								.addComponent(actualRole)
								.addComponent(actualSalary)))
				.addComponent(curProgressLabel)
				.addComponent(dateLabel)
				.addComponent(objectivesLabel)
				.addComponent(workDoneLabel)
				.addComponent(progressUpdateTableLabel)
				.addComponent(progressUpdateListScroll)
				.addGap(700)
				.addComponent(newPUButton)
				.addComponent(editButton)
				.addComponent(removePUButton)
				.addComponent(viewButton));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(doneButton)
				.addComponent(progressPageTitle)
				.addComponent(employeeInfoLabel)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(actualName))
				.addGroup(layout.createParallelGroup()
						.addComponent(jobLabel)
						.addComponent(actualJob))
				.addGroup(layout.createParallelGroup()
						.addComponent(roleLabel)
						.addComponent(actualRole))
				.addGroup(layout.createParallelGroup()
						.addComponent(salaryLabel)
						.addComponent(actualSalary))
				.addGap(20)
				.addComponent(curProgressLabel)
				.addComponent(dateLabel)
				.addComponent(objectivesLabel)
				.addComponent(workDoneLabel)
				.addGap(20)
				.addComponent(progressUpdateTableLabel)
				.addComponent(progressUpdateListScroll)
				.addComponent(newPUButton)
				.addComponent(editButton)
				.addComponent(removePUButton)
				.addComponent(viewButton)
				.addGap(10));
		pack();	
		setLocationRelativeTo(null);
	}

	public void returnToStaffPageActionPerformed() {
		new ManageStaff(thisLab).setVisible(true);
		this.dispose();
	}

	public void editButtonActionPerformed() {
		ProgressUpdate thisPU = getSelectedProgressUpdate();
		new EditProgressUpdate(thisPU, thisLab).setVisible(true);
	}

	public void newPUButtonActionPerformed() {
		new AddProgressUpdate(thisGuy, thisLab).setVisible(true);
		this.dispose();
	}

	private ProgressUpdate getSelectedProgressUpdate() {
		return thisGuy.getProgressUpdate(thisGuy.indexOfProgressUpdate(prUpList.get(index)));
	}

	private void populateTableData() {
		if(thisGuy.hasProgressUpdates()) {
			prUpList = thisGuy.getProgressUpdates();

			tableData = new String[thisGuy.getProgressUpdates().size()][1];

			Format format = new SimpleDateFormat ("yyyy-MM-dd");

			for (int i = 0; i<thisGuy.getProgressUpdates().size(); i++) {
				tableData[i][0] = format.format(thisGuy.getProgressUpdate(i).getDate()) + ", Progress Update Number "+(i+1);
			}
		}
	}

	public void viewButtonAtionPerformed() {
		ProgressUpdate selectedPU = getSelectedProgressUpdate();
		new ViewProgressUpdate(selectedPU, thisLab).setVisible(true);
	}

	public void removePUButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		aController.takeOutProgressUpdate(thisGuy,getSelectedProgressUpdate());
		refreshData();
		updateActionPerformed();
	}

	public void updateActionPerformed() {
		new ProgressPage(thisGuy, thisLab).setVisible(true);
		this.dispose();
	}

	public void refreshData() {
		populateTableData();
		progressUpdateList.repaint();
		pack();
	}

}
