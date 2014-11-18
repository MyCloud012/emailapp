/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author Vector
 */
public class MailReader {

    public static Vector<String> data = new Vector<String>();
    public static int emailId = 2;

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
            //System.out.println("Total Messages Count: " + count);
            Message msg = inbox.getMessage(emailId);
            //System.out.println("Content Type: " + msg.getContentType());
            //System.out.println("getContent Returns: " + msg.getContent());
            //data = obj.getText(msg);
            //data.addElement(Misc.getText(msg));

            Object content = msg.getContent();
            if (content instanceof Multipart) {
                Multipart mp = (Multipart) content;
                for (int i = 0; i < mp.getCount(); i++) {
                    BodyPart bp = mp.getBodyPart(i);
                    if (Pattern.compile(Pattern.quote("text/html"), Pattern.CASE_INSENSITIVE)
                            .matcher(bp.getContentType()).find()) {
                        // found html part
                        //System.out.println((String) bp.getContent());
                    } else {
                        // some other bodypart...
                        System.err.println("NOT HTML");
                    }
                }
            }
            //System.out.println(content);
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
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return data;
    }
}
