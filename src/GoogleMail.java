import java.util.Calendar;
import java.util.TimerTask;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;

public class GoogleMail extends TimerTask {
	String username;
	String password;
	Session session;
	String to;
	String subject;
	String msg;
	Calendar sendTime;

	public GoogleMail(String user, String pass, Session session, String to, String subject, String msg,
			Calendar sendTime) {
		username = user;
		password = pass;
		this.session = session;
		this.to = to;
		this.subject = subject;
		this.msg = msg;
		this.sendTime = sendTime;
		//System.out.println(sendTime.get);
		if (sendTime.getTime().compareTo(Calendar.getInstance().getTime()) == 0) {
			run();
		}
	}

	@Override
	public void run() {
		try {
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setText(msg, "utf-8");
				SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
				// if (username.contains("gmail.com")) {
				t.connect("smtp.gmail.com", username, password);
				t.sendMessage(message, message.getAllRecipients());
				t.close();
				// }
				/*
				 * else if (username.contains("yahoo.com")) { t.connect("smtp.mail.yahoo.com",
				 * username, password); t.sendMessage(message, message.getAllRecipients());
				 * t.close(); }
				 */
				System.out.println("Sent");
			} catch (MessagingException me) {
				me.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("error running thread " + ex.getMessage());
		}
	}
}
