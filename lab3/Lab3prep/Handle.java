package lab3;

import java.awt.List;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Handle {
	private ArrayList<Socket> sockets;
	
	
	public Handle(){
		sockets = new ArrayList<Socket>();
	}
	
	
	public synchronized void buffer (byte[] b, int len, Socket s){
		if (b[0] == 'Q'){
			try {
				s.close();
				sockets.remove(s);
			} catch (Exception e){
				System.out.println("Fel i Handle, ta bort s från sockets");
			}
		} else {
			try{
				OutputStream os = s.getOutputStream();
			} catch (Exception e){
				System.out.println("Fel i Handle, buffer");
			}
		}
	}
	
	
	public synchronized void newSocket (Socket s){
		if (!sockets.contains(s)){
			sockets.add(s);
		}
	}
}
