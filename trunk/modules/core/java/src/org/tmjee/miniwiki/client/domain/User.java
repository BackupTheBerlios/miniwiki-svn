package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;


import java.util.List;
import java.util.ArrayList;

import org.tmjee.miniwiki.client.beans.SourcesPropertyChangeEvents;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.PropertySupport;


/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 5:41:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class User implements IsSerializable, SourcesPropertyChangeEvents {

    private long id;
    private String username;
    private String firstName;
    private String lastName;

    private transient List<UserProperty> userProperties;
    private transient List<Group> groups;

    private PropertySupport propertySupport;

    public User() {
        userProperties = new ArrayList<UserProperty>();
        groups = new ArrayList<Group>();
        propertySupport = new PropertySupport();
    }

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public boolean isInGroup(Group group) {
        for (Group grp : groups) {
            if (grp.getId() == group.getId()) {
                return true;
            }
        }
        return false;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void addGroup(Group group) {
        groups.add(group);    
    }


    public void removeProperty(UserProperty property) {
        userProperties.remove(property);
        propertySupport.firePropertyDeletion("prop."+property.getName(), property.getValue());
    }

    public void addProperty(UserProperty property) {
        userProperties.add(property);
        propertySupport.firePropertyAddition("prop."+property.getName(), property.getValue());
    }

    public List<UserProperty> getProperties() {
        return userProperties;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        long oldId = this.id;
        this.id = id;
        propertySupport.firePropertyChange("id", oldId, this.id);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        propertySupport.firePropertyChange("username", oldUsername, this.username);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertySupport.firePropertyChange("firstName", oldFirstName, this.firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertySupport.firePropertyChange("lastName", oldLastName, this.lastName);
    }

    public void addPropertyListener(PropertyListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyListener(PropertyListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
