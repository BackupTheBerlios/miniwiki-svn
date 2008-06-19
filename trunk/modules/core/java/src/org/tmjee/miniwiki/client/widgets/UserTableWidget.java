package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.Constants;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.domain.UiUsers;
import org.tmjee.miniwiki.client.domain.UiUser;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 2, 2008
 * Time: 4:21:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTableWidget extends VerticalPanel implements SourcesMessageEvents {

    private SourcesEventsSupport sourcesEventSupport;

    private HorizontalPanel searchPanel;
    private TextBox search;
    private Button searchUser;
    private Button all;
    private Button delete;
    private Button add;
    private FlexTable table;
    private Grid grid;

    private List<UiUser> selectedUiUser;
    private State state;

    public UserTableWidget() {

        selectedUiUser = new ArrayList<UiUser>();
        state = new State(this);

        sourcesEventSupport = new SourcesEventsSupport();

        searchPanel = new HorizontalPanel();
        search = new TextBox();
        searchUser = new Button("Search User",
                new ClickListener() {
                    public void onClick(Widget sender) {
                        if (search.getText() == null || (search.getText().trim().length() <= 0)) {
                            sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                                public void handle(Object listener) {
                                    ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Username could not be empty"));
                                }
                            });
                        }
                        else {
                            listUser(search.getText(), new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
                        }
                    }
                });
        all = new Button("All",
                new ClickListener() {
                    public void onClick(Widget sender) {
                        listAllUsers(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
                    }
                });
        delete = new Button("Delete",
                new ClickListener() {
                    public void onClick(Widget sender) {
                        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
                        if (selectedUiUser.size() <= 0) {
                            sourcesEventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                                public void handle(Object listener) {
                                    ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "No users available/selected"));
                                }
                            });
                            return;
                        }
                        for (final UiUser uiUser : selectedUiUser) {
                            LoadingMessageDisplayWidget.getInstance().display("Deleting User ID "+ uiUser.getId());
                            userManagementService.deleteUser(uiUser, new AsyncCallback() {
                                public void onFailure(Throwable caught) {
                                    Logger.error(caught.toString(), caught);
                                    LoadingMessageDisplayWidget.getInstance().done();    
                                }
                                public void onSuccess(Object result) {
                                   state.restore();
                                   LoadingMessageDisplayWidget.getInstance().done();
                                }
                            });
                        }
                    }
                });
        add = new Button("Add",
                new ClickListener() {
                    public void onClick(Widget sender) {
                        new UserDetailsPopupPanel(new UiUser(), new UserDetailsPopupPanel.Handler() {
                            public void save(UiUser uiUser) {
                                state.restore();
                            }
                        });
                    }
                });
        searchPanel.add(search);
        searchPanel.add(searchUser);
        searchPanel.add(all);
        searchPanel.add(add);
        searchPanel.add(delete);
        add(searchPanel);

        grid = new Grid(1, 2);
        grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        grid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        add(grid);

        table = new FlexTable();
        table.setWidget(0, 0, new Label("Username"));
        table.setWidget(0, 1, new Label("First Name"));
        table.setWidget(0, 2, new Label("Last Name"));
        table.setWidget(0, 3, new Label("")); // checkbox
        table.setWidget(0, 4, new Label("")); // Edit button
        add(table);
        listAllUsers(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
    }


    public void listUser(final String username, final PagingInfo pagingInfo) {

        LoadingMessageDisplayWidget.getInstance().display("Loading user info ...");
        state.capture(pagingInfo, false);
        selectedUiUser.clear();
        UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.searchForUser(
                username,
                pagingInfo,
                false,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Logger.error(caught.toString(), caught);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object result) {
                        UiUsers uiUsers = (UiUsers) result;
                        final ResponsePagingInfo responsePagingInfo = uiUsers.getResponsePagingInfo();
                        if (responsePagingInfo.hasNextPage()) {
                            grid.setWidget(0, 0, new Button("Next", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listUser(username,
                                            responsePagingInfo.getNextPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button b = new Button("Next");
                            b.setEnabled(false);
                            grid.setWidget(0, 0, b);
                        }
                        if (responsePagingInfo.hasPreviousPage()) {
                            grid.setWidget(0, 1, new Button("Previous", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listUser(username,
                                            responsePagingInfo.getPreviousPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button b = new Button("Previous");
                            b.setEnabled(false);
                            grid.setWidget(0, 1, b);
                        }

                        List userlist = uiUsers.getUsers();
                        int currentRow = 1;
                        for (Iterator i = userlist.iterator(); i.hasNext(); ) {
                            UiUser uiUser = (UiUser) i.next();

                            if (currentRow < table.getRowCount()) {
                                // uiUser existing row
                                {
                                    Label l = (Label) table.getWidget(currentRow, 0);
                                    l.setText(uiUser.getUsername());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 1);
                                    l.setText(uiUser.getFirstName());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 2);
                                    l.setText(uiUser.getLastName());
                                }
                                {
                                    ObjectHoldableCheckBox c = (ObjectHoldableCheckBox) table.getWidget(currentRow, 3);
                                    c.setChecked(false);
                                    c.setObject(uiUser);
                                }
                                {
                                    EditUserButton editUserButton =(EditUserButton) table.getWidget(currentRow, 4);
                                    editUserButton.setUser(uiUser);
                                }
                            }
                            else {
                                // add row
                                ObjectHoldableCheckBox checkBox = new ObjectHoldableCheckBox(uiUser);
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedUiUser.contains(cb.getObject())) {
                                                selectedUiUser.add((UiUser)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedUiUser.contains(cb.getObject())) {
                                                selectedUiUser.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(uiUser.getUsername()));
                                table.setWidget(currentRow, 1, new Label(uiUser.getFirstName()));
                                table.setWidget(currentRow, 2, new Label(uiUser.getLastName()));
                                table.setWidget(currentRow, 3, checkBox);
                                table.setWidget(currentRow, 4, new EditUserButton(uiUser, state));
                            }
                            currentRow++;
                        }

                        while (currentRow < table.getRowCount()) {
                            // delete row
                            table.removeRow(currentRow);
                            currentRow++;
                        }

                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }

    public void listAllUsers(final PagingInfo pagingInfo) {

        LoadingMessageDisplayWidget.getInstance().display("Loading Users ...");

        state.capture(pagingInfo, true);
        selectedUiUser.clear();
        UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllUsers(pagingInfo,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Logger.error(caught.toString(), caught);
                        LoadingMessageDisplayWidget.getInstance().display("Loading User Info ...");
                    }
                    public void onSuccess(Object result) {
                        UiUsers uiUsers = (UiUsers) result;

                        // put in next, prev buttons
                        final ResponsePagingInfo responsePagingInfo = uiUsers.getResponsePagingInfo();
                        if (responsePagingInfo.hasNextPage()) {
                            grid.setWidget(0, 0, new Button("Next", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listAllUsers(
                                            responsePagingInfo.getNextPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button b = new Button("Next");
                            b.setEnabled(false);
                            grid.setWidget(0, 0, b);
                        }
                        if (responsePagingInfo.hasPreviousPage()) {
                            grid.setWidget(0, 1, new Button("Previous", new ClickListener() {
                                public void onClick(Widget sender) {
                                    listAllUsers(
                                            responsePagingInfo.getPreviousPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button b = new Button("Previous");
                            b.setEnabled(false);
                            grid.setWidget(0, 1, b);
                        }


                        // populate the table
                        int currentRow = 1;
                        for (UiUser uiUser : uiUsers.getUsers()) {
                            if (currentRow < table.getRowCount()) {
                                // uiUser existing row
                                {
                                    Label l = (Label) table.getWidget(currentRow, 0);
                                    l.setText(uiUser.getUsername());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 1);
                                    l.setText(uiUser.getFirstName());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 2);
                                    l.setText(uiUser.getLastName());
                                }
                                {
                                    ObjectHoldableCheckBox c = (ObjectHoldableCheckBox) table.getWidget(currentRow, 3);
                                    c.setObject(uiUser);
                                }
                                {
                                    EditUserButton editUserButton =(EditUserButton) table.getWidget(currentRow, 4);
                                    editUserButton.setUser(uiUser);
                                }    
                            }
                            else {
                                // add row
                                table.setWidget(currentRow, 0, new Label(uiUser.getUsername()));
                                table.setWidget(currentRow, 1, new Label(uiUser.getFirstName()));
                                table.setWidget(currentRow, 2, new Label(uiUser.getLastName()));
                                table.setWidget(currentRow, 3, new ObjectHoldableCheckBox(uiUser));
                                table.setWidget(currentRow, 4, new EditUserButton(uiUser, state));
                            }
                            currentRow++;
                        }

                        while (currentRow < table.getRowCount()) {
                            // delete row
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


    private class EditUserButton extends Button {

        private UiUser uiUser;
        private State state;

        public EditUserButton(UiUser uiUser, State state) {
            super("Edit");
            this.uiUser = uiUser;
            this.state = state;
            addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    new UserDetailsPopupPanel(EditUserButton.this.uiUser,
                            new UserDetailsPopupPanel.Handler() {
                                public void save(UiUser uiUser) {
                                    EditUserButton.this.state.restore();    
                                }
                            });
                }
            });
        }

        public void setUser(UiUser uiUser) {
            this.uiUser = uiUser;
        }

    }

    private class State {

        private UserTableWidget w;
        private String username;
        private PagingInfo pagingInfo;
        private boolean listAllUser;


        public State(UserTableWidget w) {
            this.w = w;
        }

        public void capture(PagingInfo pagingInfo, boolean listAllUser) {
            username = w.search.getText();
            this.pagingInfo = pagingInfo;
            this.listAllUser = listAllUser;
        }

        public void restore() {
            if (listAllUser) {
                w.listAllUsers(pagingInfo);
            }
            else {
                w.listUser(username, pagingInfo);
            }
        }
    }
}
