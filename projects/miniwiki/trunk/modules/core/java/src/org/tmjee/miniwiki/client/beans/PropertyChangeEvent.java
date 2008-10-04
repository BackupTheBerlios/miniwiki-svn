package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PropertyChangeEvent extends EventObject {

    private String propertyName;
    private Serializable oldValue;
    private Serializable newValue;
    private Serializable propagationId;


    public PropertyChangeEvent() {}
    public PropertyChangeEvent(Serializable source, String propertyName,
                               Serializable oldValue, Serializable newValue) {
        super(source, propertyName);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getPropagationId() {
        return propagationId;
    }

    public void setPropagationId(Serializable propagationId) {
        this.propagationId = propagationId;
    }
}
