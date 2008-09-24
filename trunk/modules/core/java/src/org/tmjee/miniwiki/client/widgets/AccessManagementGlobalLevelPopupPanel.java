package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.domain.*;
import org.tmjee.miniwiki.client.server.UiAccessManagementServiceAsync;
import org.tmjee.miniwiki.client.utils.Logger;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.core.service.AccessManagementService;
import org.tmjee.miniwiki.core.service.PriviledgeGlobalLevel;
import org.tmjee.miniwiki.core.service.PriviledgeValueState;
import org.tmjee.miniwiki.core.domain.GlobalPriviledge;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessManagementGlobalLevelPopupPanel extends AutoRegisteredDialogBox {

    private VerticalPanel mainPanel;

    private DecoratedTabPanel tabPanel;

    private Button save;
    private Button cancel;
    private HorizontalPanel buttonPanel;
    


    public AccessManagementGlobalLevelPopupPanel() {
        setTitle("Access Management (Global Level)");
        setAnimationEnabled(true);

        mainPanel = new VerticalPanel();

        tabPanel = new DecoratedTabPanel();
        tabPanel.add(new AnonymousUserTab(), "Anonymous");
        tabPanel.add(new RegisteredUserTab(), "Registered");
        tabPanel.add(new GroupUserTab(), "Group");
        tabPanel.addTabListener(new InternalTabListener());
        tabPanel.selectTab(0);
        mainPanel.add(tabPanel);

        save = new Button("Save", new ClickListener() {
            public void onClick(Widget widget) {
                // TODO:
            }
        });
        cancel = new Button("Cancel", new ClickListener() {

            public void onClick(Widget widget) {
                AccessManagementGlobalLevelPopupPanel.this.hide();
            }
        });
        buttonPanel = new HorizontalPanel();
        buttonPanel.add(save);
        buttonPanel.add(cancel);
        mainPanel.add(buttonPanel);


        setWidget(mainPanel);
        center();
    }


    private Map<String, UiGlobalPriviledge> globalPriviledges = null;
    private Map<Long, String> groupMap = null;

    class InternalTabListener implements TabListener {

        public boolean onBeforeTabSelected(SourcesTabEvents sourcesTabEvents, int tabIndex) {
            return true;
        }

        public void onTabSelected(final SourcesTabEvents sourcesTabEvents, final int tabIndex) {
            if (tabIndex == 0 || tabIndex == 1) { // anonymous OR registered
                if (globalPriviledges == null) {
                    LoadingMessageDisplayWidget.getInstance().display("Loading Global Access Priviledges ...");
                    UiAccessManagementServiceAsync s = Service.getAccessManagementService();
                    s.getGlobalPriviledges(new AsyncCallback() {
                        public void onFailure(Throwable throwable) {
                            Logger.error(throwable.getMessage(), throwable);
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                        public void onSuccess(Object o) {
                            UiGlobalPriviledges prib = (UiGlobalPriviledges) o;
                            globalPriviledges = new HashMap<String, UiGlobalPriviledge>();
                            for (UiGlobalPriviledge p : prib.getPriviledges()) {
                                globalPriviledges.put(p.getName(), p);
                            }
                            ((_Tab)((TabPanel)sourcesTabEvents).getWidget(tabIndex)).refresh();
                            LoadingMessageDisplayWidget.getInstance().done();
                        }
                    });
                }
            }
            else if (tabIndex == 2) { // group
                LoadingMessageDisplayWidget.getInstance().display("Loading Available groups ...");
                UiAccessManagementServiceAsync s = Service.getAccessManagementService();
                s.getAllGroupNames(new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Logger.error(throwable.getMessage(), throwable);
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                    public void onSuccess(Object o) {
                        UiGroupNames groupNames = (UiGroupNames) o;
                        groupMap = new HashMap<Long, String>(groupNames.getGroupNames());
                        ((_Tab)((TabPanel)sourcesTabEvents).getWidget(tabIndex)).refresh();
                        LoadingMessageDisplayWidget.getInstance().done();
                    }
                });
            }
        }
    }

    interface _Tab {
        void refresh();
    }
    abstract class Tab extends VerticalPanel implements _Tab {
        private Grid grid;

        Tab() {
            grid = new Grid(6, 1);
            grid.setWidget(0, 0, createAdministrationCheckBox());
            grid.setWidget(1, 0, createRegistrationCheckBox());
            grid.setWidget(2, 0, createCreateWikiCheckBox());
            grid.setWidget(3, 0, createViewWikiCheckBox());
            grid.setWidget(4, 0, createEditWikiCheckBox());
            grid.setWidget(5, 0, createDeleteWikiCheckBox());
            grid.setWidget(2, 0, createCreateSpaceCheckBox());
            grid.setWidget(3, 0, createDeleteSpaceCheckBox());
            grid.setWidget(4, 0, createViewSpaceCheckBox());
            grid.setWidget(5, 0, createEditSpaceCheckBox());
            add(grid);
        }


        protected abstract CheckBox createAdministrationCheckBox();
        protected abstract CheckBox createRegistrationCheckBox();
        protected abstract CheckBox createCreateWikiCheckBox();
        protected abstract CheckBox createViewWikiCheckBox();
        protected abstract CheckBox createEditWikiCheckBox();
        protected abstract CheckBox createDeleteWikiCheckBox();
        protected abstract CheckBox createCreateSpaceCheckBox();
        protected abstract CheckBox createDeleteSpaceCheckBox();
        protected abstract CheckBox createViewSpaceCheckBox();
        protected abstract CheckBox createEditSpaceCheckBox();
    }


    private void updateCheckBox(CheckBox checkBox, UiGlobalPriviledge priv) {
            if (priv != null) {
                checkBox.setChecked(priv.getValueAsBoolean());
            }
            checkBox.setChecked(false);
    }

    private void updateGlobalPrivilage(UiGlobalPriviledge priv, CheckBox checkBox) {
        priv.setValueAsBoolean(checkBox.isChecked());
    }


    class AnonymousUserTab extends Tab {

        private CheckBox administration;
        private CheckBox registration;
        private CheckBox createWiki;
        private CheckBox viewWiki;
        private CheckBox editWiki;
        private CheckBox deleteWiki;
        private CheckBox createSpace;
        private CheckBox editSpace;
        private CheckBox viewSpace;
        private CheckBox deleteSpace;


        public void refresh() {
            UiGlobalPriviledge administrationPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_ADMINISTRATION_ANONYMOUS.name());
            updateCheckBox(administration, administrationPriv);

            UiGlobalPriviledge registrationPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_REGISTRATION_ANONYMOUS.name());
            updateCheckBox(registration, registrationPriv);

            UiGlobalPriviledge createWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_WIKI_ANONYMOUS.name());
            updateCheckBox(createWiki, createWikiPriv);

            UiGlobalPriviledge viewWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_WIKI_ANONYMOUS.name());
            updateCheckBox(viewWiki, viewWikiPriv);

            UiGlobalPriviledge editWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_WIKI_ANONYMOUS.name());
            updateCheckBox(editWiki, editWikiPriv);

            UiGlobalPriviledge deleteWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_WIKI_ANONYMOUS.name());
            updateCheckBox(deleteWiki, deleteWikiPriv);

            UiGlobalPriviledge createSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_SPACE_ANONYMOUS.name());
            updateCheckBox(createSpace, createSpacePriv);

            UiGlobalPriviledge editSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_SPACE_ANONYMOUS.name());
            updateCheckBox(editSpace, editSpacePriv);

            UiGlobalPriviledge viewSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_SPACE_ANONYMOUS.name());
            updateCheckBox(viewSpace, viewSpacePriv);

            UiGlobalPriviledge deleteSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_SPACE_ANONYMOUS.name());
            updateCheckBox(deleteSpace, deleteSpacePriv);
        }

        protected CheckBox createAdministrationCheckBox() {
           administration = new CheckBox("Administration");
            administration.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_ADMINISTRATION_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return administration;
        }

        protected CheckBox createRegistrationCheckBox() {
            registration = new CheckBox("Registration");
            registration.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_REGISTRATION_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return registration;
        }

        protected CheckBox createCreateWikiCheckBox() {
            createWiki = new CheckBox("Create Wiki");
            createWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_WIKI_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return createWiki;
        }

        protected CheckBox createViewWikiCheckBox() {
            viewWiki = new CheckBox("View Wiki");
            viewWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_WIKI_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return viewWiki;
        }

        protected CheckBox createEditWikiCheckBox() {
            editWiki = new CheckBox("Edit Wiki");
            editWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_WIKI_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return editWiki;
        }

        protected CheckBox createDeleteWikiCheckBox() {
            deleteWiki = new CheckBox("Delete Wiki");
            deleteWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_WIKI_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return deleteWiki;
        }

        protected CheckBox createCreateSpaceCheckBox() {
            createSpace = new CheckBox("Create Space");
            createSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_SPACE_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return createSpace;
        }

        protected CheckBox createDeleteSpaceCheckBox() {
            deleteSpace = new CheckBox("Delete Space");
            deleteSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_SPACE_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return deleteSpace;
        }

        protected CheckBox createViewSpaceCheckBox() {
            viewSpace = new CheckBox("View Space");
            viewSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_SPACE_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return viewSpace;
        }

        protected CheckBox createEditSpaceCheckBox() {
            editSpace = new CheckBox("Edit Space");
            editSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_SPACE_ANONYMOUS.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return editSpace;
        }
    }

    class RegisteredUserTab extends Tab {

        private CheckBox administration;
        private CheckBox registration;
        private CheckBox createWiki;
        private CheckBox viewWiki;
        private CheckBox editWiki;
        private CheckBox deleteWiki;
        private CheckBox createSpace;
        private CheckBox editSpace;
        private CheckBox viewSpace;
        private CheckBox deleteSpace;

        public void refresh() {
            UiGlobalPriviledge administrationPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_ADMINISTRATION_REGISTERED.name());
            updateCheckBox(administration, administrationPriv);

            UiGlobalPriviledge registrationPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_REGISTRATION_REGISTERED.name());
            updateCheckBox(registration, registrationPriv);

            UiGlobalPriviledge createWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_WIKI_REGISTERED.name());
            updateCheckBox(createWiki, createWikiPriv);

            UiGlobalPriviledge viewWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_WIKI_REGISTERED.name());
            updateCheckBox(viewWiki, viewWikiPriv);

            UiGlobalPriviledge editWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_WIKI_REGISTERED.name());
            updateCheckBox(editWiki, editWikiPriv);
            
            UiGlobalPriviledge deleteWikiPriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_WIKI_REGISTERED.name());
            updateCheckBox(deleteWiki, deleteWikiPriv);

            UiGlobalPriviledge createSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_SPACE_REGISTERED.name());
            updateCheckBox(createSpace, createSpacePriv);

            UiGlobalPriviledge editSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_SPACE_REGISTERED.name());
            updateCheckBox(editSpace, editSpacePriv);

            UiGlobalPriviledge viewSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_SPACE_REGISTERED.name());
            updateCheckBox(viewSpace, viewSpacePriv);

            UiGlobalPriviledge deleteSpacePriv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_SPACE_REGISTERED.name());
            updateCheckBox(deleteSpace, deleteSpacePriv);
        }


        protected CheckBox createAdministrationCheckBox() {
            administration = new CheckBox("Administration");
            administration.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_ADMINISTRATION_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return administration;
        }

        protected CheckBox createRegistrationCheckBox() {
            registration = new CheckBox("Registration");
            registration.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_REGISTRATION_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return registration;
        }

        protected CheckBox createCreateSpaceCheckBox() {
            createSpace = new CheckBox("Create Space");
            createSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_SPACE_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return createSpace;
        }

        protected CheckBox createDeleteSpaceCheckBox() {
            deleteSpace = new CheckBox("Delete Space");
            deleteSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_SPACE_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return deleteSpace;
        }

        protected CheckBox createViewSpaceCheckBox() {
            viewSpace = new CheckBox("View Space");
            viewSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_SPACE_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return viewSpace;
        }

        protected CheckBox createEditSpaceCheckBox() {
            editSpace = new CheckBox("Edit Space");
            editSpace.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_SPACE_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return editSpace;
        }

        protected CheckBox createCreateWikiCheckBox() {
            createWiki = new CheckBox("Create Wiki");
            createWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_CREATE_WIKI_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return createWiki;
        }

        protected CheckBox createViewWikiCheckBox() {
            viewWiki = new CheckBox("View Wiki");
            viewWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_VIEW_WIKI_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return viewWiki;
        }

        protected CheckBox createEditWikiCheckBox() {
            editWiki = new CheckBox("Edit Wiki");
            editWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_EDIT_WIKI_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return editWiki;
        }

        protected CheckBox createDeleteWikiCheckBox() {
            deleteWiki = new CheckBox("Delete Wiki");
            deleteWiki.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    UiGlobalPriviledge priv = globalPriviledges.get(PriviledgeGlobalLevel.GLOBAL_LEVEL_DELETE_WIKI_REGISTERED.name());
                    updateGlobalPrivilage(priv, (CheckBox)widget);
                }
            });
            return deleteWiki;
        }
    }

    class GroupUserTab extends VerticalPanel implements _Tab {

        ListBox groups;

        MessageDisplayWidget messageDisplayWidget;
        Label groupLabel;
        Grid grid;

        private CheckBox administration;
        private CheckBox registration;
        private CheckBox createWiki;
        private CheckBox viewWiki;
        private CheckBox editWiki;
        private CheckBox deleteWiki;
        private CheckBox createSpace;
        private CheckBox editSpace;
        private CheckBox viewSpace;
        private CheckBox deleteSpace;

        GroupUserTab() {
            groups = new ListBox(false);
            groups.addChangeListener(new ChangeListener() {
                public void onChange(Widget widget) {

                    LoadingMessageDisplayWidget.getInstance().display("Loading Group Info ...");
                    int selectedIndex = ((ListBox)widget).getSelectedIndex();
                    Long groupId = Long.valueOf(((ListBox)widget).getValue(selectedIndex));

                    Service.getUserManagementService().getGroupById(groupId,
                            new AsyncCallback() {
                                public void onFailure(Throwable throwable) {
                                    Logger.error(throwable.getMessage(), throwable);
                                    LoadingMessageDisplayWidget.getInstance().done();
                                }
                                public void onSuccess(Object o) {
                                    UiGroup group = (UiGroup) o;
                                    updateGroupPriviledgeInfo(group);
                                    LoadingMessageDisplayWidget.getInstance().done();
                                }
                            });
                }
            });


            messageDisplayWidget = new MessageDisplayWidget();


            administration = new CheckBox;
        private CheckBox registration;
        private CheckBox createWiki;
        private CheckBox viewWiki;
        private CheckBox editWiki;
        private CheckBox deleteWiki;
        private CheckBox createSpace;
        private CheckBox editSpace;
        private CheckBox viewSpace;
        private CheckBox deleteSpace;

            groupLabel = new Label("No Groups selected");
            grid = new Grid(6,1);
            grid.setWidget(0, 0, )

            add(messageDisplayWidget);
            add(groups);
            add(groupLabel);
            add(grid);
        }

        public void refresh() {
            groups.clear();
            for (Map.Entry<Long, String> e: groupMap.entrySet()) {
                groups.addItem(e.getValue(), String.valueOf(e.getKey()));
            }
        }

        protected void updateGroupPriviledgeInfo(UiGroup group) {
            if (group != null) {
                

                return;
            }
            messageDisplayWidget.addInfoMessage("No such group found");
        }
    }
}
