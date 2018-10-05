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
	
	public Socket getSocket() {
		return s;
	}
	
	
	public void send(String str) throws IOException {
		Utils.sendString(s, str);
	}
	
	public void close() throws IOException {
		s.close();
		t.interrupt();
	}

}
