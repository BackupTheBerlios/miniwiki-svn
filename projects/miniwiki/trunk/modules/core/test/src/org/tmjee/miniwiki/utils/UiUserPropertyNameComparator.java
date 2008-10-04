package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiUserProperty;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUserPropertyNameComparator implements Comparator<UiUserProperty> {
    public int compare(UiUserProperty o1, UiUserProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
