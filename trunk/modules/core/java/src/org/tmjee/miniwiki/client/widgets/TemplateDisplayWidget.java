package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.TemplateInfo;
import org.tmjee.miniwiki.client.components.Components;
import org.tmjee.miniwiki.client.components.Parameterizable;
import org.tmjee.miniwiki.client.service.Myself;
import org.tmjee.miniwiki.client.events.CredentialListener;
import org.tmjee.miniwiki.client.events.CredentialsEvent;

import java.util.Iterator;
import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateDisplayWidget extends VerticalPanel {

    private MenuItem loginLogoutMenuItem;


    /**
     * Wiki
     *   + Login/Logout
     *   + Navigation
     *   + Access Management
     * Space
     *   + Add New Space
     *   + Remove Current Space
     *   + Edit Current Space
     *   + Access Manage Current Space
     * Page
     *   + Add New Page
     *   + Remove Current Page
     *   + Edit Current Page
     *   + Access Manage Current Page
     * User
     *   + User Management
     * Group
     *   + Group Management
     * Help
     *   + Info
     *
     * @param templateInfo
     */
    public TemplateDisplayWidget(TemplateInfo templateInfo) {

        Myself.getInstance().addCredentialListener(new CredentialListener() {
            public void onCredentialsEvent(CredentialsEvent event) {
                loginLogoutMenuItem.setText(Myself.getInstance().getCredentials().isAnonymous() ? "Login" : "Logout");
            }
        });

        setWidth("100%");
        setHeight("100%");

        // 1] Info Panel
        HorizontalPanel infoPanel = new HorizontalPanel();
        infoPanel.setWidth("100%");

        /*
         * Wiki
         *   + Login / Logout
         *   + Navigation
         *   + Access Management
         */
        MenuBar mainMenuBar = new MenuBar(false);
        MenuBar wikiMenuBar = new MenuBar(true);
        MenuItem wikiMenuItem = new MenuItem("Wiki", wikiMenuBar);
        loginLogoutMenuItem = new MenuItem(
            Myself.getInstance().getCredentials().isAnonymous() ? "Login" : "Logout",
            new Command() {
                public void execute() {
                    if (Myself.getInstance().getCredentials().isAnonymous()) {
                        new LoginPopupPanel();
                    }
                    else {
                        Myself.getInstance().setCretentialsToAnonymous();
                    }
                }
        });
        wikiMenuBar.addItem(new MenuItem("Navigation", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        wikiMenuBar.addItem(new MenuItem("Access Management", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        mainMenuBar.addItem(wikiMenuItem);
        mainMenuBar.setWidth("100%");


        /*
         * Space
         *   + Add New Space
         *   + Remove Current Space
         *   + Edit Current Space
         *   + Access Manage Current Space
         */
        MenuBar spaceMenuBar = new MenuBar(true);
        MenuItem spaceMenuItem = new MenuItem("Space", spaceMenuBar);
        spaceMenuBar.addItem(new MenuItem("Add New Space", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        spaceMenuBar.addItem(new MenuItem("Remove Current Space", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        spaceMenuBar.addItem(new MenuItem("Edit Current Space", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        spaceMenuBar.addItem(new MenuItem("Access Manage Current Space", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        mainMenuBar.addItem(spaceMenuItem);



        /*
         * Page
         *   + Add New Page
         *   + Remove Current Page
         *   + Edit Current Page
         *   + Access Manage Current Page
         */
        MenuBar pageMenuBar = new MenuBar(true);
        MenuItem pageMenuItem = new MenuItem("Page", pageMenuBar);
        pageMenuBar.addItem(new MenuItem("Add New Page", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        pageMenuBar.addItem(new MenuItem("Remove Current Page", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        pageMenuBar.addItem(new MenuItem("Edit Current Page", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        pageMenuBar.addItem(new MenuItem("Access Manage Current Page", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        mainMenuBar.addItem(pageMenuItem);


        /*
         * User
         *   + User Management
         */
        MenuBar userMenuBar = new MenuBar(true);
        MenuItem userMenuItem = new MenuItem("User", userMenuBar);
        userMenuBar.addItem(new MenuItem("User Management", new Command() {
            public void execute() {
                new UserManagementPopupPanel();
            }
        }));
        mainMenuBar.addItem(userMenuItem);


        /*
         * Group
         *   + Group Management
         */
        MenuBar groupMenuBar = new MenuBar(true);
        MenuItem groupMenuItem = new MenuItem("Group", groupMenuBar);
        groupMenuBar.addItem(new MenuItem("Group Management", new Command() {
            public void execute() {
                new GroupManagementPopupPanel();
            }
        }));
        mainMenuBar.addItem(groupMenuItem);



        /*
         * Help
         *   + Information
         */
        MenuBar helpMenuBar = new MenuBar(true);
        MenuItem helpMenuItem = new MenuItem("Help", helpMenuBar);
        helpMenuBar.addItem(new MenuItem("Information", new Command() {
            public void execute() {
                // TODO:
                Window.alert("Work In Progress !!!");
            }
        }));
        mainMenuBar.addItem(helpMenuItem);

        infoPanel.add(mainMenuBar);
        add(infoPanel);



        // 2] contents
        HTMLPanel contentPanel = new HTMLPanel(templateInfo.getHtml());
        for(Iterator i = Components.getRegisteredWidgets().entrySet().iterator(); i.hasNext();) {
              Map.Entry e = (Map.Entry) i.next();
              String name = (String) e.getKey();
              Widget widget = (Widget) e.getValue();
              if (widget instanceof Parameterizable) {
                  ((Parameterizable)widget).setParameters(templateInfo.getParams());
              }
              contentPanel.add(widget, name);
        }
        contentPanel.setWidth("100%");
        contentPanel.setHeight("100%");
        add(contentPanel);


        
        
         // 3] comments
         DisclosurePanel commentPanel = new DisclosurePanel("Comments", true);
         commentPanel.setWidth("100%");
         add(commentPanel);

    }





}

