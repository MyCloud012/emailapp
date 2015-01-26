/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

/**
 *
 * @author Vector
 */
public class Misc {
    
    
    public static boolean textIsHtml = false;

    /**
     * Return the primary text content of the message.
     */
    public static String getText(Part p) throws
                MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    //System.out.println("multipart/alternative getText -> " + text);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                    //System.out.println("text/html getText -> " + s);
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
                //System.out.println("multipart/* getText -> " + s);
                
            }
        }

        return null;
    }
    
}
