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

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_SPACE")
public class Space implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spaceIdGenerator")
    @TableGenerator(name="spaceIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_SPACE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(targetEntity = Page.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name = "SPACE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<Page> pages = new LinkedHashSet<Page>();

    @ManyToOne(targetEntity = User.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    @ForeignKey
    private User creator;

    @OneToMany(targetEntity = SpacePriviledge.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name = "SPACE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<SpacePriviledge> priviledges = new LinkedHashSet<SpacePriviledge>();

    @Version
    @Column(name = "VERSION")
    private int version;


    @Basic
    @Column(name="TEMPLATE")
    private String template = Constants.DEFAULT_SPACE_TEMPLATE;


    // === constructor ===
    public Space() {}




    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public User getCreator() {
        return creator;
    }

    public Set<SpacePriviledge> getPriviledges() {
        return priviledges;
    }

    public int getVersion() {
        return version;
    }




    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setPriviledges(Set<SpacePriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public void setVersion(int version) {
        this.version = version;
    }




    
    // === equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof Space)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Space)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
