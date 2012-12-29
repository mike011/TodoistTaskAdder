package ca.todoist;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.email.SendMail;
import ca.todoist.pocket.Link;

public class TodoistAdderForLinks {

	public static void main(String[] args) {
		List<Link> tasks = getLinks();
		new SendMail().sendTasks(tasks);
	}

	private static List<Link> getLinks() {
		List<Link> tasks = new ArrayList<Link>();
		return tasks;
	}

}
