/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

import java.util.Date;

/**
 *
 * @author Vector <your.name at your.org>
 */
public class Email {

    Email(int emailId, String message, String from, Date sentDate, String subject) {
        this.emailId = emailId;
        this.message = message;
        this.from = from;
        this.sentDate = sentDate;
        this.subject = subject;
    }
    
     Email (Email c)
     {
         emailId = c.getEmailId();
         message = c.getMessage();
         from = c.getSender();
         sentDate = c.getSendDate();
         subject = c.getSubject();
     }
    /*
     * 1.Email ID.
     * 2. Email Content.
     3. From
     4. Sent Date
     5. Subject
     */
    private final int emailId;
    private final String message;
    private final String from;
    private final Date sentDate;
    private final String subject;

    public int getEmailId() {
        return emailId;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return from;
    }

    public Date getSendDate() {
        return sentDate;
    }

    public String getSubject() {
        return subject;
    }
    
    public void setEmailId(int emailId_)
    {
        emailId_ = emailId;
    }
    
    public void setMessage(String message_)
    {
        message_ = message;
    }
    
    public void setSender(String sender_)
    {
        sender_ = from;
    }
    
    public void setDate(Date sentDate_)
    {
        //sentDate= sentDate_;
    }
    
    public void setSubject (String subject_)
    {
        subject_ = subject;
    }

    /*public static Email getMailById(int id) {
        Email obj = null;
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", ReadEmail.username, ReadEmail.password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message msg = inbox.getMessage(id);
            System.out.println("Content Type: " + msg.getContentType());
            System.out.println("getContent Returns: " + msg.getContent());
            String message = Misc.getText(msg);

            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
                String sender = address.toString();
                System.out.println("SENT DATE:" + msg.getSentDate());
                Date sentDate = msg.getSentDate();
                System.out.println("SUBJECT:" + msg.getSubject());
                String subject = msg.getSubject();

                obj = new Email(id, message, sender, sentDate, subject);
            }



        } catch (Exception mex) {
            mex.printStackTrace();
        }

        return obj;
    }*/
}
