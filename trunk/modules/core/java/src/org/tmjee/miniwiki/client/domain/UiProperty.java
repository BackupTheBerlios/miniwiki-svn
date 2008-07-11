package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class UiProperty implements IsSerializable {

    private long id;
    private String name;
    private String value;

    public UiProperty() {}

    public UiProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
