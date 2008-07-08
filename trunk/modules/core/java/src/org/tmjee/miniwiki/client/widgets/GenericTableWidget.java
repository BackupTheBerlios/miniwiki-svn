package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.Constants;

import java.util.List;

/**
 * Add :-
 *  - Next button
 *  - Prev button
 *
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class GenericTableWidget<T> extends Composite implements SourcesMessageEvents {

    private VerticalPanel mainPanel;

    private HorizontalPanel searchPanel;

    private Grid prevNextButtonsGrid;
    private Button prevPageButton;
    private Button nextPageButton;

    protected PagingInfo currentPagingInfo;
    protected PagingInfo nextPagingInfo;
    protected PagingInfo prevPagingInfo;
    protected FlexTableExt<T> table;
    protected SourcesEventsSupport sourcesEventsSupport;



    public GenericTableWidget() {
        currentPagingInfo = new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
        nextPagingInfo = new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
        prevPagingInfo = new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);

        sourcesEventsSupport = new SourcesEventsSupport();

        searchPanel = new HorizontalPanel();

        table = new FlexTableExt();

        prevPageButton = new Button("Previous", new ClickListener() {
            public void onClick(Widget widget) {
                currentPagingInfo = prevPagingInfo;
                refresh(prevPagingInfo);
            }
        });
        prevPageButton.setEnabled(false);
        nextPageButton = new Button("Next", new ClickListener() {
            public void onClick(Widget widget) {
                currentPagingInfo = nextPagingInfo;
                refresh(nextPagingInfo);
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

    public void init(FlexTableExt.TitleHandler flexTableExtTitleHandler,
                              FlexTableExt.DataHandler flexTableExtDataHandler) {
        table.init(flexTableExtTitleHandler, flexTableExtDataHandler);
        refresh(currentPagingInfo);
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.removeListener(listener);
    }

    protected abstract void addWidgetsToSearchPanel(HorizontalPanel searchPanel);

    protected abstract void refresh(PagingInfo pagingInfo);

    protected void refresh() {
        refresh(currentPagingInfo);
    }

    protected void update(ResponsePagingInfo responsePagingInfo, List<T> rowObjects) {
        nextPagingInfo = responsePagingInfo.getNextPagePagingInfo();
        prevPagingInfo = responsePagingInfo.getPreviousPagePagingInfo();
        nextPageButton.setEnabled(responsePagingInfo.hasNextPage());
        prevPageButton.setEnabled(responsePagingInfo.hasPreviousPage());
        table.refresh(rowObjects);
    }


    public List<T> getSelectedRowObjects() {
        return table.getSelectedRowObjects();
    }

    
}
