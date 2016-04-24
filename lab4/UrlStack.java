package lab4;

import java.net.URL;
import java.util.ArrayList;

public class UrlStack {

	private ArrayList<URL> urls;
	private ArrayList<String> mails;
	private ArrayList<URL> visitedUrls;
	private int linksFound;
	private int mailsFound;

	public UrlStack() {
		this.urls = new ArrayList<URL>();
		this.mails = new ArrayList<String>();
		this.visitedUrls = new ArrayList<URL>();
		linksFound = 0;
		mailsFound = 0;
	}

	public synchronized void add(URL link) {
		if (!visitedUrls.contains(link)) {
			urls.add(link);
			System.out.println("ADDED: " + link.toString());
			linksFound += 1;
		} else {
			System.out.println("link already visited: " + link.toString());
		}
		notifyAll(); //wakes waiting threads. 
	}
	
	public void addMail(String addr){
		mails.add(addr);
		System.out.println("MAIL ADDED: " + addr);
		mailsFound += 1;
	}
	
	public synchronized URL get(){
		while (urls.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		URL tmp =  urls.get(0);
		urls.remove(0);
		visitedUrls.add(tmp);		
		return tmp;
	}
	
	public synchronized boolean isEmpty(){
		return urls.isEmpty();
	}
	
	public synchronized int size(){
		return visitedUrls.size();
	}
	
	public synchronized int sizeOfFindings(){
		return linksFound + mailsFound;
	}
	
	public void printStats(){
		System.out.println("NUMBER OF LINKS VISITED: " + visitedUrls.size());
		System.out.println("NUMBER OF LINKS FOUND: " + linksFound);
		System.out.println("NUMBER OF MAIL-LINKS FOUND: " + mailsFound);
	}

}
