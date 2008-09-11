package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiWiki;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWikiNameComparator implements Comparator<UiWiki> {

    public int compare(UiWiki o1, UiWiki o2) {
        return return o1.getName().compareTo(o2);
    }
}
