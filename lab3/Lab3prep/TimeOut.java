package lab3;

import java.net.Socket;

public class TimeOut extends Thread{
	private Socket socket;

	
	public TimeOut (Socket socket){
		this.socket = socket;
		
	}
	
	public void run(){
		//http://www.tutorialspoint.com/java/lang/thread_isinterrupted.htm
		
		while (!isInterrupted()){
			try {
				
			} catch (Exception e){
				
			}
		}
	}

}
