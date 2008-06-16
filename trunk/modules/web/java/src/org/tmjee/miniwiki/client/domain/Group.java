package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 6:43:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Group implements IsSerializable {

    private long id;
    private String name;
    private String description;

    public Group() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
