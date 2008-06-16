package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 2, 2008
 * Time: 4:14:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagingInfo implements IsSerializable {

    private int pageNumber;
    private int pageSize;

    public PagingInfo() {}

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
