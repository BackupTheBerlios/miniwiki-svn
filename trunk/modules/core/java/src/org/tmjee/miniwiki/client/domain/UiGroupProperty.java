package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.client.beans.PropertySupport;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupProperty extends UiProperty {

    public UiGroupProperty() {}
    public UiGroupProperty(String name, String value) {
        super(name, value);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiGroupProperty uiGroupProperty = (UiGroupProperty) o;

        if (getName() != null ? !getName().equals(uiGroupProperty.getName()) : uiGroupProperty.getName() != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
