package ca.todoist;

import java.util.ArrayList;

import java.util.List;

import ca.todoist.adder.ItemTask;
import ca.todoist.adder.Task;
import ca.todoist.adder.email.SendMail;

//class CTCL {
//	String project = "Cracking the code interview 2";
//	int chapter;
//	String title;
//
//	boolean chapterCode;
//	boolean addAnkiCards;
//	int amountOfProblems;
//	boolean writeCodeForProblem;
//	int timesToWriteCodeForProblem = 5;
//
//	CTCL(int chap, String title, boolean chapCode, boolean addAnkiCards, int amount, boolean write) {
//		this.chapter = chap;
//		this.title = title;
//		this.chapterCode = chapCode;
//		
//		this.addAnkiCards = addAnkiCards;
//		this.amountOfProblems = amount;
//		this.writeCodeForProblem = write;
//	}
//
//	List<Task> getTasks() {
//		List<Task> tasks = new ArrayList<Task>();
//		String title = "Chapter " + chapter + ": " + this.title;
//		tasks.add(new ItemTask(title, project));
//		
//		if(addAnkiCards) {
//			tasks.add(new ItemTask(title + " - Add Anki Cards", project));
//		}
//		
//		if (chapterCode) {
//			tasks.add(new ItemTask(title + " - Write Code In Swift " + timesToWriteCodeForProblem + " times", project));
//			tasks.add(new ItemTask(title + " - Rewrite Code In Swift " + timesToWriteCodeForProblem + " times", project));
//		}
//
//		for (int x = 1; x <= amountOfProblems; x++) {
//			tasks.add(new ItemTask(title + " - Q." + x + " Read Question and Understand Answer", project));
//			if(writeCodeForProblem) {
//				tasks.add(new ItemTask(title + " - Q." + x + " Write Code In Swift " + timesToWriteCodeForProblem + " times", project));
//				tasks.add(new ItemTask(title + " - Q." + x + " Rewrite Code In Swift " + timesToWriteCodeForProblem + " times", project));
//			}
//		}
//		return tasks;
//	}
//}

public class TodoistAdderForLists {

