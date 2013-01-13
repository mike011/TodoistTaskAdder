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
		for (String item : getList()) {
			String category = "";
			tasks.add(new ItemTask(item, category));
		}
		return tasks;
	}

	private static List<String> getList() {
		List<String> list = new ArrayList<String>();
		//list.add("example");
		return list;
	}
}
