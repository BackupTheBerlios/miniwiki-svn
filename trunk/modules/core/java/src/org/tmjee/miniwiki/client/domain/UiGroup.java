package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 6:43:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiGroup implements IsSerializable {

    private long id;
    private String name;
    private String description;

    private transient List<UiGroupProperty> properties;
    private transient List<UiUser> users;


    public UiGroup() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addProperty(UiGroupProperty property) {
        if (!properties.contains(property)) {
            properties.add(property);
        }
    }

    public void removeProperty(UiGroupProperty property) {
        if (properties.contains(property)) {
            properties.remove(property);
        }
    }

    public List<UiGroupProperty> getProperties() {
        return properties;
    }


    public List<UiUser> getUsers() {
        return users;
    }

    public void addUser(UiUser user) {
        if (!users.contains(user)) {
             users.add(user);
        }
    }

    public void removeUser(UiUser user) {
        if (users.contains(user)) {
            users.remove(user);
        }
    }


}
