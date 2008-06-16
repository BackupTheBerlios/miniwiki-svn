package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.server.LoadTemplateServiceAsync;
import org.tmjee.miniwiki.client.server.LoadTemplateService;
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
        LoadTemplateServiceAsync service = Service.getLoadTemplateService();
        service.loadTemplate(template, asyncCallback);
    }
    

}
