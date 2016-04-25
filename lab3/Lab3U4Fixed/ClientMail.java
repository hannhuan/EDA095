package lab3;

import java.io.IOException;
import java.net.Socket;

public class ClientMail {

	public ClientMail(String address, int port) {
		try {
			System.out.println("Connecting");
			Socket client = new Socket(address, port);
			Thread reader = new ClientReader(client);
			Thread writer = new ClientWriter(client);
			reader.start();
			writer.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		if (args.length != 2) {
//			System.out.println("In console: java ChatClient <Address> <Port>");
//		}
//		String machine = args[0];
//		int port = Integer.parseInt(args[1]);
//		new ClientMail(machine, port);
		new ClientMail("127.0.0.1",30000);
	}

}
