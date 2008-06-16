package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.domain.User;
import org.tmjee.miniwiki.client.domain.Groups;
import org.tmjee.miniwiki.client.domain.Group;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UserManagementServiceAsync;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.Constants;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 6:24:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignGroupPopupPanel extends PopupPanel {

    private VerticalPanel mainPanel;

    private User user;
    private Grid grid; // store next / prev buttons
    private FlexTable groupsTable;

    private Handler handler;


    public interface Handler {
        void join(User user, Group group);
        void leave(User user, Group group);
    }


    public AssignGroupPopupPanel(User user, Handler handler) {

        this.user =user;
        this.handler = handler;

        groupsTable = new FlexTable();
        groupsTable.setWidget(0, 0, new Label("Group Name"));
        groupsTable.setWidget(0, 1, new Label("")); // join / leave

        grid = new Grid(1, 2);

        mainPanel = new VerticalPanel();
        mainPanel.add(grid);
        mainPanel.add(groupsTable);

        loadData(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
    }


    public void loadData(PagingInfo pagingInfo) {

        LoadingMessageDisplayWidget.getInstance().display("Loading groups info ...");


        UserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllGroups(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE),
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {

                        Groups groups = (Groups) result;
                        final ResponsePagingInfo responsePagingInfo = groups.getResponsePagingInfo();

                        // prev/next
                        if (responsePagingInfo.hasNextPage()) {
                            grid.setWidget(0, 1, new Button("Next", new ClickListener() {
                                public void onClick(Widget sender) {
                                    loadData(responsePagingInfo.getNextPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button button = new Button("Next");
                            button.setEnabled(false);
                            grid.setWidget(0, 1, button);
                        }
                        if (responsePagingInfo.hasPreviousPage()) {
                            grid.setWidget(0, 0, new Button("Previous", new ClickListener() {
                                public void onClick(Widget sender) {
                                    loadData(responsePagingInfo.getPreviousPagePagingInfo());
                                }
                            }));
                        }
                        else {
                            Button button = new Button("Previous");
                            button.setEnabled(false);
                            grid.setWidget(0, 0, button);
                        }


                        // populate table
                        groupsTable.setWidget(0, 0, new Label("Group Name"));
                        int totalRows = groupsTable.getRowCount();
                        int currentRow = 1;
                        for (final Group group : groups.getGroups()) {
                            final boolean isUserInGroup = user.isInGroup(group);
                            if (currentRow < totalRows) {
                                groupsTable.setWidget(currentRow, 0, new Label(group.getName()));
                                groupsTable.setWidget(currentRow, 1, new Button(
                                    isUserInGroup?"Join":"Leave",
                                    new ClickListener() {
                                        public void onClick(Widget sender) {
                                            if (isUserInGroup) {
                                                user.removeGroup(group);
                                                AssignGroupPopupPanel.this.handler.leave(user, group);
                                            }
                                            else {
                                                user.addGroup(group);
                                                AssignGroupPopupPanel.this.handler.join(user, group);
                                            }
                                        }
                                    }));
                            }
                            else {

                            }
                            currentRow++;
                        }
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }

}
