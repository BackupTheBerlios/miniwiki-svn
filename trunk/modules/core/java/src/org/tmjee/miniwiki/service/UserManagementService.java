package org.tmjee.miniwiki.service;

import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.core.MiniWikiConfig;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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

    public UserManagementService(EntityManager entityManager, MiniWikiConfig config, DozerBeanMapper mapper) {
        template = new JpaTemplate(entityManager);
        this.mapper = mapper;
        this.config = config;
    }

    public Credentials authenticate(final String username, final String password) {
        if (config.isPredefinedSuperAdmin(username, password)) {
            return Credentials.SUPERADMIN;
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
            org.tmjee.miniwiki.domain.User _user = (org.tmjee.miniwiki.domain.User) template.execute(new JpaCallback() {
                public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                    Query query = entityManager.createNamedQuery("getUserByUsername_full");
                    return query.getSingleResult();
                }
            });
            User user = new User();
            mapper.map(_user, user);
            return new Credentials(user);
        }
        return Credentials.ANONYMOUS;
    }

    public Users searchForUser(String username, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Users getAllUsers(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Groups searchForGroup(String groupName, PagingInfo pagingInfo, boolean exactMatch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Groups getAllGroups(PagingInfo pagingInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateGroup(Group group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteGroup(Group group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(long userId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
