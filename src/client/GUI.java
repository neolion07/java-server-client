package com.javaserverclient.client;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.net.Socket;
//import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GUI extends JFrame{
	//JFrame frm_1, frm_2, frm_3;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	String table;
	JMenuBar	mb;
	JMenu		m, n, o;
	JMenuItem	m1, m2, n1, n2, o1, o2, o3, o4;
	JDialog		dia1, dia2, dia3, dia4, dia5, dia6, dia7, dia8;
	JPanel	pan0, pan01,								// main frame
			pan11, pan12, pan13,
			pan21, pan22,
			pan31, pan32, pan33, pan34,
			pan41, pan42,
			pan51, pan52, pan53, pan54, pan55,
			pan61, pan62, pan63,
			pan71, pan72, pan73, pan74, pan75,
			pan81, pan82;
	JScrollPane	scr_01;
	JTable	tbl_01, tbl_02;
	DefaultTableModel	dtm_01, dtm_02;
	JTextField	txt_f11, txt_f12,						// createRole
				txt_f21,								// readRole
				txt_f31, txt_f32, txt_f33,				// updateRole
				txt_f41,								// deleteRole
				txt_f51, txt_f52, txt_f53, txt_f54,		// createEmployee
				txt_f61,								// readEmployee
				txt_f71, txt_f72, txt_f73, txt_f74,		// updateEmployee
				txt_f81;								// deleteEmployee
	JButton	b11, b12,
			b21, b22,
			b31, b32,
			b41, b42,
			b51, b52,
			b61, b62,
			b71, b72,
			b81, b82;
	JLabel	lbl_01,
			lbl_11, lbl_12,
			lbl_21,
			lbl_31, lbl_32, lbl_33,
			lbl_41,
			lbl_51, lbl_52, lbl_53, lbl_54,
			lbl_61,
			lbl_71, lbl_72, lbl_73, lbl_74,
			lbl_81;
	JList l2, l6;
	GridLayout gl;
	public GUI(){
		this.setTitle("Employee Management System");
		table = "role";
		gl = new GridLayout(3, 1);
		pan0 = new JPanel(new BorderLayout());
		pan01 = new JPanel(new FlowLayout());
		tbl_01 = new JTable();
		refreshTable();
		scr_01 = new JScrollPane(tbl_01);
		lbl_01 = new JLabel("Current table: " + table, JLabel.CENTER);
		mb = new JMenuBar();
		
		m = new JMenu("File");
		m1 = new JMenuItem("Refresh");
		m2 = new JMenuItem("Exit");
		
		n = new JMenu("Table");
		n1 = new JMenuItem("Role");
		n2 = new JMenuItem("Employee");
		
		o = new JMenu("Mode");
		o1 = new JMenuItem("New row");
		o2 = new JMenuItem("Search row by ID");
		o3 = new JMenuItem("Edit row by ID");
		o4 = new JMenuItem("Delete row by ID");
		
		dia1 = new JDialog(this, "Add to Role");
		dia1.setLayout(new GridLayout(3, 1));
		pan11 = new JPanel(new GridLayout(1, 2));
		pan12 = new JPanel(new GridLayout(1, 2));
		pan13 = new JPanel(new GridLayout(1, 2));
		lbl_11 = new JLabel("Name: ");
		lbl_12 = new JLabel("Salary: ");
		txt_f11 = new JTextField("");
		txt_f12 = new JTextField("");
		b11 = new JButton("Add");
		b12 = new JButton("Clear");
		
		dia2 = new JDialog(this, "Search Role");
		dia2.setLayout(new GridLayout(3, 1));
		pan21 = new JPanel(new GridLayout(1, 2));
		pan22 = new JPanel(new GridLayout(1, 2));
		lbl_21 = new JLabel("ID: ");
		txt_f21 = new JTextField("");
		b21 = new JButton("Search");
		b22 = new JButton("Clear");
		l2 = new JList();
		
		dia3 = new JDialog(this, "Update Role");
		dia3.setLayout(new GridLayout(4, 1));
		pan31 = new JPanel(new GridLayout(1, 2));
		pan32 = new JPanel(new GridLayout(1, 2));
		pan33 = new JPanel(new GridLayout(1, 2));
		pan34 = new JPanel(new GridLayout(1, 2));
		lbl_31 = new JLabel("ID: ");
		lbl_32 = new JLabel("Name: ");
		lbl_33 = new JLabel("Salary: ");
		txt_f31 = new JTextField("");
		txt_f32 = new JTextField("");
		txt_f33 = new JTextField("");
		b31 = new JButton("Update");
		b32 = new JButton("Clear");
		
		dia4 = new JDialog(this, "Delete from Role");
		dia4.setLayout(new GridLayout(2, 1));
		pan41 = new JPanel(new GridLayout(1, 2));
		pan42 = new JPanel(new GridLayout(1, 2));
		lbl_41 = new JLabel("ID: ");
		txt_f41 = new JTextField("");
		b41 = new JButton("Delete");
		b42 = new JButton("Clear");
		
		dia5 = new JDialog(this, "Add to Employee");
		dia5.setLayout(new GridLayout(5, 1));
		pan51 = new JPanel(new GridLayout(1, 2));
		pan52 = new JPanel(new GridLayout(1, 2));
		pan53 = new JPanel(new GridLayout(1, 2));
		pan54 = new JPanel(new GridLayout(1, 2));
		pan55 = new JPanel(new GridLayout(1, 2));
		lbl_51 = new JLabel("PID: ");
		lbl_52 = new JLabel("Name: ");
		lbl_53 = new JLabel("Surname: ");
		lbl_54 = new JLabel("Role ID: ");
		txt_f51 = new JTextField("");
		txt_f52 = new JTextField("");
		txt_f53 = new JTextField("");
		txt_f54 = new JTextField("");
		b51 = new JButton("Add");
		b52 = new JButton("Clear");
		
		dia6 = new JDialog(this, "Search Employee");
		dia6.setLayout(new GridLayout(3, 1));
		pan61 = new JPanel(new GridLayout(1, 2));
		pan62 = new JPanel(new GridLayout(1, 2));
		lbl_61 = new JLabel("PID: ");
		txt_f61 = new JTextField("");
		b61 = new JButton("Search");
		b62 = new JButton("Clear");
		l6 = new JList();
		
		dia7 = new JDialog(this, "Update Employee");
		dia7.setLayout(new GridLayout(5, 1));
		pan71 = new JPanel(new GridLayout(1, 2));
		pan72 = new JPanel(new GridLayout(1, 2));
		pan73 = new JPanel(new GridLayout(1, 2));
		pan74 = new JPanel(new GridLayout(1, 2));
		pan75 = new JPanel(new GridLayout(1, 2));
		lbl_71 = new JLabel("PID: ");
		lbl_72 = new JLabel("Name: ");
		lbl_73 = new JLabel("Surname: ");
		lbl_74 = new JLabel("Role ID: ");
		txt_f71 = new JTextField("");
		txt_f72 = new JTextField("");
		txt_f73 = new JTextField("");
		txt_f74 = new JTextField("");
		b71 = new JButton("Update");
		b72 = new JButton("Clear");
		
		dia8 = new JDialog(this, "Delete from Employee");
		dia8.setLayout(new GridLayout(2, 1));
		pan81 = new JPanel(new GridLayout(1, 2));
		pan82 = new JPanel(new GridLayout(1, 2));
		lbl_81 = new JLabel("ID: ");
		txt_f81 = new JTextField("");
		b81 = new JButton("Delete");
		b82 = new JButton("Clear");
		
		m1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				refreshTable();
			}
		});
		m2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		n1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				table = "role";
				refreshTable();
				lbl_01.setText("Current table: " + table);
			}
		});
		n2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				table = "employee";
				refreshTable();
				lbl_01.setText("Current table: " + table);
			}
		});
		o1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (table == "role") dia1.setVisible(true);
				if (table == "employee") dia5.setVisible(true);
			}
		});
		o2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Search row by ID");
			/*
            	if (table == "role") db.createRole("Cashier", 185.00);
				if (table == "employee") db.createEmployee("E-25101586", "Claude", "Schulz", (new Date()).getTime(), 3);
			//*/
			}
		});
		o3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	System.out.println("...");
			}
		});
		o4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	System.out.println("...");
			}
		});
		
		b11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f11.getText().equals("") ||
            		txt_f12.getText().equals("")
            	){
            		System.out.println("Some fields are missing.");
            	}
            	else {
            		System.out.println("OK");
            	/*
            		db.createRole(
            			txt_f11.getText(),
            			Double.parseDouble(txt_f12.getText())
            		);
            	//*/
            	}
			}
		});
		b12.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f11.setText("");
            	txt_f12.setText("");
			}
		});
		b51.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f51.getText().equals("") ||
            		txt_f52.getText().equals("") ||
            		txt_f53.getText().equals("") ||
            		txt_f54.getText().equals("")
            	){
            		System.out.println("Some fields are missing.");
            	}
            	else {
            		System.out.println("OK");
            	/*
            		db.createEmployee(
            			txt_f51.getText(),
            			txt_f52.getText(),
            			txt_f53.getText(),
            			(new Date()).getTime(),
            			Integer.parseInt(txt_f54.getText())
            		);
            	//*/	
            	}
			}
		});
		b52.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f51.setText("");
            	txt_f52.setText("");
            	txt_f53.setText("");
            	txt_f54.setText("");
			}
		});
		
		// Main frame:
		m.add(m1);	m.add(m2);
		n.add(n1);	n.add(n2);
		o.add(o1);	o.add(o2);	o.add(o3);	o.add(o4);
		mb.add(m);	mb.add(n);	mb.add(o);
		pan0.add(BorderLayout.NORTH, mb);
		pan01.add(lbl_01);
		
		// Add to Role:
		pan11.add(lbl_11);	pan11.add(txt_f11);
		pan12.add(lbl_12);	pan12.add(txt_f12);
		pan13.add(b11);	pan13.add(b12);
		dia1.add(pan11);
		dia1.add(pan12);
		dia1.add(pan13);
		dia1.setDefaultCloseOperation(dia1.DISPOSE_ON_CLOSE);
		dia1.setSize(320, 240);
		
		// Search:
		pan21.add(lbl_21);	pan21.add(txt_f21);
		pan22.add(b21);		pan22.add(b22);
		
		pan31.add(lbl_31);	pan31.add(txt_f31);
		pan32.add(lbl_32);	pan32.add(txt_f32);
		pan33.add(lbl_33);	pan33.add(txt_f33);
		pan34.add(b31);		pan34.add(b32);
		
		// Delete:
		pan41.add(lbl_41);	pan41.add(txt_f41);
		pan42.add(b41);		pan42.add(b42);
		
		// Add to Employee:
		pan51.add(lbl_51);	pan51.add(txt_f51);
		pan52.add(lbl_52);	pan52.add(txt_f52);
		pan53.add(lbl_53);	pan53.add(txt_f53);
		pan54.add(lbl_54);	pan54.add(txt_f54);
		pan55.add(b51);		pan55.add(b52);
		dia5.add(pan51);
		dia5.add(pan52);
		dia5.add(pan53);
		dia5.add(pan54);
		dia5.add(pan55);
		dia5.setDefaultCloseOperation(dia5.DISPOSE_ON_CLOSE);
		dia5.setSize(320, 240);
		
		this.getContentPane().add(pan0);
		this.getContentPane().add(scr_01);
		this.getContentPane().add(pan01);
		this.setSize(480, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(gl);
		this.setVisible(true);
	}
	
	public void refreshTable(){
		String[] roleColumns = new String[] {
            "ID", "Name", "Salary"
        };
        String[] employeeColumns = new String[] {
            "PID", "Name", "Surname", "Join Date", "Role ID"
        };
        
		if (this.table == "role"){
			dtm_01 = new DefaultTableModel(null, columns);
			for (int i=0; i < data.length; i++){
				dtm_01.addRow(data[i]);
			}
			dtm_01.fireTableDataChanged();
			tbl_01.setModel(dtm_01);
		}
		if (this.table == "employee"){
			dtm_02 = new DefaultTableModel(null, columns);
			for (int i=0; i < data2.length; i++){
				dtm_02.addRow(data2[i]);
			}
			dtm_02.fireTableDataChanged();
			tbl_01.setModel(dtm_02);
		}
	}
}
