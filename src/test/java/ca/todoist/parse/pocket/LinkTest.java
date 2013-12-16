package ca.todoist.parse.pocket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;



public class LinkTest {

	private static final String TEXT = "<a href=\"/patrick-gombert/2013/11/26/lispy-elixir.html\">Lispy Elixir</a>";
	private Link link;
	
	@Before
	public void setup() {
		link = new Link(TEXT, "");
	}
	
	@Test
	public void toStringTest() {
		assertThat(link.toString(), is(TEXT));
	}
	
	@Test
	public void getLink() {
		assertThat(link.getLink(), is("/patrick-gombert/2013/11/26/lispy-elixir.html"));
	}
	
	@Test
	public void getTitle() {
		assertThat(link.getTitle(), is("Lispy Elixir"));
	}
	
}
