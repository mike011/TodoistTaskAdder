package ca.todoist.util;

import java.util.Map;
import java.util.TreeMap;

public class Projects {

	private Map<String, String> names;

	Projects() {
		names = new TreeMap<String, String>();
	}

	void parse(String property) {
		String[] projectsAndEmails = property.trim().split(",");
		for (String projectAndEmail : projectsAndEmails) {
			String[] split = projectAndEmail.trim().split("=");
			names.put(split[0].toLowerCase().trim(), split[1].toLowerCase().trim());
		}
	}

	public String get(String key) {
		return names.get(key.toLowerCase());
	}

	public boolean isEmpty() {
		return names.isEmpty();
	}

}
