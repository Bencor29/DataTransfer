package fr.bencor29.datatransfert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DataTransfert extends Thread {
	
	private Socket s;
	private ServerSocket ss;
	private TransfertType type;
	private ArrayList<TransfertListener> list;
	private DataServer server;
	
	public enum TransfertType {
		CLIENT, SERVER
	}
	
	public DataTransfert(InetAddress address, int port, TransfertType type) throws UnknownHostException, IOException {
		this.type = type;
		list = new ArrayList<TransfertListener>();
		if(type == TransfertType.CLIENT)
			s = new Socket(address.getHostAddress(), port);
		else {
			ss = new ServerSocket(port);
			server = new DataServer(ss, this);
		}
		start();
	}
	
	public void closeConnection() throws ReservedException, IOException {
		if(type != TransfertType.CLIENT) throw new ReservedException("This fonction is reserved to clients!");
		s.close();
	}
	
	public void closeConnections() throws ReservedException, IOException {
		if(type != TransfertType.SERVER) throw new ReservedException("This fonction is reserved to servers!");
		server.close();
	}
	
	public TransfertType getType() {
		return type;
	}
	
	public void addTransfertListener(TransfertListener listener) {
		list.add(listener);
	}
	
	public void sendString(String str) throws IOException {
		if(type == TransfertType.CLIENT)
			Utils.sendString(s, str);
		else
			server.sendMessage(str);
	}
	
	public void sendStringToSocket(Socket s, String str) throws ReservedException, NullPointerException, IOException {
		if(type != TransfertType.SERVER)
			throw new ReservedException("This fonction is reserved to the servers!");
		server.sendMessage(str, s);
	}
	
	protected ArrayList<TransfertListener> getListeners() {
		return list;
	}
	
	@Override
	public void run() {
		if(type == TransfertType.CLIENT) {
			while(!s.isClosed() && s.isConnected()) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
				    String str = reader.readLine();
				    if(str != null && list.size() != 0)
				    	for(TransfertListener l : list)
				    		l.receive(new ReceivedString(str, s));
				} catch (Exception e) {}
			}
		}
	}

}
