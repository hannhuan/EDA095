package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP2 {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		System.out.println("Starting");
		boolean shutDown = false;

		while (!shutDown) {
			Socket client = ss.accept();
			ThreadSocket tS = new ThreadSocket(client);
			tS.start();
		}
		ss.close();
		System.out.println("end server");
	}
}