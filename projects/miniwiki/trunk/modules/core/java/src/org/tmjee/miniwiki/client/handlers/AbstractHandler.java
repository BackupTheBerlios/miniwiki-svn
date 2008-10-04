package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.server.UiTemplateManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.service.Myself;

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
        Myself.getInstance().setCurrentAccessedWikiId(wiki);
        Myself.getInstance().setCurrentAccessedSpaceId(space);
        Myself.getInstance().setCurrentAccessedPageId(page);

        doHandle(wiki, space, page, command, asyncCallback);
    }

    protected abstract void doHandle(String wiki, String space, String page, String command, AsyncCallback asyncCallback);
}

