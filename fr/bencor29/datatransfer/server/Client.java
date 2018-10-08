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
	
	public int getID() {
		return id;
	}
	
	public String getAddress() {
		return Utils.getAddress(socket);
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void close() {
		try {
			socket.close();
			th.interrupt();
		} catch (IOException e) {}
	}
	
	public void sendString(String str) throws IOException {
		Utils.sendString(socket, str);
	}

}
