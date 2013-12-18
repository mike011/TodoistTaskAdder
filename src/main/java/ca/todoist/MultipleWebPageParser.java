package ca.todoist;

import java.util.ArrayList;
import java.util.List;

import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;
import ca.todoist.parse.pocket.HtmlTask;
import ca.todoist.parse.pocket.Link;

public class MultipleWebPageParser {
	
	private List<WebPageParser> pages;
	private List<Link> links;
	
	public MultipleWebPageParser(String url, String tag) throws Exception {
		loadMultiplePages(url, tag);
		loadListOrUrls();
	}

	private void loadMultiplePages(String url, String tag) throws Exception {
		pages = new ArrayList<WebPageParser>();
		pages.add(new WebPageParser(url, url, tag));
		for (int x = 2; x < 28; x++) {
			pages.add(new WebPageParser(url, url + "/page" + x + '/', tag));
		}
	}
	
	public void loadListOrUrls() {
		links = new ArrayList<Link>();
		for(WebPageParser wpp : pages) {
			links.addAll(wpp.getParsedURLs());
		}
	}
	
	public List<Link> getListOfAllUrls() {
		return links;
	}

	public List<WebPageParser> getPages() {
		return pages;
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		for(Link link : links) {
			tasks.add(new HtmlTask(link));
		}
		return tasks;
	}
	
	public static void main(String args[]) throws Exception {
		MultipleWebPageParser mwpp = new MultipleWebPageParser("http://blog.8thlight.com/", "8th");
		
		List<Task> tasks = mwpp.getTasks();
		int index = 341;
		int end = index+50;
		while(!tasks.isEmpty()) {
			System.out.println("index"+index+ " end"+end);
			if(end > tasks.size()) {
				end = tasks.size();
			}
			new SendMail().sendTasks(tasks.subList(index, end));
			index+=50;
			end+=50;
			if(end >= tasks.size()) {
				end = tasks.size();
				break;
			}
			sleepForFiftySeconds();
		}
		new SendMail().sendTasks(tasks.subList(index, end));
	}
	
	private static void sleepForFiftySeconds() {
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
