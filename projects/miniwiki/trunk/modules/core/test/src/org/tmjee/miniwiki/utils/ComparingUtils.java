package org.tmjee.miniwiki.utils;

import java.util.List;
import java.util.Comparator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ComparingUtils {

    public static <T> boolean contains(List<T> list, T t, Comparator<T> comparator) {
        for (T _t : list) {
            if (comparator.compare(_t, t) == 0) {
                return true;
            }
        }
        return false;
    }

}
