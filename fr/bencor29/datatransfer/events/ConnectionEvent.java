package fr.bencor29.datatransfer.events;

import java.net.Socket;

public class ConnectionEvent extends Event {
	
	private boolean c;
	
	public ConnectionEvent(Socket f) {
		super(f);
	}
	
	public boolean isCancelled() {
		return c;
	}
	
	public void setCancelled(boolean cancelled) {
		this.c = cancelled;
	}

}
