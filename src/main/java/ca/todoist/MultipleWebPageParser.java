package ca.todoist;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.parse.pocket.HtmlTask;

public class MultipleWebPageParser {
	
	private List<WebPageParser> pages;
	private List<Link> urls;
	
	public MultipleWebPageParser(String url) throws Exception {
		loadMultiplePages(url);
		loadListOrUrls();
	}

	private void loadMultiplePages(String url) throws Exception {
		pages = new ArrayList<WebPageParser>();
		pages.add(new WebPageParser(url));
		for (int x = 2; x < 28; x++) {
			pages.add(new WebPageParser(url + "/page" + x + '/'));
		}
	}
	
	public void loadListOrUrls() {
		urls = new ArrayList<Link>();
		for(WebPageParser wpp : pages) {
			urls.addAll(wpp.getParsedURLs());
		}
	}
	

	public List<Link> getListOfAllUrls() {
		return urls;
	}

	public List<WebPageParser> getPages() {
		return pages;
	}

	public List<HtmlTask> getHTMLTasks() {
		List<HtmlTask> tasks = new ArrayList<HtmlTask>();
		for(Link url : urls) {
			tasks.add(new HtmlTask(url.toString()));
		}
		return tasks;
	}

}
