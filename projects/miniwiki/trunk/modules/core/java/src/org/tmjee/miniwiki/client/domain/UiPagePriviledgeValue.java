package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPagePriviledgeValue extends UiPriviledgeValue {

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiPagePriviledgeValue that = (UiPagePriviledgeValue) o;
        if (getValue() != null ? ! getValue().equals(that.getValue()) : that.getValue() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

}
