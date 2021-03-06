package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.MiniWikiConfig;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.core.domain.GroupProperty;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserManagementService extends AbstractService {

    private static final Log LOG = LogFactory.getLog(UserManagementService.class);


    private MiniWikiConfig config;

    UserManagementService() {}
    public UserManagementService(JpaTemplate template, MiniWikiConfig config, DozerBeanMapper mapper) {
        super(template, mapper);
        this.config = config;
    }

    public UiCredentials authenticate(final String username, final String password) {
        if (config.isPredefinedSuperAdmin(username, password)) {
            return UiCredentials.SUPERADMIN;
        }

        Boolean authenticated = (Boolean) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("authenticate");
                query.setParameter("username", username);
                query.setParameter("password", password);
                if (query.getResultList().size() > 0) {
                    return true;
                }
                return false;
            }
        });

        if (authenticated) {
            org.tmjee.miniwiki.core.domain.User _user = (org.tmjee.miniwiki.core.domain.User) template.execute(new JpaCallback() {
                public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                    Query query = entityManager.createNamedQuery("getUserByUsername_full");
                    query.setParameter("username", username);
                    return query.getResultList().get(0);
                }
            });
            UiUser uiUser = map(_user, UiUser.class, "UiUser");
            return new UiCredentials(uiUser);
        }
        return UiCredentials.ANONYMOUS;
    }

    public UiUsers getUserByName(final String username, final PagingInfo pagingInfo, final boolean exactMatch) {
        List<User> users = (List<User>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = exactMatch ?
                        entityManager.createNamedQuery("searchForUser_exact") :
                        entityManager.createNamedQuery("searchForUser_not_exact");
                query.setParameter("username", exactMatch ? username : ("%"+username+"%").toLowerCase());
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });


        return new UiUsers( mapToList(users, UiUser.class, "UiUser"),
                            prepareResponsePagingInfo(
                                exactMatch ? "count_searchForUser_exact" : "count_searchForUser_not_exact",
                                exactMatch ?
                                        new HashMap<String, Object>() {
                                            {put("username", username);}
                                        }:
                                        new HashMap<String, Object>() {
                                            {put("username", ("%"+username+"%").toLowerCase());}
                                        },
                                pagingInfo));
    }

    public UiUsers getAllUsers(final PagingInfo pagingInfo) {
        List<User> users = (List<User>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("allUsers");
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });

        UiUsers uiUsers = new UiUsers(
                mapToList(users, UiUser.class, "UiUser"),
                prepareResponsePagingInfo("count_allUsers", Collections.EMPTY_MAP, pagingInfo));
        return uiUsers;
    }

    public void updateUser(final UiUser uiUser) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                User user = map(uiUser, User.class, "UiUser");
                mergeOrPersist(entityManager, user);
                entityManager.flush();
                // NOTE:
                // hack to make sure relationship are maintained ok (removed properly)
                // (necessariy for the non-owning end of the bi-directional relationship
                // but having it here anyway).
                for (UiGroup removedUiGroup : uiUser.getRemovedUiGroup()) {
                    Group g = entityManager.find(Group.class, removedUiGroup.getId());
                    if (g != null) {
                        g.getUsers().remove(user);
                        user.getGroups().remove(g);
                    }
                }
                for (UiUserProperty removedUiUserProperty : uiUser.getRemovedProperties()) {
                    if (removedUiUserProperty.getId() > 0) {
                        UserProperty userProperty = entityManager.find(UserProperty.class, removedUiUserProperty.getId());
                        if (userProperty != null) {
                            entityManager.remove(userProperty);        
                        }
                    }
                }
                return null;
            }
        });
    }

    public void deleteUsers(final UiUser[] uiUsers) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                for (UiUser uiUser: uiUsers) {
                    if (uiUser.getId() > 0) {
                        User u = entityManager.find(User.class, uiUser.getId());
                        if (u != null) {
                            entityManager.remove(u);
                        }
                    }
                }
                entityManager.flush();
                return null;
            }
        });
    }

    public UiGroups getGroupByName(final String groupName, final PagingInfo pagingInfo, final boolean exactMatch) {
        List<Group> groups = (List<Group>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery(
                        exactMatch ? "searchForGroup_exact" : "searchForGroup_not_exact");
                query.setParameter("name", exactMatch ? groupName : ("%"+groupName.toLowerCase()+"%"));
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });

        return new UiGroups(
                mapToList(groups, UiGroup.class, "UiGroup"),
                prepareResponsePagingInfo(
                        exactMatch?"count_searchForGroup_exact":"count_searchForGroup_not_exact",
                        exactMatch?
                                new HashMap<String, Object>() {
                                    {put("name", groupName);}
                                }:
                                new HashMap<String, Object>() {
                                    {put("name", "%"+groupName.toLowerCase()+"%");}
                                },
                        pagingInfo));
    }

    public UiGroups getAllGroups(final PagingInfo pagingInfo) {
        List<Group> groups = (List<Group>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("allGroups");
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });

        return new UiGroups(
                mapToList(groups, UiGroup.class, "UiGroup"),
                prepareResponsePagingInfo("count_allGroups", pagingInfo));
    }

    public void updateGroup(final UiGroup uiGroup) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Group group = map(uiGroup, Group.class, "UiGroup");
                mergeOrPersist(entityManager, group);
                entityManager.flush();
                // NOTE:
                // hack to make sure relationship are maintained ok (removed properly)
                // (not necessariy for the owning end of the bi-directional relationship
                // but having it here anyway).
                for (UiUser uiUser : uiGroup.getRemovedUsers()) {
                    User u = entityManager.find(User.class, uiUser.getId());
                    u.getGroups().remove(group);
                    group.getUsers().remove(u);
                }
                for (UiGroupProperty uiGroupProperty : uiGroup.getRemovedProperties()) {
                    if (uiGroupProperty.getId() > 0) {
                        GroupProperty groupProperty = entityManager.find(GroupProperty.class, uiGroupProperty.getId());
                        if (groupProperty != null) {
                            entityManager.remove(groupProperty);
                        }
                    }
                }
                return null;
            }
        });
    }

    public void deleteGroups(final UiGroup[] uiGroups) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                for (UiGroup uiGroup : uiGroups) {
                    if (uiGroup.getId() > 0) {
                        Group g = entityManager.find(Group.class, uiGroup.getId());
                        if (g != null) {
                            entityManager.remove(g);
                        }
                    }
                }
                entityManager.flush();
                return null;
            }
        });
    }

    public UiGroup getGroupById(final long id) {
        return (UiGroup) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("getGroupById");
                query.setParameter("id", id);
                if (query.getResultList().size() > 0 ) {
                    Group g = (Group) query.getResultList().iterator().next();
                    return map(g, UiGroup.class, "UiGroup");
                }
                return null;
            }
        });
    }

}
