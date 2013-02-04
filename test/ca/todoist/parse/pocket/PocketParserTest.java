package ca.todoist.parse.pocket;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.todoist.adder.Task;

public class PocketParserTest {

	@Test
	public void testPocketParser() {
		PocketParser pp = new PocketParser(null);
		assertThat(pp, is(notNullValue()));
	}

	@Test
	public void testGetOpenLinksNotEmpty() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertThat(openLinks, is(notNullValue()));
		assertThat(openLinks.isEmpty(), is(false));
	}

	@Test
	public void testGetOpenLinksUL() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertThat(openLinks, is(notNullValue()));
		assertThat(openLinks.isEmpty(), is(false));
	}

	@Test
	public void testGetOpenLinksTwo() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertThat(openLinks.size(), is(2));
	}

	@Test
	public void testGetOpenLinksClose() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		contents.add(PocketParser.READ_ARCHIVE);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertThat(openLinks.size(), is(1));
	}

	@Test
	public void testGetOpenInvalidLinke() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add("bla bla bla");

		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertThat(openLinks.isEmpty(), is(true));
	}

	private ArrayList<String> getValidStartOfContents() {
		ArrayList<String> contents = new ArrayList<String>();
		contents.add(PocketParser.UNREAD);
		contents.add(PocketParser.UL);
		return contents;
	}

}
