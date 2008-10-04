package org.tmjee.miniwiki.core.spring;

import org.springframework.orm.jpa.JpaCallback;
import org.apache.openjpa.persistence.OpenJPAEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class OpenJpaCallback<T> implements JpaCallback {

    public Object doInJpa(EntityManager entityManager) throws PersistenceException {
        return (T) doInJpa((OpenJPAEntityManager)entityManager);
    }

    public abstract T doInJpa(OpenJPAEntityManager entityManager) throws PersistenceException;
}
