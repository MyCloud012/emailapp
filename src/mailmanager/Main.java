/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

import java.util.Vector;
import javax.mail.MessagingException;

/**
 *
 * @author Vector
 */
public class Main {

    public static Vector<String> data = new Vector<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        //SendMail obj = new SendMail();

        //ReadEmail obj = new ReadEmail();
        //obj.readMail(ReadEmail.username, ReadEmail.password);
        
         
        Config config = Config.getInstance();
        config.setUsernameAndPassword("public.github@gmail.com", "publicpassword");
        
        Inbox object = new Inbox();
        object.setVisible(true);
       
        
        /*SendMail object = new SendMail(); //
        object.Send(config, "mycloud012@outlook.com", "Hello World", "Welcome ToastKid");
*/


    }
}
