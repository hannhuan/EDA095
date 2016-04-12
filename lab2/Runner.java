package lab21;

import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Runner implements Runnable{
	private URL url;
	private String fileName;

	
	public  Runner(URL url, String fileName){
		this.url=url;
		this.fileName=fileName;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			InputStream in = url.openStream();
			FileOutputStream os = new FileOutputStream(new File(fileName));

			int length = -1;
			byte[] buffer = new byte[256];
			while ((length = in.read(buffer)) != -1) {
				os.write(buffer, 0, 256);
			}
			os.close();
			in.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	

}
