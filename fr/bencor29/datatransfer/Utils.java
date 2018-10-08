package fr.bencor29.datatransfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Utils {
	
	public static void sendString(Socket s, String str) throws IOException {
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.println(str);
		pw.flush();
	}
	
	public static String getAddress(Socket socket) {
			return socket.getInetAddress().toString().split("/")[1];
	}


}
