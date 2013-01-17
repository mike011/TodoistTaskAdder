package ca.todoist.parse.pocket;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.todoist.parse.pocket.HtmlTask;

public class HtmlTaskTest {

	private static final String EXPECTED_URL = "www.google.com";
	private static final String EXPECTED_NAME = "Google";
	private static final String EXPECTED_TAG = "DOG";
	static final String LINK = getLink(EXPECTED_TAG);

	private static String getLink(String tags) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<li><a href=\"");
		stringBuilder.append(EXPECTED_URL);
		stringBuilder.append("\" time_added=\"1355111437\"");
		if (tags.length() != 0) {
			stringBuilder.append("tags=\"");
			stringBuilder.append(tags);
			stringBuilder.append("\"");
		}
		stringBuilder.append(">");
		stringBuilder.append(EXPECTED_NAME);
		stringBuilder.append("</a></li>");
		return stringBuilder.toString();
	}

	private static final String EXPECTED_LINK_STRING = EXPECTED_URL + " ("
			+ EXPECTED_NAME + ")";

	@Test
	public void testGetURL() {
		HtmlTask link = new HtmlTask(LINK);
		assertEquals(EXPECTED_URL, link.getURL());
	}

	@Test(expected = IllegalArgumentException.class)
	public void badLeftSide() {
		new HtmlTask("BLA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void badRightSide() {
		new HtmlTask("<li><a href=\"lin");
	}

	@Test
	public void testGetTags_Empty() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertEquals(0, link.getTags().size());
	}

	@Test
	public void testGetTags() {
		HtmlTask link = new HtmlTask(getLink("Cycling"));
		assertEquals(1, link.getTags().size());
	}

	@Test
	public void testGetNameWithTag() {
		HtmlTask link = new HtmlTask(LINK);
		assertEquals(EXPECTED_NAME, link.getName());
	}

	@Test
	public void testGetNameEmpty() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertEquals(EXPECTED_NAME, link.getName());
	}

	@Test
	public void testGet() {
		HtmlTask link = new HtmlTask(LINK);
		assertEquals(EXPECTED_LINK_STRING, link.get());
	}

	@Test
	public void testGetFirstTag_Valid() {
		HtmlTask link = new HtmlTask(LINK);
		assertEquals(EXPECTED_TAG.toLowerCase(), link.getFirstTag());
	}
	
	@Test
	public void testGetFirstTag_NotSet() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertEquals("", link.getFirstTag());
	}
	
	@Test
	public void test_WithTabAtStartOfLine() {
		HtmlTask link = new HtmlTask("			<li><a href=\"http://rss.slashdot.org/~r/Slashdot/slashdot/~3/cYei53QV4d8/story01.htm\" time_added=\"1358361088\" tags=\"astronomy\">Curiosity Finds Evidence of Ancient Surface Water</a></li>");
		assertEquals("Curiosity Finds Evidence of Ancient Surface Water", link.getName());
	}

}
