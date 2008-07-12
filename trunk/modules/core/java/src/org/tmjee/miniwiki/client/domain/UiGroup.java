package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroup implements UiIdentifiable {

    private long id;
    private String name;
    private String description;

    private List<UiGroupProperty> properties;
    private List<UiUser> users;


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
