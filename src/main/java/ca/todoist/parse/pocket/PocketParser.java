package ca.todoist.parse.pocket;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.adder.Task;

public class PocketParser {

	static final String UL = "<ul>";
	static final String UNREAD = "<h1>Unread</h1>";
	static final String READ_ARCHIVE = "<h1>Read Archive</h1>";

	private List<String> lines;

	public PocketParser(List<String> contents) {
		lines = contents;
	}

	public List<Task> getOpenLinksWithNoDueDates() {
		return getOpenLinksFromFile(false);
	}

	public List<Task> getOpenLinks() {
		return getOpenLinksFromFile(true);
	}

	private List<Task> getOpenLinksFromFile(boolean withDueDates) {
		List<Task> openLinks = new ArrayList<Task>();
		boolean read = false;
		for (int x = 0; x < lines.size(); x++) {
			String line = lines.get(x);
			if (line.contains(READ_ARCHIVE)) {
				read = false;
			}
			if (read && line.contains("<li><a href=")) {
				openLinks.add(new HtmlTask(line, withDueDates));
			}
			if (line.contains(UNREAD)) {
				read = true;

				// Skip ul link;
				x++;
			}
		}
		return openLinks;
	}
}
