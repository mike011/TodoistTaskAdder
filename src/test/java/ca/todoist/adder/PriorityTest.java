package ca.todoist.adder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PriorityTest {

	@Test
	public void p1() {
		assertThat(Priority.P1.toString(), is("!!1"));
	}
	
	@Test
	public void p2() {
		assertThat(Priority.P2.toString(), is("!!2"));
	}
	
	@Test
	public void p3() {
		assertThat(Priority.P3.toString(), is("!!3"));
	}
	
	@Test
	public void p4() {
		assertThat(Priority.P4.toString(), is(""));
	}
}
