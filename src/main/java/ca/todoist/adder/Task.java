package ca.todoist.adder;

import java.util.List;

public interface Task {
	
	// For sending email. These probably should be somewhere else.
	String getTitle();
	String getBody();
	
	String getName();
	String getProject();
	List<String> getLabels();
	void addNote(String string);
}
