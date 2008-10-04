package org.tmjee.miniwiki.client.beans;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface SourcesPropertyChangeEvents {

    void addPropertyListener(PropertyListener listener);
    void removePropertyListener(PropertyListener listener);

}
