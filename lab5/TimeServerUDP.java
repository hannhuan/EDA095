package lab5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServerUDP extends Thread{
	DatagramSocket ds;
	
	public TimeServerUDP(DatagramSocket ds){
		this.ds = ds;
	}
	
	public void run(){
		String command = null;
		DateFormat df = null;
		byte[] bytes = new byte[1024];
		DatagramPacket packet = new DatagramPacket (bytes, bytes.length);
		
		while (true){
			try{
				ds.receive(packet);
				command = new String(packet.getData(), 0, 4);
				InetAddress adress = packet.getAddress();
				int port = packet.getPort();
				
				switch (command) {
				case "date":
					df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
					break;
				case "time":
					df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.ENGLISH);
					break;
				default:
					System.out.println("Check your command.");
					return;
				}
				String response = df.format(new Date());
				
				DatagramPacket send = new DatagramPacket (response.getBytes(), response.getBytes().length, adress, port);
				ds.send(send);

				
			} catch (Exception e){
				
			}
		}
		
	}
	
	

}
