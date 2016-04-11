package ca.todoist.parse.pocket;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HtmlTaskTest {

	private static final String EXPECTED_URL = "www.google.com";
	private static final String EXPECTED_NAME = "Google";
	private static final String EXPECTED_TAG = "DOG";
	private static final String EXPECTED_TIME_ADDED = "Feb 02 2013 01:19:56 PM";
	private static final String EXPECTED_DUE_DATE = "<date Mar 04 2013>";

	static final String LINK = getLink(EXPECTED_TAG);

	private static String getLink(String tags) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<li><a href=\"").append(EXPECTED_URL).append("\" ");
		stringBuilder.append("time_added=\"1359829196\"");
		if (tags.length() != 0) {
			stringBuilder.append(" tags=\"").append(tags).append("\"");
		}
		stringBuilder.append(">").append(EXPECTED_NAME).append("</a></li>");
		return stringBuilder.toString();
	}

	private static final String EXPECTED_LINK_NO_DUE_DATE = EXPECTED_URL + " (" + EXPECTED_NAME + ") at " + EXPECTED_TIME_ADDED;
	private static final String EXPECTED_LINK = EXPECTED_LINK_NO_DUE_DATE + " " + EXPECTED_DUE_DATE;

	@Test
	public void testGetTitleURL() {
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
	public void testGetTitleNameWithTag() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getName(), is(EXPECTED_NAME));
	}

	@Test
	public void testGetTitleNameEmpty() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertThat(link.getName(), is(EXPECTED_NAME));
	}

	@Test
	public void testGetTitle() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getTitle(), is(EXPECTED_LINK));
	}

	@Test
	public void testNoDueDate() {
		HtmlTask link = new HtmlTask(LINK, false);
		assertThat(link.getTitle(), is(EXPECTED_LINK_NO_DUE_DATE));
	}

	@Test
	public void testGetTitleFirstTag_Valid() {
		HtmlTask link = new HtmlTask(LINK);
		assertThat(link.getProject(), is(equalToIgnoringCase(EXPECTED_TAG)));
	}

	@Test
	public void testGetTitleFirstTag_NotSet() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertThat(link.getProject(), is(""));
	}

	@Test
	public void testWithTabAtStartOfLine() {
		HtmlTask link = new HtmlTask(
				"			<li><a href=\"http://rss.slashdot.org/~r/Slashdot/slashdot/~3/cYei53QV4d8/story01.htm\" time_added=\"1358361088\" tags=\"astronomy\">Curiosity Finds Evidence of Ancient Surface Water</a></li>");
		assertThat(link.getName(), is("Curiosity Finds Evidence of Ancient Surface Water"));
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

	@Test
	public void testGetOneMonthInMilliseconds() {
		assertThat(HtmlTask.getOneMonthInMilliseconds(), is(2592000000L));
	}

	@Test
	public void getBody() {
		HtmlTask link = new HtmlTask(LINK);
		String note = "note";
		link.addNote(note);
		assertThat(link.getBody(), is(note));
	}
	
	@Test
	public void getBodyWithLabel() throws Exception {
		HtmlTask link = new HtmlTask(getLink("@tag,tag"));
		assertThat(link.getBody(), is("@tag"));
	}

	@Test
	public void getBodyWithNoteAndLabel() throws Exception {
		HtmlTask link = new HtmlTask(getLink("@tag,tag"));
		link.addNote("note");
		assertThat(link.getBody(), is("note @tag"));
	}

	@Test
	public void getProjectEmpty() {
		HtmlTask link = new HtmlTask(getLink(""));
		assertEquals("", link.getProject());
	}

	@Test
	public void getProjectOneTag() {
		HtmlTask link = new HtmlTask(getLink("tag"));
		assertEquals("tag", link.getProject());
	}

	@Test
	public void getProjectMultipleTags() {
		HtmlTask link = new HtmlTask(getLink("tag,tag1"));
		assertEquals("tag1", link.getProject());
	}

	@Test
	public void getLabel() throws Exception {
		HtmlTask link = new HtmlTask(getLink("@tag,tag"));
		assertEquals("@tag", link.getLabels().get(0));
	}
}
