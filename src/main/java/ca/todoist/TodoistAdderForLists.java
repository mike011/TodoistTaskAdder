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
		String category = "Science Fiction";
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 01", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 02", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 03", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 04", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 05", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 06", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 07", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 08", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 09", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 10", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 11", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 12", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 13", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 14", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 15", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 16", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep 17", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 01", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 02", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 03", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 04", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 05", category));
		tasks.add(new ItemTask("Do Androids Dream Of Electric Sheep - Dust To Dust 06", category));
		tasks.add(new ItemTask("Transmetropolitan - Christmas special 2 - Next Winters", category));
		tasks.add(new ItemTask("Transmetropolitan - Edgy Winter", category));
		tasks.add(new ItemTask("Transmetropolitan 000 - Preview", category));
		tasks.add(new ItemTask("Transmetropolitan 001 - 003", category));
		tasks.add(new ItemTask("Transmetropolitan 004", category));
		tasks.add(new ItemTask("Transmetropolitan 005", category));
		tasks.add(new ItemTask("Transmetropolitan 006", category));
		tasks.add(new ItemTask("Transmetropolitan 007", category));
		tasks.add(new ItemTask("Transmetropolitan 008", category));
		tasks.add(new ItemTask("Transmetropolitan 009", category));
		tasks.add(new ItemTask("Transmetropolitan 010", category));
		tasks.add(new ItemTask("Transmetropolitan 011", category));
		tasks.add(new ItemTask("Transmetropolitan 012", category));
		tasks.add(new ItemTask("Transmetropolitan 013", category));
		tasks.add(new ItemTask("Transmetropolitan 014", category));
		tasks.add(new ItemTask("Transmetropolitan 015", category));
		tasks.add(new ItemTask("Transmetropolitan 016", category));
		tasks.add(new ItemTask("Transmetropolitan 017", category));
		tasks.add(new ItemTask("Transmetropolitan 018", category));
		tasks.add(new ItemTask("Transmetropolitan 019", category));
		tasks.add(new ItemTask("Transmetropolitan 020", category));
		tasks.add(new ItemTask("Transmetropolitan 021", category));
		tasks.add(new ItemTask("Transmetropolitan 022", category));
		tasks.add(new ItemTask("Transmetropolitan 023", category));
		tasks.add(new ItemTask("Transmetropolitan 024", category));
		tasks.add(new ItemTask("Transmetropolitan 025", category));
		tasks.add(new ItemTask("Transmetropolitan 026", category));
		tasks.add(new ItemTask("Transmetropolitan 027", category));
		tasks.add(new ItemTask("Transmetropolitan 028", category));
		tasks.add(new ItemTask("Transmetropolitan 029", category));
		tasks.add(new ItemTask("Transmetropolitan 030", category));
		tasks.add(new ItemTask("Transmetropolitan 031", category));
		tasks.add(new ItemTask("Transmetropolitan 032", category));
		tasks.add(new ItemTask("Transmetropolitan 033", category));
		tasks.add(new ItemTask("Transmetropolitan 034", category));
		tasks.add(new ItemTask("Transmetropolitan 035", category));
		tasks.add(new ItemTask("Transmetropolitan 036", category));
		tasks.add(new ItemTask("Transmetropolitan 037", category));
		tasks.add(new ItemTask("Transmetropolitan 038", category));
		tasks.add(new ItemTask("Transmetropolitan 039", category));
		tasks.add(new ItemTask("Transmetropolitan 040", category));
		tasks.add(new ItemTask("Transmetropolitan 041", category));
		tasks.add(new ItemTask("Transmetropolitan 042", category));
		tasks.add(new ItemTask("Transmetropolitan 043", category));
		tasks.add(new ItemTask("Transmetropolitan 044", category));
		tasks.add(new ItemTask("Transmetropolitan 045", category));
		tasks.add(new ItemTask("Transmetropolitan 046", category));
		tasks.add(new ItemTask("Transmetropolitan 047", category));
		tasks.add(new ItemTask("Transmetropolitan 048", category));
		tasks.add(new ItemTask("Transmetropolitan 049", category));
		tasks.add(new ItemTask("Transmetropolitan 050", category));
		tasks.add(new ItemTask("Transmetropolitan 051", category));
		tasks.add(new ItemTask("Transmetropolitan 052", category));
		tasks.add(new ItemTask("Transmetropolitan 053", category));
		tasks.add(new ItemTask("Transmetropolitan 054", category));
		tasks.add(new ItemTask("Transmetropolitan 055", category));
		tasks.add(new ItemTask("Transmetropolitan 056", category));
		tasks.add(new ItemTask("Transmetropolitan 057", category));
		tasks.add(new ItemTask("Transmetropolitan 058", category));
		tasks.add(new ItemTask("Transmetropolitan 059", category));
		tasks.add(new ItemTask("Transmetropolitan 060", category));
		return tasks;
	}
}
