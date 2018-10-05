package fr.bencor29.datatransfer.events;

import java.net.Socket;

public class TransferEvent extends Event {
	
	private String d;
	
	public TransferEvent(Socket f, String d) {
		super(f);
		this.d = d;
	}
	
	public String getData() {
		return d;
	}
	
	public void setData(String data) {
		this.d = data;
	}

}
