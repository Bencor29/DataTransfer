package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import fr.bencor29.datatransfer.DTClient;

public class TTClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// We connect to the server port
		DTClient c = new DTClient(InetAddress.getLocalHost(), 8888);
		
		// We send a simple string
		c.send("Hello, World!");
		
		// We close the socket
		// Highly recommended because it also stop running threads
		c.close();
	}

}
