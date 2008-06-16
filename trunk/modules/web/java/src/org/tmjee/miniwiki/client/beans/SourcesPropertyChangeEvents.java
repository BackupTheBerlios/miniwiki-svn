package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 5:52:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SourcesPropertyChangeEvents {

    void addPropertyListener(PropertyListener listener);
    void removePropertyListener(PropertyListener listener);

}
