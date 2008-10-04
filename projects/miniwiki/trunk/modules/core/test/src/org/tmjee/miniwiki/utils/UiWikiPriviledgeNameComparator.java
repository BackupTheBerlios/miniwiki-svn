package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiPriviledge;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWikiPriviledgeNameComparator implements Comparator<UiPriviledge> {

    public int compare(UiPriviledge o1, UiPriviledge o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
