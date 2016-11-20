package fr.bencor29.datatransfert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DataServer extends Thread {
	
	private ArrayList<Socket> s;
	private ServerSocket ss;
	private DataTransfert instance;
	
	protected DataServer(ServerSocket srv, DataTransfert inst) {
		ss = srv;
		instance = inst;
		s = new ArrayList<Socket>();
		start();
	}
	
	@Override
	public void run() {
		while(!ss.isClosed()) {
			try {
				final Socket s = ss.accept();
				ConnectionEvent e = new ConnectionEvent(s);
				for(ConnectionEventListener cel : ConnectionEventListener.getListeners())
					cel.onConnection(e);
				if(e.isAccepted()) {
					this.s.add(s);
					Thread t = new Thread() {
						@Override
						public void run() {
							while(s.isConnected()) {
								try {
									BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
								    String str = reader.readLine();
								    if(str != null && instance.getListeners().size() != 0)
								    	for(TransfertListener l : instance.getListeners())
								    		l.receive(new ReceivedString(str, s));
								} catch (Exception e) {}
							}
						}
					}; t.start();
				}
			} catch (Exception e) {}
		}
	}
	
	protected void close() throws IOException {
		for(Socket s : s) {
			s.close();
		}
		s.clear();
		ss.close();
	}
	
	protected void sendMessage(String str) throws IOException {
		for(Socket s : s)
			Utils.sendString(s, str);
	}
	
	protected void sendMessage(String str, Socket s) throws NullPointerException, IOException {
		if(!this.s.contains(s)) throw new NullPointerException("Can't find the socket!");
		Utils.sendString(s, str);
	}

}