	public static void main(String[] args) {
		List<Task> links = new ArrayList<Task>();
		String group = "CleanArchitecture";
		
		String[] subs = {
				"Episode 63 - Architecture Begins",
				"Episode 64 - The Foundations of Architecture",
				"Episode 65 - The Principles of Architecture",
				"Episode 66 - The Art of Drawing Lines",
				"Episode 67 - Screaming Architecture"
				};
		
		String[] titles = {"", " - Listen", " - Listen 2", " - Listen 3", " - Watch", " - Add Anki Cards", " - Review"};
		
//		links.add(new ItemTask("Section VI: Putting it All Together", "RXJava"));
		//for (int x = 3; x < 26; x++) {
		for(String sub: subs) {
				for (String title: titles) {
					links.add(new ItemTask(sub + title,group));
				}
			}
		//}
		//links.add(new ItemTask("", "CleanCoders"));

//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Build power and improve your body's ability to recovery under effort. - 00:56",
//				"Training", "5/29/2020 06:34AM", Priority.P3));
//		links.add(new ItemTask("BaseTempoTrioinMallorcaHD - Ride the climb in endurance zone for an hour - 01:02", "Training", "5/30/2020 7:00AM",
//				Priority.P1));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 45min Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 01:05", "Training",
//				"5/31/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("PassoGaviaNorthHD - Extended climb but -20% endurance zone. - 02:01", "Training", "6/2/2020 05:29AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/3/2020 06:17AM",
//				Priority.P1));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "6/7/2020 7:00AM",
//				Priority.P1));
//		links.add(
//				new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo intervals, 1:20 hour total - 01:19", "Training", "6/9/2020 06:11AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Good, varied workout! - 01:17", "Training", "6/10/2020 06:13AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - The whole ride start to finish - 01:28", "Training", "6/12/2020 06:02AM",
//				Priority.P3));
//		links.add(new ItemTask("BaseTempoTrioinMallorcaHD - Full ride, intensity reduced -20% endurance zone - 01:35", "Training", "6/13/2020 7:00AM",
//				Priority.P1));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/14/2020 7:00AM",
//				Priority.P1));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 1hr Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 01:19", "Training",
//				"6/16/2020 06:11AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Stepping up to 3 x 16 - 01:17", "Training", "6/17/2020 06:13AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "6/19/2020 06:32AM",
//				Priority.P3));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - warmup +64min cruise intervals + cooldown. KOW: King of Workouts! - 01:26", "Training",
//				"6/20/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "6/21/2020 7:00AM",
//				Priority.P1));
//		links.add(
//				new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo intervals, 1:20 hour total - 01:19", "Training", "6/23/2020 06:11AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Good workout! Build some FTP and work on spin! - 01:25", "Training",
//				"6/24/2020 06:05AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_FTPowerRampsHD - Add some Fast Pedal work! - 01:09", "Training", "6/26/2020 06:21AM", Priority.P3));
//		links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "6/27/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
//				"6/28/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - Grind and Fast Pedals! - 01:34", "Training", "6/30/2020 05:56AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "7/1/2020 05:57AM",
//				Priority.P1));
//		links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "7/5/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_FTPowerRampsHD - Add some Fast Pedal work! - 01:09", "Training", "7/7/2020 06:21AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - The whole ride start to finish - 01:26", "Training", "7/8/2020 06:04AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - More Power! Killer workout! - 01:14", "Training", "7/10/2020 06:16AM",
//				Priority.P3));
//		links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "7/11/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "7/12/2020 7:00AM",
//				Priority.P1));
//		links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo is hard work but you are ready! - 01:57", "Training", "7/14/2020 05:33AM",
//				Priority.P2));
//		links.add(new ItemTask("AllenPT_FTPowerRampsHD - Power Ramp! Finish strong! - 01:09", "Training", "7/15/2020 06:21AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - EPD meets FTP w/ Big Gear work! - 01:28", "Training", "7/17/2020 06:02AM",
//				Priority.P3));
//		links.add(new ItemTask("AllenPT_TempoGrindHD - The whole ride start to finish - 01:26", "Training", "7/18/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - A KOW workout: King of Workouts! - 01:38", "Training", "7/19/2020 7:00AM",
//				Priority.P1));
//		links.add(new ItemTask("LuzArdidenHD - Build some base, fun cruise! - 02:02", "Training", "7/21/2020 05:28AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_FTPowerRampsHD - The whole ride start to finish - 01:24", "Training", "7/22/2020 06:06AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "7/24/2020 05:57AM",
//				Priority.P3));
//		links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "7/25/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
//				"7/26/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("PassoGaviaNorthHD - 2 hours extended -20% easier - 02:00", "Training", "7/28/2020 05:30AM", Priority.P2));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Change of pace, mixed intervals! - 01:11", "Training", "7/29/2020 06:19AM",
//				Priority.P1));
//		links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "8/2/2020 7:00AM", Priority.P1));
//		links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "8/4/2020 05:57AM",
//				Priority.P2));
		//links.add(new ItemTask("D", "Clean Coders"))
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Add some FP drills to improve spin! - 01:04", "Training", "5/22/2020 6AM",
		// Priority.P3));
		// links.add(new ItemTask("PassoGaviaNorthHD - endurance Zone on scenic part of the climb - 01:00", "Training", "5/23/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 30min Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 00:57", "Training",
		// "5/24/2020 7AM", Priority.P1));
		// links.add(new ItemTask("LuzArdidenHD - Extended climb but easy -25% endurance zone. - 01:44", "Training", "5/26/2020 6AM", Priority.P2));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "5/27/2020 6AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Build power and improve your body's ability to recovery under effort. - 00:56",
		// "Training", "5/29/2020 4:50AM", Priority.P3));
		// links.add(new ItemTask("BaseTempoTrioinMallorcaHD - Ride the climb in endurance zone for an hour - 01:02", "Training", "5/30/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 45min Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 01:05", "Training",
		// "5/31/2020 7AM", Priority.P1));
		// links.add(new ItemTask("PassoGaviaNorthHD - Extended climb but -20% endurance zone. - 02:01", "Training", "6/2/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/3/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "6/7/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo intervals, 1:20 hour total - 01:19", "Training", "6/9/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Good, varied workout! - 01:17", "Training", "6/10/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - The whole ride start to finish - 01:28", "Training", "6/12/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("BaseTempoTrioinMallorcaHD - Full ride, intensity reduced -20% endurance zone - 01:35", "Training", "6/13/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/14/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 1hr Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 01:19", "Training",
		// "6/16/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Stepping up to 3 x 16 - 01:17", "Training", "6/17/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "6/19/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup +64min cruise intervals + cooldown. KOW: King of Workouts! - 01:26", "Training",
		// "6/20/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "6/21/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo intervals, 1:20 hour total - 01:19", "Training", "6/23/2020 6PM",
		// Priority.P2));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Good workout! Build some FTP and work on spin! - 01:25", "Training",
		// "6/24/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - Add some Fast Pedal work! - 01:09", "Training", "6/26/2020 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "6/27/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
		// "6/28/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - Grind and Fast Pedals! - 01:34", "Training", "6/30/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "7/1/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "7/5/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - Add some Fast Pedal work! - 01:09", "Training", "7/7/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - The whole ride start to finish - 01:26", "Training", "7/8/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - More Power! Killer workout! - 01:14", "Training", "7/10/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "7/11/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "7/12/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - Tempo is hard work but you are ready! - 01:57", "Training", "7/14/2020 6PM",
		// Priority.P2));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - Power Ramp! Finish strong! - 01:09", "Training", "7/15/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - EPD meets FTP w/ Big Gear work! - 01:28", "Training", "7/17/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - The whole ride start to finish - 01:26", "Training", "7/18/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - A KOW workout: King of Workouts! - 01:38", "Training", "7/19/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("LuzArdidenHD - Build some base, fun cruise! - 02:02", "Training", "7/21/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - The whole ride start to finish - 01:24", "Training", "7/22/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "7/24/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "7/25/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
		// "7/26/2020 7AM", Priority.P1));
		// links.add(new ItemTask("PassoGaviaNorthHD - 2 hours extended -20% easier - 02:00", "Training", "7/28/2020 6PM", Priority.P2));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Change of pace, mixed intervals! - 01:11", "Training", "7/29/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "8/2/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "8/4/2020 6PM",
		// Priority.P2));

		// links.add(new ItemTask("LuzArdidenHD - Good base ride with some friends -30% - 01:01", "Training", "5/12/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - warmup + Short 30 min Tempo workout + cooldown - 00:54", "Training", "5/14/2020
		// 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Pace line in endurance zone -20% - 01:01", "Training", "5/16/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("LuzArdidenHD - Good base ride with some friends -30% - 01:01", "Training", "5/19/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - warmup + Short 30 min Tempo workout + cooldown - 00:54", "Training", "5/21/2020
		// 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Pace line in endurance zone -20% - 01:01", "Training", "5/23/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "5/26/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - warmup + Short 30 min Tempo workout + cooldown - 00:54", "Training", "5/28/2020
		// 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Great winter builder! - 00:50", "Training", "5/30/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - Building our Tempo duration.warmup + 45 min tempo + cooldown - 01:04", "Training",
		// "6/2/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Add some FP drills to improve spin! - 01:04", "Training", "6/4/2020
		// 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 30min Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 00:57", "Training",
		// "6/6/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_BaseTempoVirginiaHD - The whole ride start to finish - 01:37", "Training", "6/9/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Build power and improve your body’s ability to recovery under effort. - 00:56",
		// "Training", "6/11/2020 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - warmup+ 45min Tempo + cooldown. Hit all CADENCE targets, build FORCE! - 01:05", "Training",
		// "6/13/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/16/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "6/20/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Good, varied workout! - 01:17", "Training", "6/23/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - The whole ride start to finish - 01:28", "Training", "6/25/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - The whole ride start to finish - 01:13", "Training", "6/27/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Stepping up to 3 x 16 - 01:17", "Training", "6/30/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Build some POWER while improving FTP - 00:58", "Training", "7/2/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "7/4/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - Good workout! Build some FTP and work on spin! - 01:25", "Training",
		// "7/7/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - Add some Fast Pedal work! - 01:09", "Training", "7/9/2020 4:50AM", Priority.P3));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
		// "7/11/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "7/14/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "7/18/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_TempoGrindHD - The whole ride start to finish - 01:26", "Training", "7/21/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - More Power! Killer workout! - 01:14", "Training", "7/23/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - O/U 20's with 10 x 1 min FP! - 01:30", "Training", "7/25/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - Power Ramp! Finish strong! - 01:09", "Training", "7/28/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - EPD meets FTP w/ Big Gear work! - 01:28", "Training", "7/30/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_OverUnderSweetSpotIntervalsHD - A KOW workout: King of Workouts! - 01:38", "Training", "8/1/2020 7AM",
		// Priority.P1));
		// links.add(new ItemTask("AllenPT_FTPowerRampsHD - The whole ride start to finish - 01:24", "Training", "8/4/2020 4:50AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - The whole ride start to finish - 01:33", "Training", "8/6/2020 4:50AM",
		// Priority.P3));
		// links.add(new ItemTask("AllenPT_ExplosivePowerSweetSpotHD - Make sure you grind the big gear between 55-65 RPM! - 01:49", "Training",
		// "8/8/2020 7AM", Priority.P1));
		// links.add(new ItemTask("AllenPT_PowerMicroBurstsAndTempoHD - Change of pace, mixed intervals! - 01:11", "Training", "8/11/2020 4:50AM",
		// Priority.P1));
		// links.add(new ItemTask("ThresholdTestHD - The whole ride, warm-up is configurable - 00:26", "Training", "8/15/2020 7AM", Priority.P1));

		// boolean chapterCode = true;
		// boolean addAnkiCards = true;
		// boolean writeCode = true;
		// links.addAll(new CTCL(1, "Arrays and Strings", chapterCode, false, 9, writeCode).getTasks());
		// links.addAll(new CTCL(2, "Linked Lists", chapterCode, false, 8, writeCode).getTasks());
		// links.addAll(new CTCL(3, "Stacks and Queues", chapterCode, false, 6, writeCode).getTasks());
		// links.addAll(new CTCL(4, "Trees and Graphs", chapterCode, addAnkiCards, 12, writeCode).getTasks());
		// links.addAll(new CTCL(5, "Bit Manipulation", chapterCode, addAnkiCards, 8, writeCode).getTasks());
		// links.addAll(new CTCL(6, "Math and Logic Puzzles", false, addAnkiCards, 10, false).getTasks());
		// links.addAll(new CTCL(7, "Object-Oriented Design", false, addAnkiCards, 9, false).getTasks());
		// links.addAll(new CTCL(8, "Recursion and Dynamic Programming", chapterCode, addAnkiCards, 14, writeCode).getTasks());
		// links.addAll(new CTCL(9, "System Design and Scalability", false, addAnkiCards, 8, false).getTasks());
		// links.addAll(new CTCL(10, "Sorting and Searching", chapterCode, addAnkiCards, 11, writeCode).getTasks());
		// links.addAll(new CTCL(11, "Testing", false, addAnkiCards, 6, false).getTasks());
		// links.addAll(new CTCL(15, "Threads and Locks", false, true, 0, false).getTasks());
		// links.addAll(new CTCL(16, "Moderate", false, false, 26, writeCode).getTasks());
		// links.addAll(new CTCL(17, "Hard", false, false, 26, writeCode).getTasks());

		// for(Task t: links) {
		// System.out.println(t.getTitle());
		// }
		// System.out.println(links.size());
		new SendMail().sendTasks(links);
		// new SendMail().sendTasks(getListOfLinks());
	}

