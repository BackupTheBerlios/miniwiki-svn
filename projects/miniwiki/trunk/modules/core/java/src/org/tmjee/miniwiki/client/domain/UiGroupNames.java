package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Map;
import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupNames implements IsSerializable, Serializable {

    private Map<Long, String> groupNames;

    public UiGroupNames(Map<Long, String> groupNames) {
        this.groupNames = groupNames;
    }

    public Map<Long, String> getGroupNames() {
        return groupNames;
    }
}
