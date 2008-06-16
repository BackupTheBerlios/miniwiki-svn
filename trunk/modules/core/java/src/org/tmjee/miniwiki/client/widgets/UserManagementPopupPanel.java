package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;

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
        tabPanel.add(new UserManagementTab(), "UiUser Management");
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

