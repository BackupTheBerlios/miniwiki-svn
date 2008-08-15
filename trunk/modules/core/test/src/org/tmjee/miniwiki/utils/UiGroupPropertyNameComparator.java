package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiGroupProperty;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Aug 8, 2008
 * Time: 1:36:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiGroupPropertyNameComparator implements Comparator<UiGroupProperty> {
    public int compare(UiGroupProperty o1, UiGroupProperty o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
