package ca.todoist.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.todoist.parse.Link;
import ca.todoist.util.LoadProperties;

public class SendMail {

	private static Session session;

	private String username;
	private String password;
	private String todoistProject;

	public SendMail() {
		LoadProperties load = new LoadProperties();
		username = load.getUser();
		todoistProject = load.getProject();
		password = load.getPassword();
	}

	public void sendTasks(List<Link> tasks) {
		for (int x = 0; x < tasks.size(); x++) {
			printMessage(tasks, x);
			sendEmail(tasks.get(x).toString(), "");

			if (notLastTask(tasks, x)) {
				sleepForFiveSeconds();
			}
		}
	}

	private void printMessage(List<Link> tasks, int index) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Sending task ");
		stringBuilder.append(index);
		stringBuilder.append(" of ");
		stringBuilder.append(tasks.size());
		stringBuilder.append(": ");
		stringBuilder.append(tasks.get(index).getName());
		System.out.println(stringBuilder.toString());
	}

	private boolean notLastTask(List<Link> tasks, int x) {
		return x < tasks.size() - 1;
	}

	private void sleepForFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendEmail(String subject, String note) {
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(todoistProject));
			message.setSubject(subject);
			message.setText(note);

			Transport.send(message);

			System.out.println("Done");

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

			session = Session.getInstance(props,
					new javax.mail.Authenticator() {

						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
		}
		return session;
	}
}