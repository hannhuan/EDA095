package lab4;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class UrlAnalyzer extends HTMLEditorKit.ParserCallback {

	private UrlStack stack;
	private URL baseURL;

	public UrlAnalyzer(UrlStack stack) {
		this.stack = stack;
	}
	
	public void makeBase(URL url){
		this.baseURL = url;
	}

	// http://fileadmin.cs.lth.se/cs/Education/EDA095/2016/lectures/XML/HTML_2016.pdf
	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		if (t == HTML.Tag.A) {
			String href = (String) a.getAttribute(HTML.Attribute.HREF);
			//System.out.println("Link: " + href);
			//System.out.println(baseURL.toString());
			if(href == null){
				System.out.println("IM OUT");
				return;
			}
			
			if(href.contains("mailto:")){
				stack.addMail(href.substring(7, href.length()));
				return;
			}
			
			try{
				
				URL tmp;
				if(!href.contains(baseURL.toString())){
					tmp = new URL(baseURL, href);
				} else {
					tmp = new URL(href);
				}
				stack.add(tmp);
				//System.out.println("LINK: " + tmp.toString());
			} catch (MalformedURLException e){
				System.out.println("FEEEEL I HREF: " + href);
			}
		}
	}

//	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
//		if (t == HTML.Tag.BASE) {
//			String href = (String) a.getAttribute(HTML.Attribute.HREF);
//			try {
//				baseURL = new URL(href);
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Base URL: " + href);
//		}
//		if (t == HTML.Tag.IMG) {
//			String href = (String) a.getAttribute(HTML.Attribute.SRC);
//			System.out.println("Image: " + href);
//		}
//		if (t == HTML.Tag.FRAME) {
//			String href = (String) a.getAttribute(HTML.Attribute.SRC);
//			System.out.println("Frame: " + href);
//		}
//	}
	
//	public void handleText(char[] data, int pos) {
//
//	}
//	
//	public void handleEndTag(HTML.Tag t, int pos) {
//
//	}
//	
//	public void handleComment(char[] data, int pos) {
//
//	}
//
//	public void handleEndOfLineString(String eol) {
//
//	}
//
//	public void handleError(String errorMsg, int pos) {
//
//	}
}
