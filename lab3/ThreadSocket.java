package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadSocket extends Thread {

	private Socket socket;

	public ThreadSocket(Socket s) {
		this.socket = s;
		System.out.println("Connection! With user on: ");
		System.out.println(socket.getInetAddress().toString());
		System.out.println(socket.getPort());
	}

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			while (!socket.isClosed()) {
				String line = new String();
				int bytes;

				do {
					bytes = in.read();
					line = line + (char) bytes;
				} while (bytes != '\n');

				System.out.println(line);

				if (line.contains("exit")) {
					System.out.println("end client on: " + socket.getInetAddress().toString());
					socket.close();
					return;
				}
			}
		} catch (IOException e) {
			System.out.println("Error connecting user");
			return;
		}
	}
}