package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroups;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
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
public class AssignGroupPopupPanel extends DialogBox {

    private VerticalPanel mainPanel;

    private UiUser uiUser;
    private Grid grid; // store next / prev buttons
    private FlexTable groupsTable;
    private HorizontalPanel buttonPanel;

    private Handler handler;


    public interface Handler {
        void join(UiUser uiUser, UiGroup uiGroup);
        void leave(UiUser uiUser, UiGroup uiGroup);
    }


    public AssignGroupPopupPanel(UiUser uiUser, Handler handler) {

        setText("Group Assignment");
        setAnimationEnabled(true);

        this.uiUser = uiUser;
        this.handler = handler;

        groupsTable = new FlexTable();
        groupsTable.setWidget(0, 0, new Label("Group Name"));
        groupsTable.setWidget(0, 1, new Label("")); // join / leave

        grid = new Grid(1, 2);

        buttonPanel = new HorizontalPanel();
        buttonPanel.add(new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                AssignGroupPopupPanel.this.hide();
            }
        }));

        mainPanel = new VerticalPanel();
        mainPanel.add(grid);
        mainPanel.add(groupsTable);
        mainPanel.add(buttonPanel);

        //loadData(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));

        center();
    }


    public void loadData(PagingInfo pagingInfo) {

        LoadingMessageDisplayWidget.getInstance().display("Loading groups info ...");


        UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
        userManagement.getAllGroups(new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE),
                new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        // TODO: logging
                        GWT.log(caught.toString(), caught);
                    }
                    public void onSuccess(Object result) {

                        UiGroups uiGroups = (UiGroups) result;
                        final ResponsePagingInfo responsePagingInfo = uiGroups.getResponsePagingInfo();

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
                        for (final UiGroup uiGroup : uiGroups.getGroups()) {
                            final boolean isUserInGroup = uiUser.isInGroup(uiGroup);
                            if (currentRow < totalRows) {
                                groupsTable.setWidget(currentRow, 0, new Label(uiGroup.getName()));
                                groupsTable.setWidget(currentRow, 1, new Button(
                                    isUserInGroup?"Join":"Leave",
                                    new ClickListener() {
                                        public void onClick(Widget sender) {
                                            if (isUserInGroup) {
                                                uiUser.removeGroup(uiGroup);
                                                AssignGroupPopupPanel.this.handler.leave(uiUser, uiGroup);
                                            }
                                            else {
                                                uiUser.addGroup(uiGroup);
                                                AssignGroupPopupPanel.this.handler.join(uiUser, uiGroup);
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
