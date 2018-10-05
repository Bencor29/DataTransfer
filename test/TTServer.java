package test;

import java.io.IOException;

import fr.bencor29.datatransfer.DTServer;
import fr.bencor29.datatransfer.events.ConnectionEvent;
import fr.bencor29.datatransfer.events.TransferEvent;
import fr.bencor29.datatransfer.events.listeners.ConnectionListener;
import fr.bencor29.datatransfer.events.listeners.TransferListener;

public class TTServer {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// Server created with port 8888
		DTServer srv = new DTServer(8888);
		System.out.println("Server openned");
		
		// Let's add listeners
		// We can add as many Listener as we want
		
	
		// ConnectionListener are used when a client
		// start a new socket with the server
		srv.addListener(new ConnectionListener() {
			
			@Override
			public void onConnection(ConnectionEvent event) {
				System.out.println("Client connected with ip: " + event.getAddress());
			}
		});
		
		// TransferListener are used when a string is received
		srv.addListener(new TransferListener() {
			
			@Override
			public void onReceive(TransferEvent event) {
				System.out.println("Received \"" + event.getData() + "\" from " + event.getAddress());
			}
		});
		
		// We wait 10s before stopping the server
		Thread.sleep(10000);
		
		// Stopping the server is highly recommended
		// This method close the ServerSocket and all
		// the Client's sockets. This also stop running threads.
		srv.stop();
		System.out.println("Server closed");
	}

}
