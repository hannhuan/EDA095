package lab3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWriter extends Thread {

	private Socket client;

	public ClientWriter(Socket s) {
		this.client = s;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()), true);
			while (!client.isClosed()) {
				String line = in.readLine();
				out.println(line);
			}
			client.close();
		} catch (Exception e) {
			System.out.println("Kan inte skriva");
		}
	}
}
