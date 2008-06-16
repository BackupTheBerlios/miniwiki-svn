package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 3:35:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LoadTemplateService extends RemoteService {
    TemplateInfo loadTemplate(String template);
}
