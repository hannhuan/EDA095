package lab3;

import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Handle {
	private ArrayList<Socket> sockets;

	public Handle() {
		sockets = new ArrayList<Socket>();
	}

	public synchronized void buffer(byte[] b, int len, Socket s) {

		String value = new String(b);
		System.out.println(value);
		if (b[0] == 'Q') {
			try {
				s.close();
				sockets.remove(s);
				return;
			} catch (Exception e) {
				System.out.println("Fel i Handle, ta bort s från sockets");
			}
		} else if (b[0] == 'M') {
			try {
				OutputStream os = s.getOutputStream();
			} catch (IOException e) {
				System.out.println("Fel M");
			}

		} else if (b[0] == 'E') {

		}
	}

	public synchronized void newSocket(Socket s) {
		if (!sockets.contains(s)) {
			sockets.add(s);
		}
	}
	
	public synchronized ArrayList<Socket> getSocketList (){
		return sockets;
	}
}
