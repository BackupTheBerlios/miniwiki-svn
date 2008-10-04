package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TestingSupportService {

    public interface TestingAction {
        void action(JpaTemplate template) throws Exception;
    }


    private JpaTemplate template;

    protected TestingSupportService() {}

    public TestingSupportService(JpaTemplate template) {
        this.template = template;    
    }


    public void doService(TestingAction action) throws Exception {
        action.action(template);    
    }

}
