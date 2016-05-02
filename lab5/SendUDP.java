package lab5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUDP {

	public static void main(String[] args) {
		try {
			if (args.length == 3) {
				InetAddress adress = InetAddress.getByName(args[0]);
				int port = Integer.parseInt(args[1]);
				byte[] command = args[2].getBytes();
				byte[] buffer = new byte[1024];
				
				DatagramSocket ds = new DatagramSocket (30000);
				DatagramPacket send = new DatagramPacket(command, command.length, adress, port );
				DatagramPacket receive = new DatagramPacket (buffer, buffer.length);
				
				ds.send(send);
				ds.receive(receive);
				
				System.out.println(receive.getData().toString());

			} else {
				System.out.println("Check your three arguments: machine, port and command");
			}
		} catch (Exception e) {

		}
	}

}
