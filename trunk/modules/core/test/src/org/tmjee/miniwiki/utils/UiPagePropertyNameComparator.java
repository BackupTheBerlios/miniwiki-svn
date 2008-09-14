package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiPageProperty;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPagePropertyNameComparator implements Comparator<UiPageProperty> {
    public int compare(UiPageProperty o1, UiPageProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
