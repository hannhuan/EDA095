package lab5b;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import sun.awt.dnd.SunDragSourceContextPeer;

public class TimeServerUDP2 {

	private DatagramSocket ds;

	public TimeServerUDP2(int port) {
		try {
			DatagramSocket ds = new DatagramSocket(port);
			this.ds = ds;
			System.out.println("Starting server on hardcoded port 30000");
			start();

		} catch (SocketException e) {
			System.out.println("DatagramSocket creation failed");
			// e.printStackTrace();
		}
	}

	public void start() {
		byte[] bytes = new byte[1024];
		DateFormat df = null;
		String command = null;

		while (true) {
			DatagramPacket receive = new DatagramPacket(bytes, bytes.length);
			try {
				ds.receive(receive);
				InetAddress address = receive.getAddress();
				int port = receive.getPort();
				
				System.out.println("Receiving command from (address/port): " + address.toString() + " : " + port);
				System.out.println("Fucking länd: " + receive.getLength());
				//command = new String(receive.getData(), 0, receive.getData().length, "UTF-8");
				command = new String(receive.getData(), 0, receive.getLength(), "UTF-8");
				System.out.println(command);
				System.out.println(command.length());

				switch (command) {
				case "date":
					df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
					break;
				case "time":
					df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.ENGLISH);
					break;
				default:
					System.out.println("Unrecognized command");
					
					DatagramPacket send = new DatagramPacket (command.getBytes(), command.getBytes().length, address, port);
					ds.send(send);
					
					df = null;
					break;
				}
				if(df != null){
					String response = df.format(new Date());
					DatagramPacket send = new DatagramPacket (response.getBytes(), response.getBytes().length, address, port);
					ds.send(send);
					System.out.println("Sent response to: " + address.toString());
					System.out.println("Message is: " + response);
				}
			} catch (IOException e) {
				System.out.println("Failed Receiving datagram");
			}
		}
	}

	public static void main(String[] args) {
		TimeServerUDP2 server = new TimeServerUDP2(30000);
	}
}
