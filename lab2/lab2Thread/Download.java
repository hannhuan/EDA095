package lab21;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Download {
	private Map<String, URL> map;
	private Iterator it;
	private URL link;

	public Download(String link) {
		HandleLink handle = new HandleLink(link);
		map = handle.handle();
		
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		it = map.entrySet().iterator();
	}
	
	public URL link (){
		return link;
	}

	public void downloadOne() {
		RunnerThread runner;
		Iterator entries = map.entrySet().iterator();

		for (int i = 0; i < 10; i++) {

			//Map.Entry me = (Entry) entries.next();
			runner = new RunnerThread(this);
			runner.start();

		}
	}

	public void downloadMany() {
		Runner runner;
		Iterator entries = map.entrySet().iterator();
//		ExecutorService es = Executors.newFixedThreadPool(5);
		
		for (int i = 0; i < 10; i++){
			runner = new Runner (this);
			Thread thread = new Thread (runner);
			thread.start();
			
		}
//		while (it.hasNext()) {
//			Map.Entry me = (Entry) it.next();
//			runner = new Runner((URL) me.getValue(), (String) me.getKey());
//			Thread thread = new Thread(runner);
//			thread.start();
//
//		}

	}

	public synchronized Map.Entry entry() {
		Map.Entry entry;
		try {
			entry = (Entry) it.next();
		} catch (Exception e) {
			return null;
		}
		return entry;

	}

	private void executor() {
		ExecutorService es = Executors.newFixedThreadPool(5);
		while (it.hasNext()) {
			Map.Entry me = entry();
			es.submit(new RunnerThread(this));
		}
		es.shutdown();

	}

}

class HandleLink {
	private URL url;
	private String link;

	public HandleLink(String link) {
		this.link = link;
		try {
			url = new URL(link);
		} catch (Exception e) {
			System.out.println("Fel i Link");
		}
	}

	public Map<String, URL> handle() {
		BufferedReader br = null;
		Map<String, URL> map = new HashMap<String, URL>();

		try {
			InputStream is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));

			Pattern pat = Pattern.compile("href=" + "\"" + "(.*?)" + "\"");
			String line;
			while ((line = br.readLine()) != null) {

				Matcher mat = pat.matcher(line);
				URL url;
				while (mat.find()) {
					// System.out.println("here" );
					StringBuilder build = new StringBuilder();
					if (mat.group(1).contains(".pdf")) {
						if (!mat.group(1).startsWith("http")) {
							URL urltmp = new URL(link);
							build.append("http://");
							build.append(urltmp.getHost());
							build.append("/");
						}

						try {

							build.append(mat.group(1));
							url = new URL(build.toString());

							map.put(name(url.toString()), url);
						} catch (MalformedURLException e) {
							e.printStackTrace();
							System.out.println("fel med put");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fel i handle");
		}
		return map;
	}

	public String name(String url) {
		URL dlUrl;
		String name = null;
		try {
			dlUrl = new URL(url);
			Path path = Paths.get(dlUrl.getPath());
			name = path.getFileName().toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("fel i name");
		}
		// TODO Auto-generated method stub
		System.out.println(name);
		return name;
	}
}
