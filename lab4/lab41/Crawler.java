package lab4;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.Set;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class Crawler extends HTMLEditorKit.ParserCallback {

	private LinkQueue queue;
	private Set<String> pastLinks;
	private Set<String> links;
	private Set<String> adresses;
	private URL url;

	public static void main(String[] args) {

	}

	public Crawler(LinkQueue lq, Set<String> links, Set<String> past, Set<String> adresses) {
		queue = lq;
		pastLinks = past;
		this.links = links;
		this.adresses = adresses;
	}

	/**
	 * https://docs.oracle.com/javase/7/docs/api/javax/swing/text/html/
	 * HTMLEditorKit.ParserCallback.html
	 * https://docs.oracle.com/javase/7/docs/api/javax/swing/text/
	 * MutableAttributeSet.html
	 * https://docs.oracle.com/javase/7/docs/api/javax/swing/text/html/HTML.Tag.
	 * html
	 */
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet mas) {
		getLinks(tag, mas);

	}

	public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet mas) {
		if (tag == HTML.Tag.BASE) {
			String href = (String) mas.getAttribute(HTML.Attribute.HREF);
			try {
				setURL(new URL(href));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			getLinks(tag, mas);
		}
	}

	public void getLinks(HTML.Tag tag, MutableAttributeSet mas) {
		String href = null;
		// http://www.debugease.com/javaweb/829860.html
		if (tag == null) {
			System.out.println("I'm out too!!");
		} else if (tag == HTML.Tag.A) {
			href = (String) mas.getAttribute(HTML.Attribute.HREF);
			System.out.println("href is " + href);
		} else if (tag == HTML.Tag.FRAME) {
			href = (String) mas.getAttribute(HTML.Attribute.SRC);
		} else {
			System.out.println("More tags??");
			return;
		}

		URL tmp = getURL(href);

		if (tmp != null) {
			System.out.println("Aaaaaaaaaaaa plz no null anymore");
			String p = tmp.toString();
			try {
				URI uri = new URI(p);

				System.out.println("hej");
				if (uri.getScheme().equals("mailto")) {
					adresses.add(p);
				} else {
					if (!pastLinks.contains(p)) {
						queue.push(tmp);
						pastLinks.add(p);
					}
					links.add(p);

					System.out.println("sucess!");
				}

			} catch (Exception e) {
				System.out.println("Crawler, getLinks");
			}
		}

	}

	/**
	 * Get absolute URL property of the key.
	 * 
	 * @param s:
	 *            link
	 * @return URL
	 */
	private URL getURL(String s) {
		URL link = null;

		// https://m.oschina.net/blog/64215
		// https://www.zhihu.com/question/21950864
		try {
			if (!new URI(s).isAbsolute()) {
				System.out.println("Relative URI");
				link = new URL(url, s);
			} else {
				url = new URL(s);
			}
		} catch (Exception e) {
			System.out.println("Fel i Crawler getURL(String s)");
		}
		return link;

	}

	public void setURL(URL url) {
		this.url = url;
	}

}
