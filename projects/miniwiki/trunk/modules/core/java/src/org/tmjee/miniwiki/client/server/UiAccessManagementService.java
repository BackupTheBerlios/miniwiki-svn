package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;
import org.tmjee.miniwiki.client.domain.UiGlobalPriviledges;
import org.tmjee.miniwiki.client.domain.UiGroupNames;

import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiAccessManagementService extends RemoteService {

    UiGlobalPriviledges getGlobalPriviledges();

    UiGroupNames getAllGroupNames();

    
}
