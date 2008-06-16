package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.UserManagementService;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.domain.Groups;
import org.tmjee.miniwiki.client.domain.Group;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.Constants;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 2, 2008
 * Time: 6:25:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTableWidget extends VerticalPanel implements SourcesMessageEvents {


    private SourcesEventsSupport sourcesEventSupport;

    private HorizontalPanel searchPanel;
    private TextBox search;
    private Button searchGroup;
    private Button all;
    private Button delete;
    private Button add;
    private FlexTable table;
    private Grid grid;


    private List<Group> selectedGroups;

    private State state;

    public GroupTableWidget() {

        state = new State();

        selectedGroups = new ArrayList<Group>();

        searchPanel = new HorizontalPanel();
        search = new TextBox();
        searchGroup = new Button("Search", new ClickListener() {
            public void onClick(Widget sender) {
                if (search.getText() == null || (search.getText().trim().length() <= 0)) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Group Name must not be empty"));
                        }
                    });
                }
                else {
                    listGroup(search.getText(), new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
                }
            }
        });
        all = new Button("All", new ClickListener() {
            public void onClick(Widget sender) {
                listAllGroups(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
            }
        });
        delete = new Button("Delete", new ClickListener() {
            public void onClick(Widget sender) {
                UserManagementServiceAsync userManagement = Service.getUserManagementService();
                for (final Group group : selectedGroups) {
                    LoadingMessageDisplayWidget.getInstance().display("Deleting group id "+group.getId());
                    userManagement.deleteGroup(group, new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            // TODO: logging
                            GWT.log(caught.toString(), caught);
                        }
                        public void onSuccess(Object result) {
                            LoadingMessageDisplayWidget.getInstance().done();
                            state.restore(GroupTableWidget.this);
                        }
                    });
                }
            }
        });
        add = new Button("Add", new ClickListener() {
            public void onClick(Widget sender) {
                new GroupDetailsPopupPanel(new Group());
            }
        });
        searchPanel.add(search);
        searchPanel.add(searchGroup);
        searchPanel.add(all);
        searchPanel.add(delete);
        searchPanel.add(add);
        add(searchPanel);

        grid = new Grid(1, 2);
        grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        grid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        add(grid);

        table = new FlexTable();
        table.setWidget(0, 0, new Label("Group Name"));
        table.setWidget(0, 1, new Label("Description"));
        table.setWidget(0, 2, new Label("")); // checkbox
        table.setWidget(0, 3, new Label("")); // edit button
        add(table);
    }

    public void listGroup(final String groupName, final PagingInfo pagingInfo) {
        state.capture(this, pagingInfo, false);
        selectedGroups.clear();
        UserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.searchForGroup(groupName, pagingInfo, false,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {
                        Groups groups = (Groups) result;
                        final ResponsePagingInfo responsePagingInfo = groups.getResponsePagingInfo();

                        // Prev/Next
                        if (responsePagingInfo.hasNextPage()) {
                            grid.setWidget(0, 1, new Button("Next", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listGroup(groupName, responsePagingInfo.getNextPagePagingInfo());
                                }
                            }));
                        }
                        if (responsePagingInfo.hasPreviousPage()) {
                            grid.setWidget(0, 0, new Button("Previous", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listGroup(groupName, responsePagingInfo.getPreviousPagePagingInfo());
                                }
                            }));
                        }


                        // populate table
                        int currentRow = 1;
                        for (Group group : groups.getGroups()) {
                            if (currentRow < table.getRowCount()) { // edit row if they already exists
                                ((Label)table.getWidget(currentRow, 0)).setText(group.getName());
                                ((Label)table.getWidget(currentRow, 1)).setText(group.getDescription());
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setObject(group);
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setChecked(false);
                                ((EditGroupButton)table.getWidget(currentRow, 3)).setGroup(group);
                            }
                            else { // add new row if they doesn't already exists
                                ObjectHoldableCheckBox checkBox =  new ObjectHoldableCheckBox(group);
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedGroups.contains(cb.getObject())) {
                                                selectedGroups.add((Group)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedGroups.contains(cb.getObject())) {
                                                selectedGroups.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(group.getName()));
                                table.setWidget(currentRow, 1, new Label(group.getDescription()));
                                table.setWidget(currentRow, 2, checkBox);
                                table.setWidget(currentRow, 3, new EditGroupButton(group));
                            }
                            currentRow++;
                        }
                        while (currentRow < table.getRowCount()) { // remove extra rows
                            table.removeRow(currentRow);
                            currentRow++;
                        }
                    }
                });
    }

    public void listAllGroups(PagingInfo pagingInfo) {
        state.capture(this, pagingInfo, false);
        selectedGroups.clear();
        UserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllGroups(pagingInfo,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {
                        Groups groups = (Groups) result;
                        final ResponsePagingInfo responsePagingInfo = groups.getResponsePagingInfo();

                        // Prev/Next
                        if (responsePagingInfo.hasNextPage()) {
                            grid.setWidget(0, 1, new Button("Next", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listAllGroups(responsePagingInfo.getNextPagePagingInfo());
                                }
                            }));
                        }
                        if (responsePagingInfo.hasPreviousPage()) {
                            grid.setWidget(0, 0, new Button("Previous", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listAllGroups(responsePagingInfo.getPreviousPagePagingInfo());
                                }
                            }));
                        }


                        // populate table
                        int currentRow = 1;
                        for (Group group : groups.getGroups()) {
                            if (currentRow < table.getRowCount()) { // edit row if they already exists
                                ((Label)table.getWidget(currentRow, 0)).setText(group.getName());
                                ((Label)table.getWidget(currentRow, 1)).setText(group.getDescription());
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setObject(group);
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setChecked(false);
                                ((EditGroupButton)table.getWidget(currentRow, 3)).setGroup(group);
                            }
                            else { // add new row if they doesn't already exists
                                ObjectHoldableCheckBox checkBox =  new ObjectHoldableCheckBox(group.getId());
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedGroups.contains(cb.getObject())) {
                                                selectedGroups.add((Group)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedGroups.contains(cb.getObject())) {
                                                selectedGroups.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(group.getName()));
                                table.setWidget(currentRow, 1, new Label(group.getDescription()));
                                table.setWidget(currentRow, 2, checkBox);
                                table.setWidget(currentRow, 3, new EditGroupButton(group));
                            }
                            currentRow++;
                        }
                        while (currentRow < table.getRowCount()) { // remove extra rows
                            table.removeRow(currentRow);
                            currentRow++;
                        }
                    }
                });
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventSupport.removeListener(listener);
    }


    private class EditGroupButton extends Button {
        private Group group;
        public EditGroupButton(Group group) {
            this.group = group;
            addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    new GroupDetailsPopupPanel(EditGroupButton.this.group);
                }
            });
        }
        public void setGroup(Group group) {
            this.group = group;
        }
    }


    private class State {
        private String groupName;
        private PagingInfo pagingInfo;
        private boolean listAllGroups;
        public void capture(GroupTableWidget w, PagingInfo pagingInfo, boolean listAllGroups) {
            groupName = w.search.getText();
            this.pagingInfo = pagingInfo;
            this.listAllGroups = listAllGroups;
        }

        public void restore(GroupTableWidget w) {
            if (listAllGroups) {
                w.listAllGroups(pagingInfo);
            }
            else {
                w.listGroup(groupName, pagingInfo);
            }
        }
    }
}
