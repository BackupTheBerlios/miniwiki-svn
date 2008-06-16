package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 2:33:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Credentials implements IsSerializable {

    public static Credentials ANONYMOUS = new Credentials(
            new User("anonymous", "Aanonymous", "Anonymous"));
    public static Credentials SUPERADMIN = new Credentials(
            new User("superadmin", "Superadmin", "Superadmin"));

    private User user;

    public Credentials() {
    }

    public Credentials(User user) {
        this.user = user;    
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isAnonymous() {
        return ANONYMOUS.equals(this);
    }

}
