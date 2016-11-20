package fr.bencor29.datatransfert;

import java.io.IOException;
import java.net.Socket;

public class ReceivedString {
	
	private String string;
	private Socket socket;
	
	public ReceivedString(String str, Socket s) {
		string = str;
		socket = s;
	}
	
	public String getString() {
		return string;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void reply(String str) throws IOException {
		Utils.sendString(socket, str);
	}

}
