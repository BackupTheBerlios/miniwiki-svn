package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 2:23:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpaceHandler extends AbstractHandler {
    public void handle(String space, String page, String command, AsyncCallback callback) {
        loadTemplate("space", callback);
    }
}
