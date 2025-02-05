package com.javaserverclient.client;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GUI extends JFrame{
	Socket sock;
	DataInputStream in;
	DataOutputStream out;
	String table;
	JMenuBar	mb;
	JMenu		m, n, o;
	JMenuItem	m1, m2, n1, n2, o1, o2, o3, o4;
	JDialog		dia1, dia2, dia3, dia4, dia5, dia6, dia7, dia8;
	JPanel	pan0, pan01,		// main frame
			pan11, pan12, pan13,
			pan21, pan22,
			pan31, pan32, pan33, pan34,
			pan41, pan42,
			pan51, pan52, pan53, pan54, pan55,
			pan61, pan62,
			pan71, pan72, pan73, pan74, pan75,
			pan81, pan82;
	JScrollPane	scr_01;
	JTable	tbl_01, tbl_02;
	DefaultTableModel	dtm_01, dtm_02;
	JTextField	txt_f11, txt_f12,		// createRole
				txt_f21,		// readRole
				txt_f31, txt_f32, txt_f33,		// updateRole
				txt_f41,		// deleteRole
				txt_f51, txt_f52, txt_f53, txt_f54,		// createEmployee
				txt_f61,		// readEmployee
				txt_f71, txt_f72, txt_f73, txt_f74,		// updateEmployee
				txt_f81;		// deleteEmployee
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
	GridLayout gl;
	public GUI(){
		this.setTitle("Employee Management System");
		table = "role";
		gl = new GridLayout(3, 1);
		pan0 = new JPanel(new BorderLayout());
		pan01 = new JPanel(new FlowLayout());
		tbl_01 = new JTable();
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
		dia2.setLayout(new GridLayout(2, 1));
		pan21 = new JPanel(new GridLayout(1, 2));
		pan22 = new JPanel(new GridLayout(1, 2));
		lbl_21 = new JLabel("ID: ");
		txt_f21 = new JTextField("");
		b21 = new JButton("Search");
		b22 = new JButton("Clear");
		
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
		dia6.setLayout(new GridLayout(2, 1));
		pan61 = new JPanel(new GridLayout(1, 2));
		pan62 = new JPanel(new GridLayout(1, 2));
		lbl_61 = new JLabel("PID: ");
		txt_f61 = new JTextField("");
		b61 = new JButton("Search");
		b62 = new JButton("Clear");
		
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
		lbl_81 = new JLabel("PID: ");
		txt_f81 = new JTextField("");
		b81 = new JButton("Delete");
		b82 = new JButton("Clear");
				
		// Main frame:
		m.add(m1);	m.add(m2);
		n.add(n1);	n.add(n2);
		o.add(o1);	o.add(o2);	o.add(o3);	o.add(o4);
		mb.add(m);	mb.add(n);	mb.add(o);
		pan0.add(BorderLayout.NORTH, mb);
		pan01.add(lbl_01);
		m1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				refreshTable(getData(""));
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
				refreshTable(getData(""));
				lbl_01.setText("Current table: " + table);
			}
		});
		n2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				table = "employee";
				refreshTable(getData(""));
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
				if (table == "role") dia2.setVisible(true);
				if (table == "employee") dia6.setVisible(true);
			/*
            	if (table == "role") db.createRole("Cashier", 185.00);
				if (table == "employee") db.createEmployee("E-25101586", "Claude", "Schulz", (new Date()).getTime(), 3);
			//*/
			}
		});
		o3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (table == "role") dia3.setVisible(true);
				if (table == "employee") dia7.setVisible(true);
			}
		});
		o4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (table == "role") dia4.setVisible(true);
				if (table == "employee") dia8.setVisible(true);
			}
		});
		
		// Add to Role:
		pan11.add(lbl_11);	pan11.add(txt_f11);
		pan12.add(lbl_12);	pan12.add(txt_f12);
		pan13.add(b11);	pan13.add(b12);
		dia1.add(pan11);
		dia1.add(pan12);
		dia1.add(pan13);
		dia1.setDefaultCloseOperation(dia1.DISPOSE_ON_CLOSE);
		dia1.setSize(320, 240);
		b11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f11.getText().equals("") ||
            		txt_f12.getText().equals("")
            	){
            		System.out.println("Some fields are missing.");
            	}
            	else {
            		connectToServer();
            		try {
            			out.writeInt(0);
		        		out.writeUTF(txt_f11.getText());
		        		out.writeUTF(txt_f12.getText());
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
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
		
		// Search Role by ID:
		pan21.add(lbl_21);	pan21.add(txt_f21);
		pan22.add(b21);		pan22.add(b22);
		dia2.add(pan21);
		dia2.add(pan22);
		dia2.setDefaultCloseOperation(dia2.DISPOSE_ON_CLOSE);
		dia2.setSize(320, 240);
		b21.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	refreshTable(getData(txt_f21.getText()));
			}
		});
		b22.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f21.setText("");
			}
		});
		
		// Modify Role by ID:
		pan31.add(lbl_31);	pan31.add(txt_f31);
		pan32.add(lbl_32);	pan32.add(txt_f32);
		pan33.add(lbl_33);	pan33.add(txt_f33);
		pan34.add(b31);		pan34.add(b32);
		dia3.add(pan31);
		dia3.add(pan32);
		dia3.add(pan33);
		dia3.add(pan34);
		dia3.setDefaultCloseOperation(dia3.DISPOSE_ON_CLOSE);
		dia3.setSize(320, 240);
		b31.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f31.getText().equals("") ||
            		txt_f32.getText().equals("") ||
            		txt_f33.getText().equals("")
            	){
            		System.out.println("Some fields are missing.");
            	}
            	else {
            		connectToServer();
            		try {
		        		out.writeInt(3);
		        		out.writeInt(Integer.parseInt(txt_f31.getText()));
		        		out.writeUTF(txt_f32.getText());
		        		out.writeUTF(txt_f33.getText());
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
            	}
			}
		});
		b32.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f31.setText("");
            	txt_f32.setText("");
            	txt_f33.setText("");
			}
		});
		
		// Delete Role by ID:
		pan41.add(lbl_41);	pan41.add(txt_f41);
		pan42.add(b41);		pan42.add(b42);
		dia4.add(pan41);
		dia4.add(pan42);
		dia4.setDefaultCloseOperation(dia4.DISPOSE_ON_CLOSE);
		dia4.setSize(320, 240);
		b41.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f41.getText().equals("")){
            		System.out.println("ID required.");
            	}
            	else {
            		connectToServer();
            		try {
		        		out.writeInt(4);
		        		out.writeInt(Integer.parseInt(txt_f41.getText()));
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
            	}
			}
		});
		b42.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f41.setText("");
			}
		});
		
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
            		connectToServer();
            		try {
		        		out.writeInt(5);
		        		out.writeUTF(txt_f51.getText());
		        		out.writeUTF(txt_f52.getText());
		        		out.writeUTF(txt_f53.getText());
		        		out.writeInt(Integer.parseInt(txt_f54.getText()));
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
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
		
		// Search Employee by ID:
		pan61.add(lbl_61);	pan61.add(txt_f61);
		pan62.add(b61);		pan62.add(b62);
		dia6.add(pan61);
		dia6.add(pan62);
		dia6.setDefaultCloseOperation(dia6.DISPOSE_ON_CLOSE);
		dia6.setSize(320, 240);
		b61.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
        		refreshTable(getData(txt_f61.getText()));
			}
		});
		b62.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f61.setText("");
			}
		});
		
		// Modify Employee by ID:
		pan71.add(lbl_71);	pan71.add(txt_f71);
		pan72.add(lbl_72);	pan72.add(txt_f72);
		pan73.add(lbl_73);	pan73.add(txt_f73);
		pan74.add(lbl_74);	pan74.add(txt_f74);
		pan75.add(b71);		pan75.add(b72);
		dia7.add(pan71);
		dia7.add(pan72);
		dia7.add(pan73);
		dia7.add(pan74);
		dia7.add(pan75);
		dia7.setDefaultCloseOperation(dia7.DISPOSE_ON_CLOSE);
		dia7.setSize(320, 240);
		b71.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f71.getText().equals("") ||
            		txt_f72.getText().equals("") ||
            		txt_f73.getText().equals("") ||
            		txt_f74.getText().equals("")
            	){
            		System.out.println("Some fields are missing.");
            	}
            	else {
            		connectToServer();
            		try {
		        		out.writeInt(8);
		        		out.writeUTF(txt_f71.getText());
		        		out.writeUTF(txt_f72.getText());
		        		out.writeUTF(txt_f73.getText());
		        		out.writeInt(Integer.parseInt(txt_f74.getText()));
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
            	}
			}
		});
		b72.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f71.setText("");
            	txt_f72.setText("");
            	txt_f73.setText("");
            	txt_f74.setText("");
			}
		});
		
		// Delete Employee by ID:
		pan81.add(lbl_81);	pan81.add(txt_f81);
		pan82.add(b81);		pan82.add(b82);
		dia8.add(pan81);
		dia8.add(pan82);
		dia8.setDefaultCloseOperation(dia8.DISPOSE_ON_CLOSE);
		dia8.setSize(320, 240);
		b81.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	if (txt_f81.getText().equals("")){
            		System.out.println("PID required.");
            	}
            	else {
            		connectToServer();
            		try {
		        		out.writeInt(9);
		        		out.writeUTF(txt_f81.getText());
			        	sock.close();
		        	}
		        	catch (IOException ex){
		        		Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		        	}
            	}
			}
		});
		b82.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
            	txt_f81.setText("");
			}
		});
		
		// Frame settings:
		this.getContentPane().add(pan0);
		this.getContentPane().add(scr_01);
		this.getContentPane().add(pan01);
		this.setSize(480, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(gl);
		this.setVisible(true);
		
		refreshTable(getData(""));
	}
	
	public void refreshTable(ArrayList<String[]> data){
		String[] roleColumns = new String[] {
            "ID", "Name", "Salary"
        };
        String[] employeeColumns = new String[] {
            "PID", "Name", "Surname", "Join Date", "Role ID"
        };

		if (this.table == "role"){
			dtm_01 = new DefaultTableModel(null, roleColumns);
			for (String[] obj : data){
				dtm_01.addRow(obj);
			}
			dtm_01.fireTableDataChanged();
			tbl_01.setModel(dtm_01);
		}
		if (this.table == "employee"){
			dtm_02 = new DefaultTableModel(null, employeeColumns);
			for (String[] obj : data){
				dtm_02.addRow(obj);
			}
			dtm_02.fireTableDataChanged();
			tbl_01.setModel(dtm_02);
		}
	}
	
	public ArrayList<String[]> getData(String query){
		String reply = "";
		ArrayList<String[]> data = new ArrayList<String[]>();
		if (this.table == "role"){
			try {
				connectToServer();
				out.writeInt(1);
				if (query == "") out.writeInt(0);
				else out.writeInt(Integer.parseInt(query));
				while (!(reply = in.readUTF()).equals("DONE")){
					String[] row = new String[]{reply, "", ""};
					reply = in.readUTF();
					row[1] = reply;
					reply = in.readUTF();
					row[2] = reply;
					data.add(row);
				}
				reply = "";
		    	sock.close();
			}
			catch (IOException ex){
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
    	}
		if (this.table == "employee"){
			try {
				connectToServer();
				out.writeInt(6);
				out.writeUTF(query);
				while (!(reply = in.readUTF()).equals("DONE")){
					String[] row = new String[]{reply, "", "", "", ""};
					reply = in.readUTF();
					row[1] = reply;
					reply = in.readUTF();
					row[2] = reply;
					reply = in.readUTF();
					row[3] = reply;
					reply = in.readUTF();
					row[4] = reply;
					data.add(row);
				}
				reply = "";
		    	sock.close();
			}
			catch (IOException ex){
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
    	}
    	return data;
	}
	
	public void connectToServer(){
		final String host = "127.0.0.1";
		final int port = 3000;
		try {
			this.sock = new Socket(host, port);
			this.out = new DataOutputStream(sock.getOutputStream());
			this.in = new DataInputStream(sock.getInputStream());
		}
		catch (IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
