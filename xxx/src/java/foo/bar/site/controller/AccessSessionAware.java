package foo.bar.site.controller;

import foo.bar.site.domain.LoginToken;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface AccessSessionAware {
    public void setAccessSession(LoginToken accessSession);
}
