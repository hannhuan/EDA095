package lab4;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import javax.swing.text.html.HTMLEditorKit;

public class CrawlerThread extends Thread {
	private LinkQueue queue;
	private Set<String> links;
	private Set<String> pastLinks;
	private Set<String> adresses;
	
	public CrawlerThread (LinkQueue queue, Set<String> links, Set<String> pastLinks, Set<String> adresses){
		this.queue = queue;
		this.links = links;
		this.pastLinks = pastLinks;
		this.adresses = adresses;
		
	}
	
	public void run (){
		ParserGetter sth = new ParserGetter();
		HTMLEditorKit.Parser pars = sth.getParser();
		
		Crawler callback = new Crawler (queue, links, pastLinks, adresses);
		while (!queue.isEmpty()){
			try{
				URL url = queue.pop();
				callback.setURL(url);
				
				URLConnection connection = url.openConnection();
				String type = connection.getContentType();
				
				
				if (type != null){
					InputStream is = new BufferedInputStream (connection.getInputStream());
					InputStreamReader isr = new InputStreamReader (is);
					pars.parse(isr, callback, true);
				}
			} catch (Exception e){
				System.out.println("Fel i thread run");
			}
		}
	}

}
