package org.tmjee.miniwiki.core;

import org.tmjee.miniwiki.client.domain.UiCredentials;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:06:59 PM
 * To change this template use File | Settings | File Templates.
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
