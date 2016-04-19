package lab3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MailWriter extends Thread {

	private Mailbox mail;
	private Handle h;
	private Socket s;

	public MailWriter(Mailbox box, Handle h, Socket s) {
		this.mail = box;
		this.s = s;
		this.h = h;
	}

	public void run() {
		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();

			while (!s.isClosed()) {
				String line = new String();
				int bytes;

				do {
					bytes = in.read();
					line = line + (char) bytes;

				} while (bytes != '\n');
				mail.setMessage(line);
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("FEl i MailWriter run");
		}
	}
}
