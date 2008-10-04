package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiPage;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPageNameComparator implements Comparator<UiPage> {
    public int compare(UiPage o1, UiPage o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
