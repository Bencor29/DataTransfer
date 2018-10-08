package fr.bencor29.datatransfer.events.listeners;

import fr.bencor29.datatransfer.events.ConnectionEvent;

public abstract class ConnectionListener {
	
	/**
	 * Handle new connections to the server
	 * @param event The connection informations
	 */
	public abstract void onConnection(ConnectionEvent event);

}
