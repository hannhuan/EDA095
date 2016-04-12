package lab21;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RunnerThread extends Thread {

	private URL link;
	private String fileName;

	public RunnerThread(URL link, String fileName) {
		this.link = link;
		this.fileName = fileName;

	}

	@Override
	public void run() {

		// URL link = null;
		// TODO Auto-generated method stub
		try {
			// try {
			// link = new URL (url);
			//
			// } catch (MalformedURLException e) {
			// System.out.println("DeadLink");
			// }

			InputStream in = link.openStream();
			FileOutputStream os = new FileOutputStream(new File(fileName));

			// System.out.println("read");

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
