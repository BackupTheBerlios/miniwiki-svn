package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiUser;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUserUsernameComparator implements Comparator<UiUser> {
    public int compare(UiUser o1, UiUser o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
