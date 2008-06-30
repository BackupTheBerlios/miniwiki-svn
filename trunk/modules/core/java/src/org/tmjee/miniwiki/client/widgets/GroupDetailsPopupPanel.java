package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiGroupProperty;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.utils.Utils;
import org.tmjee.miniwiki.client.server.PagingInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 2:32:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupDetailsPopupPanel extends DialogBox implements SourcesMessageEvents {

    private SourcesEventsSupport sourcesEventsSupport;

    private VerticalPanel mainPanel;

    private Grid grid;
    private TextBox groupName;
    private TextBox groupDescription;


    private GenericTableWidget usersTable;
    private HorizontalPanel assignUserButtonPanel;
    private Button assignUserButton;


    private FlexTableExt propertiesTable;
    private HorizontalPanel propertiesButtonPanel;
    private Button addPropertyButton;
    private Button removePropertyButton;
    

    private HorizontalPanel buttonsPanel;
    private Button save;
    private Button cancel;

    private UiGroup uiGroup;


    public GroupDetailsPopupPanel(UiGroup uiGroup) {

        this.uiGroup = uiGroup;
        this.sourcesEventsSupport = new SourcesEventsSupport();

        setText("Group Details");
        setAnimationEnabled(true);

        grid = new Grid(2, 2);
        grid.setWidget(0, 0, new Label("Group Name"));
        grid.setWidget(0, 1, groupName);
        grid.setWidget(1, 0, new Label("Group Description"));
        grid.setWidget(1, 1, groupDescription);



        usersTable = new GenericTableWidget() {
            protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {
                // to nothing, we don't have anything to add
            }
            protected void refresh(PagingInfo pagingInfo) {
                
            }
        };
        usersTable.init(
                        new FlexTableExt.TitleHandler() {
                            public int getTotalCols() {
                                return 0;  //To change body of implemented methods use File | Settings | File Templates.
                            }

                            public boolean hasCheckableCol() {
                                return false;  //To change body of implemented methods use File | Settings | File Templates.
                            }

                            public int numOfControlWidget() {
                                return 0;  //To change body of implemented methods use File | Settings | File Templates.
                            }

                            public Widget getTitleWidget(int col) {
                                return null;  //To change body of implemented methods use File | Settings | File Templates.
                            }
                        },
                        new FlexTableExt.DataHandler() {
                            public Widget getDataWidget(Object rowObject, int col) {
                                return null;  //To change body of implemented methods use File | Settings | File Templates.
                            }

                            public Widget getControlWidget(Object rowObject, int col, int index) {
                                return null;  //To change body of implemented methods use File | Settings | File Templates.
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
                                        rowObject.getName(),
                                        rowObject.getValue(),
                                        new PropertyDetailsPopupPanel.Handler() {
                                            public void save(String propertyName, String propertyValue) {
                                                rowObject.setName(propertyName);
                                                rowObject.setValue(propertyValue);
                                                propertiesTable.refresh(Utils.toArray(
                                                        GroupDetailsPopupPanel.this.uiGroup.getProperties()));
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
                        new PropertyDetailsPopupPanel.Handler() {
                            public void save(String propertyName, String propertyValue) {
                                GroupDetailsPopupPanel.this.uiGroup.addProperty(new UiGroupProperty(propertyName, propertyValue));
                                propertiesTable.refresh(Utils.toArray(GroupDetailsPopupPanel.this.uiGroup.getProperties()));
                            }
                        });
            }
        });
        removePropertyButton = new Button("Delete Property", new ClickListener() {
            public void onClick(Widget sender) {
                new PropertyDetailsPopupPanel(
                        new PropertyDetailsPopupPanel.Handler() {
                            public void save(String propertyName, String propertyValue) {
                                List<UiGroupProperty> selectedProperties = propertiesTable.getSelectedRowObjects();
                                for (UiGroupProperty prop: selectedProperties) {
                                    GroupDetailsPopupPanel.this.uiGroup.removeProperty(prop);
                                }
                                propertiesTable.refresh(Utils.toArray(GroupDetailsPopupPanel.this.uiGroup.getProperties()));
                            }
                        }
                );
            }
        });
        propertiesButtonPanel.add(addPropertyButton);
        propertiesButtonPanel.add(removePropertyButton);


        mainPanel = new VerticalPanel();
        mainPanel.add(grid);
        mainPanel.add(assignUserButtonPanel);
        mainPanel.add(usersTable);
        mainPanel.add(propertiesButtonPanel);
        mainPanel.add(propertiesTable);
        mainPanel.add(buttonsPanel);


        setWidget(mainPanel);

        center();
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.removeListener(listener);
    }
}
