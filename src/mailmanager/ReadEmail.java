/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

/**
 *
 * @author Vector
 */
import java.util.*;
import javax.mail.*;


/*Vector data
1. Email Content.
2. From
3. Sent Date
4. Subject

*/
public class ReadEmail {

    public static final String username = "public.github@gmail.com";
    public static final String password = "publicpassword";
    
    public static Vector<String> data = new Vector<String>();
    public static Vector<String> emailData = new Vector<String>();

    public static Vector<String> readMail(final String username, final String password) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            int count = inbox.getMessageCount();
            System.out.println("Total Messages Count: " + count);
            while (count != 0) {
                Message msg = inbox.getMessage(count);
                System.out.println("Content Type: " + msg.getContentType());
                System.out.println("getContent Returns: " + msg.getContent());
                //data = obj.getText(msg);
                data.addElement(Misc.getText(msg));

                /*Object content = msg.getContent();
                 //if (content instanceof Multipart) {
                 Multipart mp = (Multipart) content;
                 for (int i = 0; i < mp.getCount(); i++) {
                 BodyPart bp = mp.getBodyPart(i);
                 if (Pattern
                 .compile(Pattern.quote("text/html"),
                 Pattern.CASE_INSENSITIVE)
                 .matcher(bp.getContentType()).find()) {
                 // found html part
                 System.out.println((String) bp.getContent());
                 } else {
                 // some other bodypart...
                 }
                 }
                 }*/

                Address[] in = msg.getFrom();
                for (Address address : in) {
                    System.out.println("FROM:" + address.toString());
                    data.addElement(address.toString());
                    System.out.println("SENT DATE:" + msg.getSentDate());
                    data.addElement(msg.getSentDate().toString());
                    System.out.println("SUBJECT:" + msg.getSubject());
                    data.addElement(msg.getSubject());
                }


                //Multipart mp = (Multipart) msg.getContent();
                //BodyPart bp = mp.getBodyPart(0);

                //System.out.println("CONTENT:" + bp.getContent());
                count--;
            }
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return data;
    }

    public static int getCount() throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", username, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int count = inbox.getMessageCount();
        return count;
    }

    public static Vector<String> readEmailById(final String username, final String password, int emailId) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);
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
            }



        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return emailData;
    }
}
