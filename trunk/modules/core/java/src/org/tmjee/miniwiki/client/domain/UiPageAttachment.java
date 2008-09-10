package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPageAttachment implements UiIdentifiable {

    private long id;
    private String name;
    private String contentType;

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiPageAttachment that = (UiPageAttachment) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (name != null ? name.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        return result;
    }
}
