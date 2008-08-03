package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.client.domain.UiCredentials;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.GroupProperty;
import org.tmjee.miniwiki.core.domain.UserProperty;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.HashSet;


/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserManagementServiceTest extends AbstractDbTestCase {

    public UserManagementServiceTest() throws IOException {}


    protected void postSetUp() throws Exception{
        getTestingSupportService().doService(
                new TestingSupportService.TestingAction() {
                    public void action(JpaTemplate template) throws Exception {
                        template.execute(new JpaCallback() {
                            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                                Group group1 = new Group();
                                group1.setName("Group1");
                                group1.setDescription("Group1_Description");
                                group1.addProperty(new GroupProperty("Property1", "Value1"));
                                group1.addProperty(new GroupProperty("Property2", "Value2"));
                                group1.addProperty(new GroupProperty("Property3", "Value3"));

                                Group group2 = new Group();
                                group2.setName("Group2");
                                group2.setDescription("Group2_Description");
                                group2.addProperty(new GroupProperty("Property1", "Value1"));
                                group2.addProperty(new GroupProperty("Property2", "Value2"));


                                User user = new User();
                                user.setUsername("Toby");
                                user.setFirstName("Toby_FirstName");
                                user.setLastName("Toby_LastName");
                                user.setDescription("Toby_Description");
                                user.setPassword("Toby_Password");
                                user.addProperty(new UserProperty("property1", "value1"));
                                user.addProperty(new UserProperty("property2", "value2"));
                                user.addGroup(group1);
                                user.addGroup(group2);

                                entityManager.persist(group1);
                                entityManager.persist(group2);
                                entityManager.persist(user);

                                return null;
                            }
                        });
                    }
                }
        );
    }


    

    public void testQuery() throws Exception {
        getTestingSupportService().doService(
                new TestingSupportService.TestingAction() {
                    public void action(JpaTemplate template) throws Exception {
                        template.execute(new JpaCallback() {
                            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                                Query q = entityManager.createQuery("select count(user) from User as user");
                                assertEquals(0l, q.getSingleResult());

                                return null;
                            }
                        });
                    }
                });
    }




    public void testAuthenticate() throws Exception {
        UserManagementService userManagementService = (UserManagementService) getApplicationContext().getBean("userManagementService");

        /*{
            UiCredentials credentials = userManagementService.authenticate(null, null);
            assertEquals(UiCredentials.ANONYMOUS, credentials);
        }
        {
            UiCredentials credentials = userManagementService.authenticate("", "");
            assertEquals(UiCredentials.ANONYMOUS, credentials);
        }
        {
            UiCredentials credentials = userManagementService.authenticate("nosuchuser", "nosuchpassword");
            assertEquals(UiCredentials.ANONYMOUS, credentials);
        }*/
        {
            UiCredentials credentials = userManagementService.authenticate("Toby", "Toby_Password");
            assertFalse(credentials.isAnonymous());
            assertEquals("Toby", credentials.getUser().getUsername());
            assertEquals("Toby_FirstName", credentials.getUser().getFirstName());
            assertEquals("Toby_LastName", credentials.getUser().getLastName());
            assertEquals("Toby_Description", credentials.getUser().getDescription());
            assertEquals("Toby_Password", credentials.getUser().getPassword());
        }


    }


}
