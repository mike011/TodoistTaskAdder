package ca.todoist;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MultipleWebPageParserTest {

	private static final String URL = "http://blog.8thlight.com/";
	private MultipleWebPageParser mwpp;
	
	@Before
	public void setup() throws Exception {
		mwpp = new MultipleWebPageParser(URL);
	}
	
	@Test
	public void loadMultiplePages() throws Exception{
		List<WebPageParser> pages = mwpp.getPages();
		assertThat(pages.size(), is(27));
	}
	
	@Test
	public void createSuperList() throws Exception {
		assertThat(mwpp.getListOfAllUrls().size(), is(396));
	}
	
	@Test
	public void getHTMLLinks() throws Exception {
		assertThat(mwpp.getHTMLTasks().size(), is(396));
	}
}
