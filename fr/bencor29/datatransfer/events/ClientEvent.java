package fr.bencor29.datatransfer.events;

import fr.bencor29.datatransfer.server.Client;

public abstract interface ClientEvent {
	
	public Client getClient();
	
	public int getClientID();

}
