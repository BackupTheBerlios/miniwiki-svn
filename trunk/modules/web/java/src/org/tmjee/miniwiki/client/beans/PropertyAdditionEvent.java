package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 6:53:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyAdditionEvent extends EventObject {

    private Object newValue;

    public PropertyAdditionEvent(Object source, String propertyName,
                                 Object newValue) {
        super(source, propertyName);
        this.newValue = newValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
