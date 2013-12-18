package ca.todoist.parse.pocket;

public class Link {

	private String text;
	private String tag;
	private String base;

	public Link(String base, String substring, String tag) {
		this.base = base;
		this.text = substring;
		this.tag = tag;
	}
	
	public String getLink() {
		String startString = "<a href=\"";
		int ahref = text.indexOf(startString) + startString.length();
		int closeBracket = text.indexOf("\">");
		return base + text.substring(ahref, closeBracket);
	}
	
	public String getTitle() {
		String startString = "\">";
		int closeBracket = text.indexOf(startString) + startString.length();
		int ahrefEnd = text.indexOf("</a>");
		return text.substring(closeBracket, ahrefEnd);
	}
	
	public String toString() {
		return text;
	}

	public String getTag() {
		return tag;
	}

}
