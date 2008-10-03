package foo.bar.site.controller;

import foo.bar.site.domain.User;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ListUserCommandControllerCommand {

    private List<User> users = new ArrayList<User>();

   

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
