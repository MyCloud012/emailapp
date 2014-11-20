/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

/**
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vector
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    SendMail() {
    }
    private Session mailSession = null;
    private String userName;

    private void initialiseSession() {
        final Config config = Config.getInstance();
        userName = config.username;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        mailSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    private Config anonConfig;

                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(anonConfig.getUserName(), anonConfig.getPassword());
                    }

                    private javax.mail.Authenticator init(Config iconfig) {
                        anonConfig = iconfig;
                        return this;
                    }
                }.init(config));

    }

    ;

    public void Send(String to, String subject, String content) {

        try {
            if (mailSession == null) {
                initialiseSession();
            }

            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println("Google Response: Failed to Login at Gmail.");
            throw new RuntimeException(e);
        }
    }
}
