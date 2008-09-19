package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.openjpa.jdbc.meta.MappingTool;
import org.apache.openjpa.jdbc.conf.JDBCConfiguration;
import org.apache.openjpa.jdbc.conf.JDBCConfigurationImpl;
import org.apache.openjpa.lib.util.Options;
import org.tmjee.miniwiki.core.domain.Setup;
import org.tmjee.miniwiki.core.domain.GlobalPriviledge;
import org.tmjee.miniwiki.core.spring.OpenJpaCallback;

import javax.persistence.*;
import java.sql.SQLException;
import java.io.IOException;
import java.util.EnumSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class SetupService {

    private static final Log LOG = LogFactory.getLog(SetupService.class);

    private JpaTemplate template;


    private String connectionUsername;
    private String connectionPassword;
    private String connectionUrl;
    private String connectionDriver;

    SetupService() {}
    public SetupService(JpaTemplate template) {
        this.template = template;    
    }

    public void setConnectionUsername(String username) {
        this.connectionUsername = username;
    }
    public void setConnectionPassword(String password) {
        this.connectionPassword = password;
    }
    public void setConnectionUrl(String url) {
        this.connectionUrl = url;
    }
    public void setConnectionDriver(String driver) {
        this.connectionDriver = driver;    
    }

    public void setup() throws SQLException, IOException {
        setup(false);
    }

    public void setup(boolean force) throws SQLException, IOException {
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
        try {
            return (Boolean) template.execute(new JpaCallback() {
                public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                    Query q = entityManager.createNamedQuery("isSetupBefore");
                    try {
                        Setup setup = (Setup) q.getSingleResult();
                        if ("true".equalsIgnoreCase(setup.getValue())) {
                            return true;
                        }
                        return false;
                    }
                    catch(NoResultException e) {
                        return false;
                    }
                }
            });
        }
        catch(InvalidDataAccessApiUsageException e) { // we haven't possibly get the db tables setup
            LOG.info("We might have not got the db tables setup", e);
            return false;
        }
    }

    public void cleanup() throws SQLException, IOException {
        LOG.info("Dropping DB tables ...");
        JDBCConfiguration jdbcConfiguration = new JDBCConfigurationImpl();
        jdbcConfiguration.setConnectionURL(connectionUrl);
        jdbcConfiguration.setConnectionUserName(connectionUsername);
        jdbcConfiguration.setConnectionDriverName(connectionDriver);
        jdbcConfiguration.setConnectionPassword(connectionPassword);
        Options opts = new Options();
        String[] args = opts.setFromCmdLine(new String[] {
                            "-p", "persistence.xml",
                            "-sa", "drop",
                            "-dt", "true",
                            "-pk", "true",
                            "-fk", "true",
                            "-ix", "true",
                            "-i", "false"
                        });
        MappingTool.run(jdbcConfiguration, args, opts);
    }

    protected void doSetup() throws SQLException, IOException {
        doDbStructureSetup();
        doDbDataSetup();

        LOG.info("Mark in db, setup complete");
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query q = entityManager.createNamedQuery("isSetupBefore");
                    try {
                        Setup setup = (Setup) q.getSingleResult();
                        setup.setValue("true");
                    }
                    catch(NoResultException e) {
                        Setup setup = new Setup("SETUP_DONE", "true");
                        entityManager.persist(setup);
                    }
                return null;
            }
        });
    }

    protected void doDbStructureSetup() throws SQLException, IOException {
        LOG.info("Refreshing DB tables ...");
        JDBCConfiguration jdbcConfiguration = new JDBCConfigurationImpl();
        jdbcConfiguration.setConnectionURL(connectionUrl);
        jdbcConfiguration.setConnectionUserName(connectionUsername);
        jdbcConfiguration.setConnectionDriverName(connectionDriver);
        jdbcConfiguration.setConnectionPassword(connectionPassword);
        Options opts = new Options();
        String[] args = opts.setFromCmdLine(new String[] {
            "-p", "persistence.xml",
            "-sa", "refresh",
            "-dt", "true",
            "-pk", "true",
            "-fk", "true",
            "-ix", "true",
            "-i", "false"

        });
        MappingTool.run(jdbcConfiguration, args, opts);
    }

    protected void doDbDataSetup() {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                // 1] setup GlobalPriviledge
                for (PriviledgeGlobalLevel priv :
                        EnumSet.range(PriviledgeGlobalLevel.GLOBAL_LEVEL_ADMINISTRATION_ANONYMOUS,
                                PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_SPACE_REGISTERED)) {
                    Query q = entityManager.createNamedQuery("globalPriviledgeName");
                    q.setParameter("globalPriviledgeName", priv.name());

                    if (q.getResultList().size() <= 0) {
                        // not there yet, create it
                        entityManager.persist(new GlobalPriviledge(priv.name(), PriviledgeValueState.OFF.name()));
                    }
                }

                return null;
            }
        });
    }

}
