package lab4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class ThreadCrawler extends Thread {
	
	private UrlStack stack;
	private final int MAX_LINKS = 3;
	
	public ThreadCrawler(UrlStack stack) {
		this.stack = stack;
	}

	public void run() {
		UrlAnalyzer analyzer = new UrlAnalyzer(stack);
		
		//Kod från HTML förel
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();
		
		
		
		while(!stack.isEmpty() && stack.size() < MAX_LINKS){
			URL url = stack.get();
			System.out.println("ANALYZING: " + url.toString());
			analyzer.makeBase(url);
			try{
				InputStream in;
				in = new BufferedInputStream(url.openStream());
				InputStreamReader reader = new InputStreamReader(in);
				parser.parse(reader, analyzer, true);
			} catch (IOException e){
				System.out.println("ERROR gick inte riktigt");
			}
			
		}
		stack.printStats();
	}
}
