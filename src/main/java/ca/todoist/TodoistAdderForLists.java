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
		String category = "xUnit Test Patterns";
		for(int x = 7; x <= 27; x++) {
			tasks.add(new ItemTask("Read Chapter " + x, category));
			tasks.add(new ItemTask("reread Chapter " + x, category));
		}
		for(int x = 0; x <= 7; x++) {
			tasks.add(new ItemTask("Read Appendix " + x, category));
		}		
		return tasks;
	}
}
