package org.tmjee.miniwiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.tmjee.miniwiki.client.server.UiUserManagementService;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.UserManagementService;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUserManagementServiceImpl extends RemoteServiceServlet implements UiUserManagementService {

    public UiCredentials authenticate(String username, String password) {
        return getUserManagementService().authenticate(username, password);
    }

    public UiUsers getUserByName(String username, PagingInfo pagingInfo, boolean exactMatch) {
        return getUserManagementService().getUserByName(username, pagingInfo, exactMatch);
    }

    public UiUsers getAllUsers(PagingInfo pagingInfo) {
        return getUserManagementService().getAllUsers(pagingInfo);
    }

    public void updateUser(UiUser uiUser) {
        getUserManagementService().updateUser(uiUser);
    }

    public void deleteUsers(UiUser[] uiUsers) {
        getUserManagementService().deleteUsers(uiUsers);
    }

    public UiGroups getGroupByName(String groupName, PagingInfo pagingInfo, boolean exactMatch) {
        return getUserManagementService().getGroupByName(groupName, pagingInfo, exactMatch);
    }

    public UiGroups getAllGroups(PagingInfo pagingInfo) {
        try {
            return getUserManagementService().getAllGroups(pagingInfo);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateGroup(UiGroup uiGroup) {
        getUserManagementService().updateGroup(uiGroup);
    }

    public void deleteGroups(UiGroup[] uiGroups) {
        getUserManagementService().deleteGroups(uiGroups);
    }

    public UiGroup getGroupById(long id) {
        return getUserManagementService().getGroupById(id);        
    }

    protected UserManagementService getUserManagementService() {
        UserManagementService userManagementService = Bootstrap.getInstance().getUserManagementService();
        return userManagementService;
    }
}
