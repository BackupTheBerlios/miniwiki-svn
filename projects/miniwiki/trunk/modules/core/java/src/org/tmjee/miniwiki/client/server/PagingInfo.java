package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.IsSerializable;
import org.tmjee.miniwiki.client.Constants;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class PagingInfo implements IsSerializable {

    private int pageNumber;
    private int pageSize;

    public PagingInfo() {
        pageNumber = Constants.STARTING_PAGE_NUMBER;
        pageSize = Constants.DEFAULT_PAGE_SIZE;
    }

    public PagingInfo(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
    

}
