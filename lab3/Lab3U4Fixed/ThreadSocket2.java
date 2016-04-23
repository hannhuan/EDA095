package lab3;

import java.io.InputStream;
import java.net.Socket;

public class ThreadSocket2 extends Thread{
	private Socket s;
	private Handle handle;
	
	public ThreadSocket2 (Socket s, Handle handle) {
		this.s = s;
		this.handle = handle;
	}
	
	public void run (){
		InputStream is = null;
		
		try{
			is = s.getInputStream();
		} catch (Exception e){
			System.out.println("Fel i ThreadSocket2");
		}
		
		byte [] buffer = new byte [256];
		
//		while (true){
//			try {
//				handle.buffer(buffer, is.read(buffer), s);
//			} catch (Exception e){
//				System.out.println("Connection closed");
//				return;
//			}
//		}
	}

}
