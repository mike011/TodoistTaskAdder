package ca.todoist.parse.pocket;

import ca.todoist.parse.Link;

public class HTMLLink implements Link{

	private static final String HREF = "href=\"";
	private static final String TAGS = "tags=\"\">";
	private String url;
	private String name;
	
	public HTMLLink(String line) {
		setURL(line);
		setName(line);
	}

	private void setURL(String line) {
		int hrefIndex = line.indexOf(HREF);
		int timeAddedIndex = line.indexOf("\" time_added");
		if(hrefIndex < 0 || timeAddedIndex < 0) {
			throw new IllegalArgumentException("hrefIndex="+hrefIndex+" timeAddedIndex="+timeAddedIndex+ " line="+line);
		}
		url = line.substring(hrefIndex + HREF.length(), timeAddedIndex);
	}
	
	private void setName(String line) {
		int tagsIndex = line.indexOf(TAGS);
		int aCloseIndex = line.indexOf("</a></li>");
		name = line.substring(tagsIndex + TAGS.length(), aCloseIndex);
	}

	public String getURL() {
		return url;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append(" (");
		buffer.append(name);
		buffer.append(")");
		return buffer.toString();
	}

}
