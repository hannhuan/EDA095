package lab4;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class LinkQueue {
	private ArrayDeque <URL> queue;

	public LinkQueue(){
		queue = new ArrayDeque<URL>();
	}
	
	
	public synchronized void push(URL tmp) {
		// TODO Auto-generated method stub
		
		queue.push(tmp);
		notifyAll();
		
	}
	
	public boolean isEmpty (){
		if (queue.isEmpty()){
			return true;
		}
		return false;
	}
	
	public synchronized URL pop (){
		while (queue.isEmpty()){
			try{
				System.out.println("I'm waiting...");
				wait();
			} catch (Exception e){
				System.out.println("Fvck this shit!!");
			}
		}
		return queue.pop();
		
	}
	
	public ArrayDeque<URL> getList (){
		return queue;
	}



}
