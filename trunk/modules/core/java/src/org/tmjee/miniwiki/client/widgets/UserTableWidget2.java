package org.tmjee.miniwiki.client.widgets;

import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiUsers;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.service.Service;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 23, 2008
 * Time: 3:00:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTableWidget2 extends SimpleTableWidget {

    
    public UserTableWidget2() {
        super(new FlexTableExt.TitleHandler() {
            public int getTotalCols() {
                return 3;
            }
            public boolean hasCheckableCol() {
                return true;
            }
            public boolean hasControlWidget() {
                return true;
            }
            public int numOfControlWidget() {
                return 1;
            }
            public Widget getTitleWidget(int col) {
                switch(col) {
                    case 0:
                        return new Label("Username");
                    case 1:
                        return new Label("First Name");
                    case 2:
                        return new Label("Last Name");
                    default:
                        return null;
                }
            }
        });
    }


    protected void doAdd(final Status status) {
        new UserDetailsPopupPanel(
                new UiUser(),
                new UserDetailsPopupPanel.Handler() {
                            public void save(UiUser uiUser) {
                                status.restore();
                            }
                        });
    }

    protected void doDelete(Status status) {
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        for (Iterator i = status.getSelectedRowObjects().iterator(); i.hasNext(); ) {
            UiUser uiUser = (UiUser) i.next();
            LoadingMessageDisplayWidget.getInstance().display("Deleting User ID "+ uiUser.getId());
            userManagementService.deleteUser(uiUser,
                    new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            Logger.error(caught.toString(), caught);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object result) {
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                    });
        }
        status.restore();
    }

    protected void doListAll(final Status status) {
        LoadingMessageDisplayWidget.getInstance().display("Loading all users ...");
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        userManagementService.getAllUsers(
                status.getPagingInfo(),
                new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Logger.error(throwable.toString(), throwable);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object o) {
                        final UiUsers uiUsers = (UiUsers) o;
                        update(uiUsers.getResponsePagingInfo(),
                               new UiUserDataHandler(uiUsers, status));
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }

    protected void doSearch(final Status status) {
        LoadingMessageDisplayWidget.getInstance().display("Search for users ...");
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        userManagementService.searchForUser(
                status.getSearchText(),
                status.getPagingInfo(),
                false, 
                new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Logger.error(throwable.toString(), throwable);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object o) {
                        final UiUsers uiUsers = (UiUsers) o;
                        update(uiUsers.getResponsePagingInfo(),
                               new UiUserDataHandler(uiUsers, status));
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                }
        );
    }


    private class UiUserDataHandler implements FlexTableExt.DataHandler {

        private UiUsers uiUsers;
        private Status status;

        public UiUserDataHandler(UiUsers uiUsers, Status status) {
            this.uiUsers = uiUsers;
            this.status = status;
        }

        public int getTotalRows() {
            return uiUsers.getUsers().size();
        }

        public Object getRowObject(int row) {
            return uiUsers.getUsers().get(row);
        }

        public Widget getDataWidget(Object rowObject, int col) {
            UiUser uiUser = (UiUser) rowObject;
            switch (col) {
                case 0:
                    return new Label(uiUser.getUsername());
                case 1:
                    return new Label(uiUser.getFirstName());
                case 2:
                    return new Label(uiUser.getLastName());
                default:
                    return null;
            }
        }

        public Widget getControlWidget(Object rowObject, int col, int index) {
            final UiUser uiUser = (UiUser) rowObject;
            return new Button("Edit", new ClickListener() {
                        public void onClick(Widget widget) {
                            new UserDetailsPopupPanel(uiUser,
                                    new UserDetailsPopupPanel.Handler() {
                                        public void save(UiUser uiUser) {
                                            status.restore();
                                        }
                                    });
                        }
                    });
        }
    }
}
