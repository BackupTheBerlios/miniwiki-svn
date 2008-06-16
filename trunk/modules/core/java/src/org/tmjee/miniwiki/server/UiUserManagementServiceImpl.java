package org.tmjee.miniwiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.tmjee.miniwiki.client.server.UiUserManagementService;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.UserManagementService;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 10:48:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class UiUserManagementServiceImpl extends RemoteServiceServlet implements UiUserManagementService {

    public UiCredentials authenticate(String username, String password) {
        return getUserManagementService().authenticate(username, password);
    }

    public UiUsers searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch) {
        return getUserManagementService().searchForUser(username, pagingInfo, exactMatch);
    }

    public UiUsers getAllUsers(PagingInfo pagingInfo) {
        return getUserManagementService().getAllUsers(pagingInfo);
    }

    public void updateUser(UiUser uiUser) {
        getUserManagementService().updateUser(uiUser);
    }

    public void deleteUser(UiUser uiUser) {
        getUserManagementService().deleteUser(uiUser);
    }

    public UiGroups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch) {
        return getUserManagementService().searchForGroup(groupName, pagingInfo, exactMatch);
    }

    public UiGroups getAllGroups(PagingInfo pagingInfo) {
        return getUserManagementService().getAllGroups(pagingInfo);
    }

    public void updateGroup(UiGroup uiGroup) {
        getUserManagementService().updateGroup(uiGroup);
    }

    public void deleteGroup(UiGroup uiGroup) {
        getUserManagementService().deleteGroup(uiGroup);
    }

    protected UserManagementService getUserManagementService() {
        UserManagementService userManagementService = Bootstrap.getInstance().getUserManagementService();
        return userManagementService;
    }
}
