package fr.bencor29.datatransfer;

import java.util.ArrayList;

import fr.bencor29.datatransfer.events.listeners.TransferListener;

public abstract class DTMaster {
	
	private ArrayList<TransferListener> tl;
	
	public DTMaster() {
		tl = new ArrayList<>();
	}
	
	public void addListener(TransferListener tl) {
		this.tl.add(tl);
	}
	
	public ArrayList<TransferListener> getListeners() {
		return tl;
	}
	
	public void removeListener(TransferListener tl) {
		this.tl.remove(tl);
	}

}
