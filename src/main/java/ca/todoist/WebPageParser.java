package ca.todoist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import ca.todoist.parse.pocket.Link;

public class WebPageParser {
	
	private static final String ARTICLE = "<article>";
	private String tag;
	private String page;

	public WebPageParser(String url, String tag) throws Exception {
		page = readWebPage(url);
		this.tag = tag;
	}

	private String readWebPage(String urlString) throws IOException {
		URLConnection con = getURLConnection(urlString);
		return new String(read(con), getEncoding(con));
	}

	private static URLConnection getURLConnection(String urlString) throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		return con;
	}

	private static byte[] read(URLConnection con) throws IOException {
		InputStream in = con.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
		    baos.write(buf, 0, len);
		}
		return baos.toByteArray();
	}

	private static String getEncoding(URLConnection con) {
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		return encoding;
	}
	
	public String getWebPageContents() {
		return page;
	}

	public List<Link> getParsedURLs() {
		ArrayList<Link> links = new ArrayList<Link>();
		while(page.contains(ARTICLE)) {
			links.add(parsedLink());
		}
		return links;
	}

	private Link parsedLink() {
		removeEverythingBeforeArticle();
		int ahref = page.indexOf("<a href");
		String ahrefEnd = "</a>";
		int ahrefEndIndex = page.indexOf(ahrefEnd, ahref);
		return new Link(page.substring(ahref, ahrefEndIndex+ahrefEnd.length()), tag);
	}

	private void removeEverythingBeforeArticle() {
		int articleIndex = page.indexOf(ARTICLE);
		page = page.substring(articleIndex+ARTICLE.length());
	}

}
