package ca.todoist;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;

public class TodoistAdderForLists {

	public static void main(String[] args) {
		List<Task> links = getListOfLinks();
		new SendMail().sendTasks(links);
	}

	private static List<Task> getListOfLinks() {
		List<Task> list = new ArrayList<Task>();
//		tasks.add("01 - The High Energy Diet");
//		tasks.add("02 - Hoax or health");
//		tasks.add("03 - Proper Use of the Body");
//		tasks.add("04 - How NaturalHygiene Transforms You");
//		tasks.add("05 - Designing Health Program that Works");
//		tasks.add("06 - The Various Elements of Health");
//		tasks.add("07 - The Importance and Benefits of Activity");
//		tasks.add("08 - Designing A Fitness Program That Works");
//		tasks.add("09 - Raw Parenting");
//		tasks.add("10 - Living Healthfully In An Unhealthy World");
		return list;
	}

}
