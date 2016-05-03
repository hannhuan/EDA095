package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MCReaderOffer {

	public static void main(String args[]) {
		if (args.length == 2) {
			int port = Integer.parseInt(args[0]);
			byte[] command = args[1].getBytes(); 
			try {
				MulticastSocket ms = new MulticastSocket(4099);
				InetAddress ia = InetAddress.getByName("experiment.mcast.net");
				ms.joinGroup(ia);
				
				OfferThread thread = new OfferThread();
				thread.start();
				
				while (true) {
					byte[] buf = new byte[65536];
					DatagramPacket dp = new DatagramPacket(buf, buf.length);
					ms.receive(dp);
					
					
					
					String s = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
					System.out.println("Received: " + s);
					
					DateFormat df = null;
					
					switch (s){
					case "Address":
						InetAddress host =  InetAddress.getLocalHost();
						byte[] hostInfo = host.toString().getBytes();
						DatagramPacket hostInfoP = new DatagramPacket (hostInfo, hostInfo.length, dp.getAddress(), dp.getPort());
						ms.send(hostInfoP);
					case "date":
						df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
						break;
					case "time":
						df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.ENGLISH);
						break;
					default:
						System.out.println("We don't understand your command.");
						return;	
					}
					
					if (df != null){
					String time = df.format(new Date());
					ms.send(new DatagramPacket(time.getBytes(), time.getBytes().length, dp.getAddress(), dp.getPort()));
					
					}
					
				}
			} catch (IOException e) {
				System.out.println("Exception:" + e);
			}
		} else {
			System.out.println("Check args");
		}

	}

}
