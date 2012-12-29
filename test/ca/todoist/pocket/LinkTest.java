package ca.todoist.pocket;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.todoist.pocket.HTMLLink;

public class LinkTest {

	private static final String EXPECTED_URL = "www.google.com";
	private static final String EXPECTED_NAME = "Google";
	static final String LINK = "<li><a href=\"" + EXPECTED_URL
			+ "\" time_added=\"1355111437\" tags=\"\">" + EXPECTED_NAME + "</a></li>";
	private static final String EXPECTED_LINK_STRING = EXPECTED_URL + " (" + EXPECTED_NAME +")";
	
	@Test
	public void testGetURL() {
		HTMLLink link = new HTMLLink(LINK);
		assertEquals(EXPECTED_URL, link.getURL());
	}
	
	@Test
	public void testGetName() {
		HTMLLink link = new HTMLLink(LINK);
		assertEquals(EXPECTED_NAME, link.getName());
	}
	
	@Test
	public void testToString() {
		HTMLLink link = new HTMLLink(LINK);
		assertEquals(EXPECTED_LINK_STRING, link.toString());
	}

}
