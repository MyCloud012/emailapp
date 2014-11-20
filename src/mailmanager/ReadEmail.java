/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

/**
 *
 * @author Vector
 */
import java.io.IOException;
import java.util.*;
import javax.mail.*;


/*Vector data
 1. Email Content.
 2. From
 3. Sent Date
 4. Subject

 */
public class ReadEmail {

    private Config config;
    ReadEmail() {
        this.config = Config.getInstance();
    }
    private Session mailSession = null;

    public void initialiseSession() throws NoSuchProviderException, MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        mailSession = Session.getInstance(props, null);


    }
    ;
    
    public Vector<String> data = new Vector<String>();
    public Vector<String> emailData = new Vector<String>();

    public Vector<String> readMail() throws NoSuchProviderException, MessagingException, IOException {

        if (mailSession == null) {
            initialiseSession();
        }

        Store store = mailSession.getStore();
        store.connect("imap.gmail.com", config.getUserName(), config.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        int count = inbox.getMessageCount();
        System.out.println("Total Messages Count: " + count);
        while (count != 0) {
            Message msg = inbox.getMessage(count);
            System.out.println("Content Type: " + msg.getContentType());
            System.out.println("getContent Returns: " + msg.getContent());
            data.addElement(Misc.getText(msg));

            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
                data.addElement(address.toString());
                System.out.println("SENT DATE:" + msg.getSentDate());
                data.addElement(msg.getSentDate().toString());
                System.out.println("SUBJECT:" + msg.getSubject());
                data.addElement(msg.getSubject());
            }
            count--;
        }
        return data;
    }

    public int getCount() throws MessagingException {

        if (mailSession == null) {
            initialiseSession();
        }
        Store store = mailSession.getStore();
        store.connect("imap.gmail.com", config.getUserName(), config.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int count = inbox.getMessageCount();
        return count;
    }

    public Email readEmailById(Config config, int emailId) {
        Email obj = null;
        try {

            if (mailSession == null) {
                initialiseSession();
            }

            Store store = mailSession.getStore();
            store.connect("imap.gmail.com", config.getUserName(), config.getPassword());
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message msg = inbox.getMessage(emailId);
            System.out.println("Content Type: " + msg.getContentType());
            System.out.println("getContent Returns: " + msg.getContent());
            emailData.addElement(Misc.getText(msg));

            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
                emailData.addElement(address.toString());
                System.out.println("SENT DATE:" + msg.getSentDate());
                emailData.addElement(msg.getSentDate().toString());
                System.out.println("SUBJECT:" + msg.getSubject());
                emailData.addElement(msg.getSubject());

                obj = new Email(emailId, Misc.getText(msg), address.toString(), msg.getSentDate(), msg.getSubject());
            }
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return obj;
    }
}
