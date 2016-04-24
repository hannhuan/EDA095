package lab4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

	private static int numberOfThreads = 10;

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		UrlStack stack = new UrlStack();
		
		//String startingLink = "http://moodle.eit.se/";
		String startingLink = "http://cs.lth.se/pierre_nugues/";
		URL startingUrl;
		try {
			startingUrl = new URL(startingLink);
		} catch (MalformedURLException e) {
			System.out.println("Starting link is incorrect");
			startingUrl = null;
		}

		stack.add(startingUrl);
		
		for (int i = 0; i < 1; i++) {
			Thread t = new ThreadCrawler(stack);
			System.out.println("STARTING");
			threads.add(t);
			t.start();
		}
		
		//http://www.journaldev.com/1024/java-thread-join-example-with-explanation
		try {
			for(Thread t : threads){
				t.join();
			}
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		System.out.println("ENDED");
		stack.printStats();
	}
}
