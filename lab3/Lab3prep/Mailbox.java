package lab3;

public class Mailbox {
	private String message;
	
	public Mailbox(){
		message = "";
	}
	
	public synchronized void setMessage(String msg){
		while (message != ""){
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
		while (message == ""){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Fel i Mailbox, getMessage");
			}
		} 
		
		String tmp = message;
		message = "";
		notifyAll();
		return tmp;
	}

}
