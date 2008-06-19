package org.tmjee.miniwiki.client.handlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.server.TemplateInfo;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 2:23:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class WikiHandler extends AbstractHandler {

    public void handle(String space, String page, String command, AsyncCallback callback) {

        loadTemplate("wiki", callback);

    }
}
