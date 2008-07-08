package org.tmjee.miniwiki.client.utils;

import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 27, 2008
 * Time: 4:20:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static <T> PageableObjectListWrapper<T> toPageableObjectListWrapper(PagingInfo pagingInfo, List<T> objects) {
        List<T> result = new ArrayList<T>();
        int startingIndex = (pagingInfo.getPageSize() * (pagingInfo.getPageSize()-1));
        for (int a=startingIndex; a< objects.size(); a++) {
            result.add(objects.get(a));
        }
        int totalPages = objects.size()/pagingInfo.getPageSize();
        if ((objects.size() % pagingInfo.getPageSize()) != 0) {
            // there's remainder
            totalPages++;
        }
        return new PageableObjectListWrapper(
                new ResponsePagingInfo(pagingInfo, totalPages),
                result);
    }

    public static <T> T find(List<T> list, ProcessingLogic<T> processingLogic) {
        for (T t: list) {
            if (processingLogic.process(t)) {
                return t;
            }
        }
        return null;
    }

    public static interface ProcessingLogic<T> {
        boolean process(T t);
    }


    public static class PageableObjectListWrapper<T> {

        private ResponsePagingInfo responsePagingInfo;
        private List<T> objects;

        public PageableObjectListWrapper(ResponsePagingInfo responsePagingInfo, List<T> objects) {
            this.responsePagingInfo = responsePagingInfo;
            this.objects = objects;
        }
        
        public List<T> getList() {
            return objects;
        }

        public ResponsePagingInfo getResponse() {
            return responsePagingInfo;
        }

    }
}

