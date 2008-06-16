package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:39:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class IndexedPropertyChangeEvent extends PropertyChangeEvent {

    private int index;

    public IndexedPropertyChangeEvent(Object source, String propertyName, Object oldValue,
                                      Object newValue, int index) {
        super(source, propertyName, oldValue, newValue);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
