package org.tmjee.miniwiki.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tmjee.miniwiki.core.service.SetupService;
import org.tmjee.miniwiki.core.service.UserManagementService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Bootstrap {

    private static final Log LOG = LogFactory.getLog(Bootstrap.class);

    private static Bootstrap INSTANCE;

    public static Bootstrap getInstance() {
        synchronized (Bootstrap.class) {
            try {
                if (INSTANCE == null) {
                    INSTANCE = new Bootstrap();
                }

                // setup
                SetupService setupService = (SetupService) INSTANCE.getApplicationContext().getBean("setupService");
                setupService.setup();
            }
            catch(Exception e) {
                throw new RuntimeException(e.toString(), e);
            }
        }
        return INSTANCE;
    }

    private ApplicationContext applicationContext;

    private Bootstrap() {
        applicationContext = new ClassPathXmlApplicationContext(
                new String[] {
                        "/support.xml"
                }
        );
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public UserManagementService getUserManagementService() {
        return (UserManagementService) applicationContext.getBean(
                "userManagementService", UserManagementService.class);    
    }
    
}
