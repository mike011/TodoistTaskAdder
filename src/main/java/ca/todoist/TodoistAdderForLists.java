package ca.todoist;

import java.util.ArrayList;

import java.util.List;

import ca.todoist.adder.ItemTask;
import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;

public class TodoistAdderForLists {

	public static void main(String[] args) {
		List<Task> links = new ArrayList<Task>();		
		new SendMail().sendTasks(links);
	}
}
