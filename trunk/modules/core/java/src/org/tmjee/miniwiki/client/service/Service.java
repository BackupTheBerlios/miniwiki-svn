package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.server.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 24, 2008
 * Time: 6:33:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Service {

    private static UiTemplateManagementServiceAsync loadTemplateService;
    private static UiUserManagementServiceAsync userManagementService;
    private static UiLoggingManagementServiceAsync loggingManagementService;
    private static TestServiceAsync testService;

    public static UiTemplateManagementServiceAsync getLoadTemplateService() {
        loadTemplateService = (UiTemplateManagementServiceAsync) GWT.create(UiTemplateManagementService.class);
        if (loadTemplateService == null) {
            ServiceDefTarget target = (ServiceDefTarget) loadTemplateService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"templateManagement");
        }
        return loadTemplateService;
    }

    public static UiUserManagementServiceAsync getUserManagementService() {
        userManagementService = (UiUserManagementServiceAsync) GWT.create(UiUserManagementService.class);
        if (userManagementService == null) {
            ServiceDefTarget target = (ServiceDefTarget) userManagementService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"userManagement");
        }
        return userManagementService;
    }

    public static UiLoggingManagementServiceAsync getLoggingService() {
        loggingManagementService = (UiLoggingManagementServiceAsync) GWT.create(UiLoggingManagementService.class);
        if (loggingManagementService == null) {
            ServiceDefTarget target = (ServiceDefTarget) loggingManagementService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"loggingManagement");
        }
        return loggingManagementService;
    }

    public static TestServiceAsync getTestService() {
        testService = (TestServiceAsync) GWT.create(TestService.class);
        if (testService == null) {
            ServiceDefTarget target = (ServiceDefTarget) testService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"test");
        }
        return testService;
    }

}
