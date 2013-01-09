package ca.todoist.parse.pocket;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.todoist.parse.pocket.HtmlLink;

public class HtmlLinkTest {

	private static final String EXPECTED_URL = "www.google.com";
	private static final String EXPECTED_NAME = "Google";
	static final String LINK = getLink("");

	private static String getLink(String tags) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<li><a href=\"");
		stringBuilder.append(EXPECTED_URL);
		stringBuilder.append("\" time_added=\"1355111437\"");
		stringBuilder.append("tags=\"");
		stringBuilder.append(tags);
		stringBuilder.append("\">");
		stringBuilder.append(EXPECTED_NAME);
		stringBuilder.append("</a></li>");
		return stringBuilder.toString();
	}
	private static final String EXPECTED_LINK_STRING = EXPECTED_URL + " (" + EXPECTED_NAME +")";
	
	@Test
	public void testGetURL() {
		HtmlLink link = new HtmlLink(LINK);
		assertEquals(EXPECTED_URL, link.getURL());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badLeftSide() {
		new HtmlLink("BLA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badRightSide() {
		new HtmlLink("<li><a href=\"lin");
	}
	
	@Test
	public void testGetTags() {
		HtmlLink link = new HtmlLink(LINK);
		assertEquals(0, link.getTags().size());
	}
	
	@Test
	public void testGetName() {
		HtmlLink link = new HtmlLink(LINK);
		assertEquals(EXPECTED_NAME, link.getName());
	}
	
	@Test
	public void testGetNameWithTags() {
		HtmlLink link = new HtmlLink(getLink("Cycling"));
		assertEquals(EXPECTED_NAME, link.getName());
	}
	
	@Test
	public void testToString() {
		HtmlLink link = new HtmlLink(LINK);
		assertEquals(EXPECTED_LINK_STRING, link.toString());
	}

}
