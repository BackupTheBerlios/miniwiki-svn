package foo.bar.site.controller;

import foo.bar.site.domain.User;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DeleteUserCommandControllerCommand {

    private int id;
    private List<User> users = new ArrayList<User>();

    private List<Integer> userIds = new ArrayList<Integer>();

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
