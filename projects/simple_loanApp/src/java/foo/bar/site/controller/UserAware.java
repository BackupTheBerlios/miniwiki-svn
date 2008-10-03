package foo.bar.site.controller;

import foo.bar.site.domain.User;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UserAware {
    public void setUser(User accessSession);
}
