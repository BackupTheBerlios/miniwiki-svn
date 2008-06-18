package org.tmjee.miniwiki.core.spring.factory;

import org.springframework.beans.factory.FactoryBean;
import org.tmjee.miniwiki.core.MiniWikiConfig;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:05:42 PM
 * To change this template use File | Settings | File Templates.
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
