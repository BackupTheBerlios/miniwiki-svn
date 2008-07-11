package org.tmjee.miniwiki.core;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class InitializingServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        // Just try to get an instance of bootstrap, so it'll do
        // the Spring's applicationContext loading and startup etc.
        Bootstrap.getInstance();
    }
}
