package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.Constants;

import java.util.List;


/**
 * Add :-
 *  - search button
 *  - list all button
 *  - add button
 *  - delete button
 *
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class SimpleTableWidget<T> extends GenericTableWidget<T> {

    private TextBox searchTextBox;
    private Button searchButton;
    private Button listAllButton;
    private Button addButton;
    private Button deleteButton;

    protected Status status;

    public SimpleTableWidget() {
        status = new Status(State.LIST_ALL);
    }

    public void init(FlexTableExt.TitleHandler flexTableExtTitleHandler,
                             FlexTableExt.DataHandler flexTableExtDataHandler) {
        super.init(flexTableExtTitleHandler, flexTableExtDataHandler);
    }

    protected Status getStatus() {
        return status;
    }

    protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {

        searchTextBox = new TextBox();
        searchButton = new Button("Search", new ClickListener() {
            public void onClick(Widget widget) {
                if (searchTextBox.getText().trim().length() <= 0) {
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Search field is empty"));
                        }
                    });
                    return;
                }
                currentPagingInfo = new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
                status.setState(State.SEARCH);
                status.setPagingInfo(currentPagingInfo);
                status.capture();
                refresh(status);
            }
        });
        listAllButton = new Button("List All", new ClickListener() {
            public void onClick(Widget widget) {
                currentPagingInfo = new PagingInfo(Constants.STARTING_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
                status.setState(State.LIST_ALL);
                status.capture();
                refresh(status);
            }
        });
        addButton = new Button("Add", new ClickListener() {
            public void onClick(Widget widget) {
                status.setState(State.ADD);
                status.setPagingInfo(currentPagingInfo);
                refresh(status);
            }
        });
        deleteButton = new Button("Delete", new ClickListener() {
            public void onClick(Widget widget) {
                if (table.getSelectedRowObjects().size() <= 0) {
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "No entries selected for deletion"));
                        }
                    });
                }
                status.setState(State.DELETE);
                status.setPagingInfo(currentPagingInfo);
                refresh(status);
            }
        });

        searchPanel.add(searchTextBox);
        searchPanel.add(searchButton);
        searchPanel.add(listAllButton);
        searchPanel.add(addButton);
        searchPanel.add(deleteButton);
    }

    protected void refresh(PagingInfo pagingInfo) {
        status.setPagingInfo(pagingInfo);
        status.capture();
        refresh(status);
    }

    protected void refresh(Status status) {
        switch(status.getState()) {
            case ADD:
                    doAdd(status);
                break;
            case DELETE:
                    doDelete(status);
                break;
            case LIST_ALL:
                    doListAll(status);
                break;
            case SEARCH:
                    doSearch(status);
                break;
        }
    }

    protected abstract void doAdd(Status status);
    protected abstract void doDelete(Status status);
    protected abstract void doListAll(Status status);
    protected abstract void doSearch(Status status);


    public enum State {
        SEARCH,
        LIST_ALL,
        ADD,
        DELETE
    }

    public class Status {
        private State state;
        private PagingInfo pagingInfo;

        public Status(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public PagingInfo getPagingInfo() {
            return this.pagingInfo;
        }

        public void setPagingInfo(PagingInfo pagingInfo) {
            this.pagingInfo = pagingInfo;
        }

        public List<T> getSelectedRowObjects() {
            return table.getSelectedRowObjects();
        }

        public String getSearchText() {
            return searchTextBox.getText();
        }


        private State capture_state;
        private String capture_search = "";
        private PagingInfo capture_pagingInfo = null;
        public void capture() {
            if (State.LIST_ALL.equals(state) ||
                    State.SEARCH.equals(state)) {
                capture_state = state;
                capture_search = searchTextBox.getText();
                capture_pagingInfo = pagingInfo;
            }
        }

        public void restore() {
            Status restored_state = new Status(capture_state) {
                public State getState() {
                    return capture_state;
                }
                public String getSearchText() {
                    return capture_search;
                }
                public PagingInfo getPagingInfo() {
                    return capture_pagingInfo;
                }
            };
            refresh(restored_state);
        }
    }
}
