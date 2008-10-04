package org.tmjee.miniwiki.client.events;

import org.tmjee.miniwiki.client.domain.UiCredentials;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class CredentialsEvent extends Event {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;


    private int type;
    private UiCredentials oldCredentials;
    private UiCredentials newCredentials;

    public CredentialsEvent(int type, UiCredentials oldCredentials, UiCredentials newCredentials) {
        this.type = type;
        this.oldCredentials = oldCredentials;
        this.newCredentials = newCredentials;
    }

    public int getType() {
        return type;
    }

    public UiCredentials getOldCredentials() {
        return oldCredentials;
    }

    public UiCredentials getNewCredentials() {
        return newCredentials;
    }

}
