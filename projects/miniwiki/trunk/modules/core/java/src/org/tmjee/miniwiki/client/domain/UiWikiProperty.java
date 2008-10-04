package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$
 */
public class UiWikiProperty extends UiProperty {

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiWikiProperty that = (UiWikiProperty) o;
        if (getName() != null ? ! getName().equals(that.getName()) : that.getName() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
