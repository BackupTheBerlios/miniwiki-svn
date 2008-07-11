package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
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
