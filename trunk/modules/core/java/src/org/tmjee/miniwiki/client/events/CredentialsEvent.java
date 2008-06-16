package org.tmjee.miniwiki.client.events;

import org.tmjee.miniwiki.client.domain.Credentials;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 9:21:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CredentialsEvent extends Event {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;


    private int type;
    private Credentials oldCredentials;
    private Credentials newCredentials;

    public CredentialsEvent(int type, Credentials oldCredentials, Credentials newCredentials) {
        this.type = type;
        this.oldCredentials = oldCredentials;
        this.newCredentials = newCredentials;
    }

    public int getType() {
        return type;
    }

    public Credentials getOldCredentials() {
        return oldCredentials;
    }

    public Credentials getNewCredentials() {
        return newCredentials;
    }

}
