package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;

import org.tmjee.miniwiki.client.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 10:48:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UiUserManagementService extends RemoteService {

    UiCredentials authenticate(String username, String password);

    UiUsers searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch);
    UiUsers getAllUsers(PagingInfo pagingInfo);
    void updateUser(UiUser uiUser);
    void deleteUsers(UiUser[] uiUsers);

    UiGroups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch);
    UiGroups getAllGroups(PagingInfo pagingInfo);
    void updateGroup(UiGroup uiGroup);
    void deleteGroups(UiGroup[] uiGroups);
    
}
