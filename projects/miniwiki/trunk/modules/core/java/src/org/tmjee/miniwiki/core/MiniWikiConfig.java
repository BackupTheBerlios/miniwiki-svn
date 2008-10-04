package org.tmjee.miniwiki.core;

import static org.tmjee.miniwiki.core.MiniWikiPropertiesConstants.*;

import org.tmjee.miniwiki.client.domain.UiCredentials;

import java.util.Properties;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MiniWikiConfig {

    private Properties prop;

    public MiniWikiConfig(Properties prop) {
        this.prop = prop;    
    }

    public boolean isPredefinedSuperAdmin(String username, String password) {
        if ("true".equalsIgnoreCase(prop.getProperty(SUPERADMIN_ENABLED).trim()) &&
            UiCredentials.SUPERADMIN.getUser().getUsername().equals(username) &&
            prop.getProperty(SUPERADMIN_PASSWORD).trim().equals(password)) {
            return true;
        }                      
        return false;
    }

    public String getProperty(String propertyName) {
        String propValue =  prop.getProperty(propertyName);
        return propValue != null ? propValue.trim() : propValue;
    }
}
