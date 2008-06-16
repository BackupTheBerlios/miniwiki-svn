package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.domain.User;
import org.tmjee.miniwiki.client.domain.Group;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 10:48:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserManagementServiceAsync {

    void authenticate(String username, String password, AsyncCallback callback);

    void searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch, AsyncCallback callback);
    void getAllUsers(PagingInfo pagingInfo, AsyncCallback callback);
    void updateUser(User user, AsyncCallback callback);
    void deleteUser(User user, AsyncCallback callback);

    void searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch, AsyncCallback async);
    void getAllGroups(PagingInfo pagingInfo, AsyncCallback callback);
    void updateGroup(Group group, AsyncCallback callback);
    void deleteGroup(Group group, AsyncCallback callback);




}
