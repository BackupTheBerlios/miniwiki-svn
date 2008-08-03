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
            UiCredentials.SUPERADMIN.getUser().getUsername().equals(username) &&
            prop.getProperty("superadmin.password").trim().equals(password)) {
            return true;
        }                      
        return false;
    }
}
