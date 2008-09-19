package org.tmjee.miniwiki.client.domain;

import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupNames {

    private Map<Long, String> groupNames;

    public UiGroupNames(Map<Long, String> groupNames) {
        this.groupNames = groupNames;
    }

    public Map<Long, String> getGroupNames() {
        return groupNames;
    }
}
