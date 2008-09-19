package org.tmjee.miniwiki.client.server;

import org.tmjee.miniwiki.client.domain.UiGlobalPriviledges;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiAccessManagementServiceAsync {
    void getGlobalPriviledges(AsyncCallback callback);
}
