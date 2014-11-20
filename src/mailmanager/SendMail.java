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


    SendMail() 
    {
    }
    private Session mailSession = null;

    public void initialiseSession() {
        Config config = Config.getInstance();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        mailSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(config.getUserName(), config.getPassword());
                    }
                });

    }

    ;

    public void Send(String to, String subject, String content) {


        try {
            if (mailSession == null) {
                initialiseSession();
            }

            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(config.getUserName()));
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
