package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_PAGE_PREVIOUS_VERSION")
public class PagePreviousVersion implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "previousVersionIdGenerator")
    @TableGenerator(name="previousVersionIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_PREVIOUS_VERSION_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User modifiedBy;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Basic
    @Column(name="CONTENT")
    private String content;



    // === constructor ===
    public PagePreviousVersion() {
    }


    // === getter ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public int getVersion() {
        return version;
    }

    public String getContent() {
        return content;
    }


    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setContent(String content) {
        this.content = content;
    }



    

    // === equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof PagePreviousVersion)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((PagePreviousVersion)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}


