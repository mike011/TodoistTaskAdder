package ca.todoist.adder;

import java.util.ArrayList;
import java.util.List;

public class ItemTask implements Task {

	private final String name;
	private final String project;
	private String dueDate;
	private List<String> notes;
	private List<String> labels;

	public ItemTask(String name, String project, String dueDate) {
		this.name = name;
		this.project = project;
		this.dueDate = dueDate;
		this.notes = new ArrayList<String>();
		this.labels = new ArrayList<String>();
	}
	
	public ItemTask(String name, String project) {
		this(name, project, "");
	}

	public ItemTask(String name, String project, String dueDate, String note) {
		this(name, project, dueDate);
		notes.add(note);
	}
	
	public ItemTask(String name, String project, String dueDate, String note, String... labels) {
		this(name, project, dueDate, note);
		for(String label : labels) {
			this.labels.add(label);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getProject() {
		return project;
	}

	@Override
	public String getTitle() {
		return getName() + getDueDate();
	}

	private String getDueDate() {
		if(dueDate.isEmpty()) {
			return "";
		}
		return " <date " + dueDate + ">";
	}

	@Override
	public String getBody() {
		StringBuffer body = new StringBuffer();
		for (int x = 0; x < notes.size(); x++) {
			body.append(notes.get(x));
			if (x < notes.size() - 1) {
				body.append(", ");
			}
		}
		if(!notes.isEmpty() && !labels.isEmpty()) {
			body.append(' ');
		}
		for(String label : labels) {
			body.append(label).append(' ');
		}
		return body.toString().trim();
	}

	@Override
	public void addNote(String string) {
		notes.add(string);
	}

	@Override
	public List<String> getLabels() {
		return labels;
	}
}
