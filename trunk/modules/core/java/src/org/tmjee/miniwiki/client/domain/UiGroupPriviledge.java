package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGroupPriviledge implements UiIdentifiable {

    private long id;
    private String name;
    private List<UiGroupPriviledgeValue> values = new LinkedList<UiGroupPriviledgeValue>();

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

    public List<UiGroupPriviledgeValue> getValues() {
        return values;
    }

    public void setValues(List<UiGroupPriviledgeValue> values) {
        this.values = values;
    }
}
