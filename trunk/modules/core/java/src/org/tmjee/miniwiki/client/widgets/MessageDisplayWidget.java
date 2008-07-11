package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.HTML;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MessageDisplayWidget extends HTML implements MessageEventListener {

    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;

    private List infoMessages;
    private List errorMessages;
    private List noticeMessages;

    public MessageDisplayWidget() {
        infoMessages = new ArrayList();
        errorMessages = new ArrayList();
        noticeMessages = new ArrayList();
    }


    public void addMessage(int type, String message) {
        if (type == MessageEvent.LEVEL_INFO) {
            addInfoMessage(message);
        }
        else if (type == MessageEvent.LEVEL_NOTICE) {
            addNoticeMessage(message);
        }
        else if (type == MessageEvent.LEVEL_ERROR) {
            addErroMessage(message);
        }
    }

    public void addInfoMessage(String message) {
        infoMessages.add(message);
        display();
    }

    public void addNoticeMessage(String message) {
        noticeMessages.add(message);
        display();
    }

    public void addErroMessage(String message) {
        errorMessages.add(message);
        display();
    }

    protected void display() {
        String result = "";

        // info
        if (infoMessages.size() > 0) {
            result = result + "<ul>";
            for (Iterator i = infoMessages.iterator(); i.hasNext(); ) {
                result = result + "<li>"+i.next()+"</li>";
            }
            result = result + "</ul>";
        }

        // warning
        if (noticeMessages.size() > 0) {
            result = result + "<ul>";
            for (Iterator i = noticeMessages.iterator(); i.hasNext(); ) {
                result = result + "<li>"+i.next()+"</li>";
            }
            result = result + "</ul>";
        }

        // error
        if (errorMessages.size() > 0) {
            for (Iterator i = errorMessages.iterator(); i.hasNext(); ) {
                result = result + "<li>"+i.next()+"</li>";
            }
            result = result + "</ul>";
        }
        
        setHTML(result);
    }

    public void clear() {
        infoMessages.clear();
        noticeMessages.clear();
        errorMessages.clear();
        display();
    }

    public void onMessageEvent(MessageEvent event) {
        addMessage(event.getLevel(), event.getMessage());    
    }
}
