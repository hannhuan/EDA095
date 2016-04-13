package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.java_cup.internal.runtime.Symbol;

public class EchoTCP1 {
		
		
		
		public static void main(String[] args) throws IOException {
				
				ServerSocket ss = new ServerSocket(30000);
				System.out.println("Starting");
				boolean shutDown = false;
				
				while(!shutDown) {
					
					Socket client = ss.accept();
					System.out.println("Connection! With user on: ");
					System.out.println(client.getInetAddress().toString());
					System.out.println(client.getPort());
					
					InputStream in = client.getInputStream();
					OutputStream out = client.getOutputStream();
					
					
					
					while(!client.isClosed()) {
						String line = new String();
						int bytes;	
						
//						while(bytes != '\n'){
//							line = line + (char)bytes;
//							//System.out.println(bytes);
//							System.out.println((char) bytes);	
//						}
						
						do{
							bytes = in.read();
							line = line + (char) bytes;
						} while(bytes != '\n');
						
						System.out.println(line);
						
						if(line.contains("exit")){
							client.close();
							shutDown = true;
							System.out.println("end client");
						}
					}
				}
				System.out.println("end server");
				ss.close();
		}
}
