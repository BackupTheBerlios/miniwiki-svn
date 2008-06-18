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
public class UiGroups implements IsSerializable {

    private transient List<UiGroup> uiGroups;
    private ResponsePagingInfo responsePagingInfo;

    public UiGroups() {
        uiGroups = new ArrayList<UiGroup>();
    }

    public UiGroups(List<UiGroup> groups, ResponsePagingInfo responsePagingInfo) {
        this.responsePagingInfo = responsePagingInfo;
        this.uiGroups = uiGroups;
    }

    public ResponsePagingInfo getResponsePagingInfo() {
        return responsePagingInfo;
    }

    public List<UiGroup> getGroups() {
        return uiGroups;
    }
}
