package fr.bencor29.datatransfert;

import java.net.Socket;

public class ConnectionEvent {
	
	private Socket socket;
	private boolean accept;
	
	protected ConnectionEvent(Socket s) {
		this.socket = s;
		accept = true;
	}
	
	public void deny() {
		accept = false;
	}
	
	public void accept() {
		accept = true;
	}
	
	public void setAccepted(boolean accepted) {
		accept = accepted;
	}
	
	public boolean isAccepted() {
		return accept;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getAddress() {
		return socket.getInetAddress().toString();
	}

}
