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
			

			while (!s.isClosed()) {
				String line = new String();
				int bytes;
				String E = "E";
				String M = "M";
				do {
					bytes = in.read();
					line = line + (char) bytes;
				} while (bytes != '\n');
				if (line.length() == 6 && line.contains("Quit")) {
					System.out.println("User" + s.getPort() + " is disconnected.");
					s.close();
					h.getSocketList().remove(s);
					return;
				} else if (line.charAt(0) == E.charAt(0)) {
					OutputStream out = s.getOutputStream();
					out.write(line.getBytes());
					out.flush();
				} else if (line.charAt(0) == M.charAt(0)) {
					line = s.getPort() + ": " + line;
					for (Socket tmp : h.getSocketList()) {
						if (tmp != s) {
							
							OutputStream out = tmp.getOutputStream();
							out.write(line.getBytes());
							out.flush();
						}
					}

				}
				// Checks the line String
				// for (int i = 0; i < line.length(); i++) {
				// System.out.println(line.charAt(i));
				// }

				mail.setMessage(line);
				// System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("FEl i MailWriter run");
		}
	}

	public Socket getSocket() {
		return s;
	}
}
