package org.tmjee.miniwiki.client.events;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MessageEvent extends Event {

    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_NOTICE = 2;
    public static final int LEVEL_ERROR = 3;


    private int level;
    private String message;


    public MessageEvent(int level, String message) {
        this.level = level;
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
