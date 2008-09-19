package org.tmjee.miniwiki.client.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGlobalPriviledge extends UiPriviledge {

    private List<UiGlobalPriviledgeValue> values = new ArrayList<UiGlobalPriviledgeValue>();

    public List<UiGlobalPriviledgeValue> getValues() {
        return values;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiGlobalPriviledge that = (UiGlobalPriviledge) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
