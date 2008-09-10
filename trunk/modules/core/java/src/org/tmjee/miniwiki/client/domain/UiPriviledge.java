package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPriviledge implements UiIdentifiable {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
