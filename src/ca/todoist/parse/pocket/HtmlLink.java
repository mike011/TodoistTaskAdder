package ca.todoist.parse.pocket;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.parse.Link;

public class HtmlLink implements Link {

	private String url;
	private String name;
	private ArrayList<String> tags;

	public HtmlLink(String line) {
		setURL(line);
		setName(line);
		setTags(line);
	}

	private void setURL(String line) {

		String leftSide = "href=\"";
		String offset = "";
		String rightSide = "\" time_added";
		url = parseElement(line, leftSide, offset, rightSide);
	}

	private void setName(String line) {
		String leftSide = ">";
		String offset = "<li>";
		String rightSide = "</a></li>";
		name = parseElement(line, leftSide, offset, rightSide);
	}
	
	private void setTags(String line) {
		tags = new ArrayList<String>();
		
		String leftSide = "tags=\"";
		String offset = "";
		String rightSide = "\">";
		String parseElement = parseElement(line, leftSide, offset, rightSide);
		if(parseElement.length() > 0) {
			tags.add(parseElement);
		}
		
	}

	private String parseElement(String line, String leftSide, String offset,
			String rightSide) {
		int leftSideIndex = line.indexOf(leftSide, offset.length())
				+ leftSide.length();
		if(leftSideIndex < leftSide.length()) {
			throwException("left", line, leftSide);
		}
		int rightSideIndex = line.indexOf(rightSide);
		if(rightSideIndex < 0) {
			throwException("right", line, rightSide);
		}
		return line.substring(leftSideIndex, rightSideIndex);
	}

	private void throwException(String name, String line, String side) {
		throw new IllegalArgumentException("Could not find "+ name + " side index: " +  side + " for line : " + line);
	}

	public String getURL() {
		return url;
	}

	public List<String> getTags() {
		return tags;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append(" (");
		buffer.append(name);
		buffer.append(")");
		return buffer.toString();
	}
}
