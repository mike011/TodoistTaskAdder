package ca.todoist.adder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTaskTest {

	@Test
	public void testGetTitleName() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "");
		assertThat(task.getName(), is(name));
	}
	
	@Test
	public void testGetTitleFirstTag() {
		String tag = "cycling";
		ItemTask task = new ItemTask("", tag);
		assertThat(task.getProject(), is(tag));
	}

	@Test
	public void testDueDate() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "", "mar 25", Priority.P4);
		assertThat(task.getTitle(), is(name + " <date mar 25>"));
	}

	@Test
	public void getBodyWithOnlyNotes() {
		String note = "note";
		ItemTask task = new ItemTask("name", "", "", note);
		assertThat(task.getBody(), is(note));
	}

	@Test
	public void testAddNote() {
		String note = "note";
		ItemTask task = new ItemTask("name", "", "", note);
		String note2 = "2";
		task.addNote(note2);
		assertThat(task.getBody(), is(note + ", " + note2));
	}

	@Test
	public void testEmptyAddNote() {

		ItemTask task = new ItemTask("name", "");
		String note = "note";
		task.addNote(note);

		String note2 = "2";
		task.addNote(note2);
		assertThat(task.getBody(), is(note + ", " + note2));
	}

	@Test
	public void testAddNoteThree() {
		String note = "note";
		ItemTask task = new ItemTask("name", "", "", note);

		String note2 = "2";
		task.addNote(note2);

		String note3 = "3";
		task.addNote(note3);
		assertThat(task.getBody(), is(note + ", " + note2 + ", " + note3));
	}

	@Test
	public void testGetLabels() throws Exception {
		ItemTask it = new ItemTask("", "", "", "", "@mucho");
		assertThat(it.getLabels().get(0), is("@mucho"));
	}
	
	@Test
	public void getBodyWithOnlyLabels() {
		ItemTask it = new ItemTask("", "", "", "", "@mucho");
		assertThat(it.getBody(), is("@mucho"));
	}
	
	@Test
	public void getBodyWithNoteAndLabel() {
		ItemTask it = new ItemTask("", "", "", "note", "@mucho");
		assertThat(it.getBody(), is("note @mucho"));
	}
	
	@Test
	public void testPriorityP4AddsNothing() {
		String name = "name";
		String project = "project";
		String dueDate = "dueDate";
		ItemTask it = new ItemTask(name, project, dueDate, Priority.P4);
		assertThat(it.getBody(), is(""));
	}
	
	@Test
	public void testPriorityP3() {
		String name = "name";
		String project = "project";
		String dueDate = "dueDate";
		ItemTask it = new ItemTask(name, project, dueDate, Priority.P3);
		assertThat(it.getBody(), is("!!3"));
	}

}
