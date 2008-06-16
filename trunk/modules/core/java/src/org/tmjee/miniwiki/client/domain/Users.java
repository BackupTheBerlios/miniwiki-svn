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
public class Users implements IsSerializable {

    private ResponsePagingInfo responsePagingInfo;
    private transient List<User> users;


    public Users() {
        users = new ArrayList<User>();
    }
    

    public Users(ResponsePagingInfo responsePagingInfo) {
        super();
        this.responsePagingInfo = responsePagingInfo;
    }

    public ResponsePagingInfo getResponsePagingInfo() {
        return responsePagingInfo;
    }

    public List<User> getUsers() {
        return users;
    }
}
