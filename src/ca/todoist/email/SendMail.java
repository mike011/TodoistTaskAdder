package ca.todoist.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private final static String username = "michael.charland@gmail.com";
	private final static String password = "";
	private final static String todoistProject = "project.100179962@todoist.net";
	private static Session session;

	public static void main(String[] args) {
		List<String> tasks = getTasks();
		sendTasks(tasks);
	}

	private static void sendTasks(List<String> tasks) {
		for (String task : tasks) {
			sendEmail(task, "");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<String> getTasks() {
		List<String> tasks = new ArrayList<String>();
		tasks.add("http://foodnsport.com/blog/articles/801010-Athlete-Freelee.php (FoodnSport | 80/10/10 Athlete Freelee");
		return tasks;
	}

	private static void sendEmail(String subject, String note) {
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

	private static Session getSession() {
		if (session == null) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
		}
		return session;
	}
}