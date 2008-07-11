package org.tmjee.miniwiki.client.server;

import org.tmjee.miniwiki.client.Constants;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ResponsePagingInfo extends PagingInfo {

    private long totalPages;

    public ResponsePagingInfo() {}

    public ResponsePagingInfo(PagingInfo pagingInfo, int totalPages) {
        super(pagingInfo.getPageNumber(), pagingInfo.getPageSize());
        this.totalPages = totalPages;
    }

    public ResponsePagingInfo(PagingInfo pagingInfo, long totalPages) {
        super(pagingInfo.getPageNumber(), pagingInfo.getPageSize());
        this.totalPages = totalPages;
    }

    public long getTotalPages() {
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
