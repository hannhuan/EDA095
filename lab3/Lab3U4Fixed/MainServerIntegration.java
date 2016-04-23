package lab3;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServerIntegration {
	
	public static void main (String[] args){
		ServerSocket serverSocket;
		Mailbox mailbox = new Mailbox ();
		Handle handle = new Handle();
		
		MailReader mr = new MailReader (mailbox, handle);
		mr.start();
		
		//ServerIntegrationEcho server = new ServerIntegrationEcho(30000, handle, mailbox);
		
		try {
			serverSocket = new ServerSocket(30000);
			while(true){
				Socket s = serverSocket.accept();
				handle.newSocket(s);
				System.out.println("Connecting...");
				MailWriter newSocket = new MailWriter(mailbox, handle, s);
				System.out.println("MailWriter created.");
				newSocket.start();
			}
		} catch (Exception e) {
			System.out.println("Fel i main");
			// TODO: handle exception
		}
	}

}
