package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_GLOBAL_PRIVILEDGE")
public class GlobalPriviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "globalPriviledgeIdGenerator")
    @TableGenerator(name="globalPriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GLOBAL_PRIVILEDGE", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name="NAME")
    private String name;

    @OneToMany(targetEntity = GlobalPriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch = FetchType.EAGER)
    @JoinColumn(name="GLOBAL_PRIVILEDGE_ID", referencedColumnName = "ID")
    private Set<GlobalPriviledgeValue> values = new LinkedHashSet<GlobalPriviledgeValue>();


    public GlobalPriviledge(){}
    public GlobalPriviledge(String name, String value) {
        this.name = name;
        this.values.add(new GlobalPriviledgeValue(value));
    }


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

    public Set<GlobalPriviledgeValue> getValues() {
        return values;
    }

    public void setValues(Set<GlobalPriviledgeValue> values) {
        this.values = values;
    }


    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof GlobalPriviledge)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((GlobalPriviledge)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
