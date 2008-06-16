package org.tmjee.miniwiki.client.components;

import com.google.gwt.user.client.ui.Widget;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 6:08:01 PM
 * To change this template use File | Settings | File Templates.
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
