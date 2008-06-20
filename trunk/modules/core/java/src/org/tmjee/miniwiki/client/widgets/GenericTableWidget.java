package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 20, 2008
 * Time: 3:55:29 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericTableWidget extends Composite {

    private VerticalPanel mainPanel;

    private HorizontalPanel searchPanel;

    private Grid prevNextButtonsGrid;
    private Button prevPageButton;
    private Button nextPageButton;

    private FlexTableExt table;


    public GenericTableWidget(FlexTableExt.Handler flexTableExtHandler) {

        searchPanel = new HorizontalPanel();

        table = new FlexTableExt(flexTableExtHandler);

        prevPageButton = new Button("Previous", new ClickListener() {
            public void onClick(Widget widget) {
                refresh();
            }
        });
        prevPageButton.setEnabled(false);
        nextPageButton = new Button("Next", new ClickListener() {
            public void onClick(Widget widget) {
            }
        });
        nextPageButton.setEnabled(false);
        prevNextButtonsGrid = new Grid(1, 2);
        prevNextButtonsGrid.setWidth("100%");
        prevNextButtonsGrid.setWidget(0, 0, prevPageButton);
        prevNextButtonsGrid.setWidget(0, 1, nextPageButton);
        prevNextButtonsGrid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        prevNextButtonsGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);

        mainPanel = new VerticalPanel();
        mainPanel.add(searchPanel);
        mainPanel.add(prevNextButtonsGrid);
        mainPanel.add(table);

        initWidget(mainPanel);

        addWidgetsToSearchPanel(searchPanel);
    }

    protected abstract void addWidgetsToSearchPanel(HorizontalPanel searchPanel);

    protected abstract ResponsePagingInfo refresh();

    protected void update(ResponsePagingInfo responsePagingInfo) {
        table.refresh();    
    }

}
