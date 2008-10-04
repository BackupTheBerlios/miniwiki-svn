package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PropertyDeletionEvent extends EventObject {

    private Serializable oldValue;

    public PropertyDeletionEvent() {}
    public PropertyDeletionEvent(Serializable source, String propertyName,
                                 Serializable oldValue) {
        super(source, propertyName);
        this.oldValue = oldValue;
    }

    public Object getOldValue() {
        return oldValue;
    }
}
