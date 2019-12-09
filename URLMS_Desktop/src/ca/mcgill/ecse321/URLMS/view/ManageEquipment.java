package ca.mcgill.ecse321.URLMS.view;

import ca.mcgill.ecse321.URLMS.application.*;
import ca.mcgill.ecse321.URLMS.controller.*;
import ca.mcgill.ecse321.URLMS.model.*;
import ca.mcgill.ecse321.URLMS.view.*;
import ca.mcgill.ecse321.URLMS.persistence.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageEquipment extends JFrame {

	String error = "";

	private LaboratoryManager thisLab;

	private JLabel manageEquipmentTitle;
	private JLabel addEquipmentTitle;
	
	private JButton addSupplyButton;
	private JButton addEquipmentButton;
	private JButton removeEquipmentButton;
	private JButton editEquipmentButton;
	private JButton returnMainMenu;
	private JButton removeSupplyButton;
	private JButton editSupplyButton;

	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel quantityLabel;
	private JLabel priceLabel;
	private JLabel supplyTableLabel;
	private JLabel equipmentTableLabel;

	private JTextField nameTextField;
	private JTextField quantityTextField;
	private JTextField priceTextField;
	
	private JTable equipmentList;
	private JScrollPane equipmentListScroll;
	private String[] equipmentTableTitles = {"Name", "Quantity", "Price per Unit", "Total Cost"};
	private String[][] equipmentTableData = {{""},{""},{""},{""}};
	private int index = 1;
	private LinkedList<Item> eqList = new LinkedList<Item>();
	
	private JTable supplyList;
	private JScrollPane supplyListScroll;
	private String[] supplyTableTitles = {"Name", "Quantity", "Price per Unit", "Total Cost"};
	private String[][] supplyTableData = {{""},{""},{""},{""}};
	private int index2 = 1;
	private LinkedList<Item> suList = new LinkedList<Item>();

	public ManageEquipment (LaboratoryManager aLab){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thisLab = aLab;
		populateTableData();
		initAll();
		refreshData();
	}
	
	private void initAll(){
		returnMainMenu = new JButton();
		returnMainMenu.setText("Main Menu");
		returnMainMenu.setBackground(Color.LIGHT_GRAY);
		returnMainMenu.setForeground(Color.DARK_GRAY);
		returnMainMenu.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				returnMainMenuActionPerformed();
			}
		});

		manageEquipmentTitle = new JLabel();
		manageEquipmentTitle.setText("Manage Lab Items");
		manageEquipmentTitle.setBackground(Color.blue);
		manageEquipmentTitle.setFont((new java.awt.Font(null, java.awt.Font.BOLD, 25)));

		addEquipmentTitle = new JLabel();
		addEquipmentTitle.setText("Add Equipment");
		addEquipmentTitle.setBackground(Color.GREEN);
		
		supplyTableLabel = new JLabel();
		supplyTableLabel.setText("Supplies");
		supplyTableLabel.setBackground(Color.DARK_GRAY);
		
		equipmentTableLabel = new JLabel();
		equipmentTableLabel.setText("Equipment");
		equipmentTableLabel.setBackground(Color.DARK_GRAY);

		addSupplyButton = new JButton();
		addSupplyButton.setText("Add Supplies");
		addSupplyButton.setBackground(Color.LIGHT_GRAY);
		addSupplyButton.setForeground(Color.DARK_GRAY);
		addSupplyButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				addSupplyButtonActionPerformed();
			}
		});
		
		addEquipmentButton = new JButton();
		addEquipmentButton.setText("Add Equipment");
		addEquipmentButton.setBackground(Color.LIGHT_GRAY);
		addEquipmentButton.setForeground(Color.DARK_GRAY);
		addEquipmentButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				addEquipmentButtonActionPerformed();
			}
		});

		removeEquipmentButton = new JButton();
		removeEquipmentButton.setText("Remove");
		removeEquipmentButton.setBackground(Color.LIGHT_GRAY);
		removeEquipmentButton.setForeground(Color.DARK_GRAY);
		removeEquipmentButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				removeEquipmentButtonActionPerformed();
			}
		});
		
		removeSupplyButton = new JButton();
		removeSupplyButton.setText("Remove");
		removeSupplyButton.setBackground(Color.LIGHT_GRAY);
		removeSupplyButton.setForeground(Color.DARK_GRAY);
		removeSupplyButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				removeSupplyButtonActionPerformed();
			}
		});
		
		editEquipmentButton = new JButton();
		editEquipmentButton.setText("Edit");
		editEquipmentButton.setBackground(Color.LIGHT_GRAY);
		editEquipmentButton.setForeground(Color.DARK_GRAY);
		editEquipmentButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				editEquipmentButtonActionPerformed();
			}
		});
		
		editSupplyButton = new JButton();
		editSupplyButton.setText("Edit");
		editSupplyButton.setBackground(Color.LIGHT_GRAY);
		editSupplyButton.setForeground(Color.DARK_GRAY);
		editSupplyButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt){
				editSupplyButtonActionPerformed();
			}
		});

		nameLabel = new JLabel();
		nameLabel.setText("Name");
		nameLabel.setForeground(Color.DARK_GRAY);
		nameTextField = new JTextField();

		typeLabel = new JLabel();
		typeLabel.setText("Type");
		typeLabel.setForeground(Color.DARK_GRAY);

		quantityLabel = new JLabel();
		quantityLabel.setText("Quantity");
		quantityLabel.setForeground(Color.DARK_GRAY);
		quantityTextField = new JTextField();

		priceLabel = new JLabel();
		priceLabel.setText("Price");
		priceLabel.setForeground(Color.DARK_GRAY);
		priceTextField = new JTextField();
		
		equipmentList = new JTable(equipmentTableData, equipmentTableTitles) {
			private static final long serialVersionUID = 2L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		equipmentList.setGridColor(Color.DARK_GRAY);
		equipmentList.setForeground(Color.WHITE);
		equipmentList.setBackground(Color.DARK_GRAY);
		equipmentList.setSelectionBackground(Color.WHITE);
		equipmentList.setSelectionForeground(Color.BLACK);
		equipmentList.getTableHeader().setBackground(Color.DARK_GRAY);
		equipmentList.getTableHeader().setForeground(Color.WHITE);
		equipmentList.getTableHeader().setBorder(null);
		equipmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		equipmentList.setColumnSelectionAllowed(false);
		equipmentList.setCellSelectionEnabled(false);
		equipmentList.setRowSelectionAllowed(true);
		equipmentList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				index = equipmentList.getSelectedRow();
				refreshData();
			}
		});

		equipmentListScroll = new JScrollPane(equipmentList);
		equipmentListScroll.setBorder(null);
		equipmentListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		equipmentListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		equipmentListScroll.setPreferredSize(new Dimension(400, 150));
		equipmentList.setFillsViewportHeight(true);
		
		
		supplyList = new JTable(supplyTableData, supplyTableTitles) {
			private static final long serialVersionUID = 2L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		supplyList.setGridColor(Color.DARK_GRAY);
		supplyList.setForeground(Color.WHITE);
		supplyList.setBackground(Color.DARK_GRAY);
		supplyList.setSelectionBackground(Color.WHITE);
		supplyList.setSelectionForeground(Color.BLACK);
		supplyList.getTableHeader().setBackground(Color.DARK_GRAY);
		supplyList.getTableHeader().setForeground(Color.WHITE);
		supplyList.getTableHeader().setBorder(null);
		supplyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplyList.setColumnSelectionAllowed(false);
		supplyList.setCellSelectionEnabled(false);
		supplyList.setRowSelectionAllowed(true);
		supplyList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				index2 = supplyList.getSelectedRow();
				refreshData();
			}
		});

		supplyListScroll = new JScrollPane(supplyList);
		supplyListScroll.setBorder(null);
		supplyListScroll.getVerticalScrollBar().setBackground(Color.WHITE);
		supplyListScroll.getVerticalScrollBar().setForeground(Color.WHITE);
		supplyListScroll.setPreferredSize(new Dimension(400, 150));
		supplyList.setFillsViewportHeight(true);
		
		

		//Layout
		GroupLayout layoutFinal = new GroupLayout(getContentPane());
		getContentPane().setLayout(layoutFinal);
		layoutFinal.setAutoCreateGaps(true);
		layoutFinal.setAutoCreateContainerGaps(true);
		layoutFinal.setHorizontalGroup(layoutFinal.createParallelGroup()
				.addComponent(returnMainMenu)
				.addGap(10)
				.addComponent(manageEquipmentTitle)
				.addGroup(layoutFinal.createSequentialGroup()
						.addGroup(layoutFinal.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(priceLabel)
								.addComponent(quantityLabel))
						.addGroup(layoutFinal.createParallelGroup()
								.addComponent(nameTextField, 20, 20, 250)
								.addComponent(priceTextField, 20, 20, 250)
								.addComponent(quantityTextField, 20, 20, 250)))
				.addGap(1000)
				.addComponent(addSupplyButton)
				.addComponent(addEquipmentButton)
				.addComponent(equipmentTableLabel)
				.addComponent(equipmentListScroll)
				.addComponent(removeEquipmentButton)
				.addComponent(editEquipmentButton)
				.addComponent(supplyTableLabel)
				.addComponent(supplyListScroll)
				.addComponent(removeSupplyButton)
				.addComponent(editSupplyButton)
				.addGap(300)
				);
		
		layoutFinal.setVerticalGroup(layoutFinal.createSequentialGroup()
				.addGap(10)
				.addComponent(returnMainMenu)
				.addComponent(manageEquipmentTitle)
				.addGap(10)
				.addGroup(layoutFinal.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(nameTextField))
				.addGroup(layoutFinal.createParallelGroup()
						.addComponent(priceLabel)
						.addComponent(priceTextField))
				.addGroup(layoutFinal.createParallelGroup()
						.addComponent(quantityLabel)
						.addComponent(quantityTextField))
				.addGap(10)
				.addComponent(addSupplyButton)
				.addGap(10)
				.addComponent(addEquipmentButton)
				.addGap(10)
				.addComponent(equipmentTableLabel)
				.addComponent(equipmentListScroll)
				.addGap(10)
				.addComponent(removeEquipmentButton)
				.addGap(10)
				.addComponent(editEquipmentButton)
				.addGap(10)
				.addComponent(supplyTableLabel)
				.addComponent(supplyListScroll)
				.addComponent(removeSupplyButton)
				.addComponent(editSupplyButton)
				.addGap(100));
		pack();
		setLocationRelativeTo(null);
	}

	private void returnMainMenuActionPerformed(){
		new MainPage(thisLab).setVisible(true);
		this.dispose();
	}

	public void addSupplyButtonActionPerformed(){
		URLMSController aController = new URLMSController(thisLab);

		String name = nameTextField.getText();
		Double price = Double.parseDouble(priceTextField.getText());
		int quantity = Integer.parseInt(quantityTextField.getText());

		try{
			aController.createSupply(name, price, quantity);
			populateTableData();
			updateActionPerformed();
			String description = name + ", " + "unit price: " + price + "quantity: " + quantity;
			String date = "";
			Transaction transaction = new Transaction(aController.getSqlDate(), description, price);
			thisLab.addTransaction(transaction);
		}
		catch(Exception e){
			error = error + e.getMessage();
		}
	}
	
	public void addEquipmentButtonActionPerformed(){
		URLMSController aController = new URLMSController(thisLab);

		String name = nameTextField.getText();
		Double price = Double.parseDouble(priceTextField.getText());
		int quantity = Integer.parseInt(quantityTextField.getText());

		try{
			aController.createEquipment(name, price, quantity);
			populateTableData();
			updateActionPerformed();
			String description = name + ", " + "unit price: " + price + "quantity: " + quantity;
			String date = "";
			Transaction transaction = new Transaction(aController.getSqlDate(), description, price);
			thisLab.addTransaction(transaction);
		}
		catch(Exception e){
			error = error + e.getMessage();
		}
	}
	
	private Item getSelectedEquipment() {
		return thisLab.getItem(thisLab.indexOfItem(eqList.get(index)));
	}
	
	private Item getSelectedSupply() {
		return thisLab.getItem(thisLab.indexOfItem(suList.get(index2)));
	}
	
	public void removeEquipmentButtonActionPerformed(){
		URLMSController aController = new URLMSController(thisLab);
		Item selectedItem = getSelectedEquipment();
		aController.takeOutItem(selectedItem);
		refreshData();
		updateActionPerformed();
	}
	
	public void removeSupplyButtonActionPerformed() {
		URLMSController aController = new URLMSController(thisLab);
		Item selectedItem = getSelectedSupply();
		aController.takeOutItem(selectedItem);
		refreshData();
		updateActionPerformed();
	}
	
	public void editEquipmentButtonActionPerformed() {
		new EditItem(thisLab, getSelectedEquipment()).setVisible(true);
		this.dispose();
	}
	
	public void editSupplyButtonActionPerformed() {
		new EditItem(thisLab, getSelectedSupply()).setVisible(true);
		this.dispose();
	}
	
	private void updateActionPerformed(){
		new ManageEquipment(thisLab).setVisible(true);
		this.dispose();
	}
	
	private void populateTableData() {
		for(int i = 0; i<thisLab.getItems().size();i++) {
			if(Equipment.class.isInstance(thisLab.getItem(i))) {
				Equipment equipment = (Equipment) thisLab.getItem(i);
				eqList.add(equipment);
			}
			if(Supply.class.isInstance(thisLab.getItem(i))) {
				Supply supply = (Supply) thisLab.getItem(i);
				suList.add(supply);
			}
		}
		
//		eqList = thisLab.getItems();
		equipmentTableData = new String[eqList.size()][4];
		for (int i = 0; i<eqList.size();i++) {
			equipmentTableData[i][0] = eqList.get(i).getName();
			equipmentTableData[i][1] = Double.toString(eqList.get(i).getQuantity());
			equipmentTableData[i][2] = Double.toString(eqList.get(i).getPrice());
			equipmentTableData[i][3] = Double.toString(eqList.get(i).getQuantity()*eqList.get(i).getPrice());
		}
		
		supplyTableData = new String[suList.size()][4];
		for (int i = 0; i<suList.size();i++) {
			supplyTableData[i][0] = suList.get(i).getName();
			supplyTableData[i][1] = Double.toString(suList.get(i).getQuantity());
			supplyTableData[i][2] = Double.toString(suList.get(i).getPrice());
			supplyTableData[i][3] = Double.toString(suList.get(i).getQuantity()*suList.get(i).getPrice());
		}
	}

	private void refreshData(){
		populateTableData();
		equipmentList.repaint();
		supplyList.repaint();
		pack();
	}
}
