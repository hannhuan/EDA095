package lab3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadSocket extends Thread {
	private Socket socket;
	private InputStream is;

	public ThreadSocket(Socket socket) {
		this.socket = socket;

		try {
			InputStream is = socket.getInputStream();
		} catch (Exception e) {
			System.out.println("Fel i ThreadSocket");
		}
	}

	public void run() {
//		try {
//			int i = is.read();
//			while (i != -1) {
//				System.out.println(i);
//				i = is.read();
//			}
		
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
			
			BufferedReader bf = new BufferedReader (new InputStreamReader (System.in));
			while (true){
				try{
					byte [] buff = bf.readLine().getBytes();
					out.write(buff);
					out.flush();
					System.out.println("sucess!");
				} catch (Exception e){
					System.out.println("Fel i EchoTCP main while.");
				}
			}

		} catch (Exception e) {
			System.out.println("Fel i ThreadSocket");
		}
	}

}
