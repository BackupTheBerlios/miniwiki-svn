package foo.bar.site.controller;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AddUserCommandControllerCommand {

    private String username="";
    private String password="";
    private String confirmPassword="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void clear() {
        username = null;
        password = null;
        confirmPassword = null;
    }
}
