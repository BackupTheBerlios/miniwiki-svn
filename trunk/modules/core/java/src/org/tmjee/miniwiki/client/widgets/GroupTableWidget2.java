package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.domain.UiGroups;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.utils.Logger;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 23, 2008
 * Time: 6:03:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTableWidget2 extends SimpleTableWidget {

    public GroupTableWidget2() {
        super(new FlexTableExt.TitleHandler() {
            public int getTotalCols() {
                return 2;
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
                        return new Label("Group Name");
                    case 1:
                        return new Label("Description");
                    default:
                       return null;
                }
            }
        });
    }

    protected void doAdd(Status status) {
        new GroupDetailsPopupPanel(new UiGroup());
    }

    protected void doDelete(final Status status) {
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        for (Iterator i = status.getSelectedRowObjects().iterator(); i.hasNext(); ) {
            UiGroup uiGroup = (UiGroup) i.next();
            LoadingMessageDisplayWidget.getInstance().display("Deleting Group ID "+ uiGroup.getId());
            userManagementService.deleteGroup(uiGroup,
                    new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            Logger.error(caught.toString(), caught);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object result) {
                            LoadingMessageDisplayWidget.getInstance().done();
                            status.restore();
                        }
                    });
        }
    }

    protected void doListAll(final Status status) {
        LoadingMessageDisplayWidget.getInstance().display("Listing all groups ...");
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        userManagementService.getAllGroups(
                status.getPagingInfo(),
                new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Logger.error(throwable.toString(), throwable);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object o) {
                        UiGroups uiGroups = (UiGroups) o;
                        update(uiGroups.getResponsePagingInfo(),
                                new GroupDataHandler(uiGroups, status));
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }

    protected void doSearch(final Status status) {
        LoadingMessageDisplayWidget.getInstance().display("Searching groups ...");
        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        userManagementService.searchForGroup(
                status.getSearchText(),
                status.getPagingInfo(),
                false,
                new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Logger.error(throwable.toString(), throwable);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object o) {
                        UiGroups uiGroups = (UiGroups)o;
                        update(uiGroups.getResponsePagingInfo(),
                                new GroupDataHandler(uiGroups, status));
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }


    private class GroupDataHandler implements FlexTableExt.DataHandler {

        private UiGroups uiGroups;
        private Status status;

        public GroupDataHandler(UiGroups uiGroups, Status status) {
            this.uiGroups = uiGroups;
            this.status = status;
        }

        public int getTotalRows() {
            return uiGroups.getGroups().size();
        }

        public Object getRowObject(int row) {
            return uiGroups.getGroups().get(row);
        }

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
            final UiGroup uiGroup = (UiGroup) rowObject;
            return new Button("Edit", new ClickListener() {
                public void onClick(Widget widget) {
                    new GroupDetailsPopupPanel(uiGroup);
                }
            });
        }
    }
}
