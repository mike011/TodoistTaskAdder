package ca.todoist.adder;

import java.util.ArrayList;

public class ItemTask implements Task {

	private final String name;
	private final String tag;

	public ItemTask(String name, String tag) {
		this.name = name;
		this.tag = tag;
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
	public String get() {
		return name;
	}
}
