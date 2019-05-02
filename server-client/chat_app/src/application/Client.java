package application;

import java.io.*;
import java.net.*;


import java.net.Socket;

import javafx.scene.text.Text;

	public class Client {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket connection;
	private Thread startThread;
	private String serverIP = new String();
	private Text winTxt;
	
	private Thread messageThread = new Thread(new Runnable() {
		public void run() {
			while(true) {
				try {
					String s = (String)input.readObject();
					winTxt.setText(s);
				}
				catch(IOException e){
					 e.printStackTrace();
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	public Client(String ip) {
		serverIP = ip;
	
	}
	
	public void start(Text txt) {
		winTxt = txt;
		startThread = new Thread(new Runnable() {
				public void run() {
					try {
						connection = new Socket(InetAddress.getByName(serverIP), 6789);
						output = new ObjectOutputStream(connection.getOutputStream());
						output.flush();
						input = new ObjectInputStream(connection.getInputStream());
						messageThread.start();
					}
					catch(IOException e){
						 e.printStackTrace();
					}
				}
		});
		startThread.start();
	}
	
	public void stop() {
		try {
			output.close();
			input.close();
			connection.close();
		}
		catch(IOException e){
			 e.printStackTrace();
		
		 }	
	}
		
	public void sendMessage(String string) {
		try {
			output.writeObject(string);
			output.flush();
		}
		catch(IOException e){
			 e.printStackTrace();
		}
	}
}
