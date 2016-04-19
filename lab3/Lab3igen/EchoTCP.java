package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This works.
 * @author Administrator
 *
 */

public class EchoTCP {
	private static Socket s;

	public static void main(String[] args) {
		OutputStream os = null;
		InputStream is = null;

		try {
			ServerSocket ss = new ServerSocket(30005);
			s = ss.accept();
			//s = new Socket ("lab3", 30005);
			os = s.getOutputStream();
			is = s.getInputStream();
			
			//https://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html
			
			ThreadSocket ts = new ThreadSocket (s);
			ts.start();
			
			
//			while (true) {
//				String line = new String();
//				int bytes;
//
//				try {
//					do {
//						bytes = is.read();
//						line = line + (char) bytes;
//					} while (bytes != '\n');
//				} catch (Exception e) {
//					System.out.println("Fel i EchoTCP while do");
//				}
//				System.out.println(line);
//				if (line.contains("exit")) {
//					s.close();
//					System.out.println("end");
//					break;
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Fel i EchoTCP ServerSocket");
		}

	}

}
