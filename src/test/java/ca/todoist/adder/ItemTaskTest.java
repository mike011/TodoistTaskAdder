package ca.todoist.adder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTaskTest {

	@Test
		public void testGetDescriptionName() {
			String name = "cycling";
			ItemTask task = new ItemTask(name, "");
			assertThat(task.getName(), is(name));
		}

	@Test
		public void testGetDescriptionTags() {
			String tag = "cycling";
			ItemTask task = new ItemTask("", tag);
			assertThat(task.getTags().size(), is(1));
		}

	@Test
		public void testGetDescriptionFirstTag() {
			String tag = "cycling";
			ItemTask task = new ItemTask("", tag);
			assertThat(task.getFirstTag(), is(tag));
		}

	@Test
	public void testDueDate() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "", "mar 25");
		assertThat(task.getDescription(), is(name + " <date mar 25>"));
	}
	
	@Test
	public void testNote() {
		String note = "note";
		ItemTask task = new ItemTask("name", "", "", note);
		assertThat(task.getNote(), is(note));
	}
}
