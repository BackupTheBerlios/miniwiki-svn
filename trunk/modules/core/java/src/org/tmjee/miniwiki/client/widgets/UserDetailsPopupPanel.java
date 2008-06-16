package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUserUiProperty;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 3, 2008
 * Time: 7:39:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsPopupPanel extends PopupPanel {

    private UiUser uiUser;

    private VerticalPanel mainPanel;

    private TextBox username;
    private TextBox firstName;
    private TextBox lastName;
    private Grid grid;  // hold username, firstname and lastname


    private FlexTable groupsTable;
    private HorizontalPanel groupsButtonPanel;
    private Button assignGroup;


    private FlexTable propertiesTable;    // uiUser's properties
    private HorizontalPanel propertyManipulationButtonsPanel;
    private Button addProperty;
    private Button deleteProperty;

    private HorizontalPanel saveCancelButtonPanel;
    private Button saveUserDetails;
    private Button cancelUserDetails;


    public static interface Handler {
        void save(UiUser uiUser);
    }


    public UserDetailsPopupPanel(UiUser uiUser, Handler handler) {

        this.uiUser = uiUser;

        mainPanel = new VerticalPanel();

        username = new TextBox();
        username.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.uiUser.setUsername(username.getText());
            }
        });
        lastName = new TextBox();
        lastName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.uiUser.setLastName(lastName.getText());
            }
        });
        firstName = new TextBox();
        firstName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.uiUser.setFirstName(firstName.getText());
            }
        });
        grid = new Grid(3, 2);
        grid.setWidget(0, 0, new Label("Username"));
        grid.setWidget(0, 1, username);
        grid.setWidget(1, 0, new Label("First Name"));
        grid.setWidget(1, 1, firstName);
        grid.setWidget(2, 0, new Label("Last Name"));
        grid.setWidget(2, 1, lastName);


        groupsButtonPanel = new HorizontalPanel();
        assignGroup = new Button("Assign UiGroup", new ClickListener() {
            public void onClick(Widget sender) {
                new AssignGroupPopupPanel(UserDetailsPopupPanel.this.uiUser,
                        new AssignGroupPopupPanel.Handler() {
                            public void join(UiUser uiUser, UiGroup uiGroup) {
                                loadGroupInfo();
                            }
                            public void leave(UiUser uiUser, UiGroup uiGroup) {
                                loadGroupInfo();
                            }
                });
            }
        });
        groupsButtonPanel.add(assignGroup);
        groupsTable = new FlexTable();
        groupsTable.setWidget(0, 0, new Label("UiGroup Name"));
        groupsTable.setWidget(0, 1, new Label(""));


        propertiesTable = new FlexTable();
        propertiesTable.setWidget(0, 0, new Label("UiProperty Name"));
        propertiesTable.setWidget(0, 1, new Label("UiProperty Value"));
        propertiesTable.setWidget(0, 2, new Label(""));


        deleteProperty = new Button("Delete UiProperty", new ClickListener() {
            public void onClick(Widget sender) {
                for (int row=0; row< propertiesTable.getRowCount(); row++) {
                    ObjectHoldableCheckBox checkBox = (ObjectHoldableCheckBox) propertiesTable.getWidget(row, 2);
                    UserDetailsPopupPanel.this.uiUser.removeProperty((UiUserUiProperty) checkBox.getObject());
                    loadPropertiesInfo();
                }
            }
        });

        addProperty = new Button("Add UiProperty", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(new PropertyDetailsPopupPanel.Handler() {
                    public void save(String propertyName, String propertyValue) {
                        UserDetailsPopupPanel.this.uiUser.addProperty(new UiUserUiProperty(propertyName, propertyValue));
                        loadPropertiesInfo();
                    }
                });
            }
        });
        propertyManipulationButtonsPanel = new HorizontalPanel();
        propertyManipulationButtonsPanel.add(addProperty);
        propertyManipulationButtonsPanel.add(deleteProperty);

        saveUserDetails = new Button("Save", new ClickListener() {
            public void onClick(Widget sender) {
                LoadingMessageDisplayWidget.getInstance().display("Saving UiUser Info ...");
                UserManagementServiceAsync userManagement = Service.getUserManagementService();
                userManagement.updateUser(UserDetailsPopupPanel.this.uiUser, new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {
                        LoadingMessageDisplayWidget.getInstance().done();    
                    }
                });
            }
        });
        cancelUserDetails = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                hide();
            }
        });
        saveCancelButtonPanel = new HorizontalPanel();
        saveCancelButtonPanel.add(saveUserDetails);
        saveCancelButtonPanel.add(cancelUserDetails);

        mainPanel.add(grid);
        mainPanel.add(groupsButtonPanel);
        mainPanel.add(groupsTable);
        mainPanel.add(propertyManipulationButtonsPanel);
        mainPanel.add(propertiesTable);
        mainPanel.add(saveCancelButtonPanel);

        setWidget(mainPanel);
    }


    protected void loadGroupInfo() {
        {
            int currentRow = 1;
            int totalRows = groupsTable.getRowCount();
            for (final UiGroup uiGroup : uiUser.getGroups()) {
                if (currentRow < totalRows) {
                    groupsTable.setWidget(currentRow, 0, new Label(uiGroup.getName()));
                    groupsTable.setWidget(currentRow, 1, new ObjectHoldableButton(uiGroup, "Remove", new ClickListener() {
                        public void onClick(Widget sender) {
                            ObjectHoldableButton button = (ObjectHoldableButton) sender;
                            UiGroup grp = (UiGroup) button.getObject();
                            UserDetailsPopupPanel.this.uiUser.removeGroup(grp);
                        }
                    }));
                }
                else {
                    ((Label)groupsTable.getWidget(currentRow, 0)).setText(uiGroup.getName());
                    ((ObjectHoldableButton)groupsTable.getWidget(currentRow, 1)).setObject(uiGroup);
                }
                currentRow++;
            }
            while (currentRow < totalRows) {
                groupsTable.removeRow(currentRow);
                currentRow++;
            }
        }
    }

    protected void loadPropertiesInfo() {
        {
            int currentRow = 1;
            int totalRows = propertiesTable.getRowCount();
            for (final UiUserUiProperty uiUserProperty : uiUser.getProperties()) {
                if (currentRow < totalRows) {
                    propertiesTable.setWidget(currentRow, 0, new Label(uiUserProperty.getName()));
                    propertiesTable.setWidget(currentRow, 1, new Label(uiUserProperty.getValue()));
                    propertiesTable.setWidget(currentRow, 2, new ObjectHoldableCheckBox(uiUserProperty));
                }
                else {
                    ((Label)propertiesTable.getWidget(currentRow, 0)).setText(uiUserProperty.getName());
                    ((Label)propertiesTable.getWidget(currentRow, 1)).setText(uiUserProperty.getValue());
                    ((ObjectHoldableCheckBox)propertiesTable.getWidget(currentRow, 2)).setObject(uiUserProperty);
                }
                currentRow++;
            }
            while(currentRow < totalRows) {
                propertiesTable.removeRow(currentRow);
                currentRow++;
            }
        }    
    }



}
