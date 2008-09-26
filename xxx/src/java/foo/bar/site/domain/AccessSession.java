package foo.bar.site.domain;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessSession implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

