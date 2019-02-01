import java.util.Properties;
import javax.mail.Session;
public class Login {
 public static Session login(String username, String password) {
  Properties props = System.getProperties();
  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//  if (username.contains("gmail.com")) {
   props.setProperty("mail.smtps.host", "smtp.gmail.com");
   props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
   props.setProperty("mail.smtp.socketFactory.fallback", "false");
   props.setProperty("mail.smtp.port", "465");
   props.setProperty("mail.smtp.socketFactory.port", "465");
   props.setProperty("mail.smtps.auth", "true");
   props.put("mail.smtps.quitwait", "false");
 // }
/* else if (username.contains("yahoo.com")) {
   props.setProperty("mail.smtps.host", "smtp.mail.yahoo.com");
   props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
   props.setProperty("mail.smtp.socketFactory.fallback", "false");
   props.setProperty("mail.smtp.port", "587");
   props.setProperty("mail.smtp.socketFactory.port", "587");
   props.setProperty("mail.smtps.auth", "true");
   props.put("mail.smtps.quitwait", "false");
  } */
  MailAuthenticator ma = new MailAuthenticator(username, password);
  Session session = Session.getDefaultInstance(props, ma);
  return session;
 }
}