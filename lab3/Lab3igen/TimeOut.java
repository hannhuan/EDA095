package lab3;

import java.net.Socket;

public class TimeOut extends Thread {
	private Socket socket;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			TimeOut test = new TimeOut();
			test.start();
		}

	}

	public TimeOut() {

	}

	// public TimeOut (Socket socket){
	// this.socket = socket;
	//
	// }

	public void run() {
		// http://www.tutorialspoint.com/java/lang/thread_isinterrupted.htm

		// while (!isInterrupted()){
		try {
			for (int i = 0; i < 5; i++) {
				sleep((long)(Math.random()*100));
				System.out.println(getName());
			}

		} catch (Exception e) {
			System.out.println("nej");
			// }
		}
	}

}
