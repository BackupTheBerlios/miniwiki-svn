package org.tmjee.miniwiki.core.service;

import junit.framework.TestCase;

import java.util.Properties;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.openjpa.jdbc.conf.JDBCConfiguration;
import org.apache.openjpa.jdbc.conf.JDBCConfigurationImpl;
import org.apache.openjpa.jdbc.meta.MappingTool;
import org.apache.openjpa.lib.util.Options;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class AbstractDbTestCase extends TestCase {

    private Properties dbProperties;

    private String connectionUrl;
    private String connectionUsername;
    private String connectionDriver;
    private String connectionPassword;

    private ApplicationContext appContext;


    public AbstractDbTestCase(String name) throws IOException {
        super(name);

        dbProperties = new Properties();
        dbProperties.load(AbstractDbTestCase.class.getResourceAsStream("/testing_db.properties"));

        connectionUrl = dbProperties.getProperty("db.url");
        connectionUsername = dbProperties.getProperty("db.username");
        connectionDriver = dbProperties.getProperty("db.driverName");
        connectionPassword = dbProperties.getProperty("db.password");
    }

    protected void setUp() throws Exception {
        super.setUp();
        _setUp();
        postSetUp();
    }

    protected void tearDown() throws Exception {
        //_tearDown();
        postTearDown();
        super.tearDown();
    }


    protected void _setUp() throws SQLException, IOException {
        appContext = new ClassPathXmlApplicationContext(new String[] {
                "testing_support.xml"
        });
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

    protected void _tearDown() throws SQLException, IOException {
        appContext = null;
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
            "-i", "true"

        });
        MappingTool.run(jdbcConfiguration, args, opts);
    }

    protected ApplicationContext getApplicationContext() {
        return appContext;
    }

    protected TestingSupportService getTestingSupportService() {
        TestingSupportService testingSupportService =
                (TestingSupportService) getApplicationContext().getBean("testingSupportService");
        return testingSupportService;
    }

    protected void postSetUp() throws Exception {

    }

    protected void postTearDown() throws Exception {

    }

}