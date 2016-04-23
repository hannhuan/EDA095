package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIntegrationEcho {
	private ServerSocket server;
	private Handle handle;
	private Mailbox box;

	public ServerIntegrationEcho(int port, Handle handle, Mailbox box) {
		try {
			this.handle = handle;
			server = new ServerSocket(port);
			this.box = box;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newSocket() {
		Socket s;
		try {
			if((s = server.accept()) != null) {
				handle.newSocket(s);
				System.out.println("Connecting...");
				MailWriter newSocket = new MailWriter(box, handle, s);
				System.out.println("MailWriter created.");
				newSocket.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fel i EchoServer2 serverUp");
		}
	}
}
