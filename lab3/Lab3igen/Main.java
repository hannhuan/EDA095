package lab3;

import java.util.Scanner;

public class Main {

	public static void main (String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Skriv port");
		int port = scan.nextInt();
		scan.close();
		Handle h = new Handle();
		
		try {
			EchoServer2 server = new EchoServer2 (port, h);
			server.newSocket();
		} catch (Exception e){
			System.out.println("Jag orkar inte...........");
		}
	}
}
