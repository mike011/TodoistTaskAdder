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
		tasks.add(new ItemTask("Week #1 - Watch Course Intro", category, "today"));
		tasks.add(new ItemTask("Week #1 - Watch Union-Find - 1. Dynamic Connectivity", category));
		tasks.add(new ItemTask("Week #1 - Watch Union-Find - 2. Quick Find", category));
		tasks.add(new ItemTask("Week #1 - Watch Union-Find - 3. Quick Union", category));
		tasks.add(new ItemTask("Week #1 - Watch Union-Find - 4. Quick-Union Improvements", category));
		tasks.add(new ItemTask("Week #1 - Watch Union-Find - 5. Union-Find Applications", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 1. Analysis of Algorithms Introduction", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 2. Observations", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 3. Mathemetical Models", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 4. Order-of-Growth Classifications", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 5. Theory of Algorithms", category));
		tasks.add(new ItemTask("Week #1 - Watch Analysis of Algorithms - 6. Memory", category));
		tasks.add(new ItemTask("Week #1 - Exercise - 1. Union Find", category, "Feb 17"));
		tasks.add(new ItemTask("Week #1 - Exercise - 2. Analysis of Algorithms", category, "feb 17"));
		tasks.add(new ItemTask("Week #1 - Assignments - 1. Percolation", category, "feb 17"));
		tasks.add(new ItemTask("Week #1 - Interview Questions - 1. Union-Find", category, "mar 24"));
		tasks.add(new ItemTask("Week #1 - Interview Questions - 2. Analysis of Algorithms", category, "mar 24"));
		return tasks;
	}
}
