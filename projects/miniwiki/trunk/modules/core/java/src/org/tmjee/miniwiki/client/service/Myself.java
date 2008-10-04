package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.domain.UiCredentials;
import org.tmjee.miniwiki.client.events.SourcesCredentialEvents;
import org.tmjee.miniwiki.client.events.CredentialListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import com.google.gwt.core.client.GWT;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Myself implements SourcesCredentialEvents {

    private static Myself INSTANCE;

    public static Myself getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Myself();
        }
        return INSTANCE;
    }


    private UiCredentials credentials;
    private SourcesEventsSupport sourcesEventSupport;

    private String currentAccessedWikiId;
    private String currentAccessedSpaceId;
    private String currentAccessedPageId;

    Myself() {
        credentials = UiCredentials.ANONYMOUS;
        sourcesEventSupport = new SourcesEventsSupport();
    }


    public UiCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UiCredentials credentials) {
        this.credentials = credentials;
    }

    public void setCretentialsToAnonymous() {
        credentials = UiCredentials.ANONYMOUS;
    }

    public void addCredentialListener(CredentialListener credentialListener) {
        sourcesEventSupport.addListener(credentialListener);
    }

    public void removeCredentialListener(CredentialListener credentialListener) {
        sourcesEventSupport.removeListener(credentialListener);
    }

    public String getCurrentAccessedWikiId() {
        return currentAccessedWikiId;
    }

    public void setCurrentAccessedWikiId(String currentAccessedWikiId) {
        this.currentAccessedWikiId = currentAccessedWikiId;
    }

    public String getCurrentAccessedSpaceId() {
        return currentAccessedSpaceId;
    }

    public void setCurrentAccessedSpaceId(String currentAccessedSpaceId) {
        this.currentAccessedSpaceId = currentAccessedSpaceId;
    }

    public String getCurrentAccessedPageId() {
        return currentAccessedPageId;
    }

    public void setCurrentAccessedPageId(String currentAccessedPageId) {
        this.currentAccessedPageId = currentAccessedPageId;
    }
}
