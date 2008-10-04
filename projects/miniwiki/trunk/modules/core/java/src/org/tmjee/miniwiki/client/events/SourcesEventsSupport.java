package org.tmjee.miniwiki.client.events;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class SourcesEventsSupport {

    private List listeners = new ArrayList();

    public void addListener(Object listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(Object listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void iterateThroughListener(Handler handler) {
        for (Iterator i = listeners.iterator(); i.hasNext(); ) {
            Object listener = i.next();
            handler.handle(listener);
        }
    }

    public static interface Handler {
        void handle(Object listener);
    }

}
