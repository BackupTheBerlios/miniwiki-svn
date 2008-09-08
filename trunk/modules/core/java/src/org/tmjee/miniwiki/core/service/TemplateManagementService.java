package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.server.TemplateInfo;
import org.springframework.orm.jpa.JpaTemplate;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateManagementService {

    private JpaTemplate jpaTemplate;

    TemplateManagementService() {}
    public TemplateManagementService(JpaTemplate jpaTemplate) {
        this.jpaTemplate = jpaTemplate;    
    }


    public TemplateInfo loadTemplate(String wiki, String space, String page, String command) {
        if ((page != null) && (page.trim().length() > 0)) { // load this page
            // TODO:

            

            return new TemplateInfo("Page");
        }
        else if ((space != null) && (space.trim().length() > 0)) { // load this space
            // TODO:
            return new TemplateInfo("Space");
        }
        else if ((wiki != null) && (wiki.trim().length() > 0)) { // load this wiki
            // TODO:
            return new TemplateInfo("Wiki");
        }
        else { // inform them we can't load anything
            return new TemplateInfo("We can't load anything using this url / bookmark");    
        }
    }
}
