package org.tmjee.miniwiki.core.spring.factory;

import org.springframework.beans.factory.FactoryBean;
import org.tmjee.miniwiki.core.MiniWikiConfig;

import java.util.Properties;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MiniWikiConfigFactoryBean implements FactoryBean {

    public Object getObject() throws Exception {
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("miniwiki.properties"));
        return new MiniWikiConfig(prop);
    }

    public Class getObjectType() {
        return MiniWikiConfig.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
