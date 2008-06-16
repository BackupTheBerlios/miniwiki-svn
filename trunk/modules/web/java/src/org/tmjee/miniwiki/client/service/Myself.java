package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.domain.Credentials;
import org.tmjee.miniwiki.client.events.SourcesCredentialEvents;
import org.tmjee.miniwiki.client.events.CredentialListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 8:46:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Myself implements SourcesCredentialEvents {

    private static Myself INSTANCE;

    public static Myself getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Myself();
        }
        return INSTANCE;
    }


    private Credentials credentials = Credentials.ANONYMOUS;
    private SourcesEventsSupport sourcesEventSupport;

    private Myself() {
        sourcesEventSupport = new SourcesEventsSupport();
    }


    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setCretentialsToAnonymous() {
        credentials = Credentials.ANONYMOUS;
    }

    public void addCredentialListener(CredentialListener credentialListener) {
        sourcesEventSupport.addListener(credentialListener);
    }

    public void removeCredentialListener(CredentialListener credentialListener) {
        sourcesEventSupport.removeListener(credentialListener);
    }
}
