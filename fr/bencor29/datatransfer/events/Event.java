package fr.bencor29.datatransfer.events;

import java.io.IOException;
import java.net.Socket;

import fr.bencor29.datatransfer.Utils;

public abstract class Event {
	
	private Socket s;
	
	public Event(Socket s) {
		this.s = s;
	}
	
	public Socket getSocket() {
		return s;
	}
	
	public String getAddress() {
		return Utils.getAddress(s);
	}
	
	public void send(String str) throws IOException {
		Utils.sendString(s, str);
	}

}
