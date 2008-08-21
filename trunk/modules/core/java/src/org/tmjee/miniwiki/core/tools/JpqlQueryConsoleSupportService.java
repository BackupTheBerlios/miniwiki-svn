package org.tmjee.miniwiki.core.tools;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class JpqlQueryConsoleSupportService {

    private JpaTemplate template;

    JpqlQueryConsoleSupportService() {}
    public JpqlQueryConsoleSupportService(JpaTemplate template) {
        this.template = template;
    }

    public Object executeJpaQuery(final String jpaQuery) {
        return template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createQuery(jpaQuery);
                return query.getResultList();
            }
        });
    }
}
