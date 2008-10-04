package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiWikiProperty;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWikiPropertyNameComparator implements Comparator<UiWikiProperty> {

    public int compare(UiWikiProperty o1, UiWikiProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
