package lab4;

import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class ThreadCrawler extends Thread {
	
	private UrlStack stack;
	
	public ThreadCrawler(UrlStack stack) {
		this.stack = stack;
	}

	public void run() {
		UrlAnalyzer analyzer = new UrlAnalyzer(stack);
		
		//Kod från HTML förel
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();

		
		
		while(!stack.isEmpty()){
			URL url = stack.get();
			
		}
	}
}
