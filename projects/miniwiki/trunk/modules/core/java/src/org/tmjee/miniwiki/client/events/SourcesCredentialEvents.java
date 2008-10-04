package org.tmjee.miniwiki.client.events;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface SourcesCredentialEvents {

    void addCredentialListener(CredentialListener credentialListener);
    void removeCredentialListener(CredentialListener credentialListener);

}
