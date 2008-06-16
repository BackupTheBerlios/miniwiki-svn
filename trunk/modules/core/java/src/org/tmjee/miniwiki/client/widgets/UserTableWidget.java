package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.Constants;
import org.tmjee.miniwiki.client.utils.MutableInteger;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.domain.Users;
import org.tmjee.miniwiki.client.domain.User;

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

    private List<User> selectedUser;
    private State state;

    public UserTableWidget() {

        selectedUser = new ArrayList<User>();
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
                        UserManagementServiceAsync userManagementService = Service.getUserManagementService();
                        for (final User user : selectedUser) {
                            LoadingMessageDisplayWidget.getInstance().display("Deleting user ID "+user.getId());
                            userManagementService.deleteUser(user, new AsyncCallback() {
                                public void onFailure(Throwable caught) {
                                    // TODO: logging
                                    GWT.log(caught.toString(), caught);
                                }
                                public void onSuccess(Object result) {
                                   LoadingMessageDisplayWidget.getInstance().done();
                                   state.restore();
                                }
                            });
                        }
                    }
                });
        add = new Button("Add",
                new ClickListener() {
                    public void onClick(Widget sender) {
                        new UserDetailsPopupPanel(new User(), new UserDetailsPopupPanel.Handler() {
                            public void save(User user) {
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
        state.capture(pagingInfo, false);
        selectedUser.clear();
        UserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.searchForUser(
                username,
                pagingInfo,
                false,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {
                        Users users = (Users) result;
                        final ResponsePagingInfo responsePagingInfo = users.getResponsePagingInfo();
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

                        List userlist = users.getUsers();
                        int currentRow = 1;
                        for (Iterator i = userlist.iterator(); i.hasNext(); ) {
                            User user = (User) i.next();

                            if (currentRow < table.getRowCount()) {
                                // user existing row
                                {
                                    Label l = (Label) table.getWidget(currentRow, 0);
                                    l.setText(user.getUsername());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 1);
                                    l.setText(user.getFirstName());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 2);
                                    l.setText(user.getLastName());
                                }
                                {
                                    ObjectHoldableCheckBox c = (ObjectHoldableCheckBox) table.getWidget(currentRow, 3);
                                    c.setChecked(false);
                                    c.setObject(user);
                                }
                                {
                                    EditUserButton editUserButton =(EditUserButton) table.getWidget(currentRow, 4);
                                    editUserButton.setUser(user);
                                }
                            }
                            else {
                                // add row
                                ObjectHoldableCheckBox checkBox = new ObjectHoldableCheckBox(user);
                                checkBox.addClickListener(new ClickListener() {
                                    public void onClick(Widget sender) {
                                        ObjectHoldableCheckBox cb = (ObjectHoldableCheckBox) sender;
                                        if (cb.isChecked()) {
                                            if (!selectedUser.contains(cb.getObject())) {
                                                selectedUser.add((User)cb.getObject());
                                            }
                                        }
                                        else {
                                            if (selectedUser.contains(cb.getObject())) {
                                                selectedUser.remove(cb.getObject());
                                            }
                                        }
                                    }
                                });
                                table.setWidget(currentRow, 0, new Label(user.getUsername()));
                                table.setWidget(currentRow, 1, new Label(user.getFirstName()));
                                table.setWidget(currentRow, 2, new Label(user.getLastName()));
                                table.setWidget(currentRow, 3, checkBox);
                                table.setWidget(currentRow, 4, new EditUserButton(user, state));
                            }
                            currentRow++;
                        }

                        while (currentRow < table.getRowCount()) {
                            // delete row
                            table.removeRow(currentRow);
                            currentRow++;
                        }
                    }
                });
    }

    public void listAllUsers(final PagingInfo pagingInfo) {
        state.capture(pagingInfo, true);
        selectedUser.clear();
        UserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllUsers(pagingInfo,
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {
                        Users users = (Users) result;

                        // put in next, prev buttons
                        final ResponsePagingInfo responsePagingInfo = users.getResponsePagingInfo();
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
                        for (User user : users.getUsers()) {
                            if (currentRow < table.getRowCount()) {
                                // user existing row
                                {
                                    Label l = (Label) table.getWidget(currentRow, 0);
                                    l.setText(user.getUsername());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 1);
                                    l.setText(user.getFirstName());
                                }
                                {
                                    Label l = (Label) table.getWidget(currentRow, 2);
                                    l.setText(user.getLastName());
                                }
                                {
                                    ObjectHoldableCheckBox c = (ObjectHoldableCheckBox) table.getWidget(currentRow, 3);
                                    c.setObject(user);
                                }
                                {
                                    EditUserButton editUserButton =(EditUserButton) table.getWidget(currentRow, 4);
                                    editUserButton.setUser(user);
                                }    
                            }
                            else {
                                // add row
                                table.setWidget(currentRow, 0, new Label(user.getUsername()));
                                table.setWidget(currentRow, 1, new Label(user.getFirstName()));
                                table.setWidget(currentRow, 2, new Label(user.getLastName()));
                                table.setWidget(currentRow, 3, new ObjectHoldableCheckBox(user));
                                table.setWidget(currentRow, 4, new EditUserButton(user, state));
                            }
                            currentRow++;
                        }

                        while (currentRow < table.getRowCount()) {
                            // delete row
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


    private class EditUserButton extends Button {

        private User user;
        private State state;

        public EditUserButton(User user, State state) {
            super("Edit");
            this.user = user;
            this.state = state;
            addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    new UserDetailsPopupPanel(EditUserButton.this.user,
                            new UserDetailsPopupPanel.Handler() {
                                public void save(User user) {
                                    EditUserButton.this.state.restore();    
                                }
                            });
                }
            });
        }

        public void setUser(User user) {
            this.user = user;
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
