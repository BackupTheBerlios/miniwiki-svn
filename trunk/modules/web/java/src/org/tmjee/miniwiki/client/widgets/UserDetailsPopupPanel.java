package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.domain.User;
import org.tmjee.miniwiki.client.domain.Property;
import org.tmjee.miniwiki.client.domain.Group;
import org.tmjee.miniwiki.client.domain.UserProperty;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 3, 2008
 * Time: 7:39:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsPopupPanel extends PopupPanel {

    private User user;

    private VerticalPanel mainPanel;

    private TextBox username;
    private TextBox firstName;
    private TextBox lastName;
    private Grid grid;  // hold username, firstname and lastname


    private FlexTable groupsTable;
    private HorizontalPanel groupsButtonPanel;
    private Button assignGroup;


    private FlexTable propertiesTable;    // user's properties
    private HorizontalPanel propertyManipulationButtonsPanel;
    private Button addProperty;
    private Button deleteProperty;

    private HorizontalPanel saveCancelButtonPanel;
    private Button saveUserDetails;
    private Button cancelUserDetails;


    public static interface Handler {
        void save(User user);
    }


    public UserDetailsPopupPanel(User user, Handler handler) {

        this.user = user;

        mainPanel = new VerticalPanel();

        username = new TextBox();
        username.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.user.setUsername(username.getText());
            }
        });
        lastName = new TextBox();
        lastName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.user.setLastName(lastName.getText());
            }
        });
        firstName = new TextBox();
        firstName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.user.setFirstName(firstName.getText());
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
        assignGroup = new Button("Assign Group", new ClickListener() {
            public void onClick(Widget sender) {
                new AssignGroupPopupPanel(UserDetailsPopupPanel.this.user,
                        new AssignGroupPopupPanel.Handler() {
                            public void join(User user, Group group) {
                                loadGroupInfo();
                            }
                            public void leave(User user, Group group) {
                                loadGroupInfo();
                            }
                });
            }
        });
        groupsButtonPanel.add(assignGroup);
        groupsTable = new FlexTable();
        groupsTable.setWidget(0, 0, new Label("Group Name"));
        groupsTable.setWidget(0, 1, new Label(""));


        propertiesTable = new FlexTable();
        propertiesTable.setWidget(0, 0, new Label("Property Name"));
        propertiesTable.setWidget(0, 1, new Label("Property Value"));
        propertiesTable.setWidget(0, 2, new Label(""));


        deleteProperty = new Button("Delete Property", new ClickListener() {
            public void onClick(Widget sender) {
                for (int row=0; row< propertiesTable.getRowCount(); row++) {
                    ObjectHoldableCheckBox checkBox = (ObjectHoldableCheckBox) propertiesTable.getWidget(row, 2);
                    UserDetailsPopupPanel.this.user.removeProperty((UserProperty) checkBox.getObject());
                    loadPropertiesInfo();
                }
            }
        });

        addProperty = new Button("Add Property", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(new PropertyDetailsPopupPanel.Handler() {
                    public void save(String propertyName, String propertyValue) {
                        UserDetailsPopupPanel.this.user.addProperty(new UserProperty(propertyName, propertyValue));
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
                LoadingMessageDisplayWidget.getInstance().display("Saving User Info ...");
                UserManagementServiceAsync userManagement = Service.getUserManagementService();
                userManagement.updateUser(UserDetailsPopupPanel.this.user, new AsyncCallback() {
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
            for (final Group group : user.getGroups()) {
                if (currentRow < totalRows) {
                    groupsTable.setWidget(currentRow, 0, new Label(group.getName()));
                    groupsTable.setWidget(currentRow, 1, new ObjectHoldableButton(group, "Remove", new ClickListener() {
                        public void onClick(Widget sender) {
                            ObjectHoldableButton button = (ObjectHoldableButton) sender;
                            Group grp = (Group) button.getObject();
                            UserDetailsPopupPanel.this.user.removeGroup(grp);
                        }
                    }));
                }
                else {
                    ((Label)groupsTable.getWidget(currentRow, 0)).setText(group.getName());
                    ((ObjectHoldableButton)groupsTable.getWidget(currentRow, 1)).setObject(group);
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
            for (final UserProperty userProperty : user.getProperties()) {
                if (currentRow < totalRows) {
                    propertiesTable.setWidget(currentRow, 0, new Label(userProperty.getName()));
                    propertiesTable.setWidget(currentRow, 1, new Label(userProperty.getValue()));
                    propertiesTable.setWidget(currentRow, 2, new ObjectHoldableCheckBox(userProperty));
                }
                else {
                    ((Label)propertiesTable.getWidget(currentRow, 0)).setText(userProperty.getName());
                    ((Label)propertiesTable.getWidget(currentRow, 1)).setText(userProperty.getValue());
                    ((ObjectHoldableCheckBox)propertiesTable.getWidget(currentRow, 2)).setObject(userProperty);
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
