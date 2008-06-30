package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.domain.UiGroups;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.utils.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 23, 2008
 * Time: 6:03:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTableWidget extends SimpleTableWidget {

    public GroupTableWidget() {
        init(new InternalTitleHandler(), new InternalDataHandler());
    }

    protected void doAdd(Status status) {
        new GroupDetailsPopupPanel(new UiGroup());
    }

    protected void doDelete(final Status status) {
        LoadingMessageDisplayWidget.getInstance().display("Deleting Groups ...");

        UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
        UiGroup[] uiGroups = new UiGroup[status.getSelectedRowObjects().size()];
        for (int a=0; a< status.getSelectedRowObjects().size(); a++) {
            uiGroups[a] = (UiGroup) ((List)status.getSelectedRowObjects()).get(a);
        }
        userManagementService.deleteGroups(
                uiGroups,
                new AsyncCallback() {
                        public void onFailure(Throwable caught) {
                            Logger.error(caught.toString(), caught);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object result) {
                            LoadingMessageDisplayWidget.getInstance().done();
                            status.restore();
                        }
                }
        );
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
                                (UiGroup[])uiGroups.getGroups().toArray(new UiGroup[0]));
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
                                uiGroups.getGroups().toArray(new UiGroup[0]));
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
    }


    private class InternalTitleHandler implements FlexTableExt.TitleHandler<UiGroup> {
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
                            return new Label("Group Name");
                        case 1:
                            return new Label("Description");
                        default:
                            return null;
                    }
                }
    }


    private class InternalDataHandler implements FlexTableExt.DataHandler<UiGroup> {
            public Widget getDataWidget(UiGroup rowObject, int col) {
                    UiGroup uiGroup = rowObject;
                    switch(col) {
                        case 0:
                            return new Label(uiGroup.getName());
                        case 1:
                            return new Label(uiGroup.getDescription());
                        default:
                            return null;
                    }
                }
                public Widget getControlWidget(UiGroup rowObject, int col, int index) {
                    final UiGroup uiGroup = rowObject;
                    return new Button("Edit",
                        new ClickListener() {
                            public void onClick(Widget widget) {
                                new GroupDetailsPopupPanel(uiGroup);
                            }
                        });
                }
    }
}
