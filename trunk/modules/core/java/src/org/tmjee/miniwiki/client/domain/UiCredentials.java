package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 2:33:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiCredentials implements IsSerializable {

    public static UiCredentials ANONYMOUS = new UiCredentials(
            new UiUser("anonymous", "Aanonymous", "Anonymous"));
    public static UiCredentials SUPERADMIN = new UiCredentials(
            new UiUser("superadmin", "Superadmin", "Superadmin"));

    private UiUser uiUser;

    public UiCredentials() {
    }

    public UiCredentials(UiUser uiUser) {
        this.uiUser = uiUser;
    }

    public void setUser(UiUser uiUser) {
        this.uiUser = uiUser;
    }

    public UiUser getUser() {
        return uiUser;
    }

    public boolean isAnonymous() {
        return ANONYMOUS.equals(this);
    }

}
