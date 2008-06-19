package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.UiUserManagementService;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.domain.UiGroups;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.Constants;
import org.tmjee.miniwiki.client.utils.Logger;

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


    private List<UiGroup> selectedUiGroups;

    private State state;

    public GroupTableWidget() {

        state = new State();

        sourcesEventSupport = new SourcesEventsSupport();
        selectedUiGroups = new ArrayList<UiGroup>();

        searchPanel = new HorizontalPanel();
        search = new TextBox();
        searchGroup = new Button("Search", new ClickListener() {
            public void onClick(Widget sender) {
                if (search.getText() == null || (search.getText().trim().length() <= 0)) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "UiGroup Name must not be empty"));
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

                if (selectedUiGroups.size() <= 0) {
                    sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Group is not available/selected"));
                        }
                    });
                    return;
                }


                UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
                for (final UiGroup uiGroup : selectedUiGroups) {
                    LoadingMessageDisplayWidget.getInstance().display("Deleting uiGroup id "+ uiGroup.getId());
                    userManagement.deleteGroup(uiGroup, new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            Logger.error(caught.toString(), caught);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object result) {
                            state.restore(GroupTableWidget.this);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                    });
                }
            }
        });
        add = new Button("Add", new ClickListener() {
            public void onClick(Widget sender) {
                new GroupDetailsPopupPanel(new UiGroup());
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
        table.setWidget(0, 0, new Label("UiGroup Name"));
        table.setWidget(0, 1, new Label("Description"));
        table.setWidget(0, 2, new Label("")); // checkbox
        table.setWidget(0, 3, new Label("")); // edit button
        add(table);

        listAllGroups(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
    }

    public void listGroup(final String groupName, final PagingInfo pagingInfo) {

        if (groupName == null || (groupName.trim().length() <= 0)) {
            sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                public void handle(Object listener) {
                    ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Group Name is required"));
                }
            });
            return;
        }


        LoadingMessageDisplayWidget.getInstance().display("Loading groups info ...");
        state.capture(this, pagingInfo, false);
        selectedUiGroups.clear();
        UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.searchForGroup(groupName, pagingInfo, false,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Logger.error(caught.toString(), caught);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object result) {
                        UiGroups uiGroups = (UiGroups) result;
                        final ResponsePagingInfo responsePagingInfo = uiGroups.getResponsePagingInfo();

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
                        for (UiGroup uiGroup : uiGroups.getGroups()) {
                            if (currentRow < table.getRowCount()) { // edit row if they already exists
                                ((Label)table.getWidget(currentRow, 0)).setText(uiGroup.getName());
                                ((Label)table.getWidget(currentRow, 1)).setText(uiGroup.getDescription());
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setObject(uiGroup);
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setChecked(false);
                                ((EditGroupButton)table.getWidget(currentRow, 3)).setGroup(uiGroup);
                            }
                            else { // add new row if they doesn't already exists
                                ObjectHoldableCheckBox checkBox =  new ObjectHoldableCheckBox(uiGroup);
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedUiGroups.contains(cb.getObject())) {
                                                selectedUiGroups.add((UiGroup)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedUiGroups.contains(cb.getObject())) {
                                                selectedUiGroups.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(uiGroup.getName()));
                                table.setWidget(currentRow, 1, new Label(uiGroup.getDescription()));
                                table.setWidget(currentRow, 2, checkBox);
                                table.setWidget(currentRow, 3, new EditGroupButton(uiGroup));
                            }
                            currentRow++;
                        }
                        while (currentRow < table.getRowCount()) { // remove extra rows
                            table.removeRow(currentRow);
                            currentRow++;
                        }
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }

    public void listAllGroups(PagingInfo pagingInfo) {

        LoadingMessageDisplayWidget.getInstance().display("Loading Groups Info ...");
        state.capture(this, pagingInfo, false);
        selectedUiGroups.clear();
        UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllGroups(pagingInfo,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Logger.error(caught.toString(), caught);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object result) {
                        UiGroups uiGroups = (UiGroups) result;
                        final ResponsePagingInfo responsePagingInfo = uiGroups.getResponsePagingInfo();

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
                        for (UiGroup uiGroup : uiGroups.getGroups()) {
                            if (currentRow < table.getRowCount()) { // edit row if they already exists
                                ((Label)table.getWidget(currentRow, 0)).setText(uiGroup.getName());
                                ((Label)table.getWidget(currentRow, 1)).setText(uiGroup.getDescription());
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setObject(uiGroup);
                                ((ObjectHoldableCheckBox)table.getWidget(currentRow, 2)).setChecked(false);
                                ((EditGroupButton)table.getWidget(currentRow, 3)).setGroup(uiGroup);
                            }
                            else { // add new row if they doesn't already exists
                                ObjectHoldableCheckBox checkBox =  new ObjectHoldableCheckBox(uiGroup.getId());
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedUiGroups.contains(cb.getObject())) {
                                                selectedUiGroups.add((UiGroup)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedUiGroups.contains(cb.getObject())) {
                                                selectedUiGroups.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(uiGroup.getName()));
                                table.setWidget(currentRow, 1, new Label(uiGroup.getDescription()));
                                table.setWidget(currentRow, 2, checkBox);
                                table.setWidget(currentRow, 3, new EditGroupButton(uiGroup));
                            }
                            currentRow++;
                        }
                        while (currentRow < table.getRowCount()) { // remove extra rows
                            table.removeRow(currentRow);
                            currentRow++;
                        }
                        LoadingMessageDisplayWidget.getInstance().done();
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
        private UiGroup uiGroup;
        public EditGroupButton(UiGroup uiGroup) {
            this.uiGroup = uiGroup;
            addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    new GroupDetailsPopupPanel(EditGroupButton.this.uiGroup);
                }
            });
        }
        public void setGroup(UiGroup uiGroup) {
            this.uiGroup = uiGroup;
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
