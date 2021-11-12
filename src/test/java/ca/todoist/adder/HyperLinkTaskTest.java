package ca.todoist.adder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class HyperLinkTaskTest {

	@Test
	public void getName() {
		HyperLinkTask link = new HyperLinkTask("name", "link", "");
		assertThat(link.getName(), is("[link](name)"));
	}
}
