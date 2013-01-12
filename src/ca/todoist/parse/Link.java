package ca.todoist.parse;

import java.util.ArrayList;

public interface Link {
	String toString();
	String getName();
	ArrayList<String> getTags();
}
