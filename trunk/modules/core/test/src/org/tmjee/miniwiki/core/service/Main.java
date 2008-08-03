package org.tmjee.miniwiki.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

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


    public void test() throws Exception {
        getTestingSupportService().doService(
                new TestingSupportService.TestingAction() {
                    public void action(JpaTemplate template) throws Exception {
                        template.execute(new JpaCallback() {
                            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                                Query query = entityManager.createNamedQuery("getUserByUsername_full");
                                query.setParameter("username", "Toby");

                                for (Object o : query.getResultList()) {
                                    System.out.println("*** "+o);
                                }

                                return null;
                            }
                        });
                    }
                }
        );
    }

}
