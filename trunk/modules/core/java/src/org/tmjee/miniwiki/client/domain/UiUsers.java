package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 2, 2008
 * Time: 4:46:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiUsers implements IsSerializable {

    private ResponsePagingInfo responsePagingInfo;
    private transient List<UiUser> uiUsers;


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
