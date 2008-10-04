package org.tmjee.miniwiki.client.server;

import org.tmjee.miniwiki.client.domain.UiGlobalPriviledges;
import org.tmjee.miniwiki.client.domain.UiGroupNames;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiAccessManagementServiceAsync {
    void getGlobalPriviledges(AsyncCallback callback);

    void getAllGroupNames(AsyncCallback callback);
}
