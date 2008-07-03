package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.UiTemplateManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 3:27:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHandler implements Handler {


    protected void loadTemplate(String template, AsyncCallback asyncCallback) {
        UiTemplateManagementServiceAsync service = Service.getTemplateManagementService();
        service.loadTemplate(template, asyncCallback);
    }
    

}
