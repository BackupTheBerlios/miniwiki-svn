package org.tmjee.miniwiki.core.tools;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class CommandResult implements Result {

    private String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String print() {
        return message;
    }
}
