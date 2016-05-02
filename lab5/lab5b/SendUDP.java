package lab5b;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

import sun.nio.cs.StandardCharsets;

public class SendUDP {

	public static void main(String[] args) {
		System.out.println("Starting");
		try {
			if (args.length == 3) {
				InetAddress adress = InetAddress.getByName(args[0]);
				int port = Integer.parseInt(args[1]);
				byte[] command = args[2].getBytes();
				byte[] buffer = new byte[1024];
				
				System.out.println(command.toString());
				System.out.println(new String(command, "UTF-8"));
				System.out.println("command länd " + new String(command, "UTF-8").length() );
				
				DatagramSocket ds = new DatagramSocket ();
				DatagramPacket send = new DatagramPacket(command, command.length, adress, port);
				ds.send(send);
				System.out.println("Sent packet " + send.getLength());
				DatagramPacket receive = new DatagramPacket (buffer, buffer.length);
				ds.receive(receive);
				System.out.println("Received packet");
				
				//https://docs.oracle.com/javase/6/docs/api/java/lang/String.html Alltså med konstruktor 5
				System.out.println(new String(receive.getData(), 0, receive.getLength(), "UTF-8"));

			} else {
				System.out.println("Check your three arguments: machine, port and command");
			}
		} catch (Exception e) {
			System.out.println("GICK TUSAN INTE");
		}
	}

}
