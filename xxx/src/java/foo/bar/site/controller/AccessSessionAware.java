package foo.bar.site.controller;

import foo.bar.site.domain.AccessSession;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface AccessSessionAware {
    public void setAccessSession(AccessSession accessSession);
}
