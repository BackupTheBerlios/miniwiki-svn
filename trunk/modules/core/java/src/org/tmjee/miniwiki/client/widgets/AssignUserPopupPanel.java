package org.tmjee.miniwiki.client.widgets;

import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiUsers;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.utils.Utils;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.core.service.UserManagementService;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AssignUserPopupPanel extends DialogBox  {

    public static interface Handler {
        void assign(UiUser user, UiGroup group);
        void unassign(UiUser user, UiGroup group);
    }

    private UiGroup group;
    private Handler handler;

    private VerticalPanel mainPanel;
    private GenericTableWidget table;
    private HorizontalPanel buttonsPanel;

    public AssignUserPopupPanel(UiGroup group, Handler handler) {
        
        this.group = group;
        this.handler = handler;

        setText("User Assignment");
        setAnimationEnabled(true);

        buttonsPanel = new HorizontalPanel();

        table = new GenericTableWidget() {
            protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {
                // nothing to add        
            }
            protected void refresh(PagingInfo pagingInfo) {
                UiUserManagementServiceAsync service = Service.getUserManagementService();
                service.getAllUsers(
                        pagingInfo,
                        new AsyncCallback() {
                            public void onFailure(Throwable throwable) {
                                Logger.error(throwable.toString(), throwable);
                            }
                            public void onSuccess(Object o) {
                                List<UiUser> users = ((UiUsers)o).getUsers();
                                table.refresh(users);
                            }
                        });
            }
        };
        table.init(
                new FlexTableExt.TitleHandler<UiUser>() {
                    public int getTotalCols() {
                        return 4;
                    }
                    public boolean hasCheckableCol() {
                        return true;
                    }
                    public int numOfControlWidget() {
                        return 1;  
                    }
                    public Widget getTitleWidget(int col) {
                        switch (col) {
                            case 0:
                                return new Label("Username");
                            case 1:
                                return new Label("First Name");
                            case 2:
                                return new Label("Last Name");
                            case 3:
                                return new Label("Description");
                            default:
                                return null;
                        }
                    }
                },
                new FlexTableExt.DataHandler<UiUser>() {
                    public Widget getDataWidget(UiUser rowObject, int col) {
                        switch(col) {
                            case 0:
                                return new Label(rowObject.getUsername());
                            case 1:
                                return new Label(rowObject.getFirstName());
                            case 2:
                                return new Label(rowObject.getLastName());
                            case 3:
                                return new Label(rowObject.getDescription());
                            default:
                                return null;
                        }
                    }
                    public Widget getControlWidget(final UiUser rowObject, int col, int index) {
                        UiUser _u = Utils.find(
                                AssignUserPopupPanel.this.group.getUsers(),
                                new Utils.ProcessingLogic<UiUser>() {
                                    public boolean process(UiUser o) {
                                        if (o.getId() == rowObject.getId()) {
                                            return true;
                                        }
                                        return false;
                                    }
                                });
                        if (_u != null) { // this user belongs to the group this popup is representing
                            return new Button("Unassign",
                                    new ClickListener() {
                                        public void onClick(Widget widget) {
                                            AssignUserPopupPanel.this.handler.unassign(rowObject, AssignUserPopupPanel.this.group);
                                            table.refresh();
                                        }
                                    });
                        }
                        else {  // this user doesn't belongs to the group this popup is representing
                            return new Button("Assign",
                                    new ClickListener() {
                                        public void onClick(Widget widget) {
                                            AssignUserPopupPanel.this.handler.assign(rowObject, AssignUserPopupPanel.this.group);
                                            table.refresh();
                                        }
                                    });
                        }
                    }
                }
        );

        buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                AssignUserPopupPanel.this.hide();    
            }
        }));


        mainPanel = new VerticalPanel();
        mainPanel.add(table);
        mainPanel.add(buttonsPanel);

        setWidget(mainPanel);

        center();
        
    }

    
    

}
