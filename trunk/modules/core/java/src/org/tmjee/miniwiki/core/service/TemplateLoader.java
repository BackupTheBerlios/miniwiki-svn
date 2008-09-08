package org.tmjee.miniwiki.core.service;

import static org.tmjee.miniwiki.core.MiniWikiPropertiesConstants.*;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import org.tmjee.miniwiki.core.MiniWikiConfig;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateLoader extends MultiTemplateLoader {

    public TemplateLoader(final MiniWikiConfig config) throws IOException {
        super(new ArrayList<freemarker.cache.TemplateLoader>() {
            {
                // add custom classpath template loader
                if (config.getProperty(TEMPLATE_CLASSPATH).length() > 0) {
                    add(new ClassTemplateLoader(
                            org.tmjee.miniwiki.core.service.TemplateLoader.class, config.getProperty(TEMPLATE_CLASSPATH)));
                }
                // add custom classpath file loader
                if ((config.getProperty(TEMPLATE_FILEPATH).length() > 0) && (new File(config.getProperty(TEMPLATE_FILEPATH)).exists())) {
                    add(new FileTemplateLoader(new File(config.getProperty(TEMPLATE_FILEPATH))));
                }
                // add default (build-in) classpath template loader
                add(new ClassTemplateLoader(
                        org.tmjee.miniwiki.core.service.TemplateLoader.class, "/org/tmjee/miniwiki/core/templates"));
            }
        }.toArray(new TemplateLoader[0]));
    }
}
