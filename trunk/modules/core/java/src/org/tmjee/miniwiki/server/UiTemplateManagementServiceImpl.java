package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiTemplateManagementService;
import org.tmjee.miniwiki.client.server.TemplateInfo;
import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.TemplateManagementService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiTemplateManagementServiceImpl extends RemoteServiceServlet implements UiTemplateManagementService {

    public TemplateInfo loadTemplate(String wiki, String space, String page, String command) {
        return (TemplateManagementService) Bootstrap.getInstance().getTemplateManagementService().loadTemplate(wiki, space, page, command);

       /* try {
            Configuration configuration = Bootstrap.getInstance().getTemplateConfiguration();
            Template _template = configuration.getTemplate(template);
            StringWriter writer = new StringWriter();
            _template.process(prepareTemplateModel(), writer);
            return new TemplateInfo(writer.getBuffer().toString());
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    /*protected Map<String, String> prepareTemplateModel() {
        return null;
    }*/
}
