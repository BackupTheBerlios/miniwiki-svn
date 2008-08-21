package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiGroupProperty;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.utils.Utils;
import org.tmjee.miniwiki.client.utils.WidgetUtils;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.beans.PropertyListener;
import org.tmjee.miniwiki.client.beans.EventObject;
import org.tmjee.miniwiki.client.beans.PropertyAdditionEvent;
import org.tmjee.miniwiki.client.beans.PropertyDeletionEvent;
import org.tmjee.miniwiki.client.service.Service;

import java.util.List;
import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class GroupDetailsPopupPanel extends DialogBox implements SourcesMessageEvents, Initializable, CleanUpable, PropertyListener {

    private SourcesEventsSupport sourcesEventsSupport;

    private VerticalPanel mainPanel;

    private Grid grid;
    private CheckBox enabled;
    private TextBox groupName;
    private TextBox groupDescription;


    private GenericTableWidget usersTable;
    private Button assignUserButton;


    private FlexTableExt propertiesTable;
    private HorizontalPanel propertiesButtonPanel;
    private Button addPropertyButton;
    private Button removePropertyButton;
    

    private HorizontalPanel buttonsPanel;
    private Button save;
    private Button cancel;

    private UiGroup uiGroup;
    private Handler handler;
    private boolean editMode;

    public static interface Handler {
        void save(UiGroup uiGroup);
    }


    public GroupDetailsPopupPanel(boolean editMode, UiGroup uiGroup, Handler handler) {

        this.handler = handler;
        this.uiGroup = uiGroup;
        this.sourcesEventsSupport = new SourcesEventsSupport();

        setText("Group Details");
        setAnimationEnabled(true);
        this.editMode = editMode;

        enabled = new CheckBox();
        enabled.setChecked(uiGroup.isEnabled());
        enabled.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                GroupDetailsPopupPanel.this.uiGroup.setEnabled(((CheckBox)widget).isEnabled());
            }
        });

        groupName = new TextBox();
        groupName.setText(uiGroup.getName());
        groupName.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                GroupDetailsPopupPanel.this.uiGroup.setName(((TextBox)sender).getText());
            }
        });
        if (editMode) {
            groupName.setEnabled(false);
        }

        groupDescription = new TextBox();
        groupDescription.setText(uiGroup.getDescription());
        groupDescription.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                GroupDetailsPopupPanel.this.uiGroup.setName(((TextBox)sender).getText());
            }
        });


        grid = new Grid(2, 2);
        grid.setWidget(0, 0, new Label("Group Name"));
        grid.setWidget(0, 1, groupName);
        grid.setWidget(1, 0, new Label("Group Description"));
        grid.setWidget(1, 1, groupDescription);




        usersTable = new GenericTableWidget<UiUser>() {
            protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {
                assignUserButton = new Button(
                                        "Assign User",
                                        new ClickListener() {
                                            public void onClick(Widget widget) {
                                                new AssignUserPopupPanel(
                                                        GroupDetailsPopupPanel.this.uiGroup,
                                                        new AssignUserPopupPanel.Handler() {
                                                            public void assign(UiUser user, UiGroup group) {
                                                                group.addUser(user);
                                                                usersTable.refresh();
                                                            }
                                                            public void unassign(UiUser user, UiGroup group) {
                                                                group.removeUser(user);
                                                                usersTable.refresh();
                                                            }
                                                        });
                                            }
                                        });
                searchPanel.add(assignUserButton);
            }
            protected void refresh(PagingInfo pagingInfo) {
                Utils.PageableObjectListWrapper<UiUser> w = Utils.toPageableObjectListWrapper(
                        pagingInfo,
                        GroupDetailsPopupPanel.this.uiGroup.getUsers());
                update(w.getResponse(), w.getList());
            }
        };
        usersTable.init(
                        new FlexTableExt.TitleHandler<UiUser>() {
                            public int getTotalCols() {
                                return 4;
                            }

                            public boolean hasCheckableCol() {
                                return true;
                            }

                            public int numOfControlWidget() {
                                return 0;
                            }

                            public Widget getTitleWidget(int col) {
                                switch(col) {
                                    case 0:
                                        return new Label("Username");
                                    case 1:
                                        return new Label("First Name");
                                    case 2:
                                        return new Label("Last Name");
                                    case 3:
                                        return new Label("Description");
                                    default:
                                        return null;
                                }
                            }
                        },
                        new FlexTableExt.DataHandler<UiUser>() {
                            public Widget getDataWidget(UiUser rowObject, int col) {
                                switch(col) {
                                    case 0:
                                        return new Label(rowObject.getUsername());
                                    case 1:
                                        return new Label(rowObject.getFirstName());
                                    case 2:
                                        return new Label(rowObject.getLastName());
                                    case 3:
                                        return new Label(rowObject.getDescription());
                                    default:
                                        return null;
                                }
                            }

                            public Widget getControlWidget(UiUser rowObject, int col, int index) {
                                return null;
                            }
                        }
                );




        propertiesButtonPanel = new HorizontalPanel();
        propertiesTable = new FlexTableExt<UiGroupProperty>();
        propertiesTable.init(
                new FlexTableExt.TitleHandler<UiGroupProperty>() {
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
                new FlexTableExt.DataHandler<UiGroupProperty>() {
                    public Widget getDataWidget(UiGroupProperty rowObject, int col) {
                        switch(col) {
                            case 0:
                                return new Label(rowObject.getName());
                            case 1:
                                return new Label(rowObject.getValue());
                            default:
                                return null;
                        }
                    }
                    public Widget getControlWidget(final UiGroupProperty rowObject, int col, int index) {
                        return new Button("Edit", new ClickListener() {
                            public void onClick(Widget sender) {
                                new PropertyDetailsPopupPanel(
                                        true,
                                        rowObject.getName(),
                                        rowObject.getValue(),
                                        new PropertyDetailsPopupPanel.Handler() {
                                            public void save(String propertyName, String propertyValue) {
                                                rowObject.setName(propertyName);
                                                rowObject.setValue(propertyValue);
                                                propertiesTable.refresh(
                                                        GroupDetailsPopupPanel.this.uiGroup.getProperties());
                                            }
                                        });
                            }
                        });
                    }
                }
        );
        addPropertyButton = new Button("Add Property", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(
                        false, 
                        new PropertyDetailsPopupPanel.Handler() {
                            public void save(String propertyName, String propertyValue) {
                                GroupDetailsPopupPanel.this.uiGroup.addProperty(new UiGroupProperty(propertyName, propertyValue));
                                propertiesTable.refresh(GroupDetailsPopupPanel.this.uiGroup.getProperties());
                            }
                        });
            }
        });
        removePropertyButton = new Button("Delete Property", new ClickListener() {
            public void onClick(Widget sender) {
                List<UiGroupProperty> selectedProperties = propertiesTable.getSelectedRowObjects();
                                for (UiGroupProperty prop: selectedProperties) {
                                    GroupDetailsPopupPanel.this.uiGroup.removeProperty(prop);
                                }
                                propertiesTable.refresh(GroupDetailsPopupPanel.this.uiGroup.getProperties());
            }});
        propertiesButtonPanel.add(addPropertyButton);
        propertiesButtonPanel.add(removePropertyButton);


        save = new Button("Save", new ClickListener() {
            public void onClick(Widget widget) {
                // validation
                boolean hasError = false;
                if (groupName.getText().trim().length() <= 0) {
                    hasError = true;
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Group Name is required"));
                        }
                    });
                }
                if (groupDescription.getText().trim().length() <= 0) {
                    hasError = true;
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Group Description is required"));
                        }
                    });
                }
                if(!hasError) {
                    GroupDetailsPopupPanel.this.handler.save(GroupDetailsPopupPanel.this.uiGroup);
                    GroupDetailsPopupPanel.this.hide();
                }
            }
        });
        cancel = new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                cleanUp();
                GroupDetailsPopupPanel.this.hide();
            }
        });
        buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(save);
        buttonsPanel.add(cancel);


        mainPanel = new VerticalPanel();
        mainPanel.add(grid);
        mainPanel.add(usersTable);
        mainPanel.add(propertiesButtonPanel);
        mainPanel.add(propertiesTable);
        mainPanel.add(buttonsPanel);


        WidgetUtils.init(this);
        setWidget(mainPanel);

        center();
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.removeListener(listener);
    }

    public void init() {
        uiGroup.addPropertyListener(this);
    }

    public void cleanUp() {
        uiGroup.removePropertyListener(this);
    }

    public void propertyChange(EventObject event) {
        if ("property".equals(event.getPropertyName())) {
            if ((event instanceof PropertyAdditionEvent) ||
                    (event instanceof PropertyDeletionEvent)) {
                propertiesTable.refresh(uiGroup.getProperties());
            }
        }
        else if ("user".equals(event.getPropertyName())) {
            if ((event instanceof PropertyAdditionEvent) ||
                    (event instanceof PropertyDeletionEvent)) {
                usersTable.refresh();            
            }
        }
    }
}
