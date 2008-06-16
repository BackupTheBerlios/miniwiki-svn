package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiLoadTemplateService;
import org.tmjee.miniwiki.client.server.TemplateInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 3:36:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiUiLoadTemplateServiceImpl extends RemoteServiceServlet implements UiLoadTemplateService {

    public TemplateInfo loadTemplate(String template) {
        // TODO:
        return new TemplateInfo();    
    }
}
