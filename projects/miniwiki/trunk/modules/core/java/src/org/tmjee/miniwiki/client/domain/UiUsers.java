package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiUsers implements IsSerializable {

    private ResponsePagingInfo responsePagingInfo;
    private List<UiUser> uiUsers;


    public UiUsers() {
        uiUsers = new ArrayList<UiUser>();
    }
    

    public UiUsers(List<UiUser> uiUsers, ResponsePagingInfo responsePagingInfo) {
        this.responsePagingInfo = responsePagingInfo;
        this.uiUsers = uiUsers;
    }

    public ResponsePagingInfo getResponsePagingInfo() {
        return responsePagingInfo;
    }

    public List<UiUser> getUsers() {
        return uiUsers;
    }
}
