package org.tmjee.miniwiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import org.tmjee.miniwiki.client.server.UserManagementService;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.core.Bootstrap;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 26, 2008
 * Time: 10:48:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementServiceImpl extends RemoteServiceServlet implements UserManagementService {

    public Credentials authenticate(String username, String password) {
        //Bootstrap.getInstance().
        return null;    
    }

    public Users searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Users getAllUsers(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Groups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Groups getAllGroups(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateGroup(Group group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteGroup(Group group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(long userId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
