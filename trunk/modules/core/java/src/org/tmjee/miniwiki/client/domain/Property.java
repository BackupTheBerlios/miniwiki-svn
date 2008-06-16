package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 4:35:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Property implements IsSerializable {

    private long id;
    private String name;
    private String value;

    public Property() {}

    public Property(String name, String value) {
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
