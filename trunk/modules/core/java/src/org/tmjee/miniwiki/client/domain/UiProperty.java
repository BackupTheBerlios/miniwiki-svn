package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

import org.tmjee.miniwiki.client.beans.PropertySupport;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class UiProperty implements UiIdentifiable {

    private long id;
    private String name;
    private String value;

    private transient PropertySupport propertySupport = new PropertySupport();

    public UiProperty() {}

    public UiProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        long oldId = this.id;
        this.id = id;
        propertySupport.firePropertyChange("id", oldId, this.id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertySupport.firePropertyChange("name", oldName, this.name);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        propertySupport.firePropertyChange("value", oldValue, this.value);
    }
}
