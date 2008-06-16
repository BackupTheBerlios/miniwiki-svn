package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.LoadTemplateService;
import org.tmjee.miniwiki.client.server.TemplateInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 3:36:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadTemplateServiceImpl extends RemoteServiceServlet implements LoadTemplateService {

    public TemplateInfo loadTemplate(String template) {
        return new TemplateInfo();    
    }
}
