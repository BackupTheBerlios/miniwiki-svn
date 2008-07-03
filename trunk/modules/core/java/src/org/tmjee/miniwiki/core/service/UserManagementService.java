package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.MiniWikiConfig;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.Group;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

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
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:25:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementService extends AbstractService {

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
                    return query.getSingleResult();
                }
            });
            UiUser uiUser = new UiUser();
            mapper.map(_user, uiUser);
            return new UiCredentials(uiUser);
        }
        return UiCredentials.ANONYMOUS;
    }

    public UiUsers searchForUser(final String username, final PagingInfo pagingInfo, final boolean exactMatch) {
        List<User> users = (List<User>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = exactMatch ?
                        entityManager.createNamedQuery("searchForUser_exact") :
                        entityManager.createNamedQuery("searchForUser_not_exact");
                query.setParameter("username", exactMatch ? username : "%"+username+"%");
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });


        return new UiUsers( map(users, new ArrayList<UiUser>()),
                            prepareResponsePagingInfo(
                                exactMatch ? "count_searchForUser_exact" : "count_searchForUser_not_exact",
                                exactMatch ?
                                        new HashMap<String, Object>() {
                                            {put("username", username);}
                                        }:
                                        new HashMap<String, Object>() {
                                            {put("username", "%"+username+"%");}
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

        UiUsers uiUsers = new UiUsers(map(users, new ArrayList<UiUser>()),
                prepareResponsePagingInfo("count_allUsers", Collections.EMPTY_MAP, pagingInfo));
        return uiUsers;
    }

    public void updateUser(final UiUser uiUser) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                User user = map(uiUser, new User());
                entityManager.merge(user);
                return null;
            }
        });
    }

    public void deleteUsers(final UiUser[] uiUsers) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                for (UiUser uiUser: uiUsers) {
                    entityManager.remove(map(uiUser, new User()));
                }
                return null;
            }
        });
    }

    public UiGroups searchForGroup(final String groupName, final PagingInfo pagingInfo, final boolean exactMatch) {
        List<Group> groups = (List<Group>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery(
                        exactMatch ? "searchForGroup_exact" : "searchForGroup_not_exact");
                preparePagingInfo(query, pagingInfo);
                return query.getResultList();
            }
        });

        return new UiGroups(
                map(groups, new ArrayList<UiGroup>()),
                prepareResponsePagingInfo(
                        exactMatch?"count_searchForGroup_exact":"count_searchForGroup_not_exact",
                        exactMatch?
                                new HashMap<String, Object>() {
                                    {put("name", groupName);}
                                }:
                                new HashMap<String, Object>() {
                                    {put("name", "%"+groupName+"%");}
                                },
                        pagingInfo));
    }

    public UiGroups getAllGroups(PagingInfo pagingInfo) {
        List<Group> groups = (List<Group>) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("allGroups");
                return query.getResultList();
            }
        });

        return new UiGroups(map(groups, new ArrayList<UiGroup>()),
                prepareResponsePagingInfo("count_allGroups", pagingInfo));
    }

    public void updateGroup(final UiGroup uiGroup) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                entityManager.merge(map(uiGroup, new Group()));
                return null;
            }
        });
    }

    public void deleteGroups(final UiGroup[] uiGroups) {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                for (int a=0; a< uiGroups.length; a++) {
                    entityManager.remove(map(uiGroups[a], new Group()));
                }
                return null;
            }
        });
    }
}
