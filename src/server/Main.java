package com.javaserverclient.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
		int req_code; //(role) 0:post, 1:read, 3:update, 4:delete
					  //(employee) 5:post, 6:read, 8:update, 9:delete
		ArrayList<String[]> res;
		
		try {
			srv = new ServerSocket(PORT);
			System.out.printf("Server running on port %d\n", PORT);
			db = new Database();
			db.connect();
			System.out.printf("Connected to the database\n");
			
			while (true){
				sock = srv.accept();
				System.out.println("Connection from " + sock);
				in = new DataInputStream(sock.getInputStream());
				out = new DataOutputStream(sock.getOutputStream());
				
				req_code = in.readInt();
				
				switch (req_code){
					case 0:
						db.createRole(
							in.readUTF(),
							Double.parseDouble(in.readUTF())
						);
						break;
					case 1:
						res = db.readRole(in.readInt());
						//*
						for (String[] obj : res){
							out.writeUTF(obj[0]);
							out.writeUTF(obj[1]);
							out.writeUTF(obj[2]);
							out.flush();
						}
						//*/
						out.writeUTF("DONE");
						break;
					case 3:
						db.updateRole(
							in.readInt(),
							in.readUTF(),
							Double.parseDouble(in.readUTF())
						);
						break;
					case 4:
						db.deleteRole(in.readInt());
						break;
					case 5:
						db.createEmployee(
							in.readUTF(),
							in.readUTF(),
							in.readUTF(),
							System.currentTimeMillis(),
							in.readInt()
						);
						break;
					case 6:
						res = db.readEmployee(in.readUTF());
						for (String[] obj : res){
							out.writeUTF(obj[0]);
							out.writeUTF(obj[1]);
							out.writeUTF(obj[2]);
							out.writeUTF(obj[3]);
							out.writeUTF(obj[4]);
							out.flush();
						}
						out.writeUTF("DONE");
						break;
					case 8:
						db.updateEmployee(
							in.readUTF(),
							in.readUTF(),
							in.readUTF(),
							in.readInt()
						);
						break;
					case 9:
						db.deleteEmployee(in.readUTF());
						break;
					default:
						break;
				}
				
				System.out.println("Closing connection from " + sock);
				sock.close();
			}
		}
		catch (IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
