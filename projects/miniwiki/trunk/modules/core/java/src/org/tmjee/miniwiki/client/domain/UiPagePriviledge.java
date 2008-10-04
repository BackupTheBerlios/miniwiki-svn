package org.tmjee.miniwiki.client.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPagePriviledge extends UiPriviledge {

    private List<UiPagePriviledgeValue> values = new ArrayList<UiPagePriviledgeValue>();

    public List<UiPagePriviledgeValue> getValues() {
        return values;
    }

    public void setValues(List<UiPagePriviledgeValue> values) {
        this.values = values;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiPagePriviledge that = (UiPagePriviledge) o;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
