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
    
    private String username;
    private String password;
    
    Config(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public String getUserName()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
}
