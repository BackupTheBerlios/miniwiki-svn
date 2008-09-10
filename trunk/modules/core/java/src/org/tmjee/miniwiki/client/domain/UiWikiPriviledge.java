package org.tmjee.miniwiki.client.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWikiPriviledge extends UiPriviledge {

    private List<UiWikiPriviledgeValue> values = new ArrayList<UiWikiPriviledgeValue>();

   

    public List<UiWikiPriviledgeValue> getValues() {
        return values;
    }

    public void setValues(List<UiWikiPriviledgeValue> values) {
        this.values = values;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiSpaceProperty that = (UiSpaceProperty) o;
        if (getName() != null ? ! getName().equals(that.getName()) : that.getName() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }

}
