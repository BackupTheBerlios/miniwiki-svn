package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.tmjee.miniwiki.client.Constants;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_WIKI")
public class Wiki implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiIdGenerator")
    @TableGenerator(name="wikiIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_WIKI_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;


    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;


    @OneToMany(targetEntity = WikiPriviledge.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.EAGER)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<WikiPriviledge> priviledges = new LinkedHashSet<WikiPriviledge>();


    @OneToMany(targetEntity = Space.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<Space> spaces = new LinkedHashSet<Space>();


    @OneToMany(targetEntity = WikiProperty.class,
               cascade = {CascadeType.ALL},
               fetch = FetchType.EAGER)
    @ElementJoinColumn(name = "WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<WikiProperty> properties = new LinkedHashSet<WikiProperty>();



    @Version
    @Column(name = "VERSION")
    private int version;


    @Basic
    @Column(name="TEMPLATE")
    private String template = Constants.DEFAULT_WIKI_TEMPLATE;


    // === constructor ===
    public Wiki() {}



    public void addPriviledge(WikiPriviledge priviledge) {
        priviledges.add(priviledge);
    }
    public void removePriviledge(WikiPriviledge priviledge) {
        priviledges.remove(priviledge);
    }


    public void addSpace(Space space) {
        spaces.add(space);
    }
    public void removeSpace(Space space) {
        spaces.remove(space);
    }



    public Set<WikiProperty> getProperties() {
        return properties;
    }
    public void setProperties(Set<WikiProperty> properties) {
        this.properties = properties;
    }
    public void addProperty(WikiProperty property) {
        properties.add(property);
    }
    public void removeProperty(WikiProperty property) {
        properties.remove(property);
    }



    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<WikiPriviledge> getPriviledges() {
        return priviledges;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public int getVersion() {
        return version;
    }

    public String getTemplate() {
        return template;
    }


    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriviledges(Set<WikiPriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public void setSpaces(Set<Space> spaces) {
        this.spaces = spaces;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


    

    // == equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof Wiki)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Wiki)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
