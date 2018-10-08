package fr.bencor29.datatransfer;

import java.util.ArrayList;

import fr.bencor29.datatransfer.events.listeners.TransferListener;

public abstract class DTMaster {
	
	private ArrayList<TransferListener> tl;
	
	public DTMaster() {
		tl = new ArrayList<>();
	}
	
	/**
	 * Add a TransferListener to the connection instance.
	 * Listeners will be called when data is received from the server.
	 * @param tl The TransferListener object
	 */
	public void addListener(TransferListener tl) {
		this.tl.add(tl);
	}
	
	/**
	 * Get the current active listeners
	 * @return The TransferListener's list 
	 */
	public TransferListener[] getListeners() {
		return (TransferListener[]) this.tl.toArray();
	}
	
	/**
	 * Remove a listener from the active listeners
	 * @param tl The listener to remove
	 */
	public void removeListener(TransferListener tl) {
		this.tl.remove(tl);
	}

}
