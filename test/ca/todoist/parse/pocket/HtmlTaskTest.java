package ca.todoist.parse.pocket;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import ca.todoist.parse.pocket.HtmlTask;

public class HtmlTaskTest {

	private static final String EXPECTED_URL = "www.google.com";
	private static final String EXPECTED_NAME = "Google";
	private static final String EXPECTED_TAG = "DOG";
	private static final String EXPECTED_TIME_ADDED = "Feb 02 2013 01:19:56 PM";
	static final String LINK = getLink(EXPECTED_TAG);

	private static String getLink(String tags) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<li><a href=\"").append(EXPECTED_URL)
				.append("\" ");
		stringBuilder.append("time_added=\"1359829196\"");
		if (tags.length() != 0) {
			stringBuilder.append(" tags=\"").append(tags).append("\"");
		}
		stringBuilder.append(">").append(EXPECTED_NAME).append("</a></li>");
		return stringBuilder.toString();
	}

	private static final String EXPECTED_LINK_STRING = EXPECTED_URL + " ("
			+ EXPECTED_NAME + ") at " + EXPECTED_TIME_ADDED;

	@Test
	public void testGetURL() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getURL(), is(EXPECTED_URL));
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
		assertThat(link.getTags().size(), is(0));
	}

	@Test
	public void testGetTags() {
		HtmlTask link = new HtmlTask(getLink("Cycling"));
		assertThat(link.getTags().size(), is(1));
	}

	@Test
	public void testGetNameWithTag() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getName(), is(EXPECTED_NAME));
	}

	@Test
	public void testGetNameEmpty() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertThat(link.getName(), is(EXPECTED_NAME));
	}

	@Test
	public void testGet() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.get(), is(EXPECTED_LINK_STRING));
	}

	@Test
	public void testGetFirstTag_Valid() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getFirstTag(), is(equalToIgnoringCase(EXPECTED_TAG)));
	}

	@Test
	public void testGetFirstTag_NotSet() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertThat(link.getFirstTag(), is(""));
	}

	@Test
	public void testWithTabAtStartOfLine() {
		HtmlTask link = new HtmlTask(
				"			<li><a href=\"http://rss.slashdot.org/~r/Slashdot/slashdot/~3/cYei53QV4d8/story01.htm\" time_added=\"1358361088\" tags=\"astronomy\">Curiosity Finds Evidence of Ancient Surface Water</a></li>");
		assertThat(link.getName(),
				is("Curiosity Finds Evidence of Ancient Surface Water"));
	}

	@Test
	public void testTimeAdded() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertThat(link.getTimeAdded(), is(EXPECTED_TIME_ADDED));
	}

	@Test
<<<<<<< HEAD
	public void testWithTabAtStartOfLine() {
		HtmlTask link = new HtmlTask("			<li><a href=\"http://rss.slashdot.org/~r/Slashdot/slashdot/~3/cYei53QV4d8/story01.htm\" time_added=\"1358361088\" tags=\"astronomy\">Curiosity Finds Evidence of Ancient Surface Water</a></li>");
		assertEquals("Curiosity Finds Evidence of Ancient Surface Water", link.getName());
=======
	public void testTimeAddedWithTag() {
		HtmlTask link = new HtmlTask(getLink("tag"));
		assertThat(link.getTimeAdded(), is(EXPECTED_TIME_ADDED));
>>>>>>> 6e33ffc4a828da8da296207f6c62fd0f4f78326a
	}
}
