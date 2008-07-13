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
import org.tmjee.miniwiki.client.utils.Utils;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.EventObject;
import org.tmjee.miniwiki.client.beans.PropertyAdditionEvent;
import org.tmjee.miniwiki.client.beans.PropertyDeletionEvent;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserDetailsPopupPanel extends DialogBox implements SourcesMessageEvents, PropertyListener, Initializable, CleanUpable {

    private SourcesEventsSupport sourcesEventSupport;

    private UiUser uiUser;

    private VerticalPanel mainPanel;

    private MessageDisplayWidget messageDisplayWidget;

    private TextBox username;
    private TextBox firstName;
    private TextBox lastName;
    private TextArea description;
    private PasswordTextBox password;
    private PasswordTextBox confirmPassword;
    private Grid grid;  // hold username, firstname and lastname


    private FlexTableExt<UiGroup> groupsTable;
    private HorizontalPanel groupsButtonPanel;
    private Button assignGroup;


    //private FlexTable propertiesTable;    // uiUser's properties
    private FlexTableExt<UiUserProperty> propertiesTable;
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

        sourcesEventSupport = new SourcesEventsSupport();
        
        messageDisplayWidget = new MessageDisplayWidget();
        addMessageEventListener(messageDisplayWidget);

        this.uiUser = uiUser;

        mainPanel = new VerticalPanel();

        username = new TextBox();
        username.setText(uiUser.getUsername());
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
        firstName.setText(uiUser.getFirstName());
        firstName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                UserDetailsPopupPanel.this.uiUser.setFirstName(firstName.getText());
            }
        });
        description = new TextArea();
        description.setText(uiUser.getDescription());
        description.addChangeListener(new ChangeListener() {
            public void onChange(Widget widget) {
                UserDetailsPopupPanel.this.uiUser.setDescription(description.getText());
            }
        });
        password = new PasswordTextBox();
        password.setText(uiUser.getPassword());
        password.addChangeListener(new ChangeListener() {
            public void onChange(Widget widget) {
                UserDetailsPopupPanel.this.uiUser.setPassword(password.getText());
            }
        });
        confirmPassword = new PasswordTextBox();
        confirmPassword.setText(uiUser.getPassword());


        grid = new Grid(5, 2);
        grid.setWidget(0, 0, new Label("Username"));
        grid.setWidget(0, 1, username);
        grid.setWidget(1, 0, new Label("First Name"));
        grid.setWidget(1, 1, firstName);
        grid.setWidget(2, 0, new Label("Last Name"));
        grid.setWidget(2, 1, lastName);
        grid.setWidget(3, 0, new Label("Description"));
        grid.setWidget(3, 1, description);
        grid.setWidget(4, 0, new Label("Password"));
        grid.setWidget(4, 1, password);
        grid.setWidget(5, 0, new Label("Confirm Password"));
        grid.setWidget(5, 1, confirmPassword);


        groupsButtonPanel = new HorizontalPanel();
        assignGroup = new Button("Assign Group", new ClickListener() {
            public void onClick(Widget sender) {
                new AssignGroupPopupPanel(UserDetailsPopupPanel.this.uiUser,
                        new AssignGroupPopupPanel.Handler() {
                            public void join(UiUser uiUser, UiGroup uiGroup) {
                                uiUser.addGroup(uiGroup);
                                groupsTable.refresh(uiUser.getGroups());
                            }
                            public void leave(UiUser uiUser, UiGroup uiGroup) {
                                uiUser.removeGroup(uiGroup);
                                groupsTable.refresh(uiUser.getGroups());
                            }
                });
            }
        });
        groupsButtonPanel.add(assignGroup);
        groupsTable = new FlexTableExt<UiGroup>();
        groupsTable.init(
                new FlexTableExt.TitleHandler() {
                    public int getTotalCols() {
                        return 2;
                    }
                    public boolean hasCheckableCol() {
                        return false;
                    }
                    public int numOfControlWidget() {
                        return 0;
                    }
                    public Widget getTitleWidget(int col) {
                        switch(col) {
                            case 0:
                                return new Label("Name");
                            case 1:
                                return new Label("Description");
                            default:
                                return null;
                        }
                    }
                },
                new FlexTableExt.DataHandler() {
                    public Widget getDataWidget(Object rowObject, int col) {
                        UiGroup uiGroup = (UiGroup) rowObject;
                        switch(col) {
                            case 0:
                                return new Label(uiGroup.getName());
                            case 1:
                                return new Label(uiGroup.getDescription());
                            default:
                                return null;
                        }
                    }
                    public Widget getControlWidget(Object rowObject, int col, int index) {
                        return null;
                    }
                }
        );


        propertiesTable = new FlexTableExt<UiUserProperty>();
        propertiesTable.init(
                new FlexTableExt.TitleHandler() {
                    public int getTotalCols() {
                        return 2;
                    }
                    public boolean hasCheckableCol() {
                        return true;
                    }
                    public int numOfControlWidget() {
                        return 1;
                    }
                    public Widget getTitleWidget(int col) {
                        switch(col) {
                            case 0:
                                return new Label("Name");    
                            case 1:
                                return new Label("Value");
                            default:
                                return null;
                        }
                    }
            },
            new FlexTableExt.DataHandler() {
                public Widget getDataWidget(Object rowObject, int col) {
                    UiUserProperty property = (UiUserProperty) rowObject;
                    switch(col) {
                        case 0:
                            return new Label(property.getName());
                        case 1:
                            return new Label(property.getValue());
                        default:
                            return null;
                    }
                }
                public Widget getControlWidget(Object rowObject, int col, int index) {
                    final UiUserProperty uiUserProperty = (UiUserProperty) rowObject;
                    return new Button("Edit", new ClickListener() {
                        public void onClick(Widget widget) {
                            new PropertyDetailsPopupPanel(
                                    uiUserProperty.getName(),
                                    uiUserProperty.getValue(),
                                    new PropertyDetailsPopupPanel.Handler() {
                                        public void save(String propertyName, String propertyValue) {
                                            uiUserProperty.setName(propertyName);
                                            uiUserProperty.setValue(propertyValue);
                                            propertiesTable.refresh(UserDetailsPopupPanel.this.uiUser.getProperties());
                                        }
                                    });
                        }
                    });
                }
            });

        deleteProperty = new Button("Delete Property", new ClickListener() {
            public void onClick(Widget sender) {
                List<UiUserProperty> selectedProperties =  propertiesTable.getSelectedRowObjects();
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
                propertiesTable.refresh(UserDetailsPopupPanel.this.uiUser.getProperties());
            }
        });

        addProperty = new Button("Add Property", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(new PropertyDetailsPopupPanel.Handler() {
                    public void save(String propertyName, String propertyValue) {
                        UserDetailsPopupPanel.this.uiUser.addProperty(new UiUserProperty(propertyName, propertyValue));
                        propertiesTable.refresh(UserDetailsPopupPanel.this.uiUser.getProperties());
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
                            UserDetailsPopupPanel.this.hide();
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
        
        propertiesTable.refresh(uiUser.getProperties());


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


    public void init() {
        uiUser.addPropertyListener(this);
    }

    public void cleanUp() {
        uiUser.removePropertyListener(this);
    }

    public void disable() {
        saveUserDetails.setEnabled(false);
        cancelUserDetails.setEnabled(false);
    }

    public void enable() {
        saveUserDetails.setEnabled(true);
        cancelUserDetails.setEnabled(true);
    }
}
