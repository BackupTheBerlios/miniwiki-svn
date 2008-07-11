package org.tmjee.miniwiki.client.widgets;

import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.server.PagingInfo;
import com.google.gwt.user.client.ui.*;

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
                    
            }
        };
        table.init(
                new FlexTableExt.TitleHandler<UiUser>() {
                    public int getTotalCols() {
                        return 0;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                    public boolean hasCheckableCol() {
                        return false;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                    public int numOfControlWidget() {
                        return 0;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                    public Widget getTitleWidget(int col) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                },
                new FlexTableExt.DataHandler<UiUser>() {
                    public Widget getDataWidget(UiUser rowObject, int col) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                    public Widget getControlWidget(UiUser rowObject, int col, int index) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        
    }

    
    

}
