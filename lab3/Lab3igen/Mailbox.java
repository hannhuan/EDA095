package lab3;

public class Mailbox {
	private String message;
	
	public Mailbox(){
		message = null;
	}
	
	public synchronized void setMessage(String msg){
		while (message != null){
			try{
				this.wait();
			} catch (Exception e){
				System.out.println("Fel i Mailbox, setMessage, wait");
			}
		}
		
		message = msg;
		notifyAll();
	}
	
	public synchronized String getMessage(){
		while (message == null){
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("Fel i Mailbox, getMessage");
			}
		} 
		
		String tmp = message;
		message = null;
//		System.out.println(tmp);
		notifyAll();
		return tmp;
	}

}
