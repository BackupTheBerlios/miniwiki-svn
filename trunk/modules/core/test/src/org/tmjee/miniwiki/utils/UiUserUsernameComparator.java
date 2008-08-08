package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiUser;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Aug 7, 2008
 * Time: 6:55:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiUserUsernameComparator implements Comparator<UiUser> {
    public int compare(UiUser o1, UiUser o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
