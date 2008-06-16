package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 23, 2008
 * Time: 11:30:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementPopupPanel extends PopupPanel {

    private VerticalPanel mainPanel;

    private TabPanel tabPanel;
    private MessageDisplayWidget messageDisplayWidget;

    public UserManagementPopupPanel() {
        mainPanel = new VerticalPanel();

        messageDisplayWidget = new MessageDisplayWidget();

        tabPanel = new TabPanel();
        tabPanel.add(new UserManagementTab(), "User Management");
        tabPanel.selectTab(0);

        mainPanel.add(messageDisplayWidget);
        mainPanel.add(tabPanel);
        setWidget(mainPanel);
    }



    private class UserManagementTab extends SimplePanel {

        private UserTableWidget userTableWidget;

        public UserManagementTab() {

            userTableWidget = new UserTableWidget();
            userTableWidget.addMessageEventListener(messageDisplayWidget);

            setWidget(userTableWidget);
        }
    }
}

