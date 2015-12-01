/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

/**
 *
 * @author chris
 */
public class UserData {
    private String username, pswd;

    public UserData(String username, String pswd) {
        this.username = username;
        this.pswd = pswd;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
}
