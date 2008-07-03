package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:20:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventObject implements IsSerializable {

    private Serializable source;
    private String propertyName;


    public EventObject() {}
    
    public EventObject(Serializable source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }

    public Serializable getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
