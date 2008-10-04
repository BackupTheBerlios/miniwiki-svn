package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ForeignKey;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.tmjee.miniwiki.client.Constants;

import javax.persistence.*;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Date;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_PAGE")
public class Page implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pageIdGenerator")
    @TableGenerator(name="pageIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_PAGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(targetEntity = PagePriviledge.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name="PAGE_ID", referencedColumnName = "ID")
    private Set<PagePriviledge> priviledges = new LinkedHashSet<PagePriviledge>();

    @ManyToOne(targetEntity = Space.class,
                fetch=FetchType.LAZY,
                optional = false)
    @JoinColumn(name="SPACE_ID", referencedColumnName = "ID")
    private Space space;

    @OneToMany(targetEntity = PageAttachment.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private Set<PageAttachment> attachments = new LinkedHashSet<PageAttachment>();

    @ManyToOne(targetEntity = User.class,
                fetch = FetchType.EAGER,
                optional = false)
    @ForeignKey
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    private User creator;


    @ManyToOne(targetEntity = User.class,
                fetch = FetchType.EAGER)
    @JoinColumn(name = "LAST_MODIFIED_USER_ID", referencedColumnName = "ID")
    @ForeignKey
    private User lastModifiedUser;

    @Basic
    @Column(name="LAST_MODIFIED_DATE")
    private Date lastModifiedDate;


    @OneToMany(targetEntity = PagePreviousVersion.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private Set<PagePreviousVersion> previousVersions = new LinkedHashSet<PagePreviousVersion>();


    @OneToMany(targetEntity = PageProperty.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private Set<PageProperty> properties = new LinkedHashSet<PageProperty>();

    
    @OneToMany(targetEntity = Page.class,
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                mappedBy = "parent")
    private Set<Page> children = new LinkedHashSet<Page>();


    @ManyToOne(targetEntity = Page.class,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_PAGE_ID", referencedColumnName = "ID")
    @ForeignKey
    private Page parent;
   

    @Version
    @Column(name = "VERSION")
    private int version;



    @Basic
    @Column(name="CONTENT")
    private String content;



    @Basic
    @Column(name="TEMPLATE")
    private String template = Constants.DEFAULT_PAGE_TEMPLATE;



    // === constructor ===
    public Page() {}



    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<PagePriviledge> getPriviledges() {
        return priviledges;
    }

    public Space getSpace() {
        return space;
    }

    public Set<PageAttachment> getAttachments() {
        return attachments;
    }

    public User getCreator() {
        return creator;
    }

    public User getLastModifiedUser() {
        return lastModifiedUser;
    }

    public Set<PagePreviousVersion> getPreviousVersions() {
        return previousVersions;
    }

    public Set<PageProperty> getProperties() {
        return properties;
    }

    public Set<Page> getChildren() {
        return children;
    }

    public Page getParent() {
        return parent;
    }

    public int getVersion() {
        return version;
    }

    public String getTemplate() {
        return template;
    }

    public String getContent() {
        return content;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }



    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriviledges(Set<PagePriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public void setAttachments(Set<PageAttachment> attachments) {
        this.attachments = attachments;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public void setPreviousVersions(Set<PagePreviousVersion> perviousVersions) {
        this.previousVersions = perviousVersions;
    }

    public void setProperties(Set<PageProperty> properties) {
        this.properties = properties;
    }

    public void setChildren(Set<Page> children) {
        this.children = children;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    

    // === Equals & HashCode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof Page)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Page)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}


