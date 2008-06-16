package org.tmjee.miniwiki.client.events;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 9:20:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SourcesCredentialEvents {

    void addCredentialListener(CredentialListener credentialListener);
    void removeCredentialListener(CredentialListener credentialListener);

}
