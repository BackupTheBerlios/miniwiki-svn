package org.tmjee.miniwiki.client.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiSpacePriviledge extends UiPriviledge {

    private List<UiSpacePriviledgeValue> values = new ArrayList<UiSpacePriviledgeValue>();


    public List<UiSpacePriviledgeValue> getValues() {
        return values;
    }

    public void setValues(List<UiSpacePriviledgeValue> values) {
        this.values = values;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiSpacePriviledge that = (UiSpacePriviledge) o;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
