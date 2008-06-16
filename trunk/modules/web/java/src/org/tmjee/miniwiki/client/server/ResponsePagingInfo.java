package org.tmjee.miniwiki.client.server;

import org.tmjee.miniwiki.client.Constants;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 2, 2008
 * Time: 4:28:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponsePagingInfo extends PagingInfo {

    private int totalPages;

    public ResponsePagingInfo() {}

    public ResponsePagingInfo(PagingInfo pagingInfo, int totalPages) {
        super(pagingInfo.getPageNumber(), pagingInfo.getPageSize());
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean hasNextPage() {
        return (getPageNumber() < getTotalPages());
    }

    public boolean hasPreviousPage() {
        return getPageNumber() > Constants.STARTING_PAGE_NUMBER;
    }

    public PagingInfo getNextPagePagingInfo() {
        if (hasNextPage()) {
            return new PagingInfo(getPageNumber() + 1, getPageSize());        
        }
        return null;
    }

    public PagingInfo getPreviousPagePagingInfo() {
        if (hasPreviousPage()) {
            return new PagingInfo(getPageNumber() - 1, getPageSize());
        }
        return null;
    }

}
