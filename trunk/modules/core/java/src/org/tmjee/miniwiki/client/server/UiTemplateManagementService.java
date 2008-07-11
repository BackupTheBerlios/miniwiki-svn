package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiTemplateManagementService extends RemoteService {
    TemplateInfo loadTemplate(String template);
}
