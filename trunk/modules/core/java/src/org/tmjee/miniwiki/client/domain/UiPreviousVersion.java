package org.tmjee.miniwiki.client.domain;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPreviousVersion implements UiIdentifiable {

    private long id;
    private String name;
    private UiUser modifiedBy;
    private String content;

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

    public UiUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UiUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiPreviousVersion that = (UiPreviousVersion) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 * (name != null ? name.hashCode() : 0);
        return result;
    }
}
