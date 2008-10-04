package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.core.service.PriviledgeValueState;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGlobalPriviledgeValue extends UiPriviledgeValue {

    public UiGlobalPriviledgeValue() {
    }

    public UiGlobalPriviledgeValue(PriviledgeValueState state) {
        setValue(state.name());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiGlobalPriviledgeValue that = (UiGlobalPriviledgeValue) o;

        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

        
}
