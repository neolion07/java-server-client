package com.javaserverclient.server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Properties;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database{
	private static Connection conn;

	public static Properties readProperties(){
		Properties props = new Properties();
		Path myPath = Paths.get("db.properties");
		try {
			BufferedReader bf = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8);
			props.load(bf);
		} catch (IOException e) {
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, null, e);
		}
		return props;
	}
	
	public static void connect(){
		Properties props = readProperties();
		try {
			conn = DriverManager.getConnection(
				props.getProperty("db.url"),
				props.getProperty("db.user"),
				props.getProperty("db.password")
			);
		}
		catch (SQLException e){
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	// operations for table 'Role'
	public void createRole(String name, double salary){
		String query = "INSERT INTO Role(name, salary) VALUES (?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(query)){				
			conn.setAutoCommit(false);
			
			pst.setString(1, name);
			pst.setDouble(2, salary);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("Inserted data into table 'role'");
		}
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	public ArrayList<String[]> readRole(int id){
		String query = "SELECT * FROM role WHERE id=?";
		ArrayList<String[]> results = new ArrayList<String[]>();
		PreparedStatement pst = null;
		try {
			if (id <= 0){
				query = "SELECT * FROM role";
				pst = conn.prepareStatement(query);
			}
			else {
				pst = conn.prepareStatement(query);
				pst.setInt(1, id);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()){
				String[] row = new String[]{"","",""};
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				results.add(row);
			}
		}
		catch (SQLException e){
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return results;
	}
	public void updateRole(int id, String name, double salary){
		String query = "UPDATE Role SET name=?, salary=? WHERE id=?";
		try (PreparedStatement pst = conn.prepareStatement(query)){
			/*
				if (id.length != name.length || id.length != salary.length){
				throw new Exception("Some fields are missing.");
			}
			*/
			conn.setAutoCommit(false);
			//for (int i=0; i<id.length; i++){
				pst.setString(1, name);
				pst.setDouble(2, salary);
				pst.setInt(3, id);
				pst.executeUpdate();
			//}			
			conn.commit();
			System.out.println("Updated.");
		}
		/*
		catch (Exception ex){
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		*/
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	public void deleteRole(int id){
		String query = "DELETE FROM Role WHERE id=?";
		try (PreparedStatement pst = conn.prepareStatement(query)){
			conn.setAutoCommit(false);
			//for (int i=0; i<id.length; i++){
				pst.setInt(1, id);
				pst.executeUpdate();
			//}
			conn.commit();
		}
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	// operations for table 'Employee'
	public void createEmployee(String pid, String name, String surname, long join_date, int role_id){
		String query = "INSERT INTO Employee(pid, name, surname, join_date, role_id) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(query)){		
			conn.setAutoCommit(false);
			
			pst.setString(1, pid);		
			pst.setString(2, name);
			pst.setString(3, surname);
			pst.setDate(4, new Date(join_date));
			pst.setInt(5, role_id);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("Inserted data into table 'Employee'");
		}
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public ArrayList<String[]> readEmployee(String pid){
		String query = "SELECT * FROM employee WHERE pid=?";
		ArrayList<String[]> results = new ArrayList<String[]>();
		PreparedStatement pst = null;
		try {
			if (pid == ""){
				query = "SELECT * FROM employee";
				pst = conn.prepareStatement(query);
			}
			else {
				pst = conn.prepareStatement(query);
				pst.setString(1, pid);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()){
				String[] row = new String[]{"","","","",""};
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				results.add(row);
			}
		}
		catch (SQLException e){
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return results;
	}
	
	public void updateEmployee(String pid, String name, String surname, int role_id){
		String query = "UPDATE employee SET name=?, surname=?, role_id=? WHERE pid=?";
		try (PreparedStatement pst = conn.prepareStatement(query)){
			conn.setAutoCommit(false);
			//for (int i=0; i<pid.length; i++){
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setInt(3, role_id);
				pst.setString(4, pid);
				pst.executeUpdate();
			//}			
			conn.commit();
			System.out.println("Updated.");
		}
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	public void deleteEmployee(String pid){
		String query = "DELETE FROM employee WHERE pid=?";
		try (PreparedStatement pst = conn.prepareStatement(query)){
			conn.setAutoCommit(false);
			//for (int i=0; i<pid.length; i++){
				pst.setString(1, pid);
				pst.executeUpdate();
			//}
			conn.commit();
		}
		catch (SQLException e){
			if (conn != null){
				try {
					conn.rollback();
				}
				catch (SQLException e1){
					Logger lgr = Logger.getLogger(Database.class.getName());
					lgr.log(Level.WARNING, e1.getMessage(), e1);
				}
			}
			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	// empty constructor because why bother
	public Database(){}
}
