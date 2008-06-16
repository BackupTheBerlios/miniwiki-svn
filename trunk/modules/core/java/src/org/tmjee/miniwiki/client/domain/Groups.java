package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;
import java.util.ArrayList;

import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 2:06:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Groups implements IsSerializable {

    private transient List<Group> groups;
    private ResponsePagingInfo responsePagingInfo;

    public Groups() {
        groups = new ArrayList<Group>();
    }

    public Groups(ResponsePagingInfo responsePagingInfo) {
        super();
        this.responsePagingInfo = responsePagingInfo;
    }

    public ResponsePagingInfo getResponsePagingInfo() {
        return responsePagingInfo;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
