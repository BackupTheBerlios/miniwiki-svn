package org.tmjee.miniwiki.core;

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
        if ("true".equalsIgnoreCase(prop.getProperty("superadmin.enabled").trim()) &&
            username.equals(UiCredentials.SUPERADMIN.getUser().getUsername()) &&
            password.equals(prop.getProperty("superadmin.password").trim())) {
            return true;
        }                      
        return false;
    }
}
