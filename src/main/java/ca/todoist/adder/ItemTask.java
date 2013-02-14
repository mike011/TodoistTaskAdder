package ca.todoist.adder;

import java.util.ArrayList;

public class ItemTask implements Task {

	private final String name;
	private final String tag;
	private String dueDate;
	private String note;

	public ItemTask(String name, String tag) {
		this(name, tag, "");
	}

	public ItemTask(String name, String tag, String dueDate) {
		this.name = name;
		this.tag = tag;
		this.dueDate = dueDate;
	}

	public ItemTask(String name, String tag, String dueDate, String note) {
		this(name, tag, dueDate);
		this.note = note;
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
		return " <date " + dueDate + ">";
	}
	
	@Override 
	public String getNote() {
		return note;
	}
}
