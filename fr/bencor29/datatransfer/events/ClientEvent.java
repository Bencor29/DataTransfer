package fr.bencor29.datatransfer.events;

import fr.bencor29.datatransfer.server.Client;

public abstract interface ClientEvent {
	
	/**
	 * Get the client corresponding to the event
	 * @return The client object
	 */
	public Client getClient();
	
	/**
	 * Get the client id. Same as getClient().getID()
	 * @return the client's id
	 */
	public int getClientID();

}
