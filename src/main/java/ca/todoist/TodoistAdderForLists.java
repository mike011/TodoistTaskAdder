package ca.todoist;

import java.util.ArrayList;

import java.util.List;

import ca.todoist.adder.ItemTask;
import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;

public class TodoistAdderForLists {

	public static void main(String[] args) {
		List<Task> links = new ArrayList<Task>();	
		links.add(new ItemTask("https://www.mronge.com/how-not-to-screw-up-when-switching-your-app-to-subscriptions/?utm_campaign=iOS+Dev+Weekly&utm_medium=email&utm_source=iOS+Dev+Weekly+Issue+534", "dev", "", "", "@blog"));
		links.add(new ItemTask("https://engineering.atspotify.com/2021/11/16/introducing-xcremotecache-the-ios-remote-caching-tool-that-cut-our-clean-build-times-by-70/", "runkeeper", "", "", "@blog"));
		links.add(new ItemTask("https://www.amazon.ca/Clean-Craftsmanship-Disciplines-Standards-Ethics/dp/013691571X/ref=sr_1_1?keywords=robert+martin+clean+craftsmanship&qid=1636903411&sr=8-1", "dev", "", "", "@book"));
		links.add(new ItemTask("https://merowing.info/2021/11/leave-your-ego-at-the-door/", "dev", "", "", "@blog"));
		links.add(new ItemTask("https://quickbirdstudios.com/blog/swift-package-manager-cocoapods-support/", "runkeeper", "", "", "@blog"));
		links.add(new ItemTask("https://docs.google.com/spreadsheets/d/10irweviGrPsa8wFtUmaGZOrirF4VP9ay0VYY8onxx5U/edit#gid=400511806", "cycling", "", "", "@race"));
		links.add(new ItemTask("https://github.com/venmo/QuizTrain/commit/1ac8ff9aaae278f4d24e94b5e9ccdaf2c074d604", "runkeeper", "", "", "@blog"));
		links.add(new ItemTask("https://youtube.com/watch?v=hvSYHx2JbHw&feature=share", "cycling", "", "", "@audio/video"));
		links.add(new ItemTask("https://www.asics.com/ca/en-ca/women-s-winter-accelerate-jacket-2012b194-601", "runkeeper", "", "", ""));
		links.add(new ItemTask("https://developer.apple.com/documentation/security/errsecmissingentitlement", "runkeeper", "", "", "@blog"));
		links.add(new ItemTask("https://fellrnr.com/wiki/FIRST", "running", "", "", "@blog"));
		links.add(new ItemTask("https://github.com/mvemjsun/Xcode83", "runkeeper", "", "", "@blog"));
		links.add(new ItemTask("https://www.amazon.com/Code-That-Fits-Your-Head/dp/0137464401", "dev", "", "", "@book"));
		links.add(new ItemTask("https://www.asics.com/ca/en-ca/men-s-winter-accelerate-jacket-2011b195-401", "runkeeper", "", "", ""));
		new SendMail().sendTasks(links);
	}
}
