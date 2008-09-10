package org.tmjee.miniwiki.client.domain;

import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWikis {

    private ResponsePagingInfo responsePagingInfo;
    private List<UiWiki> wikis = new ArrayList<UiWiki>();

    public UiWikis(){}

    public UiWikis(List<UiWiki> wikis, ResponsePagingInfo responsePagingInfo) {
        this.responsePagingInfo = responsePagingInfo;
        this.wikis = wikis;
    }

    public ResponsePagingInfo getResponsePagingInfo() {
        return responsePagingInfo;
    }

    public List<UiWiki> getWikis() {
        return wikis;
    }
}
