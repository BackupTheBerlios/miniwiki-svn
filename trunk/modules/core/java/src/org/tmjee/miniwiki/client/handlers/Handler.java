package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 2:23:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Handler {
    void handle(String space, String page, String command, AsyncCallback asyncCallback);
}
