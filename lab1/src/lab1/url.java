package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.*;

public class url {
	private URL url;

	private static String link = "http://cs.lth.se/eda095/foerelaesningar/?no_cache=1";

	public static void main(String[] args) {
		try {
			URL url = new URL(link);
			String host = url.getHost();
			System.out.println("ost name is: " + host);
			System.out.println(url.getRef());

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			System.out.println("HERE WE GOOOOO");
			StringBuilder bob = new StringBuilder();
			ArrayList<String> urls = new ArrayList<String>();
			String line;
			// <a
			// href="http://fileadmin.cs.lth.se/cs/Education/EDA095/2014/kursprogram2014.pdf">
			String pdfs = "<a " + "href=";
			String pdf = "href=" + "\"" + "(.*?)" + "\"";
			String pdfe = "." + "pdf" + "\"" + ">";
			while ((line = reader.readLine()) != null) {
				bob.append(line);
			}
			line = bob.toString();
			Pattern pat = Pattern.compile(pdf);
			// pdfs + "(.*?)" + pdfe
			Matcher mat = pat.matcher(line);
			while (mat.find()) {
				if (mat.group(1).endsWith(".pdf")) {
					//System.out.println(mat.group(1));
					urls.add(mat.group(1));
				}

			}
			
			for (String s : urls){
				URL dlUrl = new URL(s);
				System.out.println(dlUrl);
				Path path = Paths.get(dlUrl.getPath());
				String name = path.getFileName().toString();
				System.out.println(name);
				InputStream in = dlUrl.openStream();
				FileOutputStream os = new FileOutputStream (new File(name));
				byte[] buffer = new byte[256];
				int cnt;
				while ((cnt = in.read(buffer, 0 , 256))!=-1){
					os.write(buffer, 0,	cnt);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
