package org.tmjee.miniwiki.client.utils;

import org.tmjee.miniwiki.client.domain.UiCredentials;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DataStore {

    private static DataStore dataStore;
    private DataStore(){}
    public static DataStore getInstance() {
        if (dataStore == null) {
            dataStore = new DataStore();
        }
        return dataStore;
    }


    private UiCredentials uiCredentials = UiCredentials.ANONYMOUS;
    private String currentAccessedWikiId;
    private String currentAccessedSpaceId;
    private String currentAccessedPageId;

    public UiCredentials getUiCredentials() {
        return uiCredentials;
    }

    public void setUiCredentials(UiCredentials uiCredentials) {
        this.uiCredentials = uiCredentials;
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
