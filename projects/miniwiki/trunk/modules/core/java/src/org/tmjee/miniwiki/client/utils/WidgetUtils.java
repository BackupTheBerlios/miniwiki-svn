package org.tmjee.miniwiki.client.utils;

import com.google.gwt.user.client.ui.Widget;
import org.tmjee.miniwiki.client.widgets.Initializable;
import org.tmjee.miniwiki.client.widgets.CleanUpable;
import org.tmjee.miniwiki.client.exception.BusinessException;
import org.tmjee.miniwiki.client.exception.OperationalException;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class WidgetUtils {
    
    public static void init(Widget widget) {
        if (widget instanceof Initializable) {
            ((Initializable)widget).init();
        }
    }

    public static void exception(Widget widget, SourcesEventsSupport eventSupport, Throwable t) {
        if (t instanceof BusinessException) {
            final BusinessException e = (BusinessException) t;
            if (eventSupport != null) {
                eventSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                    public void handle(Object listener) {
                        ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, e.getBusinessMessage()));
                    }
                });
            }
            cleanUp(widget);
        }
        else if (t instanceof OperationalException) {
            
        }
    }

    public static void exception(Widget widget, Throwable t) {
        exception(widget, null, t);
    }

    public static void cleanUp(Widget widget) {
        if (widget instanceof CleanUpable) {
            ((CleanUpable)widget).cleanUp();
        }
    }
}
