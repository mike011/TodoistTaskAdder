package ca.todoist;

public class Link {

	private String text;

	public Link(String substring) {
		this.text = substring;
	}
	
	String getLink() {
		int ahref = text.indexOf("<a href");
		int closeBracket = text.indexOf("\">");
		return text.substring(ahref, closeBracket);
	}
	
	String getTitle() {
		int closeBracket = text.indexOf("\">");
		int ahrefEnd = text.indexOf("</a>");
		return text.substring(closeBracket, ahrefEnd);
	}
	
	public String toString() {
		return text;
	}

}
