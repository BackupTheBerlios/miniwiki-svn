package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.core.service.PriviledgeValueState;

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

    public boolean getValueAsBoolean() {
        if (values.size() > 0) {
            UiGlobalPriviledgeValue value = values.get(0);
            if (PriviledgeValueState.state(value.getValue()).equals(PriviledgeValueState.ON)) {
                return true;
            }
        }
        return false;
    }

    public void setValueAsBoolean(boolean value) {
        if (values.size() <= 0) {
            values.add(new UiGlobalPriviledgeValue());
        }
        values.get(0).setValue(PriviledgeValueState.state(value).name());
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
