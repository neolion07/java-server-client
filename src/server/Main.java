package com.javaserverclient.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{
	public static void main(String[] args){
		ServerSocket srv = null;
		Socket sock = null;
		final int PORT = 3000;
		DataInputStream in;
		DataOutputStream out;
		Database db;
		int req_code;	// 0: post, 1: read, 2: update, 3: delete
		
		try {
			srv = new ServerSocket(PORT);
			System.out.printf("Server running on port %d\n", PORT);
			db = new Database();
			db.connect();
			System.out.printf("Connected to the database\n");
			
			while (true){
				sock = srv.accept();
				System.out.println("Client connected");
				
				in = new DataInputStream(sock.getInputStream());
				out = new DataOutputStream(sock.getOutputStream());
				
				System.out.println(in.readUTF());
				
				out.writeUTF("The Gazing Lion is no more.");
				
				sock.close();
				System.out.println("Client disconnected");
			}
		}
		catch (IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
