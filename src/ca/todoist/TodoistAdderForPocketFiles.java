package ca.todoist;

import java.util.List;

import ca.todoist.email.SendMail;
import ca.todoist.pocket.PocketParser;
import ca.todoist.util.LoadFile;

public class TodoistAdderForPocketFiles {

	public static void main(String[] args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("Specify the pocket file to load");
		}
		String filename = args[0];
		List<String> pocketLinks = LoadFile.load(filename);
		List<Link> tasks = new PocketParser(pocketLinks).getOpenLinks();

		new SendMail().sendTasks(tasks);
	}
	
}
