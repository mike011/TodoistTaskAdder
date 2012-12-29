package ca.todoist.pocket;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.todoist.Link;
import ca.todoist.pocket.PocketParser;

/**
 * @author tron
 *
 */
public class PocketParserTest {

	@Test
	public void testPocketParser() {
		PocketParser pp = new PocketParser(null);
		assertNotNull(pp);
	}

	@Test
	public void testGetOpenLinksNotEmpty() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(LinkTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Link> openLinks = pp.getOpenLinks();
		assertNotNull(openLinks);
		assertFalse(openLinks.isEmpty());
	}

	@Test
	public void testGetOpenLinksUL() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(LinkTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Link> openLinks = pp.getOpenLinks();
		assertNotNull(openLinks);
		assertFalse(openLinks.isEmpty());
	}
	
	@Test
	public void testGetOpenLinksTwo() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(LinkTest.LINK);
		contents.add(LinkTest.LINK);
		PocketParser pp = new PocketParser(contents);
		List<Link> openLinks = pp.getOpenLinks();
		assertEquals(2, openLinks.size());
	}

	@Test
	public void testGetOpenLinksClose() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add(LinkTest.LINK);
		contents.add(PocketParser.READ_ARCHIVE);
		PocketParser pp = new PocketParser(contents);
		List<Link> openLinks = pp.getOpenLinks();
		assertEquals(1, openLinks.size());
	}

	@Test
	public void testGetOpenInvalidLinke() {
		ArrayList<String> contents = getValidStartOfContents();
		contents.add("bla bla bla");
		
		PocketParser pp = new PocketParser(contents);
		List<Link> openLinks = pp.getOpenLinks();
		assertTrue(openLinks.isEmpty());
	}

	private ArrayList<String> getValidStartOfContents() {
		ArrayList<String> contents = new ArrayList<String>();
		contents.add(PocketParser.UNREAD);
		contents.add(PocketParser.UL);
		return contents;
	}
	
}
