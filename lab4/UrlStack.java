package lab4;

import java.net.URL;
import java.util.ArrayList;

public class UrlStack {

	private ArrayList<URL> urls;
	private ArrayList<URL> mails;
	private ArrayList<URL> visitedUrls;

	public UrlStack() {
		this.urls = new ArrayList<URL>();
		this.mails = new ArrayList<URL>();
		this.visitedUrls = new ArrayList<URL>();
	}

	public synchronized void add(URL link) {
		if (!visitedUrls.contains(link)) {
			urls.add(link);
			System.out.println("ADDED: " + link.toString());
		} else {
			System.out.println("link already visited: " + link.toString());
		}
		notifyAll(); //wakes waiting threads. 
	}
	
	public synchronized URL get(){
		while (urls.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		URL tmp =  urls.get(urls.size()-1);
		urls.remove(urls.size()-1);
		visitedUrls.add(tmp);		
		return tmp;
	}
	
	public synchronized boolean isEmpty(){
		return urls.isEmpty();
	}

}
