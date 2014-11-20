/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailmanager;

/**
 *
 * @author Vector <your.name at your.org>
 */
public class Config {
    
    public String username;
    public String password;
    
    private Config()
    {
    }
    
    public String getUserName()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setUsernameAndPassword(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    private static Config instance = null;

     /*
      * Config instance
      */
    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
}