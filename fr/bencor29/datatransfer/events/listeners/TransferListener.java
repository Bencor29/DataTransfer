package fr.bencor29.datatransfer.events.listeners;

import fr.bencor29.datatransfer.events.TransferEvent;

public abstract class TransferListener {
	
	public abstract void onReceive(TransferEvent event);

}