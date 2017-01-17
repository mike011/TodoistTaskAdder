package ca.todoist.adder;

public enum Priority {

	P1("!!1"), P2("!!2"), P3("!!3"), P4("");
	
	private String label;

	Priority(String label) {
		this.label = label;
	}
	
	public String toString() {
		return label;
	}
}
