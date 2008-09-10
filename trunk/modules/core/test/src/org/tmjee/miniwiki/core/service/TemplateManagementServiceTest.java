package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.Wiki;
import org.tmjee.miniwiki.core.domain.WikiPriviledge;
import org.tmjee.miniwiki.core.domain.WikiPriviledgeValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateManagementServiceTest extends AbstractDbTestCase {

    public TemplateManagementServiceTest(String name) throws IOException {
        super(name);
    }

    protected void postSetUp() throws Exception {
        getTestingSupportService().doService(new TestingSupportService.TestingAction() {
            public void action(JpaTemplate template) throws Exception {
                template.execute(new JpaCallback() {
                    public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                        // ==== setup Wiki 1

                        WikiPriviledge priviledge1 = new WikiPriviledge();
                        priviledge1.setName("Priviledge1");
                        priviledge1.setValues(new HashSet<WikiPriviledgeValue>() {
                            {
                                WikiPriviledgeValue val1 = new WikiPriviledgeValue();
                                val1.setValue("Priviledge1V1");
                                WikiPriviledgeValue val2 = new WikiPriviledgeValue();
                                val2.setValue("Priviledge1V2");
                                add(new WikiPriviledgeValue());
                            }
                        });



                        Wiki wiki = new Wiki();
                        wiki.setName("Wiki1");
                        wiki.setPriviledges(null);
                        wiki.setProperties(null);
                        wiki.setSpaces(null);



                        // === setup Wiki 2




                        // === setup Wiki 3


                        return null;
                    }
                });
            }
        });
    }

    public void testIt() throws Exception {
        getTemplateManagementService();
    }

    protected TemplateManagementService getTemplateManagementService() {
        return (TemplateManagementService) getApplicationContext().getBean("templateManagementService");
    }

}
