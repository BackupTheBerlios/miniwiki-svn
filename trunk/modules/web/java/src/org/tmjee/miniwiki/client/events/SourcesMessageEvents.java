package org.tmjee.miniwiki.client.events;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 3, 2008
 * Time: 6:33:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SourcesMessageEvents {
    public void addMessageEventListener(MessageEventListener listener);
    public void removeMessageEventListener(MessageEventListener listener);
}
