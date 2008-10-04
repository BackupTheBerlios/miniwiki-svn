package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiSpacePriviledge;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiSpacePriviledgeNameComparator implements Comparator<UiSpacePriviledge> {
    public int compare(UiSpacePriviledge o1, UiSpacePriviledge o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
