package lab21;

//import exercise.Downloader;d

public class Main {
	
	public static void main(String[] args){
		String url = "http://cs229.stanford.edu/materials.html";
		
		Download dl = new Download(url);
		
//		Download dl = null;
//		try {
//			 dl = new Download(url);
//		} catch (Exception e) {
//			
//		}
		//dl.downloadOne();
		dl.downloadMany();
		//System.out.println("Complete!");
	}

}
