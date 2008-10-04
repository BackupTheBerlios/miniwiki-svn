package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiSpaceProperty;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiSpacePropertyNameComparator implements Comparator<UiSpaceProperty> {
    public int compare(UiSpaceProperty o1, UiSpaceProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
