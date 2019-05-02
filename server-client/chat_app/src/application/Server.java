package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

	public class Server {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int c = 4;
	private Thread thread;
	public boolean canSendMessage = false;
	
	public Server() {
		try {
			
		 server = new ServerSocket(6789, 100);
		}
		 catch(IOException e){
			 e.printStackTrace();
		 }
			
	}
	
	public void start() {
		thread = new Thread(
			new Runnable() {
				public void run() {
					try {
						connection =server.accept();
						System.out.println("Server is connected");
						output = new ObjectOutputStream(connection.getOutputStream());
						output.flush();
						input = new ObjectInputStream(connection.getInputStream());
						canSendMessage = true;
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		);
		thread.start();
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
			c--;
		}
		catch(IOException e){
			 e.printStackTrace();	 
		}
		if (c == 0) {
			try {
				output.writeObject("i'm gay, don't tell anyone <3");
				output.flush();
			}
			catch(IOException e){
				 e.printStackTrace();	 
			}
		}
	}
	
	public String getMessage() {
		try {
			
			return (String)input.readObject();
		}
			
			catch(IOException e){
				 e.printStackTrace();
				 return "";
	}
	 
			catch(ClassNotFoundException e) {
				e.printStackTrace();
				 return "";
	}
			
		
		}
			
	

}
