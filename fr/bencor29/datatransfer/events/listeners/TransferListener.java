package fr.bencor29.datatransfer.events.listeners;

import fr.bencor29.datatransfer.events.TransferEvent;

public abstract class TransferListener {
	
	/**
	 * Handle received data
	 * @param event The received informations and his sender
	 */
	public abstract void onReceive(TransferEvent event);

}