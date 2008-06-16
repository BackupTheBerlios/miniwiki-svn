package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.MiniWikiConfig;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:25:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementService {

    private JpaTemplate template;
    private MiniWikiConfig config;
    private DozerBeanMapper mapper;

    public UserManagementService(EntityManagerFactory entityManagerFactory, MiniWikiConfig config, DozerBeanMapper mapper) {
        template = new JpaTemplate(entityManagerFactory);
        this.mapper = mapper;
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

    public UiUsers searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public UiUsers getAllUsers(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUser(UiUser uiUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(UiUser uiUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public UiGroups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public UiGroups getAllGroups(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateGroup(UiGroup uiGroup) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteGroup(UiGroup uiGroup) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(long userId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
