package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:19:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyChangeEvent extends EventObject {

    private String propertyName;
    private Object oldValue;
    private Object newValue;
    private Object propagationId;

    public PropertyChangeEvent(Object source, String propertyName,
                               Object oldValue, Object newValue) {
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

    public void setPropagationId(Object propagationId) {
        this.propagationId = propagationId;
    }
}
