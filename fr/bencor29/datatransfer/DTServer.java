package fr.bencor29.datatransfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.bencor29.datatransfer.events.ConnectionEvent;
import fr.bencor29.datatransfer.events.TransferEvent;
import fr.bencor29.datatransfer.events.listeners.ConnectionListener;
import fr.bencor29.datatransfer.events.listeners.TransferListener;

public class DTServer extends DTMaster {
	
	private ServerSocket ss;
	private Thread t;
	private ArrayList<ConnectionListener> cl;
	private HashMap<Socket, Thread> sts;
	
	private boolean close;
	
	public DTServer(int port) throws IOException {
		super();
		ss = new ServerSocket(port);
		ss.setSoTimeout(100);
		
		cl = new ArrayList<>();
		sts = new HashMap<>();
		
		close = false;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!close)
					try {
						Socket s = ss.accept();
						ConnectionEvent ev = new ConnectionEvent(s);
						for(ConnectionListener clis : cl)
							clis.onConnection(ev);
						if(!ev.isCancelled()) {
							Thread t = new Thread(new Runnable() {
								
								@Override
								public void run() {
									while(!s.isClosed())
									    try {
									    	BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
											String str = reader.readLine();
											if(str != null) {
												TransferEvent tev = new TransferEvent(s, str);
												for(TransferListener tl : DTServer.super.getListeners())
													tl.onReceive(tev);
											}
										} catch (IOException e) {}
								}
							});
							sts.put(s, t);
							t.start();
						}
					} catch (IOException e) {}
				try {
					ss.close();
				} catch (IOException e) {}
			}
		});
		t.start();
	}
	
	public void broadcast(String str) throws IOException {
		for(Socket s : sts.keySet())
			Utils.sendString(s, str);
	}
	
	public Socket[] getSockets() {
		return (Socket[]) sts.keySet().toArray();
	}
	
	public void addListener(ConnectionListener cl) {
		this.cl.add(cl);
	}
	
	public void removeListener(ConnectionListener cl) {
		this.cl.remove(cl);
	}
	
	public ArrayList<ConnectionListener> getConnectionListeners() {
		return this.cl;
	}

	public void stop() {
		this.close = true;
		for(Entry<Socket, Thread> st : sts.entrySet())
			try {
				st.getKey().close();
				st.getValue().interrupt();
			} catch (IOException e) {}
	}
	
	public boolean isAlive() {
		return t.isAlive();
	}

}
