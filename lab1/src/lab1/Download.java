package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.*;

public class Download {

	public static void main(String[] args) {
		// String url = "http://cs229.stanford.edu/materials.html";
		String url = "http://cs.lth.se/eda095/foerelaesningar";

		Download test = new Download(url);
		test.download();
		System.out.println("Complete!");
	}

	private URL url;

	public Download(String link) {
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void download() {
		BufferedReader reader = null;
		ArrayList<String> urls = new ArrayList<String>();
		try {
			InputStream is = url.openStream();
			reader = new BufferedReader(new InputStreamReader(is));

			Pattern pat = Pattern.compile("href=" + "\"" + "(.*?)" + "\"");
			String line;
			while ((line = reader.readLine()) != null) {
				Matcher mat = pat.matcher(line);
				while (mat.find()) {
					if (mat.group(1).endsWith(".pdf")) {
						urls.add(mat.group(1));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String s : urls) {
			URL dlUrl = null;
			try {
				dlUrl = new URL(s);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Path path = Paths.get(dlUrl.getPath());
			String name = path.getFileName().toString();
			System.out.println(name);
			InputStream in;
			try {
				in = dlUrl.openStream();
				FileOutputStream os = new FileOutputStream(new File(name));
				byte[] buffer = new byte[256];
				int cnt;
				while ((cnt = in.read(buffer, 0, 256)) != -1) {
					os.write(buffer, 0, cnt);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void getFile(String link, String fileName) {
		try {
			try {
				URL url = new URL(link);
			} catch (MalformedURLException e) {
				System.out.println("DeadLink");
			}

			InputStream in = url.openStream();
			FileOutputStream os = new FileOutputStream(new File(fileName));

			int length = -1;
			byte[] buffer = new byte[256];
			while ((length = in.read(buffer)) != -1) {
				os.write(buffer, 0, 256);
			}
			os.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
