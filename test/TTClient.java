package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import fr.bencor29.datatransfer.DTClient;
import fr.bencor29.datatransfer.events.TransferEvent;
import fr.bencor29.datatransfer.events.listeners.TransferListener;

public class TTClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// We connect to the server port
		DTClient c = new DTClient(InetAddress.getLocalHost(), 8888);
		
		// TransferListener are used when a string is received
		c.addListener(new TransferListener() {
			
			@Override
			public void onReceive(TransferEvent event) {
				System.out.println("[Client] Received \"" + event.getData() + "\" from server.");
			}
		});
		
		// We send a simple string
		c.send("Hello, World!");
		
		// We wait 1s for the server reply
		Thread.sleep(1000);
		
		// We close the socket
		// Highly recommended because it also stop running threads
		c.close();
	}

}
