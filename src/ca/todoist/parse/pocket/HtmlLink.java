package ca.todoist.parse.pocket;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.parse.Link;

public class HtmlLink implements Link {

	private static final String TAGS = "tags=\"\">";
	private String url;
	private String name;
	private ArrayList<String> arrayList;

	public HtmlLink(String line) {
		setURL(line);
		setName(line);
		arrayList = new ArrayList<String>();
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

	private String parseElement(String line, String leftSide, String offset,
			String rightSide) {
		int leftSideIndex = line.indexOf(leftSide, offset.length())
				+ leftSide.length();
		if(leftSideIndex < leftSide.length()) {
			throw new IllegalArgumentException("Could not find left side index: " +  leftSide + " for line : " + line);
		}
		int rightSideIndex = line.indexOf(rightSide);
		if(rightSideIndex < 0) {
			throw new IllegalArgumentException("Could not find right side index: " +  rightSide + " for line : " + line);
		}
		return line.substring(leftSideIndex, rightSideIndex);
	}

	public String getURL() {
		return url;
	}

	public List<String> getTags() {

		return arrayList;
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
