package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateInfo implements IsSerializable {

    private String html;

    public TemplateInfo() { this(""); }
    public TemplateInfo(String html) {
        this.html = html;        
    }


    public String getHtml() {
        return html;
    }
    public Map getParams() {
        // TODO
        return null;
    }
}
