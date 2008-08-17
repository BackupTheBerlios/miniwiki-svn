package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.GroupProperty;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.utils.UiGroupNameComparator;
import org.tmjee.miniwiki.utils.UiGroupPropertyNameComparator;
import org.tmjee.miniwiki.utils.UiUserUsernameComparator;
import org.tmjee.miniwiki.utils.UiUserPropertyNameComparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import net.sf.dozer.util.mapping.BeanFactoryIF;


/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserManagementServiceTest extends AbstractDbTestCase {

    public UserManagementServiceTest(String name) throws IOException {
        super(name);
    }


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


                                Group group3 = new Group();
                                group3.setName("Group3");
                                group3.setDescription("Group3_Description");
                                group3.addProperty(new GroupProperty("Property1", "Value1"));
                                group3.addProperty(new GroupProperty("Property2", "Value2"));


                                Group group4 = new Group();
                                group4.setName("Group4");
                                group4.setDescription("Group4_Description");
                                group4.addProperty(new GroupProperty("Property1", "Value1"));
                                group4.addProperty(new GroupProperty("Property2", "Value2"));


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
                                entityManager.persist(group3);
                                entityManager.persist(group4);
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
            assertEquals(credentials.getUser().getProperties().size(), 2);

            List<UiUserProperty> tobyUserProperties = credentials.getUser().getProperties();
            Collections.sort(tobyUserProperties, new UiUserPropertyNameComparator());
            assertEquals("Property1", tobyUserProperties.get(0).getName());
            assertEquals("Value1", tobyUserProperties.get(0).getValue());
            assertEquals("Property2", tobyUserProperties.get(1).getName());
            assertEquals("Value2", tobyUserProperties.get(1).getValue());

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
            Collections.sort(group1Properties, new UiGroupPropertyNameComparator());
            assertEquals(group1Properties.get(0).getName(), "Property1");
            assertEquals(group1Properties.get(0).getValue(), "Value1");
            assertEquals(group1Properties.get(1).getName(), "Property2");
            assertEquals(group1Properties.get(1).getValue(), "Value2");
            assertEquals(group1Properties.get(2).getName(), "Property3");
            assertEquals(group1Properties.get(2).getValue(), "Value3");

            List<UiGroupProperty> group2Properties = groups.get(1).getProperties();
            Collections.sort(group2Properties, new UiGroupPropertyNameComparator());
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
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(1, 5), false).getUsers().size(), 5);
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(2, 5), false).getUsers().size(), 5);
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(3, 5), false).getUsers().size(), 4);
        assertEquals(getUserManagementService().searchForUser("user", new PagingInfo(4, 5), false).getUsers().size(), 0);
    }

    public void testSearchForUserExact() throws Exception {
        assertEquals(getUserManagementService().searchForUser("User5", new PagingInfo(1, 10), true).getUsers().size(), 1);
        assertEquals(getUserManagementService().searchForUser("Toby", new PagingInfo(1, 10), true).getUsers().size(), 1);
        assertEquals(getUserManagementService().searchForUser("Toby", new PagingInfo(2, 10), true).getUsers().size(), 0);
        assertEquals(getUserManagementService().searchForUser("NoSuchUser", new PagingInfo(1, 10), true).getUsers().size(), 0);
    }


    public void testGetAllUser() throws Exception {
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(1, 5)).getUsers().size(), 5);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(2, 5)).getUsers().size(), 5);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(3, 5)).getUsers().size(), 5);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(4, 5)).getUsers().size(), 1);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(5, 5)).getUsers().size(), 0);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(1, 20)).getUsers().size(), 16);
        assertEquals(getUserManagementService().getAllUsers(new PagingInfo(2, 20)).getUsers().size(), 0);
    }


    public void testSearchForGroupNotExact() throws Exception {
        UiGroups _groups = getUserManagementService().searchForGroup("group", new PagingInfo(1, 10), false);
        List<UiGroup> groups = _groups.getGroups();
        Collections.sort(groups, new UiGroupNameComparator());

        assertEquals(groups.size(), 4);
        assertEquals(groups.get(0).getName(), "Group1");
        assertEquals(groups.get(0).getDescription(), "Group1_Description");
        assertEquals(groups.get(0).getProperties().size(), 3);
        assertEquals(groups.get(0).getUsers().size(), 2);
        assertEquals(groups.get(1).getName(), "Group2");
        assertEquals(groups.get(1).getDescription(), "Group2_Description");
        assertEquals(groups.get(1).getProperties().size(), 2);
        assertEquals(groups.get(1).getUsers().size(), 1);
        assertEquals(groups.get(2).getName(), "Group3");
        assertEquals(groups.get(2).getDescription(), "Group3_Description");
        assertEquals(groups.get(2).getProperties().size(), 2);
        assertEquals(groups.get(2).getUsers().size(), 0);
        assertEquals(groups.get(3).getName(), "Group4");
        assertEquals(groups.get(3).getDescription(), "Group4_Description");
        assertEquals(groups.get(3).getProperties().size(), 2);
        assertEquals(groups.get(3).getUsers().size(), 0);

        // group 1 properties
        List<UiGroupProperty> group1Properties = groups.get(0).getProperties();
        Collections.sort(group1Properties, new UiGroupPropertyNameComparator());
        assertEquals(group1Properties.get(0).getName(), "Property1");
        assertEquals(group1Properties.get(0).getValue(), "Value1");
        assertEquals(group1Properties.get(1).getName(), "Property2");
        assertEquals(group1Properties.get(1).getValue(), "Value2");
        assertEquals(group1Properties.get(2).getName(), "Property3");
        assertEquals(group1Properties.get(2).getValue(), "Value3");

        // group 2 properties
        List<UiGroupProperty> group2Properties = groups.get(1).getProperties();
        Collections.sort(group2Properties, new UiGroupPropertyNameComparator());
        assertEquals(group2Properties.get(0).getName(), "Property1");
        assertEquals(group2Properties.get(0).getValue(), "Value1");
        assertEquals(group2Properties.get(1).getName(), "Property2");
        assertEquals(group2Properties.get(1).getValue(), "Value2");

        // group 3 properties
        List<UiGroupProperty> group3Properties = groups.get(2).getProperties();
        Collections.sort(group3Properties, new UiGroupPropertyNameComparator());
        assertEquals(group3Properties.get(0).getName(), "Property1");
        assertEquals(group3Properties.get(0).getValue(), "Value1");
        assertEquals(group3Properties.get(1).getName(), "Property2");
        assertEquals(group3Properties.get(1).getValue(), "Value2");

        // group 4 properties
        List<UiGroupProperty> group4Properties = groups.get(3).getProperties();
        Collections.sort(group3Properties, new UiGroupPropertyNameComparator());
        assertEquals(group3Properties.get(0).getName(), "Property1");
        assertEquals(group3Properties.get(0).getValue(), "Value1");
        assertEquals(group3Properties.get(1).getName(), "Property2");
        assertEquals(group3Properties.get(1).getValue(), "Value2");


        // group 1 users
        List<UiUser> group1Users = groups.get(0).getUsers();
        Collections.sort(group1Users, new UiUserUsernameComparator());
        assertEquals(group1Users.get(0).getUsername(), "Jim");
        assertEquals(group1Users.get(1).getUsername(), "Toby");

        // group 2 users
        List<UiUser> group2Users = groups.get(1).getUsers();
        Collections.sort(group2Users, new UiUserUsernameComparator());
        assertEquals(group2Users.get(0).getUsername(), "Toby");

        // group 3 users (no users)

        // group 4 users (no users)
    }

    public void testSearchForGroupExact() throws Exception {
        UiGroups _groups = getUserManagementService().searchForGroup("Group1", new PagingInfo(1, 10), true);
        List<UiGroup> groups = _groups.getGroups();
        Collections.sort(groups, new UiGroupNameComparator());

        assertEquals(groups.size(), 1);
        assertEquals(groups.get(0).getName(), "Group1");
        assertEquals(groups.get(0).getDescription(), "Group1_Description");
        assertEquals(groups.get(0).getProperties().size(), 3);
        assertEquals(groups.get(0).getUsers().size(), 2);

        // group 1 properties
        List<UiGroupProperty> group1Properties = groups.get(0).getProperties();
        Collections.sort(group1Properties, new UiGroupPropertyNameComparator());
        assertEquals(group1Properties.get(0).getName(), "Property1");
        assertEquals(group1Properties.get(0).getValue(), "Value1");
        assertEquals(group1Properties.get(1).getName(), "Property2");
        assertEquals(group1Properties.get(1).getValue(), "Value2");
        assertEquals(group1Properties.get(2).getName(), "Property3");
        assertEquals(group1Properties.get(2).getValue(), "Value3");

        // group 1 users
        List<UiUser> group1Users = groups.get(0).getUsers();
        Collections.sort(group1Users, new UiUserUsernameComparator());
        assertEquals(group1Users.get(0).getUsername(), "Jim");
        assertEquals(group1Users.get(1).getUsername(), "Toby");
    }


    public void testGetAllGroups() throws Exception {
        List<UiGroup> group_page1 = getUserManagementService().getAllGroups(new PagingInfo(1, 10)).getGroups();

        Collections.sort(group_page1, new UiGroupNameComparator());

        assertEquals(group_page1.size(), 4);
        assertEquals(group_page1.get(0).getName(), "Group1");
        assertEquals(group_page1.get(1).getName(), "Group2");
        assertEquals(group_page1.get(2).getName(), "Group3");
        assertEquals(group_page1.get(3).getName(), "Group4");
    }


    public void testUpdateUser() throws Exception {

        UiGroup group3 = getUserManagementService().searchForGroup("Group3", new PagingInfo(1, 10), true).getGroups().iterator().next();
        UiGroup group4 = getUserManagementService().searchForGroup("Group4", new PagingInfo(1, 10), true).getGroups().iterator().next();
        assertNotNull(group3);
        assertNotNull(group4);

        UiUser user = getUserManagementService().searchForUser("Toby", new PagingInfo(1, 5), true).getUsers().get(0);
        user.setFirstName("Toby_FirstName_New");
        user.setLastName("Toby_LastName_New");
        user.setDescription("Toby_Description_New");
        user.setPassword("Toby_Password_New");

        // user properties
        List<UiUserProperty> tobyProperties = user.getProperties();
        Collections.sort(tobyProperties, new UiUserPropertyNameComparator());
        user.removeProperty(tobyProperties.get(1)); // remove Property2
        user.addProperty(new UiUserProperty("xxx", "yyy"));

        // user group
        List<UiGroup> tobyGroups = user.getGroups();
        Collections.sort(tobyGroups, new UiGroupNameComparator());
        user.removeGroup(tobyGroups.get(1)); // remove group2
        user.addGroup(group3);
        user.addGroup(group4);

        getUserManagementService().updateUser(user);


        user = getUserManagementService().searchForUser("Toby", new PagingInfo(1, 10), true).getUsers().get(0);
        assertEquals("Toby_FirstName_New", user.getFirstName());
        assertEquals("Toby_LastName_New", user.getLastName());
        assertEquals("Toby_Description_New", user.getDescription());
        assertEquals("Toby_Password_New", user.getPassword());

        // user properties
        tobyProperties = user.getProperties();
        Collections.sort(tobyProperties, new UiUserPropertyNameComparator());
        assertEquals(2, tobyProperties.size());
        assertEquals("property1", tobyProperties.get(0).getName());
        assertEquals("value1", tobyProperties.get(0).getValue());
        assertEquals("xxx", tobyProperties.get(1).getName());
        assertEquals("yyy", tobyProperties.get(1).getValue());

        // user group
        tobyGroups = user.getGroups();
        Collections.sort(tobyGroups, new UiGroupNameComparator());
        assertEquals(3, tobyGroups.size());
        assertEquals("Group1", tobyGroups.get(0).getName());
        assertEquals("Group3", tobyGroups.get(1).getName());
        assertEquals("Group4", tobyGroups.get(2).getName());
    }


    public void testUpdateGroup() throws Exception {

        UiUser user1 = getUserManagementService().searchForUser("User1", new PagingInfo(1, 10), true).getUsers().iterator().next();
        UiUser user2 = getUserManagementService().searchForUser("User2", new PagingInfo(1, 10), true).getUsers().iterator().next();
        assertNotNull(user1);
        assertNotNull(user2);

        UiGroup group1 = getUserManagementService().searchForGroup("Group1", new PagingInfo(1, 100), true).getGroups().iterator().next();
        group1.setDescription("Group1_Description_New");

        // group1 properties
        List<UiGroupProperty> group1Properties = group1.getProperties();
        Collections.sort(group1Properties, new UiGroupPropertyNameComparator());
        group1Properties.get(0).setValue("888888888"); // change the value of Property1
        group1.removeProperty(group1Properties.get(1)); // remove Property2
        group1.addProperty(new UiGroupProperty("xxx", "yyy"));
        group1.addProperty(new UiGroupProperty("zzz", "uuu"));


        // group1 users
        List<UiUser> group1Users = group1.getUsers();
        Collections.sort(group1Users, new UiUserUsernameComparator());
        group1Users.get(0).setFirstName("Jim_FirstName_New"); // change Jim's Name
        group1Users.get(0).setLastName("Jim_LastName_New");
        group1.removeUser(group1Users.get(1)); // remove Toby
        group1.addUser(user1);
        user1.addGroup(group1);
        group1.addUser(user2);
        user2.addGroup(group1);

        // perform update
        getUserManagementService().updateGroup(group1);

        group1 = getUserManagementService().searchForGroup("Group1", new PagingInfo(1, 10), true).getGroups().iterator().next();
        group1Users = group1.getUsers();
        Collections.sort(group1Users, new UiUserUsernameComparator());
        group1 = getUserManagementService().searchForGroup("Group1", new PagingInfo(1, 10), true).getGroups().iterator().next();
        assertEquals("Group1_Description_New", group1.getDescription());


        // group 1 properties
        group1Properties = group1.getProperties();
        Collections.sort(group1Properties, new UiGroupPropertyNameComparator());
        assertEquals(4, group1Properties.size());
        assertEquals("Property1", group1Properties.get(0).getName());
        assertEquals("888888888", group1Properties.get(0).getValue());
        assertEquals("Property3", group1Properties.get(1).getName());
        assertEquals("Value3", group1Properties.get(1).getValue());
        assertEquals("xxx", group1Properties.get(2).getName());
        assertEquals("yyy", group1Properties.get(2).getValue());
        assertEquals("zzz", group1Properties.get(3).getName());
        assertEquals("uuu", group1Properties.get(3).getValue());

        // group1 users
        group1Users = group1.getUsers();
        Collections.sort(group1Users, new UiUserUsernameComparator());
        assertEquals(3, group1Users.size());
        assertEquals("Jim", group1Users.get(0).getUsername());
        assertEquals("Jim_FirstName_New", group1Users.get(0).getFirstName());
        assertEquals("Jim_LastName_New", group1Users.get(0).getLastName());
        assertEquals("User1", group1Users.get(1).getUsername());
        assertEquals("User2", group1Users.get(2).getUsername());
    }



    public void testDeleteGroup() throws Exception {
        UiGroup group2 = getUserManagementService().searchForGroup("Group2", new PagingInfo(1, 10), true).getGroups().iterator().next();
        UiGroup group4 = getUserManagementService().searchForGroup("Group4", new PagingInfo(1, 10), true).getGroups().iterator().next();
        getUserManagementService().deleteGroups(
                new UiGroup[] {
                    group2, group4                
                });


        assertEquals(getUserManagementService().searchForGroup("Group2", new PagingInfo(1, 10), true).getGroups().size(), 0);
        assertEquals(getUserManagementService().searchForGroup("Group4", new PagingInfo(1, 10), true).getGroups().size(), 0);

        UiUser toby = getUserManagementService().searchForUser("Toby", new PagingInfo(1, 10), true).getUsers().iterator().next();

        assertNotNull(toby);
        assertEquals(toby.getGroups().size(), 1);
        assertEquals(toby.getGroups().get(0).getName(), "Group1");
    }


    public void testDeleteUser() throws Exception {
        UiUser toby = getUserManagementService().searchForUser("Toby", new PagingInfo(1, 10), true).getUsers().iterator().next();

        getUserManagementService().deleteUsers(
                new UiUser[] {
                        toby
                }
        );


        assertEquals(getUserManagementService().searchForUser("Toby", new PagingInfo(1, 10), true).getUsers().size(), 0);
    }


}