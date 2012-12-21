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

	private static Session session;

	public static void main(String[] args) {
		List<String> tasks = getTasks();
		new SendMail().sendTasks(tasks);
	}

	private static List<String> getTasks() {
		List<String> tasks = new ArrayList<String>();
		tasks.add("http://www.everydiet.org/diet/80-10-10-diet (80 10 10 Diet)");
		return tasks;
	}

	private String username;
	private String todoistProject;
	private String password;
	
	SendMail() {
		LoadProperties load = new LoadProperties();
		username = load.getUser();
		todoistProject = load.getProject();
		password = load.getPassword();
	}

	private void sendTasks(List<String> tasks) {
		for (String task : tasks) {
			sendEmail(task, "");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
		}
		return session;
	}
}