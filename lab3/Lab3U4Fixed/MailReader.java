package lab3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MailReader extends Thread {

	private Mailbox mail;
	private Handle h;

	public MailReader(Mailbox box, Handle h) {
		this.h = h;
		this.mail = box;
	}

	public void run() {
		System.out.println("READER STARTED");
		while (true) {
			ArrayList<Socket> socketList = h.getSocketList();
			String msg = mail.getMessage();
//			for (Socket s : socketList) {
//				try {
//					OutputStream os = s.getOutputStream();
//					//os.write(msg.getBytes());
//					System.out.println(msg);
//					//os.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//					return;
//				}
//			}
		}
	}
}
