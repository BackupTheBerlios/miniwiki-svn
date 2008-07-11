package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_PAGE_PROPERTY")
public class PageProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagePropertyIdGenerator")
    @TableGenerator(name="pagePropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_PAGE_PROPERTY_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @Basic
    @Column(name="VALUE", nullable = false)
    private String value;


    @Version
    @Column(name = "VERSION")
    private int version;


    // === constructor ===
    public PageProperty() {}


    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
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

    public void setValue(String value) {
        this.value = value;
    }

    public void setVersion(int version) {
        this.version = version;
    }




    
    // === equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof PageProperty)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((PageProperty)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
