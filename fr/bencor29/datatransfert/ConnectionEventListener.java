package fr.bencor29.datatransfert;

import java.util.ArrayList;

public abstract class ConnectionEventListener {
	
	private static ArrayList<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>();
	
	public abstract void onConnection(ConnectionEvent event);
	
	public static void addListener(ConnectionEventListener listener) {
		listeners.add(listener);
	}
	
	protected static ArrayList<ConnectionEventListener> getListeners() {
		return listeners;
	}

}
