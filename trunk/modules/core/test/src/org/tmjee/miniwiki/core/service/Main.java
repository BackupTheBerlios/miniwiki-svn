package org.tmjee.miniwiki.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.core.domain.GroupProperty;

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
        getTestingSupportService().doService(
                new TestingSupportService.TestingAction() {
                    public void action(JpaTemplate template) throws Exception {

                        template.execute(new JpaCallback() {
                            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                                Query query = entityManager.createNamedQuery("getUserByUsername_full");
                                query.setParameter("username", "Toby");

                                for (Object o : query.getResultList()) {
                                    user = (User) o;
                                }
                                return null;
                            }
                        });
                    }
                }
        );

        System.out.println("* "+user);
        group = ((Group)user.getGroups().iterator().next());
        System.out.println("* "+group);
        GroupProperty up = (GroupProperty) group.getProperties().iterator().next();
        System.out.println("* "+up.getName()+"="+up.getValue());


    }

}
