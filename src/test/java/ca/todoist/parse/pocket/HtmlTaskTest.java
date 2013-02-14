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
	private static final String EXPECTED_DUE_DATE = "<date Jan 13 2013>";
	
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
			+ EXPECTED_NAME + ") at " + EXPECTED_TIME_ADDED + " " + EXPECTED_DUE_DATE;

	@Test
		public void testGetDescriptionURL() {
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
		public void testGetDescriptionTags_Empty() {
			HtmlTask link = new HtmlTask(getLink(""));
			assertThat(link.getTags().size(), is(0));
		}

	@Test
		public void testGetDescriptionTags() {
			HtmlTask link = new HtmlTask(getLink("Cycling"));
			assertThat(link.getTags().size(), is(1));
		}

	@Test
		public void testGetDescriptionNameWithTag() {
			HtmlTask link = new HtmlTask(LINK);
			assertThat(link.getName(), is(EXPECTED_NAME));
		}

	@Test
		public void testGetDescriptionNameEmpty() {
			HtmlTask link = new HtmlTask(getLink(""));
			assertThat(link.getName(), is(EXPECTED_NAME));
		}

	@Test
		public void testGetDescription() {
			HtmlTask link = new HtmlTask(LINK);
			assertThat(link.getDescription(), is(EXPECTED_LINK_STRING));
		}

	@Test
		public void testGetDescriptionFirstTag_Valid() {
			HtmlTask link = new HtmlTask(LINK);
			assertThat(link.getFirstTag(), is(equalToIgnoringCase(EXPECTED_TAG)));
		}

	@Test
		public void testGetDescriptionFirstTag_NotSet() {
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
	public void testTimeAddedWithTag() {
		HtmlTask link = new HtmlTask(getLink("tag"));
		assertThat(link.getTimeAdded(), is(EXPECTED_TIME_ADDED));
	}
}
