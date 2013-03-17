package ca.todoist;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.adder.ItemTask;
import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;

public class TodoistAdderForLists {

	public static void main(String[] args) {
		List<Task> links = getListOfLinks();
		new SendMail().sendTasks(links);
	}

	private static List<Task> getListOfLinks() {
		List<Task> tasks = new ArrayList<Task>();
		String category = "Algorithm Course";
		tasks.add(new ItemTask("Read in Algorithms - Section 3.3", category));
		return tasks;
	}
}