	private static List<Task> getListOfLinks() {

		String[] chapters = new String[] { "For The Love Of Glutes!", "How Much Do You Really Know About Your Butt?!",
				"To Squat Or Not To Squat That Is The Question", "Why A Variety Of Movements Helps You Get The BEST Results",
				"The 'How To' Of Modifying Moves", "My Top 10 Must-Do Glute Moves", "Tweak That Form For Better Booty Gains!",
				"Want To Be Pain-Free? Work Those Glutes!", "Even Your Ankles Can Affect Your Glutes! Pay Attention To Your Injuries!",
				"More Is Better...Or Is Less More?", "So How Does Cardio Fit In?", "Let's Get To The Fun Part - The Workouts!",
				"Don't Forget The Rest - Upper Body And Full-Body Workouts!", "Don't Wait! Get Started!", "Studies And References" };

		String[] types = new String[] { "Read", "Add Anki Cards", "Review" };

		List<Task> tasks = new ArrayList<Task>();
		String project = "Glute Camp";

		for (String name : chapters) {
			for (String type : types) {
				tasks.add(new ItemTask(type + " - " + name, project));
			}
		}
		return tasks;
	}

	private static List<String> getNames() {
		List<String> tasks = new ArrayList<String>();
		tasks.add("Part I: Getting Ready");
		tasks.add("Chapter 1: Understanding Design Patterns");
		tasks.add("Chapter 2: Getting Used to Xcode");
		tasks.add("Chapter 3: Creating the SportsStore App");
		tasks.add("Part II: The Creation Patterns");
		tasks.add("Chapter 4: The Object Template Pattern");
		tasks.add("Chapter 5: The Prototype Pattern");
		tasks.add("Chapter 6: The Singleton Pattern");
		tasks.add("Chapter 7: The Object Pool Pattern");
		tasks.add("Chapter 8: Object Pool Variations");
		tasks.add("Chapter 9: The Factory Method Pattern");
		tasks.add("Chapter 10: Abstract Factory Pattern");
		tasks.add("Chapter 11: The Builder Pattern");
		tasks.add("Part III: The Structural Patterns");
		tasks.add("Chapter 12: The Adapter Pattern");
		tasks.add("Chapter 13: The Bridge Pattern");
		tasks.add("Chapter 14: The Decorator Pattern");
		tasks.add("Chapter 15: The Composite Pattern");
		tasks.add("Chapter 16: The Façade Pattern");
		tasks.add("Chapter 17: The Flyweight Pattern");
		tasks.add("Chapter 18: The Proxy Pattern");
		tasks.add("Part IV: The Behavioral Patterns");
		tasks.add("Chapter 19: The Chain of Responsibility Pattern");
		tasks.add("Chapter 20: The Command Pattern");
		tasks.add("Chapter 21: The Mediator Pattern");
		tasks.add("Chapter 22: The Observer Pattern");
		tasks.add("Chapter 23: The Memento Pattern");
		tasks.add("Chapter 24: The Strategy Pattern");
		tasks.add("Chapter 25: The Visitor Pattern");
		tasks.add("Chapter 26: The Template Method Pattern");
		tasks.add("Part V: The MVC Pattern");
		tasks.add("Chapter 27: The Model/View/Controller Pattern");
		return tasks;
	}
}
