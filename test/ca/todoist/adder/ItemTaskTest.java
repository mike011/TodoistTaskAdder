package ca.todoist.adder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTaskTest {

	@Test
	public void testGetName() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "");
		assertEquals(name, task.getName());
	}

	@Test
	public void testGetTags() {
		String tag = "cycling";
		ItemTask task = new ItemTask("", tag);
		assertEquals(1, task.getTags().size());
	}

	@Test
	public void testGetFirstTag() {
		String tag = "cycling";
		ItemTask task = new ItemTask("", tag);
		assertEquals(tag, task.getFirstTag());
	}

	@Test
	public void testDueDate() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "", "mar 25");
		assertEquals(name + " <date mar 25>", task.get());
	}
}
