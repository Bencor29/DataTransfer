package fr.bencor29.datatransfer.events;

import fr.bencor29.datatransfer.server.Client;

public class ServerTransferEvent extends TransferEvent implements ClientEvent {

	private Client c;
	
	/**
	 * Create a ServerTransferEvent.
	 * @param c The client object corresponding to the event
	 * @param d The data received
	 */
	public ServerTransferEvent(Client c, String d) {
		super(c.getSocket(), d);
		this.c = c;
	}

	@Override
	public Client getClient() {
		return c;
	}

	@Override
	public int getClientID() {
		return c.getID();
	}

}
