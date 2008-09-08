package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PrivilegesSetupPopupPanel extends DialogBox {

    private VerticalPanel mainPanel;
    private HorizontalPanel buttonsPanel;
    private TabPanel tabPanel;
    private WikiPriviledgesTab wikiPriviledgesTab;
    private SpacePriviledgesTab spacePriviledgesTab;
    private PagePriviledgesTab pagePriviledgesTab;

    private Button saveButton;
    private Button cancelButton;

    public PrivilegesSetupPopupPanel() {
        setTitle("Priviledges Setup");
        setAnimationEnabled(true);

        mainPanel = new VerticalPanel();


        wikiPriviledgesTab = new WikiPriviledgesTab();
        spacePriviledgesTab = new SpacePriviledgesTab();
        pagePriviledgesTab = new PagePriviledgesTab();

        tabPanel = new TabPanel();
        tabPanel.add(wikiPriviledgesTab, "Wiki Priviledges");
        tabPanel.add(spacePriviledgesTab, "Space Priviledges");
        tabPanel.add(pagePriviledgesTab, "Page Priviledges");

        saveButton = new Button("Save",
                new ClickListener() {
                    public void onClick(Widget widget) {
                    }
                });
        cancelButton = new Button("Cancel",
                new ClickListener() {
                    public void onClick(Widget widget) {
                    }
                });
        buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        mainPanel.add(buttonsPanel);
        setWidget(mainPanel);
        center();
    }


    public class WikiPriviledgesTab extends VerticalPanel {
        //private Grid 
    }

    public class SpacePriviledgesTab extends VerticalPanel {

    }

    public class PagePriviledgesTab extends VerticalPanel {

    }

}
