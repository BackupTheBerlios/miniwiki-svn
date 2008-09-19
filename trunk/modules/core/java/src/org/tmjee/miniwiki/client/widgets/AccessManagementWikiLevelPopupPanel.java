package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessManagementWikiLevelPopupPanel extends AutoRegisteredDialogBox {

    private VerticalPanel mainPanel;

    private TabPanel tabPanel;

    private Button save;
    private Button cancel;
    private HorizontalPanel buttonPanel;


    public AccessManagementWikiLevelPopupPanel() {
        setText("Access Management (Global Level)");
        setAnimationEnabled(true);

        mainPanel = new VerticalPanel();
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");

        tabPanel = new TabPanel();
        tabPanel.add(new GlobalWikiLevelAccessManagementTab(), "Global Wiki Level");
        tabPanel.add(new GlobalSpaceLevelAccessManagementTab(), "Global Space Level");
        tabPanel.add(new GlobalPageLevelAccessManagementTab(), "Global Page Level");
        tabPanel.setWidth("100%");
        tabPanel.setHeight("100%");
        mainPanel.add(tabPanel);


        save = new Button("Save", new ClickListener() {
            public void onClick(Widget widget) {
                // TODO:
            }
        });
        cancel = new Button("Cancel", new ClickListener() {
            public void onClick(Widget widget) {
                AccessManagementWikiLevelPopupPanel.this.hide();        
            }
        });
        buttonPanel = new HorizontalPanel();
        buttonPanel.add(save);
        buttonPanel.add(cancel);
        buttonPanel.setWidth("100%");
        buttonPanel.setHeight("100%");
        mainPanel.add(buttonPanel);

        setWidget(mainPanel);
        center();
    }


    class GlobalWikiLevelAccessManagementTab extends VerticalPanel {
        GlobalWikiLevelAccessManagementTab() {
            
        }
    }

    class GlobalSpaceLevelAccessManagementTab extends VerticalPanel {
         GlobalSpaceLevelAccessManagementTab() {

         }
    }

    class GlobalPageLevelAccessManagementTab extends VerticalPanel {
        GlobalPageLevelAccessManagementTab() {
            
        }
    }
}
