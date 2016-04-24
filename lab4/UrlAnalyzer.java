package lab4;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class UrlAnalyzer extends HTMLEditorKit.ParserCallback {

	private UrlStack stack;

	public UrlAnalyzer(UrlStack stack) {
		this.stack = stack;
	}
	
	// http://fileadmin.cs.lth.se/cs/Education/EDA095/2016/lectures/XML/HTML_2016.pdf
	public void handleText(char[] data, int pos) { // TagStripper.java

	}

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

	}

	public void handleEndTag(HTML.Tag t, int pos) {

	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {

	}

	public void handleComment(char[] data, int pos) {

	}

	public void handleEndOfLineString(String eol) {

	}

	public void handleError(String errorMsg, int pos) {

	}

}
