package ca.mcgill.ecse321.URLMS.view;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.TableColumn;

import ca.mcgill.ecse321.URLMS.model.LaboratoryManager;

public class ManageStaffTable extends ManageStaff {

	public JTable staffTable;
	public JScrollPane staffScrollPane;
	public JComboBox roleCombo;
	public JScrollPane theScrollPane;

	public ManageStaffTable(LaboratoryManager aLab) {
		super(aLab);
		initAllTable();
	}

	String[] role = {"Reseach Associate", "Research Assistant"};

	public void initAllTable(){
		staffTable = new JTable();
		staffTable.setAutoCreateRowSorter(true);
		staffTable.setGridColor(Color.GRAY);
		staffTable.setBackground(Color.LIGHT_GRAY);

		roleCombo = new JComboBox(role);
		TableColumn roleColumn = staffTable.getColumnModel().getColumn(3);
		roleColumn.setCellEditor(new DefaultCellEditor(roleCombo));

		theScrollPane = new JScrollPane();


		staffScrollPane = new JScrollPane();
	}

}
