package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;


/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserManagementServiceTest extends AbstractDbTestCase {

    public UserManagementServiceTest() throws IOException {}

    public void test1() throws Exception {
        getTestingSupportService().doService(
                new TestingSupportService.TestingAction() {
                    public void action(JpaTemplate template) throws Exception {
                        template.execute(new JpaCallback() {
                            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                                Query q = entityManager.createQuery("select count(user) from User as user");
                                System.out.println(q.getSingleResult());
                                
                                return null;
                            }
                        });
                    }
                });
    }

}
