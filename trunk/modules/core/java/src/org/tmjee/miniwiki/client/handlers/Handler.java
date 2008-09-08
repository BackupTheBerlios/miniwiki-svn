package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface Handler {
    void handle(String wiki, String space, String page, String command, AsyncCallback asyncCallback);
}
