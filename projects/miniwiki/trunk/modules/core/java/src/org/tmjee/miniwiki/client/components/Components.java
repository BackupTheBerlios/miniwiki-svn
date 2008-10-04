package org.tmjee.miniwiki.client.components;

import com.google.gwt.user.client.ui.Widget;

import java.util.Map;
import java.util.HashMap;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Components {

    private static Map widgetRegistry = new HashMap();

    public static void register(String name, Widget widget) {
        widgetRegistry.put(name, widget);        
    }

    public static Map getRegisteredWidgets() {
        return widgetRegistry;
    }

}
