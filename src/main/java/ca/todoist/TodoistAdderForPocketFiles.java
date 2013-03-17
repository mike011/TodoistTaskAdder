package ca.todoist;

import java.io.File;
import java.util.List;

import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;
import ca.todoist.parse.pocket.PocketParser;
import ca.todoist.util.LoadFile;

public class TodoistAdderForPocketFiles {

	public static void main(String[] args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("Specify the pocket file to load");
		}
		String filename = args[0];
		List<String> pocketLinks = LoadFile.load(filename);
		List<Task> tasks = new PocketParser(pocketLinks).getOpenLinksWithNoDueDates();

		new SendMail().sendTasks(tasks);
		
		deleteFile(filename);
	}

	private static void deleteFile(String file) {
		new File(file).delete();
		System.out.println("File deleted: " + file);
	}
	
}
