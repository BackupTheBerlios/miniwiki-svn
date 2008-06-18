package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 5:06:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupManagementPopupPanel extends DialogBox {

    private VerticalPanel mainPanel;
    private TabPanel tabPanel;
    private MessageDisplayWidget messageDisplayWidget;


    public GroupManagementPopupPanel() {
        setText("Group Management");
        setAnimationEnabled(true);

        messageDisplayWidget = new MessageDisplayWidget();

        tabPanel = new TabPanel();
        tabPanel.add(new GroupManagementTab(), "Group Management");
        tabPanel.selectTab(0);

        mainPanel = new VerticalPanel();
        mainPanel.add(messageDisplayWidget);
        mainPanel.add(tabPanel);

        center();
    }


    private class GroupManagementTab extends SimplePanel {
        private GroupTableWidget groupsTable;

        public GroupManagementTab() {
            groupsTable = new GroupTableWidget();

            setWidget(groupsTable);
        }
    }

}
