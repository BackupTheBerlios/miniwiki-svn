package org.tmjee.miniwiki.core;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 19, 2008
 * Time: 3:19:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitializingServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        // Just try to get an instance of bootstrap, so it'll do
        // the Spring's applicationContext loading and startup etc.
        Bootstrap.getInstance();
    }
}
