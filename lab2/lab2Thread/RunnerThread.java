package lab21;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RunnerThread extends Thread {

	private Download d;

	public RunnerThread(Download d ) {
		this.d = d;

	}

	@Override
	public void run() {
		Map.Entry me;
		
		while (true) {
		if((me = d.entry()) == null) {
			return;
		}
		// URL link = null;
		// TODO Auto-generated method stub
		try {
			
			System.out.println("SKRIV SKRIV");
			System.out.println(me.getValue().toString());
			InputStream in = ((URL) me.getValue()).openStream();
			FileOutputStream os = new FileOutputStream(new File(me.getKey().toString()));


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

}
