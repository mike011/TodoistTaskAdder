package ca.todoist.adder;

import java.util.ArrayList;

public class ItemTask implements Task{

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
		return null;
	}

}
