package org.tmjee.miniwiki.client.events;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface SourcesMessageEvents {
    public void addMessageEventListener(MessageEventListener listener);
    public void removeMessageEventListener(MessageEventListener listener);
}
