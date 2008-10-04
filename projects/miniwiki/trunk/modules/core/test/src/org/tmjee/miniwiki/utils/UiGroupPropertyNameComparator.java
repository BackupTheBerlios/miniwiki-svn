package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiGroupProperty;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupPropertyNameComparator implements Comparator<UiGroupProperty> {
    public int compare(UiGroupProperty o1, UiGroupProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
