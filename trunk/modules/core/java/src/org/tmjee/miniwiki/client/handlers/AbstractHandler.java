package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.UiTemplateManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class AbstractHandler implements Handler {


    protected void loadTemplate(String template, AsyncCallback asyncCallback) {
        UiTemplateManagementServiceAsync service = Service.getTemplateManagementService();
        service.loadTemplate(template, asyncCallback);
    }
    

}
