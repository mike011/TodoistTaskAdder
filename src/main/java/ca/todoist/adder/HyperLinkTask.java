package ca.todoist.adder;

public class HyperLinkTask extends ItemTask {

	private String name;
	private String link;

	public HyperLinkTask(String name, String link, String project) {
		super(name, project);
		this.name = name;
		this.link = link;
	}

	@Override
	public String getName() {
		return name;
		//return "[" + link + "](" + name + ")";
	}
}
