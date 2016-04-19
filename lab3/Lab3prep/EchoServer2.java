package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
	private ServerSocket server;
	private Handle handle;
	
	public EchoServer2 (int port, Handle handle){
		try {
			handle = new Handle();
			server = new ServerSocket (port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newSocket (){
		Socket s;
		try {
			while ((s = server.accept()) != null){
				handle.newSocket(s);
				ThreadSocket2 newSocket = new ThreadSocket2(s, handle);
				newSocket.start();
			}
		} catch (Exception e){
			System.out.println("Fel i EchoServer2 serverUp");
		}
	}

}
