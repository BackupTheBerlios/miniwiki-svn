package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUserProperty extends UiProperty {
    public UiUserProperty(String name, String value) {
        super(name, value);
    }

    public UiUserProperty() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiUserProperty uiUserProperty = (UiUserProperty) o;

        if (getName() != null ? !getName().equals(uiUserProperty.getName()) : uiUserProperty.getName() != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
