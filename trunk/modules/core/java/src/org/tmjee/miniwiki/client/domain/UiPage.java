package org.tmjee.miniwiki.client.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiPage implements UiIdentifiable {

    private long id;
    private String name;
    private List<UiPagePriviledge> priviledges = new ArrayList<UiPagePriviledge>();
    private UiSpace space;
    private List<UiPageAttachment> attachments = new ArrayList<UiPageAttachment>();
    private UiUser creator;
    private UiUser lastModifiedUser;
    private List<UiPagePreviousVersion> previousVersions = new ArrayList<UiPagePreviousVersion>();
    private List<UiPageProperty> properties = new ArrayList<UiPageProperty>();
    private List<UiPage> children = new ArrayList<UiPage>();
    private UiPage parent;
    private String content;
    private String template;
    private Date lastModifiedDate;


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

    public List<UiPagePriviledge> getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(List<UiPagePriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public UiSpace getSpace() {
        return space;
    }

    public void setSpace(UiSpace space) {
        this.space = space;
    }

    public List<UiPageAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<UiPageAttachment> attachments) {
        this.attachments = attachments;
    }

    public UiUser getCreator() {
        return creator;
    }

    public void setCreator(UiUser creator) {
        this.creator = creator;
    }

    public UiUser getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(UiUser lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public List<UiPagePreviousVersion> getPreviousVersions() {
        return previousVersions;
    }

    public void setPreviousVersions(List<UiPagePreviousVersion> previousVersions) {
        this.previousVersions = previousVersions;
    }

    public List<UiPageProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UiPageProperty> properties) {
        this.properties = properties;
    }

    public List<UiPage> getChildren() {
        return children;
    }

    public void setChildren(List<UiPage> children) {
        this.children = children;
    }

    public UiPage getParent() {
        return parent;
    }

    public void setParent(UiPage parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UiPage uiPage = (UiPage) o;
        if (name != null ? !name.equals(uiPage.name) : uiPage.name != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = 31 *  (name != null ? name.hashCode() : 0);
        return result;
    }
}
