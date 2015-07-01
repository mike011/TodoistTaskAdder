package ca.todoist.adder;

import java.util.ArrayList;
import java.util.List;

public class ItemTask implements Task {

	private final String name;
	private final String tag;
	private String dueDate;
	private List<String> notes;

	public ItemTask(String name, String tag, String dueDate) {
		this.name = name;
		this.tag = tag;
		this.dueDate = dueDate;
		this.notes = new ArrayList<String>();
	}
	
	public ItemTask(String name, String tag) {
		this(name, tag, "");
	}

	public ItemTask(String name, String tag, String dueDate, String note) {
		this(name, tag, dueDate);
		notes.add(note);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<String> getTags() {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add(tag);
		return tags;
	}

	@Override
	public String getFirstTag() {
		return tag;
	}

	@Override
	public String getDescription() {
		return name + getDueDate();
	}

	private String getDueDate() {
		if(dueDate.isEmpty()) {
			return "";
		}
		return " <date " + dueDate + ">";
	}

	@Override
	public String getNote() {
		StringBuffer note = new StringBuffer();
		for (int x = 0; x < notes.size(); x++) {
			note.append(notes.get(x));
			if (x < notes.size() - 1) {
				note.append(", ");
			}
		}
		return note.toString();
	}

	@Override
	public void addNote(String string) {
		notes.add(string);
	}
}
