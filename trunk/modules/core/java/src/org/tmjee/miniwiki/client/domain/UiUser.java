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
public class UiUser implements IsSerializable, SourcesPropertyChangeEvents {

    private long id;
    private String username;
    private String firstName;
    private String lastName;

    private transient List<UiUserUiProperty> uiUserProperties;
    private transient List<UiGroup> uiGroups;

    private PropertySupport propertySupport;

    public UiUser() {
        uiUserProperties = new ArrayList<UiUserUiProperty>();
        uiGroups = new ArrayList<UiGroup>();
        propertySupport = new PropertySupport();
    }

    public UiUser(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public boolean isInGroup(UiGroup uiGroup) {
        for (UiGroup grp : uiGroups) {
            if (grp.getId() == uiGroup.getId()) {
                return true;
            }
        }
        return false;
    }

    public List<UiGroup> getGroups() {
        return uiGroups;
    }

    public void removeGroup(UiGroup uiGroup) {
        uiGroups.remove(uiGroup);
    }

    public void addGroup(UiGroup uiGroup) {
        uiGroups.add(uiGroup);
    }


    public void removeProperty(UiUserUiProperty propertyUi) {
        uiUserProperties.remove(propertyUi);
        propertySupport.firePropertyDeletion("prop."+ propertyUi.getName(), propertyUi.getValue());
    }

    public void addProperty(UiUserUiProperty propertyUi) {
        uiUserProperties.add(propertyUi);
        propertySupport.firePropertyAddition("prop."+ propertyUi.getName(), propertyUi.getValue());
    }

    public List<UiUserUiProperty> getProperties() {
        return uiUserProperties;
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
