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
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Main extends AbstractDbTestCase {

    public Main(String name) throws Exception {
        super(name);
    }


    User user = null;
    Group group = null;
    public void test() throws Exception {

        TestingSupportService service = (TestingSupportService) getApplicationContext().getBean("testingSupportService");
        service.doService(new TestingSupportService.TestingAction() {
            public void action(JpaTemplate template) throws Exception {
                template.execute(new JpaCallback() {
                    public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                        Group g = entityManager.find(Group.class, 2);
                        System.out.println("group user size="+g.getUsers().size());

                        User u = entityManager.find(User.class, 14);
                        System.out.println("user group size="+u.getGroups().size());


                        return null;
                    }
                });
            }
        });

        UserManagementService ums = (UserManagementService) getApplicationContext().getBean("userManagementService");
        UiUser user = ums.searchForUser("User1", new PagingInfo(1, 100), true).getUsers().iterator().next();
        UiGroup group1 = ums.searchForGroup("Group1", new PagingInfo(1, 100), true).getGroups().iterator().next();

        group1.removeUser(user);
        ums.updateGroup(group1);

        service.doService(new TestingSupportService.TestingAction() {
            public void action(JpaTemplate template) throws Exception {
                template.execute(new JpaCallback() {
                    public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                        Group g = entityManager.find(Group.class, 2);
                        System.out.println("group user size="+g.getUsers().size());

                        User u = entityManager.find(User.class, 14);
                        System.out.println("user group size="+u.getGroups().size());


                        return null;
                    }
                });
            }
        });
    }

}
