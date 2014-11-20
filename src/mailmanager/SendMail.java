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

    //We intilaizeSession to send an email, establish connections to the server etc.
    //This function is called only one time in the send method, because of two things
    //1. We store the session in an outter private member mailSession.
    //2. We check that variable value everytime we call the send method, to avoid calling the function again.
    private void initialiseSession() {
        //This line makes a call to the same instance we firstly created at the main. 
        //We only limited to use one instance as we don't need more.
        //We could have created another object of Config everytime we need thee uesrname, password.
        //But this ineffiecient way to do that in our case.
        final Config config = Config.getInstance();
        userName = config.username; //We later will parse this to the Send method.
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
