package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ClientReader extends Thread {

	private Socket client;

	public ClientReader(Socket s) {
		this.client = s;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while (!client.isClosed()) {
				String line = in.readLine();
				System.out.println(line);
			}
			client.close();
		} catch (Exception e) {
			System.out.println("Kan inte läsa");
		}
	}
}
