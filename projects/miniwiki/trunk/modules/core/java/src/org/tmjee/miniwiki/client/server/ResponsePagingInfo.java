package org.tmjee.miniwiki.client.server;

import org.tmjee.miniwiki.client.Constants;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ResponsePagingInfo extends PagingInfo {

    private long totalPages;

    public ResponsePagingInfo() {}

    public ResponsePagingInfo(PagingInfo pagingInfo, int totalEntries) {
        super(pagingInfo.getPageNumber(), pagingInfo.getPageSize());
        totalPages = _calculateTotalPages(pagingInfo, totalEntries);
    }

    public ResponsePagingInfo(PagingInfo pagingInfo, long totalEntries) {
        super(pagingInfo.getPageNumber(), pagingInfo.getPageSize());
        totalPages = _calculateTotalPages(pagingInfo, totalEntries);
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


    private long _calculateTotalPages(PagingInfo pagingInfo, long totalEntries) {
        long totalPages = totalEntries / pagingInfo.getPageSize();
        if ((totalEntries % pagingInfo.getPageSize()) != 0) {
            totalPages = totalPages + 1;
        }
        return totalPages;
    }

}
