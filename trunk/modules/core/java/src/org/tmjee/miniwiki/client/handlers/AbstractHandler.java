package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.UiTemplateManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.utils.DataStore;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class AbstractHandler implements Handler {


    protected void loadTemplate(String wiki, String space, String page, String command, AsyncCallback asyncCallback) {
        UiTemplateManagementServiceAsync service = Service.getTemplateManagementService();
        service.loadTemplate(wiki, space, page, command, asyncCallback);
    }

    public void handle(String wiki, String space, String page, String command, AsyncCallback asyncCallback) {
        DataStore.getInstance().setCurrentAccessedWikiId(wiki);
        DataStore.getInstance().setCurrentAccessedSpaceId(space);
        DataStore.getInstance().setCurrentAccessedPageId(page);

        doHandle(wiki, space, page, command, asyncCallback);
    }

    protected abstract void doHandle(String wiki, String space, String page, String command, AsyncCallback asyncCallback);
}

