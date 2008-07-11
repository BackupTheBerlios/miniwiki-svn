package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PageHandler extends AbstractHandler {
    
    public void handle(String space, String page, String command, AsyncCallback callback) {
        loadTemplate("page", callback);
    }

    

}
