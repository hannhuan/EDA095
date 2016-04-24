package lab4;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	private static int numberOfThreads = 10;

	public static void main(String[] args) {
		UrlStack stack = new UrlStack();

		String startingLink = "http://cs.lth.se/pierre_nugues/";
		URL startingUrl;
		try {
			startingUrl = new URL(startingLink);
		} catch (MalformedURLException e) {
			System.out.println("Starting link is incorrect");
			startingUrl = null;
		}

		stack.add(startingUrl);
		
		for (int i = 0; i < numberOfThreads; i++) {
			Thread t = new ThreadCrawler(stack);
			t.start();
		}

	}

}
