package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiGroup;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupNameComparator implements Comparator<UiGroup> {

    public int compare(UiGroup o1, UiGroup o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
