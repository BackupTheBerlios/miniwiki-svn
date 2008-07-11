package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;
import java.util.ArrayList;

import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroups implements IsSerializable {

    private List<UiGroup> uiGroups;
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
