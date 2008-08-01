package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jul 31, 2008
 * Time: 11:39:45 AM
 * To change this template use File | Settings | File Templates.
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
