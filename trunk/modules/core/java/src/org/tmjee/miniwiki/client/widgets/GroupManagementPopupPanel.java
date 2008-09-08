package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class GroupManagementPopupPanel extends AutoRegisteredDialogBox {

    private VerticalPanel mainPanel;
    private GroupTableWidget groupTableWidget;
    private MessageDisplayWidget messageDisplayWidget;

    private HorizontalPanel buttonsPanel;

    public GroupManagementPopupPanel() {
        setText("Group Management");
        setAnimationEnabled(true);

        messageDisplayWidget = new MessageDisplayWidget();

        groupTableWidget = new GroupTableWidget();
        groupTableWidget.addMessageEventListener(messageDisplayWidget);

        buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                hide();
            }
        }));


        mainPanel = new VerticalPanel();
        mainPanel.add(messageDisplayWidget);
        mainPanel.add(groupTableWidget);
        mainPanel.add(buttonsPanel);

        setWidget(mainPanel);

        center();
    }




}
