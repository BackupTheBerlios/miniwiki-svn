package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.GroupProperty;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.utils.UiGroupNameComparator;
import org.tmjee.miniwiki.utils.UiGroupPropertiesComparator;
import org.tmjee.miniwiki.utils.UiUserUsernameComparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


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


                                User toby = new User();
                                toby.setUsername("Toby");
                                toby.setFirstName("Toby_FirstName");
                                toby.setLastName("Toby_LastName");
                                toby.setDescription("Toby_Description");
                                toby.setPassword("Toby_Password");
                                toby.addProperty(new UserProperty("property1", "value1"));
                                toby.addProperty(new UserProperty("property2", "value2"));
                                toby.addGroup(group1);
                                toby.addGroup(group2);

                                User jim = new User();
                                jim.setUsername("Jim");
                                jim.setFirstName("Jim_FirstName");
                                jim.setLastName("Jim_LastName");
                                jim.setDescription("Jim_Description");
                                jim.setPassword("Jim_Password");
                                jim.addProperty(new UserProperty("prop1", "val1"));
                                jim.addProperty(new UserProperty("prop2", "val2"));
                                jim.addGroup(group1);

                                User user1 = new User();
                                user1.setUsername("User1");
                                user1.setFirstName("User1_FirstName");
                                user1.setLastName("User1_LastName");
                                user1.setDescription("User1_Description");
                                user1.setPassword("User1_Password");

                                User user2 = new User();
                                user2.setUsername("User2");
                                user2.setFirstName("User2_FirstName");
                                user2.setLastName("User2_LastName");
                                user2.setDescription("User2_Description");
                                user2.setPassword("User2_Password");

                                User user3 = new User();
                                user3.setUsername("User3");
                                user3.setFirstName("User3_FirstName");
                                user3.setLastName("User3_LastName");
                                user3.setDescription("User3_Description");
                                user3.setPassword("User3_Password");


                                User user4 = new User();
                                user4.setUsername("User4");
                                user4.setFirstName("User4_FirstName");
                                user4.setLastName("User4_LastName");
                                user4.setDescription("User4_Description");
                                user4.setPassword("User4_Password");

                                User user5 = new User();
                                user5.setUsername("User5");
                                user5.setFirstName("User5_FirstName");
                                user5.setLastName("User5_LastName");
                                user5.setDescription("User5_Description");
                                user5.setPassword("User5_Password");

                                User user6 = new User();
                                user6.setUsername("User6");
                                user6.setFirstName("User6_FirstName");
                                user6.setLastName("User6_LastName");
                                user6.setDescription("User6_Description");
                                user6.setPassword("User6_Password");

                                User user7 = new User();
                                user7.setUsername("User7");
                                user7.setFirstName("User7_FirstName");
                                user7.setLastName("User7_LastName");
                                user7.setDescription("User7_Description");
                                user7.setPassword("User7_Password");

                                User user8 = new User();
                                user8.setUsername("User8");
                                user8.setFirstName("User8_FirstName");
                                user8.setLastName("User8_LastName");
                                user8.setDescription("User8_Description");
                                user8.setPassword("User8_Password");

                                User user9 = new User();
                                user9.setUsername("User9");
                                user9.setFirstName("User9_FirstName");
                                user9.setLastName("User9_LastName");
                                user9.setDescription("User9_Description");
                                user9.setPassword("User9_Password");

                                User user10 = new User();
                                user10.setUsername("User10");
                                user10.setFirstName("User10_FirstName");
                                user10.setLastName("User10_LastName");
                                user10.setDescription("User10_Description");
                                user10.setPassword("User10_Password");

                                User user11 = new User();
                                user11.setUsername("User11");
                                user11.setFirstName("User11_FirstName");
                                user11.setLastName("User11_LastName");
                                user11.setDescription("User11_Description");
                                user11.setPassword("User11_Password");

                                User user12 = new User();
                                user12.setUsername("User12");
                                user12.setFirstName("User12_FirstName");
                                user12.setLastName("User12_LastName");
                                user12.setDescription("User12_Description");
                                user12.setPassword("User12_Password");

                                User user13 = new User();
                                user13.setUsername("User13");
                                user13.setFirstName("User13_FirstName");
                                user13.setLastName("User13_LastName");
                                user13.setDescription("User13_Description");
                                user13.setPassword("User13_Password");

                                User user14 = new User();
                                user14.setUsername("User14");
                                user14.setFirstName("User14_FirstName");
                                user14.setLastName("User14_LastName");
                                user14.setDescription("User14_Description");
                                user14.setPassword("User14_Password");


                                entityManager.persist(group1);
                                entityManager.persist(group2);
                                entityManager.persist(toby);
                                entityManager.persist(jim);
                                entityManager.persist(user1);
                                entityManager.persist(user2);
                                entityManager.persist(user3);
                                entityManager.persist(user4);
                                entityManager.persist(user5);
                                entityManager.persist(user6);
                                entityManager.persist(user7);
                                entityManager.persist(user8);
                                entityManager.persist(user9);
                                entityManager.persist(user10);
                                entityManager.persist(user11);
                                entityManager.persist(user12);
                                entityManager.persist(user13);
                                entityManager.persist(user14);

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



    protected UserManagementService getUserManagementService() {
        return (UserManagementService) getApplicationContext().getBean("userManagementService");
    }


    public void testAuthenticate() throws Exception {
        UserManagementService userManagementService = getUserManagementService();
        {
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
        }
        {
            UiCredentials credentials = userManagementService.authenticate("Toby", "Toby_Password");
            assertFalse(credentials.isAnonymous());
            assertEquals("Toby", credentials.getUser().getUsername());
            assertEquals("Toby_FirstName", credentials.getUser().getFirstName());
            assertEquals("Toby_LastName", credentials.getUser().getLastName());
            assertEquals("Toby_Description", credentials.getUser().getDescription());
            assertEquals("Toby_Password", credentials.getUser().getPassword());
            assertEquals(credentials.getUser().getGroups().size(), 2);

            List<UiGroup> groups = credentials.getUser().getGroups();
            Collections.sort(groups, new UiGroupNameComparator());
            assertEquals(groups.get(0).getName(), "Group1");
            assertEquals(groups.get(0).getDescription(), "Group1_Description");
            assertEquals(groups.get(0).getProperties().size(), 3);
            assertEquals(groups.get(0).getUsers().size(), 2);
            assertEquals(groups.get(1).getName(), "Group2");
            assertEquals(groups.get(1).getDescription(), "Group2_Description");
            assertEquals(groups.get(1).getProperties().size(), 2);
            assertEquals(groups.get(1).getUsers().size(), 1);

            List<UiGroupProperty> group1Properties = groups.get(0).getProperties();
            Collections.sort(group1Properties, new UiGroupPropertiesComparator());
            assertEquals(group1Properties.get(0).getName(), "Property1");
            assertEquals(group1Properties.get(0).getValue(), "Value1");
            assertEquals(group1Properties.get(1).getName(), "Property2");
            assertEquals(group1Properties.get(1).getValue(), "Value2");
            assertEquals(group1Properties.get(2).getName(), "Property3");
            assertEquals(group1Properties.get(2).getValue(), "Value3");

            List<UiGroupProperty> group2Properties = groups.get(1).getProperties();
            Collections.sort(group2Properties, new UiGroupPropertiesComparator());
            assertEquals(group2Properties.get(0).getName(), "Property1");
            assertEquals(group2Properties.get(0).getValue(), "Value1");
            assertEquals(group2Properties.get(1).getName(), "Property2");
            assertEquals(group2Properties.get(1).getValue(), "Value2");

            List<UiUser> group1Users = groups.get(0).getUsers();
            Collections.sort(group1Users, new UiUserUsernameComparator());
            assertEquals(group1Users.get(0).getUsername(), "Jim");
            assertEquals(group1Users.get(0).getFirstName(), "Jim_FirstName");
            assertEquals(group1Users.get(0).getLastName(), "Jim_LastName");
            assertEquals(group1Users.get(0).getPassword(), "Jim_Password");
            assertEquals(group1Users.get(0).getDescription(), "Jim_Description");
            assertEquals(group1Users.get(1).getUsername(), "Toby");
            assertEquals(group1Users.get(1).getFirstName(), "Toby_FirstName");
            assertEquals(group1Users.get(1).getLastName(), "Toby_LastName");
            assertEquals(group1Users.get(1).getPassword(), "Toby_Password");
            assertEquals(group1Users.get(1).getDescription(), "Toby_Description");


            List<UiUser> group2Users = groups.get(1).getUsers();
            Collections.sort(group2Users, new UiUserUsernameComparator());
            assertEquals(group2Users.get(0).getUsername(), "Toby");
            assertEquals(group2Users.get(0).getFirstName(), "Toby_FirstName");
            assertEquals(group2Users.get(0).getLastName(), "Toby_LastName");
            assertEquals(group2Users.get(0).getPassword(), "Toby_Password");
            assertEquals(group2Users.get(0).getDescription(), "Toby_Description");
        }
    }


    public void testSearchForUserNotExact() throws Exception {
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(1, 5), false).getUsers(), 5);
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(2, 5), false).getUsers(), 5);
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(3, 5), false).getUsers(), 4);

    }

    public void testSearchForUserExact() throws Exception {
        
    }


    


}
