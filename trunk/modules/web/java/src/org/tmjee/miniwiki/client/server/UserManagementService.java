package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

import org.tmjee.miniwiki.client.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 10:48:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserManagementService extends RemoteService {

    Credentials authenticate(String username, String password);

    Users searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch);
    Users getAllUsers(PagingInfo pagingInfo);
    void updateUser(User user);
    void deleteUser(User user);

    Groups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch);
    Groups getAllGroups(PagingInfo pagingInfo);
    void updateGroup(Group group);
    void deleteGroup(Group group);
    
}
