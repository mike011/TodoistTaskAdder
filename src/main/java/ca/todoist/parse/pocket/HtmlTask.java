package ca.todoist.parse.pocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.todoist.adder.Task;

public class HtmlTask implements Task {

	private String url;
	private String name;
	private String timeAdded;
	private ArrayList<String> tags;
	private String note;
	private boolean addDueDate;

	public HtmlTask(Link link) {
		url = link.getLink();
		name = link.getTitle();
		tags = new ArrayList<String>();
		tags.add(link.getTag());
		note = "";
	}

	public HtmlTask(String fullLine) {
		this(fullLine, true);
	}

	public HtmlTask(String fullLine, boolean addDueDate) {
		String line = fullLine.trim();
		setURL(line);
		setName(line);
		setTime(line);
		setTags(line);
		this.note = "";
		this.addDueDate = addDueDate;
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
			for (String tag : parseElement.split(",")) {
				tags.add(tag);
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
	public String getProject() {
		if (!tags.isEmpty()) {
			for(String t : tags) {
				if(!t.startsWith("@")) {
					return t.toLowerCase();
				}
			}
		}
		return "";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTitle() {
		return getStringOfDescription() + getDueDate();
	}

	private String getStringOfDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append(" (").append(name).append(")");
		if (timeAdded != null) {
			buffer.append(" at ").append(getTimeAdded());
		}
		return buffer.toString();
	}

	private String getDueDate() {
		if (addDueDate) {
			return " <date " + getFormatedTime(getAddedTimeInMilliseconds() + getOneMonthInMilliseconds()) + ">";
		}
		return "";
	}

	private String getFormatedTime(Long time) {
		SimpleDateFormat date = new SimpleDateFormat("MMM dd yyyy");
		return date.format(time);
	}

	static long getOneMonthInMilliseconds() {
		return (365L / 12L) * 24L * 60L * 60L * 1000L;
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
	public String getBody() {
		String body = note;
		if(!body.isEmpty()) {
			body += ' ';
		}
		for(String label: getLabels()) {
			body += label + ' ';
		}
		return body.trim();
	}

	@Override
	public void addNote(String string) {
		note = string;
	}

	@Override
	public List<String> getLabels() {
		List<String> labels = new ArrayList<String>();
		for(String t : tags) {
			if(t.startsWith("@")) {
				labels.add(t);
			}
		}
		return labels;
	}
}
