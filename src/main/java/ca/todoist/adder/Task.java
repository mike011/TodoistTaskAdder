package ca.todoist.adder;

import java.util.ArrayList;

public interface Task {
	String getDescription();
	String getName();
	ArrayList<String> getTags();
	String getFirstTag();
	String getNote();
	void addNote(String string);
}
