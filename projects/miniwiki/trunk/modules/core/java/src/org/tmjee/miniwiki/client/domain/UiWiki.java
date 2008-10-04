package org.tmjee.miniwiki.client.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiWiki implements UiIdentifiable {

    private long id;
    private String name;
    private List<UiWikiPriviledge> priviledges = new ArrayList<UiWikiPriviledge>();
    private List<UiSpace> spaces = new ArrayList<UiSpace>();
    private List<UiWikiProperty> properties = new ArrayList<UiWikiProperty>();
    private String template;

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

    public List<UiWikiPriviledge> getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(List<UiWikiPriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public List<UiSpace> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<UiSpace> spaces) {
        this.spaces = spaces;
    }

    public List<UiWikiProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UiWikiProperty> properties) {
        this.properties = properties;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiWiki that = (UiWiki) o;
        if (getName() != null ? ! getName().equals(that.getName()) : that.getName() != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
