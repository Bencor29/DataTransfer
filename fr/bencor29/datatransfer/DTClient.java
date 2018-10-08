package fr.bencor29.datatransfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import fr.bencor29.datatransfer.events.TransferEvent;
import fr.bencor29.datatransfer.events.listeners.TransferListener;

public class DTClient extends DTMaster {
	
	private Socket s;
	private Thread t;
	
	/**
	 * Create a new Client instance
	 * @param addr The server's address
	 * @param port The server's port
	 * @throws IOException
	 */
	public DTClient(InetAddress addr, int port) throws IOException {
		s = new Socket(addr, port);
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!s.isClosed())
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String str = reader.readLine();
						if(str != null) {
							TransferEvent tev = new TransferEvent(s, str);
							for(TransferListener tl : DTClient.super.getListeners())
								tl.onReceive(tev);
						}
					} catch (IOException e) {}
			}
		});
		t.start();
	}
	
	/**
	 * Get the connection socket
	 * @return the socket
	 */
	public Socket getSocket() {
		return s;
	}
	
	/**
	 * Send data as a string to the server
	 * @param str the data to send
	 * @throws IOException
	 */
	public void send(String str) throws IOException {
		Utils.sendString(s, str);
	}
	
	/**
	 * Close the connection to the server and interrupt thread
	 * @throws IOException
	 */
	public void close() throws IOException {
		s.close();
		t.interrupt();
	}

}
