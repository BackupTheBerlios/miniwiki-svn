package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.server.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Service {

    private static UiTemplateManagementServiceAsync loadTemplateService;
    private static UiUserManagementServiceAsync userManagementService;
    private static UiLoggingManagementServiceAsync loggingManagementService;
    private static UiAccessManagementServiceAsync accessManagementService;
    private static TestServiceAsync testService;

    public static UiTemplateManagementServiceAsync getTemplateManagementService() {
        if (loadTemplateService == null) {
            loadTemplateService = (UiTemplateManagementServiceAsync) GWT.create(UiTemplateManagementService.class);
            ServiceDefTarget target = (ServiceDefTarget) loadTemplateService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"templateManagement");
        }
        return loadTemplateService;
    }

    public static UiUserManagementServiceAsync getUserManagementService() {
        if (userManagementService == null) {
            userManagementService = (UiUserManagementServiceAsync) GWT.create(UiUserManagementService.class);
            ServiceDefTarget target = (ServiceDefTarget) userManagementService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"userManagement");
        }
        return userManagementService;
    }

    public static UiLoggingManagementServiceAsync getLoggingService() {
        if (loggingManagementService == null) {
            loggingManagementService = (UiLoggingManagementServiceAsync) GWT.create(UiLoggingManagementService.class);
            ServiceDefTarget target = (ServiceDefTarget) loggingManagementService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"loggingManagement");
        }
        return loggingManagementService;
    }

    public static UiAccessManagementServiceAsync getAccessManagementService() {
        if (accessManagementService == null) {
            accessManagementService = (UiAccessManagementServiceAsync) GWT.create(UiAccessManagementService.class);
            ServiceDefTarget target = (ServiceDefTarget) accessManagementService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"accessManagement");
        }
        return accessManagementService;
    }

    public static TestServiceAsync getTestService() {
        if (testService == null) {
            testService = (TestServiceAsync) GWT.create(TestService.class);
            ServiceDefTarget target = (ServiceDefTarget) testService;
            target.setServiceEntryPoint(GWT.getModuleBaseURL()+"test");
        }
        return testService;
    }



}
