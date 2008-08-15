package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiUserProperty;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Aug 11, 2008
 * Time: 11:20:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class UiUserPropertyNameComparator implements Comparator<UiUserProperty> {
    public int compare(UiUserProperty o1, UiUserProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
