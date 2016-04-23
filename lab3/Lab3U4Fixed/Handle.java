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
	


	public synchronized void newSocket(Socket s) {
		if (!sockets.contains(s)) {
			sockets.add(s);
			System.out.println("Connected to " + s.getPort());
		}
	}
	
	public synchronized ArrayList<Socket> getSocketList (){
		return sockets;
	}
}
