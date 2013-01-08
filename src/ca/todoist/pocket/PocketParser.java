package ca.todoist.pocket;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.Link;

public class PocketParser {

	static final String UL = "<ul>";
	static final String UNREAD = "<h1>Unread</h1>";
	static final String READ_ARCHIVE = "<h1>Read Archive</h1>";

	private List<String> lines;

	public PocketParser(List<String> contents) {
		lines = contents;
	}

	public List<Link> getOpenLinks() {
		List<Link> openLinks = new ArrayList<Link>();
		boolean read = false;
		for (int x = 0; x < lines.size(); x++) {
			String line = lines.get(x);
			if (line.contains(READ_ARCHIVE)) {
				read = false;
			}
			if (read && line.contains("<li><a href=")) {
				openLinks.add(new HTMLLink(line));
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
