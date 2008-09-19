package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.domain.UiGlobalPriviledges;
import org.tmjee.miniwiki.client.domain.UiGlobalPriviledge;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import net.sf.dozer.util.mapping.DozerBeanMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessManagementService extends AbstractService {

    AccessManagementService() {}
    public AccessManagementService(JpaTemplate template, DozerBeanMapper mapper) {
        super(template, mapper);
    }

    public UiGlobalPriviledges getGlobalPriviledges() {
        return (UiGlobalPriviledges) getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                Query query = entityManager.createNamedQuery("getGlobalPriviledges");
                return new UiGlobalPriviledges(
                        mapToList(query.getResultList(), UiGlobalPriviledge.class, "UiGlobalPriviledge"));
            }
        });
    }

}
