package fr.bencor29.datatransfer.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import fr.bencor29.datatransfer.Utils;
import fr.bencor29.datatransfer.events.ServerTransferEvent;
import fr.bencor29.datatransfer.events.listeners.TransferListener;

public class Client {
	
	private static int lastID = 0;
	
	private int id;
	private Socket socket;
	private Thread th;
	
	protected Client(Socket so, DTServer server) {
		lastID++;
		
		this.id = lastID;
		this.socket = so;
		th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!socket.isClosed())
				    try {
				    	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String str = reader.readLine();
						if(str != null) {
							ServerTransferEvent ste = new ServerTransferEvent(Client.this, str);
							for(TransferListener tl : server.getListeners())
								tl.onReceive(ste);
						}
					} catch (IOException e) {}
			}
		});
	}
	
	protected void accept() {
		if(!th.isAlive())
			th.start();
	}
	
	/**
	 * Get the client id as an integer
	 * @return the client id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Get the client's IP address as a String. Same as Utils.getAddress(socket)
	 * @return the client's IP address
	 */
	public String getAddress() {
		return Utils.getAddress(socket);
	}
	
	/**
	 * Get the client's socket
	 * @return the client's socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Close the socket and interrupt the thread.
	 * Don't forget to call this method to be sure that all threads are interrupted
	 */
	public void close() {
		try {
			socket.close();
			th.interrupt();
		} catch (IOException e) {}
	}
	
	/**
	 * Send data as a String to the client. Same as Utils.sendString(socket, string)
	 * @param str The data to send
	 * @throws IOException
	 */
	public void sendString(String str) throws IOException {
		Utils.sendString(socket, str);
	}

}
