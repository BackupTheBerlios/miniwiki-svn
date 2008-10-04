package org.tmjee.miniwiki.client.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiSpace implements UiIdentifiable {

    private long id;
    private String name;
    //private List<UiPage> pages = new ArrayList<UiPage>();
    private UiUser creator;
    private List<UiSpacePriviledge> priviledges = new ArrayList<UiSpacePriviledge>();
    private List<UiSpaceProperty> properties = new ArrayList<UiSpaceProperty>();
    private String template;
    private UiPage defaultPage;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<UiSpaceProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UiSpaceProperty> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public List<UiPage> getPages() {
        return pages;
    }

    public void setPages(List<UiPage> pages) {
        this.pages = pages;
    }*/

    public UiPage getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(UiPage defaultPage) {
        this.defaultPage = defaultPage;
    }

    public UiUser getCreator() {
        return creator;
    }

    public void setCreator(UiUser creator) {
        this.creator = creator;
    }

    public List<UiSpacePriviledge> getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(List<UiSpacePriviledge> priviledges) {
        this.priviledges = priviledges;
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
        UiSpace that = (UiSpace) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (name != null ? name.hashCode() : 0);
        return result;
    }
}
