package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_PAGE_PRIVILEDGE")
public class PagePriviledge implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagePriviledgeIdGenerator")
    @TableGenerator(name="pagePriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_PAGE_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @Basic
    @Column(name="NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(targetEntity = PagePriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name="PAGE_PRIVILEDGE_ID", referencedColumnName = "ID")
    private Set<PagePriviledgeValue> values = new LinkedHashSet<PagePriviledgeValue>();

    @Version
    @Column(name = "VERSION")
    private int version;


    // === Constructor ===
    public PagePriviledge() {}


    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<PagePriviledgeValue> getValues() {
        return values;
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

    public void setValues(Set<PagePriviledgeValue> values) {
        this.values = values;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    

    // === Equals & HashCode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof PagePriviledge)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((PagePriviledge)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}


