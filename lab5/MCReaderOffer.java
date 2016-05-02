package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCReaderOffer {

	public static void main(String args[]) {
		if (args.length == 2) {
			int port = Integer.parseInt(args[0]);
			byte[] command = args[1].getBytes(); 
			try {
				MulticastSocket ms = new MulticastSocket(4099);
				InetAddress ia = InetAddress.getByName("experiment.mcast.net");
				ms.joinGroup(ia);
				while (true) {
					byte[] buf = new byte[65536];
					DatagramPacket dp = new DatagramPacket(buf, buf.length);
					ms.receive(dp);
					
					
					String s = new String(dp.getData(), 0, dp.getLength());
					System.out.println("Received: " + s);
					

					InetAddress host =  InetAddress.getLocalHost();
					byte[] hostInfo = host.toString().getBytes();
					DatagramPacket hostInfoP = new DatagramPacket (hostInfo, hostInfo.length);
					ms.send(hostInfoP);
				}
			} catch (IOException e) {
				System.out.println("Exception:" + e);
			}
		} else {
			System.out.println("Check args");
		}

	}

}
