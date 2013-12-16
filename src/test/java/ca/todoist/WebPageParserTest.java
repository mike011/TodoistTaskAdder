package ca.todoist;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WebPageParserTest {

	private static final String URL = "http://blog.8thlight.com/";
	private WebPageParser webPageParser;
	
	@Before
	public void setup() throws Exception {
		webPageParser = new WebPageParser(URL);
	}
	
	@Test
	public void readAWebPage() throws Exception {
		String webPageContents = webPageParser.getWebPageContents();
		assertTrue(webPageContents.contains("article"));
	}
	
	@Test
	public void getUrls() throws Exception {
		List<Link> urls = webPageParser.getParsedURLs();
		assertFalse(urls.isEmpty());
		assertThat(urls.size(), is(15));
		assertThat(urls.get(0).toString(), is("<a href=\"/uncle-bob/2013/12/10/Thankyou-Kent.html\">Extreme Programming, a Reflection</a>"));
		assertThat(urls.get(1).toString(), is("<a href=\"/patrick-gombert/2013/11/26/lispy-elixir.html\">Lispy Elixir</a>"));
	}
}
