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

    public static Credentials ANONYMOUS = null;
    public static Credentials SUPERADMIN = null;

    static {
        ANONYMOUS = new Credentials();
        User anonymousUser = new User();
        anonymousUser.setUsername("Anonymous");
        anonymousUser.setFirstName("Anonymous");
        anonymousUser.setLastName("Anonymous");

        SUPERADMIN = new Credentials();
        User superAdmin = new User();
        superAdmin.setUsername("Superadmin");
        superAdmin.setFirstName("Superadmin");
        superAdmin.setLastName("Superadmin");
    }

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
