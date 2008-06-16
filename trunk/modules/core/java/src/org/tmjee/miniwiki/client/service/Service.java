package org.tmjee.miniwiki.client.service;

import org.tmjee.miniwiki.client.server.UiLoadTemplateServiceAsync;
import org.tmjee.miniwiki.client.server.UiLoadTemplateService;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.server.UserManagementService;
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

    private static UiLoadTemplateServiceAsync loadTemplateService;
    private static UserManagementServiceAsync userManagementService;

    public static UiLoadTemplateServiceAsync getLoadTemplateService() {
        loadTemplateService = (UiLoadTemplateServiceAsync) GWT.create(UiLoadTemplateService.class);
        ServiceDefTarget target = (ServiceDefTarget) loadTemplateService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL()+"loadTemplate");
        return loadTemplateService;
    }

    public static UserManagementServiceAsync getUserManagementService() {
        userManagementService = (UserManagementServiceAsync) GWT.create(UserManagementService.class);
        ServiceDefTarget target = (ServiceDefTarget) userManagementService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL()+"userManagement");
        return userManagementService;
    }

}
