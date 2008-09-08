package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.server.TemplateInfo;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class WikiHandler extends AbstractHandler {

    public void doHandle(String wiki, String space, String page, String command, AsyncCallback callback) {

        loadTemplate(wiki, space, page, command, callback);

    }
}
