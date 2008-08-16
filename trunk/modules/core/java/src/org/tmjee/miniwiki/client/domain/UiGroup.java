package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;
import java.util.ArrayList;

import org.tmjee.miniwiki.client.beans.SourcesPropertyChangeEvents;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.PropertySupport;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroup implements UiIdentifiable, SourcesPropertyChangeEvents {

    private long id;
    private String name;
    private String description;

    private List<UiGroupProperty> properties = new ArrayList<UiGroupProperty>();
    private List<UiUser> users = new ArrayList<UiUser>();

    private transient PropertySupport propertySupport = new PropertySupport();


    public UiGroup() {}
    public UiGroup(String name, String description) {
        this.name = name;
        this.description = description;
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
        String oldValue = this.name;
        this.name = name;
        propertySupport.firePropertyChange("name", oldValue, name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertySupport.firePropertyChange("description", oldValue, description);
    }

    public void addProperty(UiGroupProperty property) {
        if (!properties.contains(property)) {
            properties.add(property);
            propertySupport.firePropertyAddition("property", property);
        }
    }

    public void removeProperty(UiGroupProperty property) {
        if (properties.contains(property)) {
            properties.remove(property);
            propertySupport.firePropertyDeletion("property", property);
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
            user.addGroup(this);
            propertySupport.firePropertyAddition("user", user);
        }
    }

    public void removeUser(UiUser user) {
        if (users.contains(user)) {
            users.remove(user);
            user.removeGroup(this);
            propertySupport.firePropertyDeletion("user", user);
        }
    }


    public void addPropertyListener(PropertyListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyListener(PropertyListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiGroup uiGroup = (UiGroup) o;

        if (name != null ? !name.equals(uiGroup.name) : uiGroup.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (name != null ? name.hashCode() : 0);
        return result;
    }
}
