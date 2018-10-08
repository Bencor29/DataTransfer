package fr.bencor29.datatransfer.events;

import fr.bencor29.datatransfer.server.Client;

public class ConnectionEvent extends Event implements ClientEvent {
	
	private boolean c;
	private Client cl;
	
	public ConnectionEvent(Client c) {
		super(c.getSocket());
		this.c = false;
		this.cl = c;
	}
	
	public boolean isCancelled() {
		return c;
	}
	
	public void setCancelled(boolean cancelled) {
		this.c = cancelled;
	}

	@Override
	public Client getClient() {
		return cl;
	}

	@Override
	public int getClientID() {
		return cl.getID();
	}

}
