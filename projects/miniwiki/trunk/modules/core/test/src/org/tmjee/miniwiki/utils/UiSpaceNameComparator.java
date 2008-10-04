package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiSpace;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ Id$
 */
public class UiSpaceNameComparator implements Comparator<UiSpace> {
    public int compare(UiSpace o1, UiSpace o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
