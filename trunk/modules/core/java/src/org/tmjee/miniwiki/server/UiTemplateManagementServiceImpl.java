package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiTemplateManagementService;
import org.tmjee.miniwiki.client.server.TemplateInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiTemplateManagementServiceImpl extends RemoteServiceServlet implements UiTemplateManagementService {

    public TemplateInfo loadTemplate(String template) {
        // TODO:
        return new TemplateInfo();    
    }
}
