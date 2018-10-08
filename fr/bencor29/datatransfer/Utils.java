package fr.bencor29.datatransfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Utils {
	
	/**
	 * Send data as a String to a socket.
	 * @param s the socket you want to send data
	 * @param str the data to send
	 * @throws IOException
	 */
	public static void sendString(Socket s, String str) throws IOException {
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.println(str);
		pw.flush();
	}
	
	/**
	 * Get a socket IP address
	 * @param socket The socket
	 * @return the IP address as a String
	 */
	public static String getAddress(Socket socket) {
			return socket.getInetAddress().toString().split("/")[1];
	}


}
