package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
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
