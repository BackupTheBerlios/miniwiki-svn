package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 6:21:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyDeletionEvent extends EventObject {

    private Object oldValue;

    public PropertyDeletionEvent(Object source, String propertyName,
                                 Object oldValue) {
        super(source, propertyName);
        this.oldValue = oldValue;
    }

    public Object getOldValue() {
        return oldValue;
    }
}
