package fr.bencor29.datatransfer.events;

import fr.bencor29.datatransfer.server.Client;

public class ConnectionEvent extends Event implements ClientEvent {
	
	private boolean c;
	private Client cl;
	
	/**
	 * Create a new ConnectionEvent
	 * @param c The client object
	 */
	public ConnectionEvent(Client c) {
		super(c.getSocket());
		this.c = false;
		this.cl = c;
	}
	
	/**
	 * Get the connection status
	 * @return true if the connection is cancelled
	 */
	public boolean isCancelled() {
		return c;
	}
	
	/**
	 * Define the connection status
	 * @param cancelled true to cancel the connection
	 */
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
