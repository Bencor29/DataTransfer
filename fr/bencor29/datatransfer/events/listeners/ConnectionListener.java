package fr.bencor29.datatransfer.events.listeners;

import fr.bencor29.datatransfer.events.ConnectionEvent;

public abstract class ConnectionListener {
	
	public abstract void onConnection(ConnectionEvent event);

}
