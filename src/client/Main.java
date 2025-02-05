package com.javaserverclient.client;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{
	public static void main(String[] args){
		GUI gui;
		final String HOST = "127.0.0.1";
		final int PORT = 3000;
		DataOutputStream out;
		DataInputStream in;
		
		try {
			Socket sock = new Socket(HOST, PORT);
			
			out = new DataOutputStream(sock.getOutputStream());
			in = new DataInputStream(sock.getInputStream());
			
			out.writeUTF("Client: where is the lion?");
			System.out.println(in.readUTF());
			
			sock.close();
			gui = new GUI();
		}
		catch (IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
