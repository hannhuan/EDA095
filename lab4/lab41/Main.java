package lab4;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static void main (String[] args){
		String testLink ="http://cs.lth.se/eda095/";
		Scanner scan = new Scanner(System.in);
		System.out.println("How many threads do you want?");
		int threads = scan.nextInt();
		scan.close();
		
		ArrayList<Thread> thread = new ArrayList<Thread>();
		
		LinkQueue queue = new LinkQueue ();
		Set<String> pastLink = Collections.synchronizedSet(new HashSet<String>());
		Set<String> addresses = Collections.synchronizedSet(new HashSet<String>());
		Set<String> links = Collections.synchronizedSet(new HashSet<String>());
		
		try {
			URL test = new URL ("http://cs.lth.se/eda095/");
			
			queue.push(test);
			
			for (int i = 0; i < threads; i++){
				Thread Cthread = new CrawlerThread (queue, links, pastLink, addresses);
				thread.add(Cthread);
				Cthread.start();
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Fel i MainnnnnnnThread......");
		}
		
		for (Thread t : thread){
			try{
				t.join();
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Why don't u die...");
			}
		}
		System.out.println("Links: " + "\n" );
		for (String s : links){
			System.out.println(s);
			System.out.println("\n");
		}
		
		System.out.println("Adresses: " + "\n");
		for (String s: addresses){
			System.out.println(s);
		}
	}

}
