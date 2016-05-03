package lab5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendUDP2 {
	private static byte[] command;
	private static int port;
	private static byte[] buffer;

	public static void main(String[] args) {
		try {
			if (args.length == 2) {
				port = Integer.parseInt(args[0]);
				command = args[1].getBytes();
				buffer = new byte[65536];

				DatagramPacket packetService = discoverServer();

				if (packetService != null) {
					DatagramSocket socket = new DatagramSocket();
					socket.send(new DatagramPacket(command, command.length, packetService.getAddress(),
							packetService.getPort()));

					socket.receive(new DatagramPacket(buffer, buffer.length));
				} else {

					System.out.println("Check your three arguments: machine, port and command");

				}
			}
		} catch (Exception e) {

		}
	}

	public static DatagramPacket discoverServer() {
		try {
			DatagramSocket socket = new DatagramSocket();

			InetAddress ia = InetAddress.getByName("experiment.mcast.net");

			socket.send(new DatagramPacket(command, command.length, ia, port));

			DatagramPacket packetReceive = new DatagramPacket(buffer, buffer.length);
			socket.receive(packetReceive);

			return packetReceive;

		} catch (Exception e) {

		}
		return null;
	}

}
