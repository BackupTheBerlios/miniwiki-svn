package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 23, 2008
 * Time: 11:30:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementPopupPanel extends DialogBox {

    private VerticalPanel mainPanel;

    private HorizontalPanel buttonsPanel;
    private MessageDisplayWidget messageDisplayWidget;
    private UserTableWidget userTableWidget;

    public UserManagementPopupPanel() {
        
        setText("User Management");
        setAnimationEnabled(true);

        mainPanel = new VerticalPanel();

        messageDisplayWidget = new MessageDisplayWidget();

        userTableWidget = new UserTableWidget();
        userTableWidget.addMessageEventListener(messageDisplayWidget);

        buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                UserManagementPopupPanel.this.hide();
            }
        }));

        mainPanel.add(messageDisplayWidget);
        mainPanel.add(userTableWidget);
        mainPanel.add(buttonsPanel);
        setWidget(mainPanel);

        center();
    }


}

