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
	private int count;

	public Download(String link) {
		count = 0;
		HandleLink handle = new HandleLink(link);
		map = handle.handle();
		it = map.entrySet().iterator();
	}

	public void downloadOne() {
		RunnerThread runner;
		Iterator entries = map.entrySet().iterator();

		while (entries.hasNext()) {

			Map.Entry entry = (Map.Entry) entries.next();
			runner = new RunnerThread((URL) entry.getValue(), (String) entry.getKey());
			runner.start();
			count++;
			if (!runner.isAlive()) {
				count--;
			}
		}
	}

	public void downloadMany() {
		Runner runner;

	}

	private synchronized Map.Entry entry() {
		Map.Entry entry = (Entry) it.next();
		return entry;

	}

	private void executor() {
		ExecutorService es = Executors.newFixedThreadPool(5);
		while (it.hasNext()) {
			Map.Entry me = entry();
			es.execute(new RunnerThread((URL) me.getValue(), (String) me.getKey()));
		}
		es.shutdown();

	}

}

class HandleLink {
	private URL url;

	public HandleLink(String link) {
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
					if (mat.group(1).contains(".pdf")) {
						try {
							url = new URL(mat.group(1));
							map.put(name(url.toString()), url);
						} catch (MalformedURLException e) {
							// e.printStackTrace();
							// System.out.println("fel med put");
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
		return name;
	}
}
