package fr.bencor29.datatransfer.events;

import java.net.Socket;

public class TransferEvent extends Event {
	
	private String d;
	
	/**
	 * Create a TransferEvent
	 * @param f The socket corresponding to the event
	 * @param d The data received
	 */
	public TransferEvent(Socket f, String d) {
		super(f);
		this.d = d;
	}
	
	/**
	 * Get the received data
	 * @return the received data as a String
	 */
	public String getData() {
		return d;
	}
	
	/**
	 * Modify the received data (Have no effect on listeners called previously)
	 * @param data the new data
	 */
	public void setData(String data) {
		this.d = data;
	}

}
