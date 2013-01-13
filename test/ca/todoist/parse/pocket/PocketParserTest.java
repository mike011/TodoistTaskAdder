package ca.todoist.parse.pocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.todoist.adder.Task;

public class PocketParserTest {

	@Test
	public void testPocketParser() {
		PocketParser pp = new PocketParser(null);
		assertNotNull(pp);
	}

	@Test
	public void testGetOpenLinksNotEmpty() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertNotNull(openLinks);
		assertFalse(openLinks.isEmpty());
	}

	@Test
	public void testGetOpenLinksUL() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertNotNull(openLinks);
		assertFalse(openLinks.isEmpty());
	}
	
	@Test
	public void testGetOpenLinksTwo() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		contents.add(HtmlTaskTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertEquals(2, openLinks.size());
	}

	@Test
	public void testGetOpenLinksClose() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(HtmlTaskTest.LINK);
		contents.add(PocketParser.READ_ARCHIVE);
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertEquals(1, openLinks.size());
	}

	@Test
	public void testGetOpenInvalidLinke() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add("bla bla bla");
		
		PocketParser pp = new PocketParser(contents);
		List<Task> openLinks = pp.getOpenLinks();
		assertTrue(openLinks.isEmpty());
	}

	private ArrayList<String> getValidStartOfContents() {
		ArrayList<String> contents = new ArrayList<String>();
		contents.add(PocketParser.UNREAD);
		contents.add(PocketParser.UL);
		return contents;
	}
	
}
