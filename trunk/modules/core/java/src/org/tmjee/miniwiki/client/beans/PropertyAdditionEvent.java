package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PropertyAdditionEvent extends EventObject {

    private Serializable newValue;

    public PropertyAdditionEvent() {}
    public PropertyAdditionEvent(Serializable source, String propertyName,
                                 Serializable newValue) {
        super(source, propertyName);
        this.newValue = newValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
