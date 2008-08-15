package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.client.domain.UiIdentifiable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.dozer.util.mapping.BeanFactoryIF;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiObjectToJpaObjectDozerBeanFactory implements BeanFactoryIF {

    private static final Log LOG = LogFactory.getLog(UiObjectToJpaObjectDozerBeanFactory.class);

    private JpaTemplate template;

    UiObjectToJpaObjectDozerBeanFactory() {}
    public UiObjectToJpaObjectDozerBeanFactory(JpaTemplate template) {
        this.template = template;
    }

    public Object createBean(Object source, Class sourceClass, String targetBeanId) {
        LOG.info("xxxxxxxxxxxxxxxxxxx Creating Dozer Bean [source="+source+",sourceClass="+sourceClass+",targetBeanId="+targetBeanId+"]");

        if (source instanceof UiIdentifiable) {
            try {
                final long id = ((UiIdentifiable) source).getId();
                final Class clazz = Class.forName(targetBeanId);

                Object result = null;
                if (id > 0) {
                    result = template.execute(new JpaCallback() {
                        public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                            return entityManager.find(clazz, id);
                        }
                    });
                    LOG.info("Query for id="+id+", type="+clazz+", object queried="+result);
                }
                if (result == null) {
                    result = clazz.newInstance();
                    LOG.info("Instantiating object ["+result+"]");
                }
                return result;
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException("Error creating Dozer mapper bean", e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Error creating Dozer mapper bean", e);
            }
            catch (InstantiationException e) {
                throw new RuntimeException("Error creating Dozer mapper bean", e);
            }
        }
        throw new RuntimeException("Error source object is not instance of " + UiIdentifiable.class);
    }
}
