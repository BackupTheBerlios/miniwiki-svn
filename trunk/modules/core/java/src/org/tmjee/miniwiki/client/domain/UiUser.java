package org.tmjee.miniwiki.client.domain;


import java.util.List;
import java.util.ArrayList;

import org.tmjee.miniwiki.client.beans.SourcesPropertyChangeEvents;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.PropertySupport;


/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUser implements UiIdentifiable, SourcesPropertyChangeEvents {

    private long id;
    private boolean enabled;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String password;

    private List<UiUserProperty> uiUserProperties = new ArrayList<UiUserProperty>();
    private List<UiGroup> uiGroups = new ArrayList<UiGroup>();

    private List<UiGroup> removedUiGroups = new ArrayList<UiGroup>();
    private List<UiUserProperty> removedUiUserProperties = new ArrayList<UiUserProperty>();

    private transient PropertySupport propertySupport = new PropertySupport();

    public UiUser() {
    }

    public UiUser(String username, String firstName, String lastName, String description) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }


    public boolean isInGroup(UiGroup uiGroup) {
        for (UiGroup grp : uiGroups) {
            if (grp.getId() == uiGroup.getId()) {
                return true;
            }
        }
        return false;
    }

    public void setGroups(List<UiGroup> groups) {
        this.uiGroups = groups;
    }

    public List<UiGroup> getGroups() {
        return uiGroups;
    }

    public List<UiGroup> getRemovedUiGroup() {
        return removedUiGroups;
    }

    public void removeGroup(UiGroup uiGroup) {
        if (uiGroups.contains(uiGroup)) {
            if (!removedUiGroups.contains(uiGroup)) {
                removedUiGroups.add(uiGroup);
            }
            uiGroups.remove(uiGroup);
            uiGroup.removeUser(this);
            propertySupport.firePropertyDeletion("group", uiGroup);
        }
    }

    public void addGroup(UiGroup uiGroup) {
        if (!uiGroups.contains(uiGroup)) {
            if (removedUiGroups.contains(uiGroup)) {
                removedUiGroups.remove(uiGroup);
            }
            uiGroups.add(uiGroup);
            uiGroup.addUser(this);
            propertySupport.firePropertyAddition("group", uiGroup);
        }
    }


    public List<UiUserProperty> getRemovedProperties() {
        return removedUiUserProperties;
    }

    public void removeProperty(UiUserProperty propertyUi) {
        if (uiUserProperties.contains(propertyUi)) {
            if (!removedUiUserProperties.contains(propertyUi)) {
                removedUiUserProperties.add(propertyUi);
            }
            uiUserProperties.remove(propertyUi);
            propertySupport.firePropertyDeletion("property", propertyUi);
        }
    }

    public void addProperty(UiUserProperty propertyUi) {
        if (!uiUserProperties.contains(propertyUi)) {
            if (removedUiUserProperties.contains(propertyUi)) {
                removedUiUserProperties.remove(propertyUi);
            }
            uiUserProperties.add(propertyUi);
            propertySupport.firePropertyAddition("property", propertyUi);
        }
    }

    public List<UiUserProperty> getProperties() {
        return uiUserProperties;
    }

    public void setProperties(List<UiUserProperty> properties) {
        this.uiUserProperties = properties;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        long oldId = this.id;
        this.id = id;
        propertySupport.firePropertyChange("id", oldId, this.id);

    }

    public void setEnabled(boolean enabled) {
        boolean oldEnabled = this.enabled;
        this.enabled = enabled;
        propertySupport.firePropertyChange("enabled", oldEnabled, this.enabled);
    }

    public boolean isEnabled() {
        return enabled;
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

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        propertySupport.firePropertyChange("description", oldDescription, this.description);
    }

    public String getDescription() {
        return description;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        propertySupport.firePropertyChange("password", oldPassword, this.password);
    }

    public String getPassword() {
        return password;
    }

    public void addPropertyListener(PropertyListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyListener(PropertyListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }


    public String toString() {
        return "id=["+id+"],username=["+username+"],firstName=["+firstName+"],lastName=["+lastName+"],password=["+password+"],"+
                "description=["+description+"],properties=["+uiUserProperties+"],groups=["+uiGroups+"]";
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UiUser)) return false;
        UiUser uiUser = (UiUser) o;
        if (username != null ? !username.equals(uiUser.username) : uiUser.username != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (username != null ? username.hashCode() : 0);
        return result;
    }
}
