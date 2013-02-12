package ca.todoist.adder;

import java.util.ArrayList;

public interface Task {
	String get();
	String getName();
	ArrayList<String> getTags();
	String getFirstTag();
}
