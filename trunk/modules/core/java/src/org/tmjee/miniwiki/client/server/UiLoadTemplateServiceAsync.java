package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 3:36:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UiLoadTemplateServiceAsync {
    void loadTemplate(String template, AsyncCallback callback);
}
