package ca.todoist.adder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTaskTest {

	@Test
	public void testGetName() {
		String name = "cycling";
		ItemTask task = new ItemTask(name, "");
		assertThat(task.getName(), is(name));
	}
	
	@Test
	public void testGetTags() {
		String tag = "cycling";
		ItemTask task = new ItemTask("", tag);
		assertThat(task.getTags().size(), is(1));
	}
	
	@Test
	public void testGetFirstTag() {
		String tag = "cycling";
		ItemTask task = new ItemTask("", tag);
		assertThat(task.getFirstTag(), is(tag));
	}

}
