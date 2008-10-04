package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiTemplateManagementServiceAsync {
    void loadTemplate(String wiki, String space, String page, String command, AsyncCallback callback);
}
