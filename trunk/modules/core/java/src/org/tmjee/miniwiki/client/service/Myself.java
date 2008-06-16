package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.domain.Credentials;
import org.tmjee.miniwiki.client.events.SourcesCredentialEvents;
import org.tmjee.miniwiki.client.events.CredentialListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import com.google.gwt.core.client.GWT;

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
        GWT.log("1", null);
        if (INSTANCE == null) {
            GWT.log("2="+INSTANCE, null);
            INSTANCE = new Myself();
            GWT.log("3="+INSTANCE, null);
        }
        GWT.log("4="+INSTANCE, null);
        return INSTANCE;
    }


    private Credentials credentials;
    private SourcesEventsSupport sourcesEventSupport;

    Myself() {
        credentials = Credentials.ANONYMOUS;
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
