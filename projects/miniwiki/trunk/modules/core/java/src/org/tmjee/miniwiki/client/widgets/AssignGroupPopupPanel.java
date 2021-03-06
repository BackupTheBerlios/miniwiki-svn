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
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.utils.Utils;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AssignGroupPopupPanel extends AutoRegisteredDialogBox {

    private VerticalPanel mainPanel;

    private UiUser uiUser;
    private GenericTableWidget table;
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

        table = new GenericTableWidget<UiGroup>() {
            protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {
                // nothing
            }
            protected void refresh(PagingInfo pagingInfo) {
                UiUserManagementServiceAsync userManagement = Service.getUserManagementService();
                userManagement.getAllGroups(pagingInfo,
                        new AsyncCallback() {
                            public void onFailure(Throwable throwable) {
                                Logger.error(throwable.toString(), throwable);
                            }
                            public void onSuccess(Object o) {
                                UiGroups uiGroups = (UiGroups) o;
                                table.refresh(uiGroups.getGroups());
                            }
                        });
            }
        };
        table.init(
                new FlexTableExt.TitleHandler<UiGroup>() {
                    public int getTotalCols() {
                        return 2;
                    }
                    public boolean hasCheckableCol() {
                        return false;
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
                },
                new FlexTableExt.DataHandler<UiGroup>() {
                    public Widget getDataWidget(UiGroup rowObject, int col) {
                        switch(col) {
                            case 0:
                                return new Label(rowObject.getName());
                            case 1:
                                return new Label(rowObject.getDescription());
                            default:
                                return null;
                        }
                    }
                    public Widget getControlWidget(final UiGroup rowObject, int col, int index) {
                        UiGroup g = Utils.find(
                                AssignGroupPopupPanel.this.uiUser.getGroups(),
                                new Utils.ProcessingLogic<UiGroup>() {
                                    public boolean process(UiGroup uiGroup) {
                                        if (uiGroup.getId() == rowObject.getId()) {
                                            return true;
                                        }
                                        return false;
                                    }
                                });
                        if (g != null) { // the rowObject is in uiUser.getGroups() list
                                         // Group is already part of this user's group
                            return new Button(
                                    "Unassign",
                                    new ClickListener() {
                                        public void onClick(Widget widget) {
                                            AssignGroupPopupPanel.this.handler.leave(
                                                    AssignGroupPopupPanel.this.uiUser,
                                                    rowObject
                                            );
                                            table.refresh();
                                        }
                                    });
                        }
                        return new Button(  // Group is not part of this user's group
                                "Assign",
                                new ClickListener() {
                                    public void onClick(Widget widget) {
                                        AssignGroupPopupPanel.this.handler.join(
                                                AssignGroupPopupPanel.this.uiUser,
                                                rowObject

                                        );
                                        table.refresh();
                                    }
                                });
                    }
                }
        );


        buttonPanel = new HorizontalPanel();
        buttonPanel.add(new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                AssignGroupPopupPanel.this.hide();
            }
        }));

        mainPanel = new VerticalPanel();
        mainPanel.add(table);
        mainPanel.add(buttonPanel);

        setWidget(mainPanel);

        center();
    }
}


