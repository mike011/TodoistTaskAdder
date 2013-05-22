package ca.todoist.adder.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.todoist.adder.Task;
import ca.todoist.util.LoadProperties;
import ca.todoist.util.Projects;

public class SendMail {

	private static Session session;

	private final String username;
	private final String password;
	private final Projects todoistProjects;

	public SendMail() {
		LoadProperties load = new LoadProperties();
		username = load.getUser();
		password = load.getPassword();
		todoistProjects = load.getProjects();
	}

	public void sendTasks(List<Task> tasks) {
		checkToMakeSureAllProjectsExist(tasks);
		send(tasks);
	}

	private void checkToMakeSureAllProjectsExist(List<Task> tasks) {
		for (Task task : tasks) {
			String firstTag = task.getFirstTag();
			String to = todoistProjects.get(firstTag);
			if (isFirstTagNotSet(to)) {
				throw new IllegalArgumentException("For task <" + task.getName() + "> could not find project: " + firstTag + " No emails sent.");
			}
		}
	}

	private void send(List<Task> tasks) {
		for (int x = 0; x < tasks.size(); x++) {
			printMessage(tasks, x);
			Task task = tasks.get(x);
			String to = todoistProjects.get(task.getFirstTag());
			sendEmail(to, task.getDescription(), task.getNote());

			if (notLastTask(tasks, x)) {
				sleepForFiveSeconds();
			}
		}
	}

	private boolean isFirstTagNotSet(String to) {
		return to == null;
	}

	private void printMessage(List<Task> tasks, int index) {
		Task link = tasks.get(index);

		StringBuilder message = new StringBuilder();
		message.append("Sending task ");
		message.append(index + 1);
		message.append(" of ");
		message.append(tasks.size());
		message.append(" with tags ");
		message.append(link.getTags());

		message.append(": ");
		message.append(link.getName());
		System.out.println(message.toString());
	}

	private boolean notLastTask(List<Task> tasks, int x) {
		return x < tasks.size() - 1;
	}

	private void sleepForFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendEmail(String to, String subject, String note) {
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(note);

			Transport.send(message);

			System.out.println("Email Sending Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Session getSession() {
		if (session == null) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			session = Session.getInstance(props, new javax.mail.Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		}
		return session;
	}
}