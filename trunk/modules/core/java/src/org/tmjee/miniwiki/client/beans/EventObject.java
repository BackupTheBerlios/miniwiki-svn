package org.tmjee.miniwiki.client.beans;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:20:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventObject {

    private Object source;
    private String propertyName;


    public EventObject(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }

    public Object getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
