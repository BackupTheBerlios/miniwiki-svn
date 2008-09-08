package org.tmjee.miniwiki.client.utils;

import org.tmjee.miniwiki.client.widgets.AutoRegisteredDialogBox;

import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PopupRegistry {

    private static PopupRegistry popupRegistry;

    private List<AutoRegisteredDialogBox> popups = new ArrayList<AutoRegisteredDialoagBox>();

    private PopupRegistry() {}

    public static PopupRegistry getInstance() {
        if (popupRegistry == null) {
            popupRegistry = new PopupRegistry();
        }
        return popupRegistry;
    }


    public void register(AutoRegisteredDialogBox popup) {
        if (!popups.contains(popup)) {
            popups.add(popup);
        }
    }

    public void unregister(AutoRegisteredDialogBox popup) {
        if (popups.contains(popup)) {
            popups.remove(popup);    
        }
    }

}
