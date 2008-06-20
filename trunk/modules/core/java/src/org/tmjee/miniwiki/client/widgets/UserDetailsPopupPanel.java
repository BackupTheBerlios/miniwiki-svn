package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUserProperty;
import org.tmjee.miniwiki.client.domain.UiProperty;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.EventObject;
import org.tmjee.miniwiki.client.beans.PropertyAdditionEvent;
import org.tmjee.miniwiki.client.beans.PropertyDeletionEvent;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 3, 2008
 * Time: 7:39:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsPopupPanel extends DialogBox implements SourcesMessageEvents, PropertyListener {

    private SourcesEventsSupport sourcesEventSupport;

    private UiUser uiUser;
    private List<UiUserProperty> selectedProperties;

    private VerticalPanel mainPanel;

    private MessageDisplayWidget messageDisplayWidget;

    private TextBox username;
    private TextBox firstName;
    private TextBox lastName;
    private TextArea description;
    private PasswordTextBox password;
    private PasswordTextBox confirmPassword;
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

        setText("User Details");
        setAnimationEnabled(true);

        selectedProperties = new ArrayList<UiUserProperty>();

        sourcesEventSupport = new SourcesEventsSupport();
        
        messageDisplayWidget = new MessageDisplayWidget();
        addMessageEventListener(messageDisplayWidget);

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
        description = new TextArea();
        description.addChangeListener(new ChangeListener() {
            public void onChange(Widget widget) {
                UserDetailsPopupPanel.this.uiUser.setDescription(description.getText());
            }
        });
        password = new PasswordTextBox();
        password.addChangeListener(new ChangeListener() {
            public void onChange(Widget widget) {
                UserDetailsPopupPanel.this.uiUser.setPassword(password.getText());
            }
        });
        confirmPassword = new PasswordTextBox();


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
        groupsTable.setWidget(0, 0, new Label("Group Name"));
        groupsTable.setWidget(0, 1, new Label(""));  // button to remove group


        propertiesTable = new FlexTable();
        propertiesTable.setWidget(0, 0, new Label("Property Name"));
        propertiesTable.setWidget(0, 1, new Label("Property Value"));
        propertiesTable.setWidget(0, 2, new Label("")); // checkbox 


        deleteProperty = new Button("Delete Property", new ClickListener() {
            public void onClick(Widget sender) {
                if (selectedProperties.size() <= 0) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "No property(s) available / selected"));
                        }
                    });
                    return;
                }
                for (UiUserProperty p : selectedProperties) {
                    UserDetailsPopupPanel.this.uiUser.removeProperty(p);
                }
            }
        });

        addProperty = new Button("Add Property", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(new PropertyDetailsPopupPanel.Handler() {
                    public void save(String propertyName, String propertyValue) {
                        UserDetailsPopupPanel.this.uiUser.addProperty(new UiUserProperty(propertyName, propertyValue));
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
                if (username.getText().trim().length() <= 0) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Username is required"));
                        }
                    });
                }
                if (firstName.getText().trim().length() <= 0) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "First Name is required"));
                        }
                    });
                }
                if (lastName.getText().trim().length() <= 0) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Last Name is required"));
                        }
                    });
                }


                LoadingMessageDisplayWidget.getInstance().display("Saving User Info ...");
                UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
                userManagement.updateUser(UserDetailsPopupPanel.this.uiUser,
                    new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            Logger.error(caught.toString(), caught);
                            UserDetailsPopupPanel.this.cleanUp();
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object result) {
                            UserDetailsPopupPanel.this.cleanUp();
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                    });
            }
        });
        cancelUserDetails = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                UserDetailsPopupPanel.this.cleanUp();
                hide();
            }
        });
        saveCancelButtonPanel = new HorizontalPanel();
        saveCancelButtonPanel.add(saveUserDetails);
        saveCancelButtonPanel.add(cancelUserDetails);

        mainPanel.add(messageDisplayWidget);
        mainPanel.add(grid);
        mainPanel.add(groupsButtonPanel);
        mainPanel.add(groupsTable);
        mainPanel.add(propertyManipulationButtonsPanel);
        mainPanel.add(propertiesTable);
        mainPanel.add(saveCancelButtonPanel);

        init();

        setWidget(mainPanel);

        center();
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventSupport.removeListener(listener);
    }

    public void propertyChange(EventObject event) {
        String propName = event.getPropertyName();
        if (event instanceof PropertyAdditionEvent) {
            if ("group".equals(propName)) {
                for (int a=1; a< propertiesTable.getRowCount(); a++) {
                    //propertiesTable    
                }
            }
            else if ("property".equals(propName)) {

            }
        }
        else if (event instanceof PropertyDeletionEvent) {
            
        }
    }


    protected void init() {
        uiUser.addPropertyListener(this);
    }

    protected void cleanUp() {
        uiUser.removePropertyListener(this);
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
            for (final UiUserProperty uiUserProperty : uiUser.getProperties()) {
                if (currentRow < totalRows) {
                    propertiesTable.setWidget(currentRow, 0, new Label(uiUserProperty.getName()));
                    propertiesTable.setWidget(currentRow, 1, new Label(uiUserProperty.getValue()));
                    propertiesTable.setWidget(currentRow, 2, new ObjectHoldableCheckBox(uiUserProperty,
                            new ClickListener() {
                                public void onClick(Widget widget) {
                                    ObjectHoldableCheckBox cb  = (ObjectHoldableCheckBox) widget;
                                    UiUserProperty property = (UiUserProperty) cb.getObject();
                                    if (cb.isChecked()) {
                                        if (selectedProperties.contains(property)) {
                                            selectedProperties.add(property);
                                        }
                                    }
                                    else {
                                        if (selectedProperties.contains(property)) {
                                            selectedProperties.remove(property);
                                        }
                                    }
                                }
                            }));
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
