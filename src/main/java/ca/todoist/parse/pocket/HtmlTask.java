package ca.todoist.parse.pocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ca.todoist.adder.Task;

public class HtmlTask implements Task {

	private String url;
	private String name;
	private String timeAdded;
	private ArrayList<String> tags;

	public HtmlTask(String fullLine) {
		String line = fullLine.trim();
		setURL(line);
		setName(line);
		setTime(line);
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

	private void setTime(String line) {
		String leftSide = "time_added=\"";
		String offset = "<li>";
		String rightSide = "\"";
		if (line.contains("tags")) {
			rightSide += " tags";
		} else {
			rightSide += ">";
		}
		timeAdded = parseElement(line, leftSide, offset, rightSide);

	}

	private void setTags(String line) {
		tags = new ArrayList<String>();

		String leftSide = "tags=\"";
		String offset = "";
		String rightSide = "\">";
		if (line.contains(leftSide)) {
			String parseElement = parseElement(line, leftSide, offset, rightSide);
			if (parseElement.length() > 0) {
				tags.add(parseElement);
			}
		}
	}

	private String parseElement(String line, String leftSide, String offset, String rightSide) {
		int leftSideIndex = line.indexOf(leftSide, offset.length()) + leftSide.length();
		if (leftSideIndex < leftSide.length()) {
			throwException("left", line, leftSide);
		}
		int rightSideIndex = line.indexOf(rightSide);
		if (rightSideIndex < 0) {
			throwException("right", line, rightSide);
		}
		return line.substring(leftSideIndex, rightSideIndex);
	}

	private void throwException(String name, String line, String side) {
		throw new IllegalArgumentException("Could not find " + name + " side index: " + side + " for line : " + line);
	}

	public String getURL() {
		return url;
	}

	@Override
	public ArrayList<String> getTags() {
		return tags;
	}

	@Override
	public String getFirstTag() {
		if (!tags.isEmpty()) {
			return tags.get(0).toLowerCase();
		}
		return "";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return getStringOfDescription() + getDueDate();
	}

	private String getStringOfDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append(" (").append(name).append(")");
		buffer.append(" at ").append(getTimeAdded());
		return buffer.toString();
	}

	private String getDueDate() {
		return " <date " + getFormatedTime(getAddedTimeInMilliseconds() + oneMonthInMilliseconds()) + ">";
	}
	
	private String getFormatedTime(Long time) {
		SimpleDateFormat date = new SimpleDateFormat("MMM dd yyyy");
		return date.format(time);
	}

	private long oneMonthInMilliseconds() {
		return (365 / 12) * 24 * 60 * 60 * 1000;
	}

	String getTimeAdded() {
		return getTimeAdded(getAddedTimeInMilliseconds());
	}

	private long getAddedTimeInMilliseconds() {
		return Long.parseLong(timeAdded + "000");
	}

	private String getTimeAdded(Long time) {
		SimpleDateFormat date = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a");
		return date.format(time);
	}
	
	@Override
	public String getNote() {
		return "";
	}
}
