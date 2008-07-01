package org.tmjee.miniwiki.client.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 27, 2008
 * Time: 4:20:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static <T> T[] toArray(T object) {
        ArrayList<T> t = new ArrayList<T>();
        t.add(object);
        return (T[]) t.toArray();
    }


    public static <T> T[] toArray(List<T> list) {
        return (T[]) list.toArray();
    }
}

