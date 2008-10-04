package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiAccessManagementService;
import org.tmjee.miniwiki.client.domain.UiGlobalPriviledges;
import org.tmjee.miniwiki.core.service.AccessManagementService;
import org.tmjee.miniwiki.core.Bootstrap;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiAccessManagementServiceImpl extends RemoteServiceServlet implements UiAccessManagementService {

    public UiGlobalPriviledges getGlobalPriviledges() {
        return getAccessManagementService().getGlobalPriviledges();        
    }

    protected AccessManagementService getAccessManagementService() {
        return Bootstrap.getInstance().getAccessManagementService();    
    }
}
