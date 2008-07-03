package org.tmjee.miniwiki.client.beans;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 6:21:51 PM
 * To change this template use File | Settings | File Templates.
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
