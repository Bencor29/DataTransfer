package fr.bencor29.datatransfer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import fr.bencor29.datatransfer.DTMaster;
import fr.bencor29.datatransfer.events.ConnectionEvent;
import fr.bencor29.datatransfer.events.listeners.ConnectionListener;

public class DTServer extends DTMaster {
	
	private ServerSocket ss;
	private Thread t;
	private ArrayList<ConnectionListener> cl;
	private ArrayList<Client> sts;
	
	private boolean close;
	
	public DTServer(int port) throws IOException {
		super();
		ss = new ServerSocket(port);
		ss.setSoTimeout(100);
		
		cl = new ArrayList<>();
		sts = new ArrayList<>();
		
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
							Client c = new Client(s, DTServer.this);
							sts.add(c);
						}
					} catch (IOException e) {}
				try {
					ss.close();
				} catch (IOException e) {}
			}
		});
		t.start();
	}
	
	public void checkClient(Client c) {
		if(c.getSocket().isClosed() && sts.contains(c))
			sts.remove(c);
		else if(!c.getSocket().isClosed() && !sts.contains(c))
				sts.add(c);
	}
	
	public void broadcast(String str) throws IOException {
		for(Client c : sts)
			c.sendString(str);
	}
	
	public Socket[] getSockets() {
		ArrayList<Socket> sockets = new ArrayList<>();
		for(Client c : sts)
			sockets.add(c.getSocket());
		return (Socket[]) sockets.toArray();
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
		for(Client c : sts)
			c.close();
	}
	
	public boolean isAlive() {
		return t.isAlive();
	}

}
