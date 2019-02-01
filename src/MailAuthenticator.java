import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class MailAuthenticator extends Authenticator {
 String user;
 String pass;
 public MailAuthenticator(String username, String password) {
  super();
  this.user = username;
  this.pass =    password;
 }
 public PasswordAuthentication getPasswordAuthentication() {
  return new PasswordAuthentication(user, pass);
 }
}
