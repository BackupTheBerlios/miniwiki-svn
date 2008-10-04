package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiUserManagementServiceAsync {

    void authenticate(String username, String password, AsyncCallback callback);

    void getUserByName(String username, PagingInfo pagingInfo, boolean exactMatch, AsyncCallback callback);
    void getAllUsers(PagingInfo pagingInfo, AsyncCallback callback);
    void updateUser(UiUser uiUser, AsyncCallback callback);
    void deleteUsers(UiUser[] uiUser, AsyncCallback callback);

    void getGroupByName(String groupName, PagingInfo pagingInfo, boolean exactMatch, AsyncCallback async);
    void getAllGroups(PagingInfo pagingInfo, AsyncCallback callback);
    void updateGroup(UiGroup uiGroup, AsyncCallback callback);
    void deleteGroups(UiGroup[] uiGroup, AsyncCallback callback);
    void getGroupById(long id, AsyncCallback callback);
}
