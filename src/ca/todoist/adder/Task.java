package ca.todoist.adder;

import java.util.ArrayList;

public interface Task {
	String toString();
	String getName();
	ArrayList<String> getTags();
}
