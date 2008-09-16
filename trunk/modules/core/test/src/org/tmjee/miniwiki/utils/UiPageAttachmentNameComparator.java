package org.tmjee.miniwiki.utils;

import org.tmjee.miniwiki.client.domain.UiPageAttachment;

import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPageAttachmentNameComparator implements Comparator<UiPageAttachment> {
    public int compare(UiPageAttachment o1, UiPageAttachment o2) {
        return o1.getName().compareTo(o2.getName());    
    }
}
