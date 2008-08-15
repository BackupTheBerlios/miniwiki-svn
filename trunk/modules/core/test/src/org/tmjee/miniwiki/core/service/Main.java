package org.tmjee.miniwiki.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.core.domain.GroupProperty;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.UiUsers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Main extends AbstractDbTestCase {

    public Main() throws Exception {
    }


    User user = null;
    Group group = null;
    public void test() throws Exception {
        UserManagementService userManagementService = (UserManagementService) getApplicationContext().getBean("userManagementService");
        //System.out.println(userManagementService.getAllGroups(new PagingInfo(1, 2)).getGroups().size());
        //System.out.println(userManagementService.getAllUsers(new PagingInfo(1, 5)).getUsers().size());
        System.out.println(userManagementService.searchForGroup("group", new PagingInfo(1, 2), false).getGroups().size());
    }

}
