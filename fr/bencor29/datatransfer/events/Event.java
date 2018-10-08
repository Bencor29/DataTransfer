package fr.bencor29.datatransfer.events;

import java.io.IOException;
import java.net.Socket;

import fr.bencor29.datatransfer.Utils;

public abstract class Event {
	
	private Socket s;
	
	/**
	 * Create a new event
	 * @param s The socket
	 */
	public Event(Socket s) {
		this.s = s;
	}
	
	/**
	 * Get the socket corresponding to the event
	 * @return the socket
	 */
	public Socket getSocket() {
		return s;
	}
	
	/**
	 * Get the socket address. Same as Utils.getAddress(socket)
	 * @return The address as a String
	 */
	public String getAddress() {
		return Utils.getAddress(s);
	}
	
	/**
	 * Send data to the socket. Same as Utils.sendString(socket, string)
	 * @param str the data to send
	 * @throws IOException
	 */
	public void send(String str) throws IOException {
		Utils.sendString(s, str);
	}

}
