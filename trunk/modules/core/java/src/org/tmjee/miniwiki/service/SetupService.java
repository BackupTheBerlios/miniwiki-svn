package org.tmjee.miniwiki.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 21, 2008
 * Time: 4:52:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetupService {

    private static final Log LOG = LogFactory.getLog(SetupService.class);

    private JpaTemplate template;

    public SetupService(EntityManagerFactory entityManagerfactory) {
        this.template = new JpaTemplate(entityManagerfactory);    
    }

    public void setup() {
        setup(false);
    }

    public void setup(boolean force) {
        if(hasBeenSetupBefore()) {
            if (force) {
                LOG.info("Setup done before, forcing a re-setup");
                cleanup();
                doSetup();
            }
            else {
                LOG.info("Setup done before");
            }
        }
        else {
            LOG.info("Not setup before doing setup now");
            doSetup();
        }
    }

    public boolean hasBeenSetupBefore() {
        return (Boolean) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                Query q = entityManager.createNamedQuery("isSetupBefore");
                if (((Long)q.getSingleResult()) > 0) {
                    return true;
                }
                return false;
            }
        });
    }

    public void cleanup() {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                //Query q = entityManager.createNativeQuery("DELETE FROM TABLE TBL_ATTACHEMENT");
                //q.executeUpdate();

                return null;
            }
        });
    }

    protected void doSetup() {
            
    }

}
