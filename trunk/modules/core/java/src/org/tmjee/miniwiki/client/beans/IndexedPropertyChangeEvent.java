package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:39:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexedPropertyChangeEvent extends PropertyChangeEvent {

    private int index;

    public IndexedPropertyChangeEvent() {}
    public IndexedPropertyChangeEvent(Serializable source, String propertyName,
                                      Serializable oldValue,
                                      Serializable newValue, int index) {
        super(source, propertyName, oldValue, newValue);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
